package org.yidu.novel.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.yidu.novel.action.base.AbstractAdminBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractInstallBaseAction;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.service.UserService;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * <p>
 * 认证用拦截器
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class AuthCheckInterceptor extends AbstractInterceptor {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 6232895416881210405L;
    /**
     * 认证错误的键
     */
    private static final String AUTH_ERROR_KEY = "errors.unauthority";
    /**
     * 未知错误的键
     */
    private static final String UNKNOWN_ERROR_KEY = "errors.unknown";
    /**
     * logger
     */
    private static Log logger = LogFactory.getLog(AuthCheckInterceptor.class);

    /**
     * 用户关联操作服务
     */
    @Autowired
    protected UserService userService;

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {

        logger.debug("AuthCheckInterceptor start.");

        // 安装页面直接访问
        if (invocation.getAction() instanceof AbstractInstallBaseAction) {
            return invocation.invoke();
        }

        boolean skipAuthCheck = YiDuConstants.yiduConf.getBoolean("skipAuthCheck", false);
        if (skipAuthCheck) {
            return invocation.invoke();
        }

        // 如果没有登录的话，读取Cookie信息尝试登录
        if (!LoginManager.isLoginFlag()) {
            CookieUtils.getUserCookieAndLogoin(ServletActionContext.getRequest(), userService);
        }

        // 访问用户画面，没登录的话跳转到登陆画面
        if (invocation.getAction() instanceof AbstractUserBaseAction) {
            if (LoginManager.isLoginFlag()) {
                return invocation.invoke();
            } else {
                return AbstractBaseAction.GOTO_LOGIN;
            }
        }
        // 访问管理画面，没登录的话或非管理员的话跳转到登陆画面
        if (invocation.getAction() instanceof AbstractAdminBaseAction) {
            AbstractAdminBaseAction action = (AbstractAdminBaseAction) invocation.getAction();
            if (LoginManager.isLoginFlag()) {
                if (LoginManager.getLoginUser().getType() == YiDuConstants.UserType.ADMINISTRATOR) {
                    return invocation.invoke();
                } else {
                    action.addActionError(action.getText(AUTH_ERROR_KEY));
                    return AbstractBaseAction.ADMIN_ERROR;
                }
            } else {
                return AbstractBaseAction.GOTO_LOGIN;
            }
        }

        // 公共画面页面随意访问
        if (invocation.getAction() instanceof AbstractPublicBaseAction) {
            return invocation.invoke();
        } else {
            AbstractBaseAction action = (AbstractBaseAction) invocation.getAction();
            String errorMsg = action.getText(UNKNOWN_ERROR_KEY);
            action.addActionError(errorMsg);
            return AbstractBaseAction.FREEMARKER_ERROR;
        }
    }
}
