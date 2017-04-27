package org.yidu.novel.action;

import java.util.Date;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 
 * <p>
 * 登录Action。
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class LoginAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1L;

    /**
     * 功能名称。
     */
    public static final String NAME = "login";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    /**
     * 登录ID
     */
    private String loginid;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 是否保存Cookie
     */
    private boolean useCookie;

    /**
     * 获取登录ID
     * 
     * @return 登录ID
     */
    public String getLoginid() {
        return loginid;
    }

    /**
     * 设置登录ID
     * 
     * @param loginid
     *            登录ID
     */
    @Validations(
    // 必須
    requiredStrings = { @RequiredStringValidator(message = "${getText(\"errors.required.input\", "
            + "{getText(\"label.user.loginid\")})}") }, stringLengthFields = { @StringLengthFieldValidator(maxLength = "32", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.loginid\")})}") })
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    /**
     * 获取登录密码
     * 
     * @return 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     * 
     * @param password
     *            登录密码
     */
    @Validations(
    // 必須
    requiredStrings = { @RequiredStringValidator(message = "${getText(\"errors.required.input\","
            + " {getText(\"label.user.password\")})}") }, stringLengthFields = { @StringLengthFieldValidator(maxLength = "32", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.password\")})}") })
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取是否保存Cookie
     * 
     * @return 是否保存Cookie
     */
    public boolean isUseCookie() {
        return useCookie;
    }

    /**
     * 设置是否保存Cookie
     * 
     * @param useCookie
     *            是否保存Cookie
     */
    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    @Override
    public String getTempName() {
        return "login";
    }

    @SkipValidation
    @Override
    public String execute() {
        logger.debug("LoginAction execute has been excuted.");
        initCollections(new String[] { "collectionProperties.article.category" });
        if (LoginManager.isLoginFlag()) {
            return GO_TOP;
        } else {
            // 记录访问地址
            LoginManager.setReferer();
            return FREEMARKER;
        }
    }

    /**
     * 登录处理
     * 
     * @return 返回页
     */
    @Transactional
    public String login() {
        logger.debug("LoginAction login has been excuted.");
        TUser user = userService.findByLoginInfo(loginid, Utils.convert2MD5(password));
        if (user != null) {

            if (user.getActivedflag() == null || !user.getActivedflag()) {
                addActionError(getText("errors.login.actived"));
                return FREEMARKER;
            }
            logger.info("user " + loginid + " has logined.");

            // 正常登录
            LoginManager.doLogin(user);
            // 更新用户最后登录时间
            user.setLastlogin(new Date());
            userService.save(user);
            if (useCookie) {
                Cookie cookie = CookieUtils.addUserCookie(user);
                // 添加cookie到response中
                ServletActionContext.getResponse().addCookie(cookie);
            }
            logger.debug("LoginAction login user is exist. normally end.");
            return REDIRECT;
        } else {
            addActionError(getText("errors.login.failed"));
            logger.info("user " + loginid + " try to login ,but failed.");
            logger.debug("LoginAction login user is not exist. abnormally end.");
        }
        return FREEMARKER;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_LOGIN;
    }

    @Override
    protected void loadData() {
    }

}
