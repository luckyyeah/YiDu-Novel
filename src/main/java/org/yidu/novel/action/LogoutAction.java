package org.yidu.novel.action;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;

/**
 * 
 * <p>
 * 退出Action
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class LogoutAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5189599136868434255L;

    @SkipValidation
    @Override
    public String execute() {
        logger.info("LoginOutAction execute has been excuted.");
        if (LoginManager.isLoginFlag()) {
            Cookie cookie = CookieUtils.delUserCookie(ServletActionContext.getRequest());
            if (cookie != null) {
                ServletActionContext.getResponse().addCookie(cookie);
            }
            LoginManager.doLogout();
        }
        return GO_TOP;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public String getTempName() {
        return null;
    }
}
