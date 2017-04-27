package org.yidu.novel.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.ReviewSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TReview;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 评价页
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "reviewList")
public class ReviewListAction extends AbstractPublicListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 7851872585464786041L;

    /**
     * 功能名称。
     */
    public static final String NAME = "reviewList";

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
     * 页号
     */
    private int page;
    /**
     * 评论信息
     */
    private String review;

    /**
     * 是否是来自Form
     */
    private boolean isFromForm;

    /**
     * 评论列表
     */
    private List<TReview> reviewList;
    /**
     * 小说信息
     */
    private TArticle article;
    /**
     * 用户信息
     */
    private TUser user;

    /**
     * 获取articleno
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
     * 获取pinyin
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
     * 获取page
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
     * 获取review
     * 
     * @return review
     */
    public String getReview() {
        return review;
    }

    /**
     * 
     * 设置review
     * 
     * 
     * @param review
     *            review
     */
    public void setReview(String review) {
        this.review = StringUtils.trim(review);
    }

    /**
     * 获取isFromForm
     * 
     * @return isFromForm
     */
    public boolean isFromForm() {
        return isFromForm;
    }

    /**
     * 
     * 设置isFromForm
     * 
     * 
     * @param isFromForm
     *            isFromForm
     */
    public void setFromForm(boolean isFromForm) {
        this.isFromForm = isFromForm;
    }

    /**
     * 获取reviewList
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
     * 获取article
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
     * 获取user
     * 
     * @return user
     */
    public TUser getUser() {
        return user;
    }

    /**
     * 
     * 设置user
     * 
     * 
     * @param user
     *            user
     */
    public void setUser(TUser user) {
        this.user = user;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_REVIEW;
    }

    @Override
    public String getTempName() {
        return "reviewList";
    }

    /**
     * 获得回退URL
     * 
     * @return 回退URL
     */
    public String getBackUrl() {
        return ServletActionContext.getResponse().encodeURL(
                "/reviewList?subdir=" + articleno / 1000 + "&articleno=" + articleno);
    }

    /**
     * 获得JSON类型返回数据
     * 
     * @return JSON类型返回数据
     */
    public String getData() {
        if (hasActionErrors()) {
            return StringUtils.join(getActionErrors(), ",");
        } else {
            return "success";
        }
    }

    @Override
    protected void loadData() {

        logger.debug("loadData start.");

        if (articleno != 0) {
            // 获取小说信息
            article = articleService.getByNo(articleno);
        } else if (StringUtils.isNotBlank(pinyin)) {
            ArticleSearchBean searchBean = new ArticleSearchBean();
            searchBean.setPinyin(pinyin);
            List<TArticle> articleList = articleService.find(searchBean);
            if (Utils.isDefined(articleList)) {
                article = articleList.get(0);
            }
        }
        // 小说信息不存在的情况
        if (article == null) {
            addActionError(getText("errors.not.exsits.article"));
            return;
        }

        ReviewSearchBean searchBean = new ReviewSearchBean();
        searchBean.setArticleno(article.getArticleno());

        pagination.setPageNumber(page == 0 ? 1 : page);
        pagination.setSortColumn(TReview.PROP_POSTDATE);
        pagination.setSortOrder("DESC");

        // 总件数设置
        pagination.setPreperties(reviewService.getCount(searchBean));
        searchBean.setPagination(pagination);
        // 获取评论信息
        reviewList = reviewService.find(searchBean);

        logger.debug("normally end.");

    }

    /**
     * 添加评论
     * 
     * @return 表示页面
     */
    @Transactional
    public String addReview() {

        boolean addWithoutLogin = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ADD_REVIEW_WITHOUT_LOGIN, false);
        // 不允许非登录状态评论的话，检查登录状态
        if (!addWithoutLogin && !LoginManager.isLoginFlag()) {
            addActionError(getText("errors.notLogin"));
        }

        // 章节编号没添的话，报未知错误
        if (articleno == 0) {
            addActionError(getText("errors.unknown"));
        }

        // 输入检查
        if (StringUtils.length(review) < 5 || StringUtils.length(review) > 500) {
            addActionError(getText("errors.lengthrange", new String[] { "5", "500", "评论" }));
        }

        if (hasActionErrors()) {
            if (!isFromForm) {
                return JSON_RESULT;
            }
            // 重新读取评论信息
            this.loadData();
            return FREEMARKER;
        }

        // 开始登录评论信息
        TReview review = new TReview();
        TArticle article = articleService.getByNo(articleno);
        TUser user = LoginManager.getLoginUser();

        BeanUtils.copyProperties(this, review);
        BeanUtils.copyProperties(article, review);
        BeanUtils.copyProperties(user, review);

        review.setPostdate(new Date());
        review.setDeleteflag(false);
        review.setModifyuserno(user.getUserno());
        review.setModifytime(new Date());
        this.reviewService.save(review);

        if (!isFromForm) {
            return JSON_RESULT;
        }

        return REDIRECT;
    }

}
