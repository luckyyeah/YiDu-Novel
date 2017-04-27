package org.yidu.novel.bean;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Range;

public class ChapterSearchBean extends BaseSearchBean {

    /**
     * 小说号
     */
    private int articleno;

    /**
     * 小说编号列表
     */
    private List<Integer> articlenoList;

    /**
     * 章节号
     */
    private int chapterno;

    private Range<Date> dateRange;

    /**
     * 章节编号列表
     */
    private List<Integer> chapternoList;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    /**
     * 获取articlenoList
     * 
     * @return articlenoList
     */
    public List<Integer> getArticlenoList() {
        return articlenoList;
    }

    /**
     * 
     * 设置articlenoList
     * 
     * 
     * @param articlenoList
     *            articlenoList
     */
    public void setArticlenoList(List<Integer> articlenoList) {
        this.articlenoList = articlenoList;
    }

    /**
     * 获取chapternoList
     * 
     * @return chapternoList
     */
    public List<Integer> getChapternoList() {
        return chapternoList;
    }

    /**
     * 
     * 设置chapternoList
     * 
     * 
     * @param chapternoList
     *            chapternoList
     */
    public void setChapternoList(List<Integer> chapternoList) {
        this.chapternoList = chapternoList;
    }

    /**
     * 获取dateRange
     * 
     * @return dateRange
     */
    public Range<Date> getDateRange() {
        return dateRange;
    }

    /**
     * 
     * 设置dateRange
     * 
     * 
     * @param dateRange
     *            dateRange
     */
    public void setDateRange(Range<Date> dateRange) {
        this.dateRange = dateRange;
    }

}
