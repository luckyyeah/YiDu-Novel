package org.yidu.novel.action;

import java.util.List;

import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.bean.ReviewSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.ChapterDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.entity.TReview;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 小说简介页
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class InfoAction extends AbstractPublicBaseAction {

    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4215796997609788238L;

    /**
     * 功能名称。
     */
    public static final String NAME = "info";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说编号
     */
    private int articleno;
    /**
     * 拼音
     */
    private String pinyin;
    /**
     * 小说信息
     */
    private TArticle article;
    /**
     * 章节列表信息
     */
    private List<ChapterDTO> chapterList;
    /**
     * 评论件数
     */
    private int reviewCount;
    /**
     * 评论列表
     */
    private List<TReview> reviewList;

    /**
     * 获取 articleno
     * 
     * @return articleno
     */
    public int getArticleno() {
        return articleno;
    }

    /**
     * 
     * 设置articleno
     * 
     * 
     * @param articleno
     *            articleno
     */
    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    /**
     * 获取 pinyin
     * 
     * @return pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 
     * 设置pinyin
     * 
     * 
     * @param pinyin
     *            pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * 获取 article
     * 
     * @return article
     */
    public TArticle getArticle() {
        return article;
    }

    /**
     * 
     * 设置article
     * 
     * 
     * @param article
     *            article
     */
    public void setArticle(TArticle article) {
        this.article = article;
    }

    /**
     * 获取chapterList
     * 
     * @return chapterList
     */
    public List<ChapterDTO> getChapterList() {
        return chapterList;
    }

    /**
     * 
     * 设置chapterList
     * 
     * 
     * @param chapterList
     *            chapterList
     */
    public void setChapterList(List<ChapterDTO> chapterList) {
        this.chapterList = chapterList;
    }

    /**
     * 获取 reviewCount
     * 
     * @return reviewCount
     */
    public int getReviewCount() {
        return reviewCount;
    }

    /**
     * 
     * 设置reviewCount
     * 
     * 
     * @param reviewCount
     *            reviewCount
     */
    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    /**
     * 获取 reviewList
     * 
     * @return reviewList
     */
    public List<TReview> getReviewList() {
        return reviewList;
    }

    /**
     * 
     * 设置reviewList
     * 
     * 
     * @param reviewList
     *            reviewList
     */
    public void setReviewList(List<TReview> reviewList) {
        this.reviewList = reviewList;
    }

    /**
     * 获取小说子目录
     * 
     * @return 小说子目录
     */
    @Deprecated
    public int getSubDir() {
        return articleno / YiDuConstants.SUB_DIR_ARTICLES;
    }

    @Override
    public String getTempName() {
        return "info";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");

        if (articleno != 0) {
            article = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno);
            if (!Utils.isDefined(article)) {
                article = articleService.getByNo(articleno);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno, article);
            }
        } else if (Utils.isDefined(pinyin)) {
            article = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, pinyin);
            if (!Utils.isDefined(article)) {
                ArticleSearchBean searchBean = new ArticleSearchBean();
                searchBean.setPinyin(pinyin);
                List<TArticle> articleList = articleService.find(searchBean);
                if (Utils.isDefined(articleList)) {
                    article = articleList.get(0);
                    CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, pinyin, article);
                }
            }
        }

        if (article != null) {
//            if (!YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false)
//                    || (YiDuConstants.singleBookFlag.get() != null && YiDuConstants.singleBookFlag.get())) {
                // 获取章节信息
                ChapterSearchBean searchBean = new ChapterSearchBean();
                searchBean.setArticleno(article.getArticleno());
                chapterList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_LIST_PREFIX,
                        searchBean);
                if (!Utils.isDefined(chapterList)) {
                    chapterList = chapterService.findWithPinyin(searchBean);
                    if (Utils.isDefined(chapterList)) {
                        CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_LIST_PREFIX, searchBean,
                                chapterList);
                    }
                }
//            }

            // 获取评论信息
            ReviewSearchBean reviewSearchBean = new ReviewSearchBean();
            reviewSearchBean.setArticleno(article.getArticleno());
            // 获取评论件数
            reviewCount = reviewService.getCount(reviewSearchBean);
            // 获取评论
            Pagination pagination = new Pagination(YiDuConstants.yiduConf.getInt(YiDuConfig.REVIEW_NUM, 5), 1);
            pagination.setSortColumn(TChapter.PROP_POSTDATE);
            pagination.setSortOrder("DESC");
            reviewSearchBean.setPagination(pagination);
            this.reviewList = reviewService.find(reviewSearchBean);
            // 更新统计信息
            articleService.updateVisitStatistic(article.getArticleno());
        } else {
            addActionError(getText("errors.not.exsits.article"));
            setNotFound(true);
        }
        logger.debug("loadData normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_ARTICLE_INFO;
    }

    @Override
    protected String getBlockKey() {
        return CacheManager.CacheKeyPrefix.CACHE_KEY_INFO_BLOCK + articleno;
    }

    @Override
    protected Short getBlockTarget() {
        return YiDuConstants.BlockTarget.ARTICLE_INFO;
    }

    /**
     * 取得单本用singleBookServerName
     * 
     * @return 单本用singleBookServerName
     */
    public String getSingleBookServerName() {
        return YiDuConstants.singleBookServerName.get();
    }

    @Override
    protected int getRecommondArticleno() {
        if (Utils.isDefined(article)) {
            return article.getArticleno();
        }
        return 0;
    }

    @Override
    protected int getRecommondCategory() {
        if (Utils.isDefined(article) && Utils.isDefined(article.getCategory())) {
            return article.getCategory();
        } else {
            return 0;
        }
    }

    @Override
    protected String getRelativeArticleName() {
        if (Utils.isDefined(article)) {
            return article.getArticlename();
        }
        return null;
    }
    
    @Override
    protected String getAuthor() {
        if (Utils.isDefined(article)) {
            return article.getAuthor();
        }
        return null;
    }

}
