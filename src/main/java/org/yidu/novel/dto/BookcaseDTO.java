package org.yidu.novel.dto;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.InfoAction;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TBookcase;

/**
 * 
 * <p>
 * 书签DTO
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class BookcaseDTO extends TBookcase {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 2193825089517868949L;
    /**
     * 最新章节编号
     */
    private Integer lastchapterno;
    /**
     * 最新章节名称
     */
    private String lastchapter;
    /**
     * 章节数
     */
    private Integer chapters;
    /**
     * 字数
     */
    private Integer size;
    /**
     * 全本标识
     */
    private Boolean fullflag;
    /**
     * 最后更新时间
     */
    private Date lastupdate;
    /**
     * 图片标识
     */
    private Integer imgflag;

    private String pinyin;

    /**
     * 获取最新章节编号
     * 
     * @return 最新章节编号
     */
    public Integer getLastchapterno() {
        return lastchapterno;
    }

    /**
     * 设定最新章节编号
     * 
     * @param lastchapterno
     *            最新章节编号
     */
    public void setLastchapterno(Integer lastchapterno) {
        this.lastchapterno = lastchapterno;
    }

    /**
     * 获取最新章节名
     * 
     * @return 最新章节名
     */
    public String getLastchapter() {
        return lastchapter;
    }

    /**
     * 设定最新章节名
     * 
     * @param lastchapter
     *            最新章节名
     */
    public void setLastchapter(String lastchapter) {
        this.lastchapter = lastchapter;
    }

    /**
     * 获取 chapters
     * 
     * @return chapters
     */
    public Integer getChapters() {
        return chapters;
    }

    /**
     * 
     * 设置chapters
     * 
     * 
     * @param chapters
     *            chapters
     */
    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    /**
     * 获取 size
     * 
     * @return size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 
     * 设置size
     * 
     * 
     * @param size
     *            size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * 获取 fullflag
     * 
     * @return fullflag
     */
    public Boolean getFullflag() {
        return fullflag;
    }

    /**
     * 
     * 设置fullflag
     * 
     * 
     * @param fullflag
     *            fullflag
     */
    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    /**
     * 获取 lastupdate
     * 
     * @return lastupdate
     */
    public Date getLastupdate() {
        return lastupdate;
    }

    /**
     * 
     * 设置lastupdate
     * 
     * 
     * @param lastupdate
     *            lastupdate
     */
    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    /**
     * 获取 imgflag
     * 
     * @return imgflag
     */
    public Integer getImgflag() {
        return imgflag;
    }

    /**
     * 
     * 设置imgflag
     * 
     * 
     * @param imgflag
     *            imgflag
     */
    public void setImgflag(Integer imgflag) {
        this.imgflag = imgflag;
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
     * 获得图片URL
     * 
     * @return 图片URL
     */
    public String getImgUrl() {
        String fileName = "";
        if (imgflag == null) {
            fileName = "nocover.jpg";
        } else {
            switch (imgflag) {
            case 1:
                fileName = getArticleno() + "s.jpg";
                break;
            case 2:
                fileName = getArticleno() + "s.gif";
                break;
            case 3:
                fileName = getArticleno() + "s.png";
                break;
            case 10:
                fileName = getArticleno() + "l.jpg";
                break;
            default:
                fileName = "nocover.jpg";
                break;
            }
        }
        String imgUrl = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH) + "/";
        if (StringUtils.equals("nocover.jpg", fileName)) {
            imgUrl = imgUrl + fileName;
        } else {
            imgUrl = imgUrl + getArticleno() / YiDuConstants.SUB_DIR_ARTICLES + "/" + getArticleno() + "/" + fileName;
        }
        return imgUrl;
    }

    /**
     * 获取最新章节URL
     * 
     * @return 最新章节URL
     */
    public String getLastChapterUrl() {

        HttpServletResponse response = ServletActionContext.getResponse();
        String url = response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                + "&chapterno=" + getLastchapterno());

        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
            url = response.encodeURL(ReaderAction.URL + "?pinyin=" + getPinyin() + "&chapterno=" + getLastchapterno());
        }
        return url;
    }

    /**
     * 获取最新章节URL
     * 
     * @return 最新章节URL
     */
    public String getBookmarkUrl() {

        HttpServletResponse response = ServletActionContext.getResponse();
        String url = response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                + "&chapterno=" + getChapterno());

        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
            url = response.encodeURL(ReaderAction.URL + "?pinyin=" + getPinyin() + "&chapterno=" + getChapterno());
        }
        return url;
    }

    /**
     * 获取最新章节URL
     * 
     * @return 最新章节URL
     */
    public String getInfoUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        String url = response.encodeURL(InfoAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
            url = response.encodeURL(InfoAction.URL + "?pinyin=" + getPinyin());
        }
        return url;
    }

}
