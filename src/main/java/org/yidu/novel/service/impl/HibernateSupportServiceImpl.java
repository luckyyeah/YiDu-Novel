package org.yidu.novel.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Hibernate操作用基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@SuppressWarnings("unchecked")
public class HibernateSupportServiceImpl extends BaseServiceImpl {
    /**
     * sessionFactory
     */
    protected SessionFactory sessionFactory;

    /**
     * 
     * 设置sessionFactory
     * 
     * 
     * @param sessionFactory
     *            sessionFactory
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 根据entityClass和ID获取Entity实例
     * 
     * @param entityClass
     *            entityClass名
     * @param id
     *            ID
     * @return Entity实例
     */
    protected final <T> T get(final Class<T> entityClass, final Serializable id) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }

    /**
     * 删除Entity实例
     * 
     * @param entity
     *            Entity实例
     */
    protected final void delete(final Object entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    /**
     * 保存Entity实例
     * 
     * @param entity
     *            Entity实例
     */
    protected final void save(final Object entity) {
        this.sessionFactory.getCurrentSession().save(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    /**
     * 更新Entity实例
     * 
     * @param entity
     *            Entity实例
     */
    protected final void update(final Object entity) {
        this.sessionFactory.getCurrentSession().update(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    /**
     * 保存或更新Entity实例
     * 
     * @param entity
     *            Entity实例
     */
    protected final void saveOrUpdate(final Object entity) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    /**
     * 格式化HQL到JPQL格式
     * 
     * @param queryString
     *            检索字符串
     * @return JPQL格式检索字符串
     */
    private String fomatHQL(String queryString) {
        StringBuffer buffer = new StringBuffer(queryString);
        int start = 0;
        int order = 0;
        while ((start = buffer.indexOf("?", start + 1)) > 0) {
            buffer.insert(start + 1, order);
            order++;
        }
        return buffer.toString();
    }

    /**
     * 根据hql和参数构成Query
     * 
     * @param hql
     *            hql
     * @param params
     *            参数
     * @return Query
     */
    protected final <T> List<T> find(final String hql, final List<?> params) {
        return this.find(hql, params.toArray());
    }

    /**
     * 根据hql和参数构成Query
     * 
     * @param hql
     *            hql
     * @param params
     *            参数
     * @return Query
     */
    protected final Query getQuery(final String hql, final Object... params) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(fomatHQL(hql));
        if (params != null && params.length != 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + "", params[i]);
            }
        }
        return query;
    }

    /**
     * 根据hql和参数构成Query
     * 
     * @param sql
     *            sql
     * @param params
     *            参数
     */
    protected final void sqlQuery(final String sql, final List<?> params) {
        this.sqlQuery(sql, params.toArray());
    }

    /**
     * 根据hql和参数获得Int结果
     * 
     * @param hql
     *            hql
     * @param params
     *            参数
     * @return Int结果
     */
    protected final Integer getIntResult(final String hql, final List<?> params) {
        return getIntResult(hql, params.toArray());
    }

    /**
     * 根据hql和参数获得Int结果
     * 
     * @param hql
     *            hql
     * @param params
     *            参数
     * @return Int结果
     */
    protected final Integer getIntResult(final String hql, final Object... params) {
        Query query = this.getQuery(hql, params);
        return new Integer(((Number) query.uniqueResult()).intValue());
    }

    /**
     * 根据hql和参数获得List结果
     * 
     * @param hql
     *            hql
     * @param params
     *            参数
     * @return List结果
     */
    protected final <T> List<T> find(final String hql, final Object... params) {
        Query query = this.getQuery(hql, params);
        return query.list();
    }

    /**
     * 根据hql,参数,指定区间获得List结果
     * 
     * @param hql
     *            hql
     * @param firstResult
     *            第一条结果
     * @param maxResults
     *            最大结果
     * @param params
     *            参数
     * @return List结果
     */
    protected final <T> List<T> findByRange(final String hql, final int firstResult, final int maxResults,
            final List<?> params) {
        return this.findByRange(hql, firstResult, maxResults, params.toArray());
    }

    /**
     * 根据hql,参数,指定区间获得List结果
     * 
     * @param hql
     *            hql
     * @param firstResult
     *            第一条结果
     * @param maxResults
     *            最大结果
     * @param params
     *            参数
     * @return List结果
     */
    protected final <T> List<T> findByRange(final String hql, final int firstResult, final int maxResults,
            final Object... params) {
        Query query = this.getQuery(hql, params);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.list();
    }

    /**
     * 执行指定hql
     * 
     * @param sql
     *            Hql
     * @param params
     *            参数
     */
    protected final void sqlQuery(final String sql, final Object... params) {
        Query query = getQuery(sql, params);
        query.executeUpdate();
        this.sessionFactory.getCurrentSession().clear();
    }

}
