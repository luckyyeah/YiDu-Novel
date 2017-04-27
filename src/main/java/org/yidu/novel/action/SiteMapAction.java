package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.entity.TArticle;

/**
 * 
 * <p>
 * 网站地图Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "siteMap")
public class SiteMapAction extends AbstractPublicListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -3069730816500421029L;

    /**
     * 功能名称。
     */
    public static final String NAME = "siteMap";
    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    /**
     * 小说列表
     */
    private List<TArticle> articleList = new ArrayList<TArticle>();

    /**
     * 获取 articleList
     * 
     * @return articleList
     */
    public List<TArticle> getArticleList() {
        return articleList;
    }

    /**
     * 
     * 设置articleList
     * 
     * 
     * @param articleList
     *            articleList
     */
    public void setArticleList(List<TArticle> articleList) {
        this.articleList = articleList;
    }

    @Override
    public int getPageType() {
        return 0;
    }

    @Override
    public String getTempName() {
        return "siteMap";
    }

    @Override
    protected void loadData() {
        articleList = articleService.find(new ArticleSearchBean());
    }

}
