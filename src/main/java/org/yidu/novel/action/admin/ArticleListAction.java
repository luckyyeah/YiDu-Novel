package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 小说列表Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "articleList")
public class ArticleListAction extends AbstractAdminListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 6039988916270544079L;

    /**
     * 功能名称。
     */
    public static final String NAME = "articleList";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 检索关键字
     */
    private int articleno;

    /**
     * 批量操作参数值
     */
    private String articleNoList;

    public String getArticleNoList() {
        return articleNoList;
    }

    public void setArticleNoList(String articleNoList) {
        this.articleNoList = articleNoList;
    }

    /**
     * 检索关键字
     */
    private String key;

    /**
     * 页号
     */
    private int page;

    /**
     * 类别
     */
    private int category;

    /**
     * 小说列表
     */
    private List<TArticle> articleList = new ArrayList<TArticle>();

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = StringUtils.trim(key);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TArticle> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    @Override
    protected void loadData() {
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean, new String[] { "articleno" });
        searchBean.setPageType(ArticleSearchBean.PageType.adminPage);
        if (StringUtils.isEmpty(pagination.getSortColumn())) {
            pagination.setSortColumn("lastupdate");
            pagination.setSortOrder("DESC");
        }
        // 总件数设置
        pagination.setPreperties(articleService.getCount(searchBean));
        searchBean.setPagination(pagination);
        articleList = articleService.find(searchBean);
        // Setting number of records in the particular page
        pagination.setPageRecords(articleList.size());
    }

    public String delete() throws Exception {

        initCollections(new String[] { "collectionProperties.article.category" });
        if (StringUtils.isNotBlank(articleNoList)) {
            String[] articleNo = articleNoList.split(",");
            for (String ano : articleNo) {
                TArticle article = articleService.getByNo(Integer.valueOf(ano));
                article.setDeleteflag(true);
                article.setModifyuserno(LoginManager.getLoginUser().getUserno());
                article.setModifytime(new Date());
                articleService.save(article);
            }
        } else {
            TArticle article = articleService.getByNo(articleno);
            article.setDeleteflag(true);
            article.setModifyuserno(LoginManager.getLoginUser().getUserno());
            article.setModifytime(new Date());
            articleService.save(article);
        }

        loadData();
        return INPUT;
    }
}
