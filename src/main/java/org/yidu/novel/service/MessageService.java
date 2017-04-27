package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.entity.TMessage;

/**
 * 
 * <p>
 * 提供消息信息操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface MessageService {
    /**
     * 通过消息编号取得消息信息
     * 
     * @param messageno
     *            消息编号
     * @return 消息信息
     */
    TMessage getByNo(final int messageno);

    /**
     * 通过消息编号删除消息信息
     * 
     * @param messageno
     *            消息编号
     */
    void deleteByNo(final int messageno);

    /**
     * 保存消息信息
     * 
     * @param message
     *            消息信息
     */
    void save(final TMessage message);

    /**
     * 根据条件取得消息列表
     * 
     * @param searchBean
     *            检索条件
     * @return 消息列表
     */
    List<TMessage> find(final MessageSearchBean searchBean);

    /**
     * 根据条件取得消息件数
     * 
     * @param searchBean
     *            检索条件
     * @return 消息件数
     */
    int getCount(final MessageSearchBean searchBean);

}
