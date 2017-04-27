package org.yidu.novel.bean;

import java.util.Date;

import org.apache.commons.lang3.Range;

public class SubscribeSearchBean extends BaseSearchBean {

    private int subscribeno;
    private int articleno;
    private int userno;
    private Range<Date> dateRange;

    /**
     * 获取subscribeno
     * 
     * @return subscribeno
     */
    public int getSubscribeno() {
        return subscribeno;
    }

    /**
     * 
     * 设置subscribeno
     * 
     * 
     * @param subscribeno
     *            subscribeno
     */
    public void setSubscribeno(int subscribeno) {
        this.subscribeno = subscribeno;
    }

    /**
     * 获取articleno
     * 
     * @return articleno
     */
    public int getArticleno() {
        return articleno;
    }

    /**
     * 
     * 设置articleno
     * 
     * 
     * @param articleno
     *            articleno
     */
    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    /**
     * 获取userno
     * 
     * @return userno
     */
    public int getUserno() {
        return userno;
    }

    /**
     * 
     * 设置userno
     * 
     * 
     * @param userno
     *            userno
     */
    public void setUserno(int userno) {
        this.userno = userno;
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
