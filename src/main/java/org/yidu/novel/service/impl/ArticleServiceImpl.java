package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.dto.CategoryCountDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 提供小说信息操作的服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class ArticleServiceImpl extends HibernateSupportServiceImpl implements ArticleService {

    @Override
    public List<TArticle> find(final ArticleSearchBean searchBean) {

        // 初期SQL做成
        StringBuffer hql = new StringBuffer();
        hql.append("From TArticle WHERE deleteflag=false  ");
        List<Object> params = new ArrayList<Object>();

        buildCondtion(searchBean, hql, params);

        Pagination pagination = searchBean.getPagination();
        // 添加排序信息
        if (pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getPageSize(), params);
        } else {
            hql.append("ORDER BY articleno");
            return this.find(hql.toString(), params);
        }
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
    private void buildCondtion(ArticleSearchBean searchBean, StringBuffer hql, List<Object> params) {
        // 小说号条件追加
        if (searchBean.getArticleno() != 0) {
            hql.append(" AND articleno = ? ");
            params.add(searchBean.getArticleno());
        }
        // 小说名条件追加
        if (StringUtils.isNotEmpty(searchBean.getArticlename())) {
            hql.append(" AND articlename = ? ");
            params.add(searchBean.getArticlename());
        }
        // 拼音条件追加
        if (StringUtils.isNotEmpty(searchBean.getPinyin())) {
            hql.append(" AND pinyin = ? ");
            params.add(searchBean.getPinyin());
        }
        // 小说名条件追加
        if (searchBean.getFromArticleno() != 0) {
            hql.append(" AND articleno > ? ");
            params.add(searchBean.getFromArticleno());
        }
        // 小说作者条件追加
        if (StringUtils.isNotEmpty(searchBean.getAuthor())) {
            hql.append(" AND author = ? ");
            params.add(searchBean.getAuthor());
        }

        // 小说种别追加
        if (searchBean.getCategory() != null && searchBean.getCategory() != 0) {
            hql.append(" AND category = ? ");
            params.add(searchBean.getCategory());
        }

        // 完本标识追加
        if (searchBean.getFullflag() != null && searchBean.getFullflag()) {
            hql.append(" AND fullflag = TRUE ");
        }

        // 小说编号数组追加
        if (StringUtils.isNotEmpty(searchBean.getArticlenos())) {
            hql.append(" AND articleno in ( " + searchBean.getArticlenos() + " )  ");
        }

        // 条件追加
        if (StringUtils.isNotEmpty(searchBean.getKey())) {
            // 文章名
            hql.append(" AND  (LOWER(articlename) like '%"
                    + StringEscapeUtils.escapeSql(searchBean.getKey().toLowerCase()) + "%' OR LOWER(author) like '%"
                    + StringEscapeUtils.escapeSql(searchBean.getKey().toLowerCase()) + "%' )");
        }

        if (searchBean.getAuthorid() != null && searchBean.getAuthorid() > 0) {
            // 作者号指定的话，添加作者号
            hql.append(" AND authorid = ? ");
            params.add(searchBean.getAuthorid());
        }

        if (Utils.isDefined(searchBean.getTag())) {
            // 标签指定的话，添加标签条件
            hql.append(" AND articlename like '%"
                    + StringEscapeUtils.escapeSql(searchBean.getTag()) + "%'");
        }

        if (searchBean.getPageType() != ArticleSearchBean.PageType.authorPage
                && searchBean.getPageType() != ArticleSearchBean.PageType.adminPage) {
            // 非作者和管理员界面的话过滤空小说
            hql.append(" AND lastupdate is not null ");
            hql.append(" AND lastchapterno is not null ");
        }
    }

    @Override
    public TArticle getByNo(final int articleno) {
        return this.get(TArticle.class, articleno);
    }

    @Override
    public void deleteByNo(final int articleno) {
        TArticle article = getByNo(articleno);
        this.delete(article);

    }

    @Override
    public void save(TArticle article) {
        this.saveOrUpdate(article);
    }

    @Override
    public Integer getCount(ArticleSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("SELECT count(*) FROM TArticle where deleteflag = false ");
        buildCondtion(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    @Override
    public List<CategoryCountDTO> getCountPerCategory() {
        String sql = " SELECT category,count(*) FROM t_article where deleteflag = false group by category ";
        return this.yiduJdbcTemplate.query(sql, new BeanPropertyRowMapper<CategoryCountDTO>(CategoryCountDTO.class));
    }

    @Override
    public void updateVisitStatistic(int articleno) {
        String sql = "update TArticle set   dayvisit  = dayvisit +1 , weekvisit= weekvisit +1 ,"
                + "monthvisit =monthvisit+1 , allvisit = allvisit +1 where articleno =  ? ";
        this.sqlQuery(sql, articleno);
    }

    @Override
    public void updateVoteStatistic(int articleno) {
        String sql = "update TArticle set  dayvote  = dayvote +1 , weekvote= weekvote +1 ,"
                + "monthvote =monthvote+1 , allvote = allvote +1 where articleno =  ? ";
        this.sqlQuery(sql, articleno);
    }

    @Override
    @Transactional
    public void cleanStatistics() {
        logger.info("cleanStatistics start");
        String sql = "update t_article set dayvote = 0 ,dayvisit = 0";
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        // 每周一清空周统计信息
        if (dayOfWeek == Calendar.MONDAY) {
            sql += ",weekvote = 0,weekvisit=0";
        }
        // 每月1号清空月统计信息
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        if (dayOfMonth == 1) {
            sql += ",monthvote = 0,monthvisit=0";
        }
        this.yiduJdbcTemplate.update(sql);
        logger.info("cleanStatistics end");
    }

    @Override
    public TArticle findByPinyinRegularRxpressions(String pinyin) {
        String sql = "SELECT * FROM t_article where pinyin ~ '^" + pinyin + "[\\d]*$' order by pinyin desc;";
        List<TArticle> articleList = this.yiduJdbcTemplate.query(sql, new BeanPropertyRowMapper<TArticle>(
                TArticle.class));
        if (articleList != null && articleList.size() > 0) {
            return articleList.get(0);
        }
        return null;
    }

    @Override
    public Integer getCountByJDBC(ArticleSearchBean searchBean) {
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        sql.append("SELECT count(*) FROM t_article where deleteflag = false ");
        buildCondtion(searchBean, sql, params);
        return yiduJdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
    }

    @Override
    public List<TArticle> findRecommendArticleList(final int category, int articleno, final int count) {
        List<Object> params = new ArrayList<Object>();
        params.add(articleno);
        StringBuffer hql = new StringBuffer();
        hql.append("FROM TArticle where articleno > ? and deleteflag = false ");
        if (category != 0) {
            hql.append(" AND  category = ?  ");
            params.add(category);
        }
        hql.append(" AND lastupdate is not null ");
        hql.append(" AND lastchapterno is not null ");
        hql.append("  order by articleno  ");
        return this.findByRange(hql.toString(), 1, count, params);
    }

    @Override
    public List<TArticle> findRandomRecommendArticleList(final int count) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        // 为了提升性能，没有用hibernate，写了nactiveSQL
        sql.append(" SELECT * ");
        sql.append(" FROM  ( ");
        sql.append("    SELECT DISTINCT 1 + floor(random() * (select max(articleno) from t_article) )::integer AS articleno ");
        sql.append("    FROM   generate_series(1, 50) g ");
        sql.append("    ) r ");
        sql.append(" JOIN   t_article USING (articleno) ");
        sql.append(" where  deleteflag = false ");
        sql.append(" AND lastupdate is not null  ");
        sql.append(" AND lastchapterno is not null ");
        sql.append("LIMIT  ?");
        params.add(count);

        return this.yiduJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<TArticle>(
                TArticle.class));
    }

    @Override
    public List<TArticle> findRelativeArticleList(List<String> keys, String sortCol, boolean isAsc, int limitNum) {

        List<Object> params = new ArrayList<Object>();
        params.addAll(keys);
        params.add(sortCol);

        String cond = "";
        boolean isFirst = true;
        for (int i = 0; i < keys.size(); i++) {
            if (isFirst) {
                cond += " ?  % articlename ";
                isFirst = false;
            } else {
                cond += (" OR ?  % articlename ");
            }
        }
        String sql = "SELECT * FROM t_article where " + cond + "  order by ?  " + (isAsc ? "ASC" : "DESC") + " limit "
                + limitNum;
        return this.yiduJdbcTemplate.query(sql, params.toArray(), new BeanPropertyRowMapper<TArticle>(TArticle.class));
    }
}
