package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.ReviewSearchBean;
import org.yidu.novel.entity.TReview;

/**
 * <p>
 * 提供评论信息操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface ReviewService {
    /**
     * 通过评论编号取得评论信息
     * 
     * @param reviewno
     *            评论编号
     * @return 评论信息
     */
    TReview getByNo(final int reviewno);

    /**
     * 通过评论编号删除评论信息
     * 
     * @param reviewno
     *            评论编号
     */
    void deleteByNo(final int reviewno);

    /**
     * 保存评论信息
     * 
     * @param review
     *            评论信息
     */
    void save(final TReview review);

    /**
     * 根据条件取得评论列表
     * 
     * @param searchBean
     *            检索条件
     * @return 评论列表
     */
    List<TReview> find(final ReviewSearchBean searchBean);

    /**
     * 根据条件取得评论件数
     * 
     * @param searchBean
     *            检索条件
     * @return 评论件数
     */
    int getCount(final ReviewSearchBean searchBean);

}
