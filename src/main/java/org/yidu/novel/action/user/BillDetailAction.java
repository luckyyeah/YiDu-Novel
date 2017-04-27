package org.yidu.novel.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 账单管理Action
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "billdetail")
public class BillDetailAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -2984522801349519469L;

    /**
     * 小说编号
     */
    private int articleno;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_AUTHER_BILL_DETAIL;
    }

    @SkipValidation
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
        return "billdetail";
    }
}
