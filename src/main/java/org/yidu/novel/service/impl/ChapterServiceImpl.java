package org.yidu.novel.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.dto.ChapterDTO;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.service.ChapterService;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 提供章节信息操作的服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class ChapterServiceImpl extends HibernateSupportServiceImpl implements ChapterService {

    @Override
    public List<TChapter> find(ChapterSearchBean searchBean) {

        StringBuffer hql = new StringBuffer();
        hql.append("FROM TChapter WHERE  deleteflag=false  ");
        List<Object> params = new ArrayList<Object>();
        // 追加小说ID条件
        if (searchBean.getArticleno() != 0) {
            hql.append(" AND articleno = ? ");
            params.add(searchBean.getArticleno());
        }
        if (Utils.isDefined(searchBean.getChapternoList())) {
            hql.append(" AND chapterno in ( " + StringUtils.join(searchBean.getChapternoList(), ",") + ") ");
        }

        if (Utils.isDefined(searchBean.getArticlenoList())) {
            hql.append(" AND articleno in ( " + StringUtils.join(searchBean.getArticlenoList(), ",") + ") ");
        }

        if (Utils.isDefined(searchBean.getDateRange())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.sss");
            hql.append(" AND postdate >= '" + sdf.format(searchBean.getDateRange().getMinimum()) + "'");
            hql.append(" AND postdate < '" + sdf.format(searchBean.getDateRange().getMaximum()) + "'");
        }

        Pagination pagination = searchBean.getPagination();
        // 添加排序信息
        if (pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getPageSize(), params);
        } else {
            hql.append("ORDER BY articleno asc ,chapterno ASC ");
            return this.find(hql.toString(), params);
        }
    }

    @Override
    public TChapter getByNo(int chapterno) {
        return this.get(TChapter.class, chapterno);
    }
    @Override
    public boolean checkVipByNo(int articleno,int chapterno,int vipstartno) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TChapter WHERE deleteflag=false and articleno = ? ");
        // 追加小说号条件
        params.add(articleno);

        hql.append(" AND chapterno <= ?  AND chaptertype = 0 ORDER BY  chapterno  ");

        // 追加章节号条件
        params.add(chapterno);
        // 只取一条记录
        List<TChapter> chapterList = this.find(hql.toString(), params);
        if (chapterList.size()>=vipstartno) {
           return true;
        }
        return false;
    }
    @Override
    public TChapter getNextChapter(int articleno, int chapterno, boolean isNext) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TChapter WHERE deleteflag=false and articleno = ? ");
        // 追加小说号条件
        params.add(articleno);
        if (isNext) {
            hql.append(" AND chapterno > ?  AND chaptertype = 0 ORDER BY  chapterno ASC ");
        } else {
            hql.append(" AND chapterno < ?  AND chaptertype = 0 ORDER BY  chapterno DESC ");
        }
        // 追加章节号条件
        params.add(chapterno);
        // 只取一条记录
        List<TChapter> chapterList = this.findByRange(hql.toString(), 0, 1, params);
        if (Utils.isDefined(chapterList)) {
            return chapterList.get(0);
        }
        return null;
    }

    @Override
    public void save(TChapter chapter) {
        this.saveOrUpdate(chapter);
    }

    @Override
    public void deleteByNo(int chapterno) {
        TChapter chapter = getByNo(chapterno);
        this.delete(chapter);
    }

    @Override
    public Integer getCount(ChapterSearchBean searchBean) {
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        sql.append("SELECT count(*) FROM TChapter where  deleteflag=false ");

        // 小说号条件追加
        if (searchBean.getArticleno() != 0) {
            sql.append(" AND articleno = ? ");
            params.add(searchBean.getArticleno());
        }

        return this.getIntResult(sql.toString(), params);
    }

    @Override
    public Integer getChapterCount(int articleno) {
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        sql.append("SELECT sum(size) FROM TChapter where  deleteflag=false and chaptertype = 0 ");
        sql.append(" AND articleno = ? ");
        params.add(articleno);
        return this.getIntResult(sql.toString(), params);
    }

    @Override
    public TChapter getLastChapter(int articleno) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TChapter WHERE deleteflag=false and articleno = ? order by chapterno desc");
        // 追加小说号条件
        params.add(articleno);
        // 只取一条记录
        List<TChapter> chapterList = this.findByRange(hql.toString(), 0, 1, params);
        if (Utils.isDefined(chapterList)) {
            return chapterList.get(0);
        }
        return null;
    }

    @Override
    public List<TChapter> getChapterInSegement(Integer articleno, Integer chapterno, Integer toChapterno) {
        StringBuffer hql = new StringBuffer("FROM TChapter "
                + "WHERE deleteflag=false and articleno = ? and chapterno>=? and chapterno<=? "
                + "order by chapterno asc ");
        List<Object> params = new ArrayList<Object>();
        // 追加小说号条件
        params.add(articleno);
        params.add(chapterno);
        params.add(toChapterno);
        return this.find(hql.toString(), params);
    }

    @Override
    public void deleteAllByArticlno(int articleno) {
        StringBuffer sql = new StringBuffer("update TChapter set deleteflag=true where articleno = ?");
        List<Object> params = new ArrayList<Object>();
        // 追加小说号条件
        params.add(articleno);
        sqlQuery(sql.toString(), params);
    }

    @Override
    public List<ChapterDTO> findWithPinyin(ChapterSearchBean searchBean) {

        StringBuffer sql = new StringBuffer();
        sql.append("select tc.* ,ta.pinyin from t_chapter tc join t_article ta on tc.articleno = ta.articleno "
                + "WHERE  tc.deleteflag=false  ");
        List<Object> params = new ArrayList<Object>();
        // 追加小说ID条件
        if (searchBean.getArticleno() != 0) {
            sql.append(" AND tc.articleno = ? ");
            params.add(searchBean.getArticleno());
        }
        if (Utils.isDefined(searchBean.getChapternoList())) {
            sql.append(" AND tc.chapterno in ( " + StringUtils.join(searchBean.getChapternoList(), ",") + ") ");
        }

        if (Utils.isDefined(searchBean.getArticlenoList())) {
            sql.append(" AND tc.articleno in ( " + StringUtils.join(searchBean.getArticlenoList(), ",") + ") ");
        }

        if (Utils.isDefined(searchBean.getDateRange())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.sss");
            sql.append(" AND tc.postdate >= '" + sdf.format(searchBean.getDateRange().getMinimum()) + "'");
            sql.append(" AND tc.postdate < '" + sdf.format(searchBean.getDateRange().getMaximum()) + "'");
        }

        sql.append(" ORDER BY tc.articleno asc ,tc.chapterno ASC ");

        return yiduJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<ChapterDTO>(
                ChapterDTO.class));

    }
}
