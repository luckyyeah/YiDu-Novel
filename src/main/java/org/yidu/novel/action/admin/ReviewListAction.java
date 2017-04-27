package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.ReviewSearchBean;
import org.yidu.novel.entity.TReview;

/**
 * <p>
 * 评论列表Action
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "reviewList")
public class ReviewListAction extends AbstractAdminListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 50763768615695410L;

    /**
     * 功能名称。
     */
    public static final String NAME = "reviewList";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 检索对象
     */
    private String option;

    /**
     * 检索关键字
     */
    private String key;

    /**
     * 评论编号
     */
    private int reviewno;

    /**
     * 页号
     */
    private int page;

    /**
     * 评论列表
     */
    private List<TReview> reviewList = new ArrayList<TReview>();

    public int getReviewno() {
        return reviewno;
    }

    public void setReviewno(int reviewno) {
        this.reviewno = reviewno;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = StringUtils.trim(key);
    }

    public List<TReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<TReview> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    protected void loadData() {

        initCollections(new String[] { "collectionProperties.review.searchkey" });
        ReviewSearchBean searchBean = new ReviewSearchBean();
        BeanUtils.copyProperties(this, searchBean, new String[] { "articleno" });

        try {
            PropertyUtils.setProperty(searchBean, option, key);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (StringUtils.isEmpty(pagination.getSortColumn())) {
            pagination.setSortColumn(TReview.PROP_POSTDATE);
            pagination.setSortOrder("DESC");
        }

        // 总件数设置
        pagination.setPreperties(reviewService.getCount(searchBean));
        searchBean.setPagination(pagination);
        reviewList = reviewService.find(searchBean);
        // Setting number of records in the particular page
        pagination.setPageRecords(reviewList.size());
    }

    public String delete() throws Exception {
        reviewService.deleteByNo(reviewno);
        loadData();
        return INPUT;
    }
}
