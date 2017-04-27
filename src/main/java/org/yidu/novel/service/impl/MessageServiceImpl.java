package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.entity.TMessage;
import org.yidu.novel.service.MessageService;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 提供消息信息操作的服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class MessageServiceImpl extends HibernateSupportServiceImpl implements MessageService {

    @Override
    public TMessage getByNo(int messageno) {
        return this.get(TMessage.class, messageno);
    }

    @Override
    public void deleteByNo(int messageno) {
        TMessage message = getByNo(messageno);
        this.delete(message);

    }

    @Override
    public void save(TMessage message) {
        this.saveOrUpdate(message);

    }

    @Override
    public int getCount(MessageSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("SELECT count(*) FROM TMessage where  deleteflag=false ");
        return this.getIntResult(hql.toString(), params);
    }

    @Override
    public List<TMessage> find(MessageSearchBean searchBean) {
        // 初期SQL做成
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("From TMessage WHERE  deleteflag=false  ");
        if (Utils.isDefined(searchBean.getUserno())) {
            hql.append(" AND (userno = ? OR touserno = ? )");
            params.add(searchBean.getUserno());
            params.add(searchBean.getUserno());
        }

        Pagination pagination = searchBean.getPagination();
        // 添加排序信息
        if (pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            hql.append(" ORDER BY messageno");
            return this.find(hql.toString(), params);
        }
    }

}
