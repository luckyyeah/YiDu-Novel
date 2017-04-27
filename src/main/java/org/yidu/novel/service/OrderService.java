package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TChapterOrder;
import org.yidu.novel.entity.TChargeOrder;
import org.yidu.novel.entity.TOrder;
import org.yidu.novel.entity.TUser;

/**
 * 
 * <p>
 * 提供用户信息操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface OrderService {


    /**
     * 根据用户号取得用户信息，如果取不到就返回NULL<br>
     * 
     * @param chapterorderno
     *            用户编号
     * @return 用户信息
     */
	 TChapterOrder getByNo(final int chapterorderno);

    /**
     * 根据用户号删除用户信息
     * 
     * @param chapterorderno
     *            用户编号
     */
    void deleteByNo(final int chapterorderno);

    /**
     * 生成章节购买记录
     * 
     * @param userno
     *            用户编号
     * @param chapterno
     *            章节编号
     * @param fee
     *            金额
     */
    void doBuyChapter(int userno, int chapterno,int fee);
    void save(final TChapterOrder tChapterOrder);
    void save(final TOrder tOrder);
    void save(final TChargeOrder tChargeOrder);
    
    /**
     * 取得条件订单列表
     * 
     * @param orderno
     *            检索条件
     * @return 订单列表
     */
    List<TOrder> findOrder(final String orderno);
    
    TChargeOrder findChargeOrderByOrderNo(final String orderno);
    /**
     * 取得VIP章节列表
     * 
     * @param userno
     *            用户id
    * @param chapterno
     *            章节编号
     * @return 订单列表
     */
    List<TChapterOrder> findVipOrder(final int userno, final int chapterno);
}
