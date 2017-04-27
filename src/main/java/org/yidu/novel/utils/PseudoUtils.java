package org.yidu.novel.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
import org.yidu.novel.bean.ReplaceKeywordsBean;
import org.yidu.novel.constant.PseudoConstants;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;

/**
 * 
 * <p>
 * 伪原创工具类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author tkts@qq.com
 */
public class PseudoUtils {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(PseudoUtils.class);

    /**
     * 保存替换用的关键词
     */
    private static List<ReplaceKeywordsBean> toReplace = new ArrayList<ReplaceKeywordsBean>();

    static {
        initReplace();
    }

    /**
     * 初始化替换类
     */
    private static void initReplace() {
        URL url = Utils.class.getClassLoader().getResource("replace.txt");
        try {
            File file = new File(url.toURI());
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.indexOf("=") < 0) {
                    continue;
                }
                ReplaceKeywordsBean word = new ReplaceKeywordsBean();
                word.setKey(line.split("=")[0]);
                word.setValue(line.split("=")[1]);
                word.setReplaced(false);
                toReplace.add(word);
            }
            read.close();
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 替换字符串
     * 
     * @param content
     *            字符串
     * @return 替换后的字符串
     */
    public static String replaceKeywords(String content) {
        ReplaceKeywordsBean word = null;

        // 重置标志位
        for (int i = 0; i < toReplace.size(); i++) {
            word = toReplace.get(i);
            word.setReplaced(false);

        }
        // 第一次循环：正向替换

        for (int i = 0; i < toReplace.size(); i++) {
            word = toReplace.get(i);
            // 判断正文中是否出现伪原创关键词， 如果出现则替换， 并修改标志位
            if (content.indexOf(word.getKey()) >= 0) {
                content = content.replace(word.getKey(), word.getValue());
                word.setReplaced(true);
            }
        }
        // 第二次循环：逆向替换
        for (int i = 0; i < toReplace.size(); i++) {
            word = toReplace.get(i);
            // 判断正文中是否出现伪原创关键词， 如果出现则替换， 并修改标志位
            if (!word.getReplaced()) {
                if (content.indexOf(word.getValue()) >= 0) {
                    content = content.replace(word.getValue(), word.getKey());
                    word.setReplaced(true);
                }
            }
        }
        return content;
    }

    /**
     * 章节头部添加信息
     * 
     * @param content
     *            章节信息
     * @return 添加信息后的章节信息
     */
    public static String topAppend(String content) {
        String topAppend = YiDuConstants.pseudoConf.getString("top_append_text", "");

        String baseUrl = YiDuConstants.yiduConf.getString("uri");
        topAppend = topAppend.replace("{baseURL}", baseUrl);

        return topAppend + content;
    }

    /**
     * 章节尾部添加信息
     * 
     * @param chapter
     *            章节entity
     * @param content
     *            章节内容
     * @return 添加信息后的章节内容
     */
    public static String bottomAppend(TChapter chapter, String content) {

        // TODO URL变成动态的
        String bottomAppend = YiDuConstants.pseudoConf.getString("bottom_append_text", "");

        String baseUrl = YiDuConstants.yiduConf.getString("uri");
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        int articleno = chapter.getArticleno();
        int chapterno = chapter.getChapterno();

        String articleUrl = baseUrl + "book/" + articleno + ".html";
        String chapterUrl = baseUrl + "reader/" + articleno / 1000 + "/" + articleno + "/" + chapterno + ".html";

        bottomAppend = bottomAppend.replace("{articleurl}", articleUrl)
                .replace("{articlename}", chapter.getArticlename()).replace("{chapterurl}", chapterUrl)
                .replace("{chaptername}", chapter.getChaptername());

        return bottomAppend + content;
    }

    /**
     * 通过分词获取章节关键字
     * 
     * @param content
     *            章节内容
     * @return 章节关键字
     */
    public static String fetchKeywords(String content) {
        StringBuffer keywords = new StringBuffer("本文关键词统计： ");

        String text = content.replaceAll("&nbsp;", "").replaceAll("<.+?>", "");
        IKSegmentation segmentation = new IKSegmentation(new StringReader(text), false);
        Lexeme lexeme = null;
        Map<String, Integer> kwCount = new HashMap<String, Integer>();
        try {
            while (null != (lexeme = segmentation.next())) {
                String lexemeText = lexeme.getLexemeText();
                if (lexemeText.length() > 1) {
                    if (kwCount.get(lexemeText) != null) {
                        kwCount.put(lexemeText, kwCount.get(lexemeText).intValue() + 1);
                    } else {
                        kwCount.put(lexemeText, 1);
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        List<Map.Entry<String, Integer>> kwCountList = new ArrayList<Map.Entry<String, Integer>>(kwCount.entrySet());
        Collections.sort(kwCountList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
                return obj2.getValue() - obj1.getValue();
            }
        });

        int keywordsCount = YiDuConstants.pseudoConf.getInt("keywords_count", 0);
        keywordsCount = keywordsCount < kwCountList.size() ? keywordsCount : kwCountList.size();

        for (int i = 0; i < keywordsCount; i++) {
            Map.Entry<String, Integer> kwEntry = kwCountList.get(i);
            keywords.append(" ").append(kwEntry.getKey()).append("(").append(kwEntry.getValue()).append("),");
        }
        keywords.deleteCharAt(keywords.length() - 1);

        String keywordsPosition = YiDuConstants.pseudoConf.getString("keywords_position", "bottom");
        if (PseudoConstants.KEYWORDS_POSITION_TOP.equalsIgnoreCase(keywordsPosition)) {
            content = keywords + content;
        } else {
            content = content + keywords;
        }
        return content;
    }

}
