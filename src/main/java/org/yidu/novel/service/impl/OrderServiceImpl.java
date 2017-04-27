package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TChapterOrder;
import org.yidu.novel.entity.TChargeOrder;
import org.yidu.novel.entity.TOrder;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.service.OrderService;
import org.yidu.novel.utils.Pagination;

/**
 * 
 * <p>
 * 提供用户信息操作的服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class OrderServiceImpl extends HibernateSupportServiceImpl implements OrderService {

    @Override
    public void deleteByNo(int chapterorderno) {
    	 TChapterOrder chapterOrder = this.get(TChapterOrder.class, chapterorderno);
        this.delete(chapterOrder);
    }


    @Override
    public TChapterOrder getByNo(int chapterorderno) {
        return this.get(TChapterOrder.class, chapterorderno);
    }
    @Override
    public void doBuyChapter(int userno, int chapterno,int fee) {
        String sql = "update t_user set chargefee = chargefee-? where userno = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(fee);
        params.add(userno);
        this.yiduJdbcTemplate.update(sql, params.toArray());
   
    }
    @Override
    public void save(TChapterOrder chapterOrder) {
        this.saveOrUpdate(chapterOrder);
    }
    @Override
    public void save(TOrder tOrderr) {
        this.saveOrUpdate(tOrderr);
    }
    @Override
    public void save(TChargeOrder tChargeOrder) {
        this.saveOrUpdate(tChargeOrder);
    }
    @Override
    public List<TOrder> findOrder(String orderno) {
        StringBuilder hql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TOrder WHERE orderno=? ");
        params.add(orderno);
        hql.append("ORDER BY modifytime");
        return this.find(hql.toString(), params);
       
    }
    @Override
    public TChargeOrder findChargeOrderByOrderNo(String orderno) {
        String sql = "FROM TChargeOrder WHERE orderno = ? AND status=-1";
        List<Object> params = new ArrayList<Object>();
        params.add(orderno);
        List<TChargeOrder> chargeOrderList = this.find(sql, params);
        if (chargeOrderList.size() > 0) {
            return chargeOrderList.get(0);
        }
        return null;
    }
    /**
     * 取得VIP章节列表
     * 
     * @param userno
     *            用户id
    * @param chapterno
     *            章节编号
     * @return 订单列表
     */
    @Override
	public List<TChapterOrder> findVipOrder(int userno, int chapterno){
        StringBuilder hql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TChapterOrder WHERE userno = ? AND chapterno=? ");
        params.add(userno);
        params.add(chapterno);
        return this.find(hql.toString(), params);
    }
}
