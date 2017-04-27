package org.yidu.novel.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 小说件数管理器
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class SingleBookManager {

    /**
     * 输出log
     */
    private static Log logger = LogFactory.getLog(SingleBookManager.class);
    /**
     * 小说拼音到编号映射
     */
    private static final Map<String, Integer> ARTICLE_PINYIN2NO_MAP = new HashMap<String, Integer>();
    /**
     * 小说编号到拼音映射
     */
    private static final Map<Integer, String> ARTICLE_NO2PINYIN_MAP = new HashMap<Integer, String>();
    /**
     * 最大小说编号
     */
    private static int MAX_ARTICLENO = 0;

    /**
     * 单本处理线程
     */
    private static Thread loadSingleBookDataThread;

    /**
     * 获得小说编号
     * 
     * @param key
     *            拼音缩写
     * @return 小说编号
     */
    public static int getArticleno(String key) {
        if (ARTICLE_PINYIN2NO_MAP.get(key) != null) {
            return ARTICLE_PINYIN2NO_MAP.get(key);
        }
        return 0;
    }

    /**
     * 获得拼音缩写
     * 
     * @param key
     *            小说编号
     * @return 拼音缩写
     */
    public static String getPinYinHeadChar(Integer artileno) {
        if (ARTICLE_NO2PINYIN_MAP.get(artileno) != null) {
            return ARTICLE_NO2PINYIN_MAP.get(artileno);
        }
        return "";
    }

    /**
     * 初始化小说管理器
     */
    public static void initSingleBookManager() {
        logger.info("going to init SingleBookManager.");
        loadSingleBookDataThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO 新现成启动初始化，会导致刚启动时小说数不正确的问题，但是只是启动时，马上就恢复了，将来启动先加载
                logger.info("start SingleBookManager Manager daemon process.");
                ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                        new String[] { "spring/springWithoutBatch.xml" });
                ArticleService articleService = (ArticleService) context.getBean("articleService");
                while (true) {
                    try {
                        logger.info("SingleBookManager Manager daemon process going to load info.");
                        ArticleSearchBean searchBean = new ArticleSearchBean();
                        searchBean.setFromArticleno(MAX_ARTICLENO);
                        List<TArticle> articleList = articleService.find(searchBean);
                        String excludeSubDomains = YiDuConstants.yiduConf.getString(YiDuConfig.EXCLUDE_SUB_DOMAIN);
                        List<String> excludeSubDomainList = new ArrayList<String>();
                        excludeSubDomainList.addAll(Arrays.asList(StringUtils.split(excludeSubDomains, ";")));
                        // 每次只做增量
                        for (TArticle tArticle : articleList) {
                            // TODO 暂时自己做，将来直接从数据库里取
                            String pinYinHeadChar = Utils.getPinYinHeadChar(tArticle.getArticlename());
                            // 如果是排除对象的话，在拼音上加1
                            if (excludeSubDomainList.contains(pinYinHeadChar)) {
                                // 如果是www或m的话，把他改掉
                                pinYinHeadChar += "1";
                            }

                            String key = getKeyWillInsertIntoMap(ARTICLE_PINYIN2NO_MAP, pinYinHeadChar);
                            ARTICLE_PINYIN2NO_MAP.put(key, tArticle.getArticleno());
                            ARTICLE_NO2PINYIN_MAP.put(tArticle.getArticleno(), key);
                            // TODO 是否需要将拼音写回数据库
                            MAX_ARTICLENO = tArticle.getArticleno();
                        }
                        logger.debug("SingleBookManager Manager daemon process going to sleep.");
                        Thread.sleep(YiDuConstants.yiduConf.getInt(YiDuConfig.RELOAD_SINGLE_BOOK_INTERVAL, 120) * 60 * 1000);
                    } catch (Exception e) {
                        if (!(e instanceof InterruptedException)) {
                            logger.error("init SingleBookManager failed.", e);
                        }
                        break;
                    }
                }
                context.close();
            }
        });
        loadSingleBookDataThread.start();

        logger.info("init SingleBookManager normally end.");
    }

    /**
     * 把指定Key和Value添加到Map中，如果存在的话就把key自动加1，直到可以添加进去为止
     * 
     * @param length
     *            字符串长度
     * @return 随机字符串
     */
    public static final String getKeyWillInsertIntoMap(Map<String, Integer> map, String key) {
        int index = 0;
        while (true) {
            Integer mapValue = null;
            if (index == 0) {
                mapValue = map.get(key);
            } else {
                mapValue = map.get(key + index);
            }
            // 指定元素不存在的话，把当前的信息添进去
            if (mapValue == null) {
                key = key + (index == 0 ? "" : index);
                return key;
            }
            index++;
        }
    }

    /**
     * 销毁小说件数缓存
     */
    public static void dispose() {
        loadSingleBookDataThread.interrupt();
        try {
            loadSingleBookDataThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        ARTICLE_PINYIN2NO_MAP.clear();
        ARTICLE_NO2PINYIN_MAP.clear();
    }
}
