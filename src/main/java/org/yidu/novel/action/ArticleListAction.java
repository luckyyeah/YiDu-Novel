package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.cache.ArticleCountManager;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.utils.Utils;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * 小说列表Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "articleList")
public class ArticleListAction extends AbstractPublicListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4215796997609788238L;

    /**
     * 功能名称。
     */
    public static final String NAME = "articleList";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说种类
     */
    private Integer category;

    /**
     * 页号
     */
    private int page;

    /**
     * 作者
     */
    private String author;

    /**
     * 完本标志
     */
    private Boolean fullflag;
    /**
     * 标签
     */
    private String tag;

    /**
     * 小说列表
     */
    private List<TArticle> articleList = new ArrayList<TArticle>();

    /**
     * 全本的最新入库小说列表 TODO 区块对应
     */
    private List<TArticle> lastPostFullArticleList = new ArrayList<TArticle>();

    /**
     * 获取category
     * 
     * @return category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 
     * 设置category
     * 
     * 
     * @param category
     *            category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 获取 page
     * 
     * @return page
     */
    public int getPage() {
        return page ==0 ? 1 : page;
    }

    /**
     * 
     * 设置page
     * 
     * 
     * @param page
     *            page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 获取 author
     * 
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * 设置author
     * 
     * 
     * @param author
     *            author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取 fullflag
     * 
     * @return fullflag
     */
    public Boolean getFullflag() {
        return fullflag;
    }

    /**
     * 
     * 设置fullflag
     * 
     * 
     * @param fullflag
     *            fullflag
     */
    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

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

    public List<TArticle> getLastPostFullArticleList() {
        return lastPostFullArticleList;
    }

    public void setLastPostFullArticleList(List<TArticle> lastPostFullArticleList) {
        this.lastPostFullArticleList = lastPostFullArticleList;
    }

    @Override
    public String getTempName() {
        return "articleList";
    }
    
    /**
     * 获取排行榜名字的JSON数据
     * 
     * @return 排行榜名字的JSON数据
     */
    public String getTopNameJsonData() {
        Gson gson = new Gson();
        return gson.toJson(YiDuConstants.TOP_NAME_MAP, Map.class);
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");

        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        searchBean.setPageType(ArticleSearchBean.PageType.publicPage);

        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn(TArticle.PROP_LASTUPDATE);
        pagination.setSortOrder("DESC");

        int count = 0;
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, false)) {
            // 开启缓存件数的话
            if (Utils.isDefined(category)) {
                // 分类
                count = ArticleCountManager.getArticleCount(String.valueOf(category));
            } else if (Utils.isDefined(author)) {
                count = ArticleCountManager.getArticleCount(YiDuConstants.CacheCountType.AUTHOR);
            } else if (fullflag != null && fullflag) {
                count = ArticleCountManager.getArticleCount(YiDuConstants.CacheCountType.FULLFLAG);
            } else if (Utils.isDefined(tag)) {
                count = ArticleCountManager.getArticleCount(YiDuConstants.CacheCountType.TAG);
            } else {
                count = ArticleCountManager.getArticleCount(YiDuConstants.CacheCountType.ALL);
            }
        } else {
            Object countInfo = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX,
                    searchBean);
            if (countInfo == null) {
                count = articleService.getCount(searchBean);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX, searchBean,
                        count);
            } else {
                count = Integer.parseInt(countInfo.toString());
            }
        }
        // 总件数设置
        pagination.setPreperties(count);
        searchBean.setPagination(pagination);

        articleList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX, searchBean);
        if (!Utils.isDefined(articleList)) {
            articleList = articleService.find(searchBean);
            CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX, searchBean, articleList);
        }

        if (Utils.isDefined(fullflag) && fullflag) {
            lastPostFullArticleList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX
                    + fullflag, searchBean);
            if (!Utils.isDefined(lastPostFullArticleList)) {
                searchBean.getPagination().setSortColumn(TArticle.PROP_POSTDATE);
                lastPostFullArticleList = articleService.find(searchBean);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX + fullflag,
                        searchBean, lastPostFullArticleList);
            }
        }

        logger.debug("normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_ARTICLE_LIST;
    }

    @Override
    protected String getBlockKey() {
        return CacheManager.CacheKeyPrefix.CACHE_KEY_CATEGORY_LIST_BLOCK;
    }

    @Override
    protected Short getBlockTarget() {
        return YiDuConstants.BlockTarget.ARTICLE_LIST;
    }
}
