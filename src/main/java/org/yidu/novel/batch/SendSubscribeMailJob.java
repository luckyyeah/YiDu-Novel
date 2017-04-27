package org.yidu.novel.batch;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.bean.SubscribeSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.SubscribeDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.service.ChapterService;
import org.yidu.novel.service.SubscribeService;
import org.yidu.novel.utils.MailUtils;
import org.yidu.novel.utils.Utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * <p>
 * 发送订阅邮件
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.5
 * @author shinpa.you
 */
public class SendSubscribeMailJob extends QuartzJobBean {

    /**
     * 输出log
     */
    private Log logger = LogFactory.getLog(this.getClass());

    /**
     * 订阅信息管理服务
     */
    private SubscribeService subscribeService;
    /**
     * 小说信息管理服务
     */
    private ArticleService articleService;
    /**
     * 章节信息管理服务
     */

    private ChapterService chapterService;

    /**
     * 
     * 设置subscribeService
     * 
     * 
     * @param subscribeService
     *            subscribeService
     */
    @Autowired
    public void setSubscribeService(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    /**
     * 
     * 设置articleService
     * 
     * 
     * @param articleService
     *            articleService
     */
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 
     * 设置chapterService
     * 
     * 
     * @param chapterService
     *            chapterService
     */
    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    /**
     * 模版路径
     */
    private static final String THEMES_DIR = "themes/{0}/mail/";

    private static final String TEMPLATE_NAME = "subscribeMail.ftl";

    /**
     * 邮件标题
     */
    private static final String MAIL_TITLE = "{0}小说订阅服务--更新通知";

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        logger.debug("SendSubscribeMailJob start.");
        try {
            // 查找所有有更新的订阅信息
            SubscribeSearchBean searchBean = new SubscribeSearchBean();
            // 获取检查期间
            Range<Date> dateRange = this.getDateRange();
            searchBean.setDateRange(dateRange);

            List<SubscribeDTO> subscribeList = this.subscribeService.findAllData(searchBean);

            // 整理订阅信息
            Map<TUser, List<Integer>> subscribeMap = new HashMap<TUser, List<Integer>>();

            for (SubscribeDTO subscribeDTO : subscribeList) {
                TUser user = new TUser();
                BeanUtils.copyProperties(subscribeDTO, user);
                List<Integer> articlenoList = subscribeMap.get(user);
                if (!Utils.isDefined(articlenoList)) {
                    articlenoList = new ArrayList<Integer>();
                }
                articlenoList.add(subscribeDTO.getArticleno());
                subscribeMap.put(user, articlenoList);
            }

            // 按用户发邮件
            for (Map.Entry<TUser, List<Integer>> entry : subscribeMap.entrySet()) {
                ChapterSearchBean chapterSearchBean = new ChapterSearchBean();
                chapterSearchBean.setArticlenoList(entry.getValue());
                chapterSearchBean.setDateRange(dateRange);
                List<TChapter> chapterList = chapterService.find(chapterSearchBean);

                // 获取推荐小说
                List<TArticle> randomRecommendArticleList = articleService.findRandomRecommendArticleList(10);
                // 创建
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("chapterList", chapterList);
                dataMap.put("user", entry.getKey());
                dataMap.put("randomRecommendArticleList", randomRecommendArticleList);

                // 读取语言文件
                PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread()
                        .getContextClassLoader().getResource("language/package.properties"));

                dataMap.put("sitename", languageConf.getProperty(YiDuConfig.NAME));
                dataMap.put("siteuri", YiDuConstants.yiduConf.getString(YiDuConfig.URI));
                dataMap.put("mobileuri", YiDuConstants.yiduConf.getString(YiDuConfig.MOBILESITE_DOMIAN));

                Configuration freemarkerCfg = new Configuration();

                // 获取邮件模版
                String classBasePath = SendSubscribeMailJob.class.getClassLoader().getResource("").getPath();
                String siteRootPath = classBasePath + "../../";
                String templatePath = siteRootPath
                        + MessageFormat.format(THEMES_DIR,
                                new Object[] { YiDuConstants.yiduConf.getString("themeName") });

                freemarkerCfg.setDirectoryForTemplateLoading(new File(templatePath));

                // 指定模版路径
                Template template = freemarkerCfg.getTemplate(TEMPLATE_NAME, "UTF-8");
                template.setEncoding("UTF-8");

                Writer out = new StringWriter();
                template.process(dataMap, out);

                // 发送邮件
                MailUtils.sendMail(entry.getKey().getEmail(),
                        MessageFormat.format(MAIL_TITLE, new Object[] { languageConf.getProperty(YiDuConfig.NAME) }),
                        out.toString(), false);

            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.debug("SendSubscribeMailJob normally end.");

    }

    private Range<Date> getDateRange() {
        Date endDate = DateUtils.truncate(new Date(), Calendar.MINUTE);
        Date startDate = DateUtils.addMinutes(endDate,
                -YiDuConstants.yiduConf.getInt(YiDuConfig.SEND_SUBSCRIBE_INTEVAL, 15));
        return Range.between(startDate, endDate);
    }
}
