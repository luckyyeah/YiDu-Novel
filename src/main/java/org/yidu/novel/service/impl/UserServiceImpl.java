package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TChapterOrder;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.service.UserService;
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
public class UserServiceImpl extends HibernateSupportServiceImpl implements UserService {

    @Override
    public TUser findByLoginInfo(final String loginid, final String password) {
        String sql = "FROM TUser where loginid = ? AND password = ? AND deleteflag=false";
        List<Object> params = new ArrayList<Object>();
        params.add(loginid);
        params.add(password);
        List<TUser> userinfoList = this.find(sql, params);
        if (userinfoList.size() > 0) {
            return userinfoList.get(0);
        }
        return null;
    }

    @Override
    public TUser findByLoginInfoByJDBC(final String loginid, final String password) {
        String sql = "select * from t_user where loginid = ? AND password = ? AND deleteflag=false";
        List<Object> params = new ArrayList<Object>();
        params.add(loginid);
        params.add(password);
        List<TUser> userinfoList = this.yiduJdbcTemplate.query(sql, params.toArray(), new BeanPropertyRowMapper<TUser>(
                TUser.class));
        if (userinfoList.size() > 0) {
            return userinfoList.get(0);
        }
        return null;
    }

    @Override
    public List<TUser> find(final UserSearchBean searchBean) {
        StringBuilder hql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TUser WHERE deleteflag=false ");
        buildCondition(searchBean, hql, params);

        Pagination pagination = searchBean.getPagination();
        // 添加排序信息
        if (pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            hql.append("ORDER BY userno");
            return this.find(hql.toString(), params);
        }
    }

    @Override
    public void deleteByNo(int userno) {
        TUser user = this.get(TUser.class, userno);
        this.delete(user);
    }

    @Override
    public void save(TUser user) {
        this.saveOrUpdate(user);
    }
    @Override
    public void save(TChapterOrder tChapterOrder) {
        this.saveOrUpdate(tChapterOrder);
    }
    @Override
    public TUser getByNo(int userno) {
        return this.get(TUser.class, userno);
    }

    @Override
    public int getCount(UserSearchBean searchBean) {
        StringBuilder hql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        hql.append("SELECT count(*) FROM TUser WHERE deleteflag=false ");
        buildCondition(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    /**
     * 创建检索条件
     * 
     * @param searchBean
     *            检索条件
     * @param hql
     *            HQL
     * @param params
     *            参数
     */
    private void buildCondition(UserSearchBean searchBean, StringBuilder hql, List<Object> params) {
        if (searchBean.getUserno() != 0) {
            hql.append(" AND userno = ? ");
            params.add(searchBean.getUserno());
        }

        if (StringUtils.isNotBlank(searchBean.getLoginid())) {
            hql.append(" AND loginid = ? ");
            params.add(searchBean.getLoginid());
        }

        if (StringUtils.isNotBlank(searchBean.getPassword())) {
            hql.append(" AND password = ? ");
            params.add(searchBean.getPassword());
        }

        if (StringUtils.isNotBlank(searchBean.getUsername())) {
            hql.append(" AND username = ? ");
            params.add(searchBean.getUsername());
        }

        if (StringUtils.isNotBlank(searchBean.getEmail())) {
            hql.append(" AND email = ? ");
            params.add(searchBean.getEmail());
        }

        if (searchBean.getDeleteflag() != null) {
            hql.append(" AND deleteflag = ? ");
            params.add(searchBean.getDeleteflag());
        }

        if (searchBean.getActivedflag() != null) {
            hql.append(" AND activedflag = ? ");
            params.add(searchBean.getActivedflag());
        }

        if (searchBean.getModifytime() != null) {
            hql.append(" AND modifytime > ? ");
            params.add(searchBean.getModifytime());
        }

    }

    @Override
    public void updateLastLoginDate(int userno, Date lastLoginDate) {
        String sql = "update t_user set lastlogin = ? where userno = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(lastLoginDate);
        params.add(userno);
        this.yiduJdbcTemplate.update(sql, params.toArray());
    }

    @Override
    public TUser findByOpenid(String openid) {
        String sql = "FROM TUser WHERE openid = ? AND deleteflag=false";
        List<Object> params = new ArrayList<Object>();
        params.add(openid);
        List<TUser> userinfoList = this.find(sql, params);
        if (userinfoList.size() > 0) {
            return userinfoList.get(0);
        }
        return null;
    }

    @Override
    public void lockTUser() {
        this.yiduJdbcTemplate.execute("lock table t_user in EXCLUSIVE mode");
    }

}
