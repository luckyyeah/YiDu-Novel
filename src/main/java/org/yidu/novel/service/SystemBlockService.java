package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.entity.TSystemBlock;

/**
 * <p>
 * 提供区块信息操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */

public interface SystemBlockService {
    /**
     * 通过区块编号取得区块信息
     * 
     * @param blockno
     *            区块编号
     * @return 区块详细
     */
    TSystemBlock getByNo(final int blockno);

    /**
     * 通过区块编号删除区块信息
     * 
     * @param blockno
     *            区块编号
     */
    void deleteByNo(final int blockno);

    /**
     * 保存区块信息
     * 
     * @param systemBlock
     *            区块信息
     */
    void save(final TSystemBlock systemBlock);

    /**
     * 通过条件查找区块信息列表
     * 
     * @param searchBean
     *            检索条件
     * @return 区块信息列表
     */
    List<TSystemBlock> find(final SystemBlockSearchBean searchBean);

    /**
     * 根据条件取得区块件数
     * 
     * @param searchBean
     *            检索条件
     * @return 区块件数
     */
    Integer getCount(final SystemBlockSearchBean searchBean);

}
