package org.yidu.novel.bean;

import java.util.Date;

/**
 * 
 * <p>
 * 小说搜索条件
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class ArticleSearchBean extends BaseSearchBean {

    private int articleno;
    private Date lastupdate;
    private String articlename;
    private String keywords;
    private Integer authorid;
    private String author;
    private Integer category;
    private Boolean fullflag;

    private String key;

    private String articlenos;

    private int pageType;

    private int fromArticleno;

    private String pinyin;

    private String tag;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getFullflag() {
        return fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public String getArticlenos() {
        return articlenos;
    }

    public void setArticlenos(String articlenos) {
        this.articlenos = articlenos;
    }

    public int getPageType() {
        return pageType;
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }

    /**
     * 获取fromArticleno
     * 
     * @return fromArticleno
     */
    public int getFromArticleno() {
        return fromArticleno;
    }

    /**
     * 
     * 设置fromArticleno
     * 
     * 
     * @param fromArticleno
     *            fromArticleno
     */
    public void setFromArticleno(int fromArticleno) {
        this.fromArticleno = fromArticleno;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ArticleSearchBean [articleno=" + articleno + ", lastupdate=" + lastupdate + ", articlename="
                + articlename + ", keywords=" + keywords + ", authorid=" + authorid + ", author=" + author
                + ", category=" + category + ", fullflag=" + fullflag + ", key=" + key + ", articlenos=" + articlenos
                + ", pageType=" + pageType + getPagination() + "]";
    }

    public class PageType {
        public static final int publicPage = 1;
        public static final int authorPage = 2;
        public static final int adminPage = 3;
    }
}
