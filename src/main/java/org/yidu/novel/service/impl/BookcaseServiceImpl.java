package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TBookcase;
import org.yidu.novel.service.BookcaseService;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 提供书签信息操作的服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class BookcaseServiceImpl extends HibernateSupportServiceImpl implements BookcaseService {

    @Override
    public List<TBookcase> find(BookcaseSearchBean searchBean) {
        // 初期SQL做成
        StringBuffer hql = new StringBuffer();
        hql.append("From TBookcase WHERE  deleteflag=false  ");
        List<Object> params = new ArrayList<Object>();
        buildCondtion(searchBean, hql, params);
        hql.append(" ORDER BY createtime DESC");
        return this.find(hql.toString(), params);

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
    private void buildCondtion(BookcaseSearchBean searchBean, StringBuffer hql, List<Object> params) {
        if (Utils.isDefined(searchBean.getUserno() )) {
            hql.append("  AND userno=? ");
            params.add(searchBean.getUserno());
        }

        if (Utils.isDefined(searchBean.getArticleno() )) {
            hql.append("  AND articleno=? ");
            params.add(searchBean.getArticleno());
        }
    }

    @Override
    public Integer getCount(BookcaseSearchBean searchBean) {
        // 初期SQL做成
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT count(*)  From TBookcase WHERE  deleteflag=false  ");
        List<Object> params = new ArrayList<Object>();
        buildCondtion(searchBean, hql, params);
        return this.getIntResult(hql.toString(), params);
    }

    @Override
    public TBookcase getByNo(int bookcaseno) {
        return this.get(TBookcase.class, bookcaseno);
    }

    @Override
    public void deleteByNo(int bookcaseno) {
        TBookcase bookcase = getByNo(bookcaseno);
        this.delete(bookcase);
    }

    @Override
    public void save(TBookcase bookcase) {
        this.saveOrUpdate(bookcase);
    }

    @Override
    public List<BookcaseDTO> findWithArticleInfo(BookcaseSearchBean searchBean) {
        // 初期SQL做成
        StringBuffer sql = new StringBuffer();
        sql.append("Select tb.*,ta.lastchapterno,ta.lastchapter,ta.chapters,ta.size,ta.fullflag,ta.lastupdate "
                + " ,ta.imgflag ,ta.pinyin  "
                + "      FROM t_bookcase tb                                                                     "
                + "      LEFT JOIN t_article ta ON tb.articleno = ta.articleno                                  "
                + "WHERE tb.userno= ");
        sql.append(searchBean.getUserno());
        // 添加排序信息
        if (searchBean.getPagination() != null) {
            sql.append(searchBean.getPagination().getSortInfo());
        } else {
            sql.append("ORDER BY ta.lastupdate DESC");
        }
        return yiduJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<BookcaseDTO>(BookcaseDTO.class));
    }

    @Override
    public TBookcase getByArticlenoAndUserno(int userno, int articleno) {
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setArticleno(articleno);
        searchBean.setUserno(userno);
        List<TBookcase> list = this.find(searchBean);
        if (Utils.isDefined(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void batchDeleteByNo(String bookcasenos, int userno) {
        // 初期SQL做成
        StringBuffer sql = new StringBuffer();
        sql.append("delete from  t_bookcase   " + "WHERE userno= ");
        sql.append(userno);
        sql.append("  AND bookcaseno in (");
        sql.append(bookcasenos);
        sql.append("  )");
        yiduJdbcTemplate.execute(sql.toString());
    }

    @Override
    public void deleteByArticleno(int articleno, int userno) {
        // 初期SQL做成
        StringBuffer sql = new StringBuffer();
        sql.append("delete from  t_bookcase   " + "WHERE userno= ");
        sql.append(userno);
        sql.append("  AND articleno =  ");
        sql.append(articleno);
        yiduJdbcTemplate.execute(sql.toString());
    }

}
