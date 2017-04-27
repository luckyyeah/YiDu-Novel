package org.yidu.novel.action.user;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 小说推荐Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
/**
 * <p>
 * TODO。
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class VoteAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -2984522801349519469L;

    /**
     * 功能名称。
     */
    public static final String NAME = "vote";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说编号
     */
    private int articleno;

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

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @SkipValidation
    @Override
    public String execute() {
        logger.debug("execute start.");
        if (articleno != 0) {
            articleService.updateVoteStatistic(articleno);
        } else {
            addActionError(getText("errors.not.exsits.article"));
            return FREEMARKER_ERROR;
        }
        logger.debug("execute normally start.");
        return FREEMARKER;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public String getTempName() {
        return "message";
    }
}
