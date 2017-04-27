package org.yidu.novel.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 
 * <p>
 * 邮件验证Action。
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "emailValidate")
public class EmailValidateAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -3436520744504471823L;

    /**
     * 功能名称。
     */
    public static final String NAME = "emailValidate";

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
     * 邮件验证码
     */
    private String mailtoken;

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
     * 获取mailtoken
     * 
     * @return mailtoken
     */
    public String getMailtoken() {
        return mailtoken;
    }

    /**
     * 
     * 设置mailtoken
     * 
     * 
     * @param mailtoken
     *            mailtoken
     */
    public void setMailtoken(String mailtoken) {
        this.mailtoken = mailtoken;
    }

    @Override
    public String getTempName() {
        return "emailValidate";
    }

    @SkipValidation
    @Override
    public String execute() {
        logger.info("EmailValidateAction execute has been excuted.");
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
    public String emailValidate() {
        logger.debug("EmailValidateAction login has been excuted.");

        UserSearchBean searchBean = new UserSearchBean();
        searchBean.setDeleteflag(false);
        searchBean.setLoginid(loginid);
        searchBean.setMailtoken(mailtoken);
        searchBean.setPassword(Utils.convert2MD5(password));
        searchBean.setActivedflag(false);
        searchBean.setModifytime(DateUtils.addHours(new Date(), -3));

        List<TUser> userList = userService.find(searchBean);
        if (Utils.isDefined(userList)) {
            logger.info("user " + loginid + " has logined.");
            TUser user = userList.get(0);
            // 更新用户最后登录时间
            user.setLastlogin(new Date());
            user.setActivedflag(true);
            userService.save(user);
            // 正常登录
            LoginManager.doLogin(user);
            logger.debug("emailValidate normally end.");
            return REDIRECT;
        } else {
            addActionError(getText("errors.emailValidate.failed"));
            logger.info("user " + loginid + " emailValidate failed.");
            logger.debug("emailValidate abnormally end.");
        }
        return FREEMARKER;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_MAIL_VALIDATE;
    }

    @Override
    protected void loadData() {
    }

}
