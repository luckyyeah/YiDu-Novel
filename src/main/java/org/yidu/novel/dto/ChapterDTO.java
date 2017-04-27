package org.yidu.novel.dto;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ChapterListAction;
import org.yidu.novel.action.InfoAction;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;

/**
 * 
 * <p>
 * 章节DTO
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class ChapterDTO extends TChapter {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -9171385880720383954L;
    /**
     * 下一章章节编号
     */
    private int nextChapterno;
    /**
     * 上一章章节编号
     */
    private int preChapterno;
    /**
     * 章节内容
     */
    private String content;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 获取 nextChapterno
     * 
     * @return nextChapterno
     */
    public int getNextChapterno() {
        return nextChapterno;
    }

    /**
     * 
     * 设置nextChapterno
     * 
     * 
     * @param nextChapterno
     *            nextChapterno
     */
    public void setNextChapterno(int nextChapterno) {
        this.nextChapterno = nextChapterno;
    }

    /**
     * 获取 preChapterno
     * 
     * @return preChapterno
     */
    public int getPreChapterno() {
        return preChapterno;
    }

    /**
     * 
     * 设置preChapterno
     * 
     * 
     * @param preChapterno
     *            preChapterno
     */
    public void setPreChapterno(int preChapterno) {
        this.preChapterno = preChapterno;
    }

    /**
     * 获取pinyin
     * 
     * @return pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 
     * 设置pinyin
     * 
     * 
     * @param pinyin
     *            pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * 获取 content
     * 
     * @return content
     */
    public String getContent() {
        String keywords = YiDuConstants.yiduConf.getString(YiDuConfig.FILTER_KEYWORD);
        String[] keywordArr = StringUtils.split(keywords, "#");
        for (String string : keywordArr) {
            content = content.replaceAll(string, "");
        }
        return content;
    }

    public String getEsccapeContent() {
        String escapeContent = StringUtils.replace(this.getContent(), "<", "&lt;");
        escapeContent = StringUtils.replace(escapeContent, ">", "&gt;");
        return escapeContent;
    }

    public String getReplacedContent() {
        String keywords = YiDuConstants.yiduConf.getString("filterKeyWord");
        String[] keywordArr = StringUtils.split(keywords, ",");
        String replaced = getContent();
        for (String string : keywordArr) {
            replaced = replaced.replaceAll(string, "");
        }
        return replaced;
    }

    /**
     * 
     * 设置content
     * 
     * 
     * @param content
     *            content
     */
    public void setContent(String content) {
        this.content = (content == null ? "" : content);
    }

    /**
     * 获取章节列表URL
     * 
     * @return 分类列表URL
     */
    public String getUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        String url = response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                + "&chapterno=" + getChapterno());
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
            url = response.encodeURL(ReaderAction.URL + "?pinyin=" + pinyin + "&chapterno=" + getChapterno());
        }
        return url;
    }

    /**
     * 获取章节列表URL
     * 
     * @return 分类列表URL
     */
    public String getChapterListUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        String url = response.encodeURL(ChapterListAction.URL + "?subdir=" + getSubdir() + "&articleno="
                + getArticleno());
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
            url = response.encodeURL(ChapterListAction.URL + "?pinyin=" + pinyin);
        }
        return url;
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 下一章章节URL
     */

    public String getNextChapterUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        if (getNextChapterno() != 0) {
            String url = response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno="
                    + getArticleno() + "&chapterno=" + getNextChapterno());

            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
                url = response.encodeURL(ReaderAction.URL + "?pinyin=" + pinyin + "&chapterno=" + getNextChapterno());
            }
            return url;
        } else {
            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false)) {
                return getChapterListUrl();
            } else {
                String url = getInfoUrl();
                if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
                    if(YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false)){
                        url = response.encodeURL(ChapterListAction.URL + "?pinyin=" + pinyin);
                    }else{
                        url = response.encodeURL(InfoAction.URL + "?pinyin=" + pinyin);
                    }
                }
                return url;
            }
        }
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 上一章章节URL
     */
    public String getPreChapterUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        if (getPreChapterno() != 0) {
            String url = response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno="
                    + getArticleno() + "&chapterno=" + getPreChapterno());

            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
                url = response.encodeURL(ReaderAction.URL + "?pinyin=" + pinyin + "&chapterno=" + getPreChapterno());
            }
            return url;

        } else {
            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false)) {
                return getChapterListUrl();
            } else {
                String url = getInfoUrl();
                if(YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false)){
                    url = response.encodeURL(ChapterListAction.URL + "?pinyin=" + pinyin);
                }else{
                    url = response.encodeURL(InfoAction.URL + "?pinyin=" + pinyin);
                }
                return url;
            }
        }
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 下一章章节URL
     */

    public String getNextChapterThumbnailUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReaderAction.URL + "?chapterno=" + getNextChapterno());
    }

    /**
     * 获取下一章章节URL
     * 
     * @return 上一章章节URL
     */
    public String getPreChapterThumbnailUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReaderAction.URL + "?chapterno=" + getPreChapterno());
    }

}
