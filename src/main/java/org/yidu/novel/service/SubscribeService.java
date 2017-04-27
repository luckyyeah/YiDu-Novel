package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.SubscribeSearchBean;
import org.yidu.novel.dto.SubscribeDTO;
import org.yidu.novel.entity.TSubscribe;

/**
 * <p>
 * 提供订阅信息操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface SubscribeService {
    /**
     * 通过订阅编号取得订阅信息
     * 
     * @param subscribeno
     *            订阅编号
     * @return 订阅信息
     */
    TSubscribe getByNo(final int subscribeno);

    /**
     * 通过订阅编号删除订阅信息
     * 
     * @param subscribeno
     *            订阅编号
     */
    void deleteByNo(final int subscribeno);

    /**
     * 保存订阅信息
     * 
     * @param subscribe
     *            订阅信息
     */
    void save(final TSubscribe subscribe);

    /**
     * 根据条件取得订阅列表
     * 
     * @param searchBean
     *            检索条件
     * @return 订阅信息列表
     */
    List<TSubscribe> find(final SubscribeSearchBean searchBean);

    /**
     * 根据条件取得订阅列表
     * 
     * @param searchBean
     *            检索条件
     * @return 订阅信息列表
     */
    List<SubscribeDTO> findAllData(final SubscribeSearchBean searchBean);

    /**
     * 根据条件取得订阅件数
     * 
     * @param searchBean
     *            检索条件
     * @return 订阅件数
     */
    int getCount(final SubscribeSearchBean searchBean);
}
