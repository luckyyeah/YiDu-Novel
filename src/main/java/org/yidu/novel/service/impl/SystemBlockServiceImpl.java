package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.service.SystemBlockService;
import org.yidu.novel.utils.Pagination;

/**
 * <p>
 * 提供区块信息操作的服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class SystemBlockServiceImpl extends HibernateSupportServiceImpl implements SystemBlockService {

    @Override
    public List<TSystemBlock> find(SystemBlockSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TSystemBlock where  deleteflag=false ");

        if (searchBean.getBlockno() != 0) {
            hql.append(" AND blockno = ? ");
            params.add(searchBean.getBlockno());
        }

        if (StringUtils.isNotEmpty(searchBean.getBlockname())) {
            hql.append(" AND blockname = ? ");
            params.add(searchBean.getBlockname());
        }

        if (searchBean.getType() != null && searchBean.getType() != 0) {
            hql.append(" AND type = ? ");
            params.add(searchBean.getType());
        }

        if (ArrayUtils.isNotEmpty(searchBean.getTargets())) {
            hql.append(" AND target in (" + StringUtils.join(searchBean.getTargets(), ",") + ") ");
        }

        Pagination pagination = searchBean.getPagination();
        // 添加排序信息
        if (pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            return this.find(hql.toString(), params);
        }
    }

    @Override
    public TSystemBlock getByNo(int blockno) {
        TSystemBlock systemBlock = this.get(TSystemBlock.class, blockno);
        return systemBlock;
    }

    @Override
    public void deleteByNo(int blockno) {
        TSystemBlock systemBlock = getByNo(blockno);
        this.delete(systemBlock);
    }

    @Override
    public void save(TSystemBlock systemBlock) {
        this.saveOrUpdate(systemBlock);
    }

    @Override
    public Integer getCount(SystemBlockSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("Select count(*) FROM TSystemBlock where  deleteflag=false ");

        if (searchBean.getBlockno() != 0) {
            hql.append(" AND blockno = ? ");
            params.add(String.valueOf(searchBean.getBlockno()));
        }

        if (StringUtils.isNotEmpty(searchBean.getBlockname())) {
            hql.append(" AND blockname = ? ");
            params.add(searchBean.getBlockname());
        }

        if (searchBean.getType() != null && searchBean.getType() != 0) {
            hql.append(" AND type = ? ");
            params.add(searchBean.getType());
        }
        return this.getIntResult(hql.toString(), params);

    }

}
