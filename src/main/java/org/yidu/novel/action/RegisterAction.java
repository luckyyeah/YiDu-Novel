package org.yidu.novel.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.MailUtils;
import org.yidu.novel.utils.Utils;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

/**
 * 
 * <p>
 * 用户注册Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class RegisterAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1L;
    /**
     * 登录ID
     */
    private String loginid;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 重新输入的密码
     */
    private String repassword;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * QQ号
     */
    private String qq;

    public String getLoginid() {
        return loginid;
    }

    @RequiredStringValidator(message = "${getText(\"errors.required.input\"," + " {getText(\"label.user.loginid\")})}")
    @StringLengthFieldValidator(minLength = "5", maxLength = "32", message = "${getText(\"errors.lengthrange\", "
            + "{ {minLength}, {maxLength},getText(\"label.user.loginid\")})}")
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    // 必須
    @RequiredStringValidator(message = "${getText(\"errors.required.input\"," + " {getText(\"label.user.password\")})}")
    @StringLengthFieldValidator(minLength = "6", maxLength = "32", message = "${getText(\"errors.lengthrange\", "
            + "{ {minLength},{maxLength},getText(\"label.user.password\")})}")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getEmail() {
        return email;
    }

    // 必須
    @RequiredStringValidator(message = "${getText(\"errors.required.input\"," + " {getText(\"label.user.email\")})}")
    // 长度
    @StringLengthFieldValidator(maxLength = "60", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.email\")})}")
    @RegexFieldValidator(regexExpression = YiDuConstants.Regex.EMAIL, message = "${getText(\"errors.format.email\", {getText('label.user.email')})}")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "15", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.qq\")})}")
    // 数字检查
    @RegexFieldValidator(regexExpression = YiDuConstants.Regex.NUMBER, message = "${getText(\"errors.format.number\", {getText('label.user.qq')})}")
    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String getTempName() {
        return "register";
    }

    @SkipValidation
    @Override
    public String execute() {
        logger.info("RegisterAction execute has been excuted.");
        if (LoginManager.isLoginFlag()) {
            return REDIRECT;
        } else {
            // 记录访问地址
            LoginManager.setReferer();
            return FREEMARKER;
        }
    }

    /**
     * 用户注册
     * 
     * @return 显示页面
     */
    @Transactional
    public String register() {
        logger.info("RegisterAction register started.");
        // 密码检查
        if (!StringUtils.equals(password, repassword)) {
            addActionError(getText("errors.repassword"));
            return FREEMARKER;
        }

        userService.lockTUser();

        // 重复检查
        UserSearchBean searchBean = new UserSearchBean();
        searchBean.setLoginid(loginid);
        searchBean.setDeleteflag(false);
        List<TUser> userList = this.userService.find(searchBean);
        if (Utils.isDefined(userList)) {
           addActionError(this.getText("errors.duplicated", new String[] { this.getText("label.user.loginid") }));
            return FREEMARKER;
        }

        // 邮箱重复检查
        searchBean = new UserSearchBean();
        searchBean.setEmail(email);
        searchBean.setDeleteflag(false);
        userList = this.userService.find(searchBean);
        if (Utils.isDefined(userList)) {
            addActionError(this.getText("errors.duplicated", new String[] { this.getText("label.user.email") }));
            return FREEMARKER;
        }

        TUser user = new TUser();
        BeanUtils.copyProperties(this, user);
        user.setDeleteflag(false);
        user.setRegdate(new Date());
        user.setPassword(Utils.convert2MD5(password));
        user.setType(YiDuConstants.UserType.NORMAL_USER);
        user.setModifytime(new Date());

        // 是否开启邮箱验证
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_MAIL_AUTH, false)) {
            // 开启的话发邮件结束
            MailUtils.sendMail(email, getText("reg.mail.title", new String[] { getText("label.system.name") }),
                    creatContent(), false);
            addActionMessage(getText("message.mail.auth"));
            user.setMailtoken(Utils.convert2MD5(loginid + password + email));
            user.setActivedflag(false);
            // 注册用户登录
            this.userService.save(user);

            return FREEMARKER_MESSAGE;
        } else {

            user.setActivedflag(true);
            // 注册用户登录
            this.userService.save(user);
            // 未开启的话，直接登录
            LoginManager.doLogin(user);
            // 登录处理
            logger.debug("RegisterAction register normally end.");
            return REDIRECT;
        }
    }

    /**
     * 合成邮件内容
     * 
     * @return 邮件内容
     */
    private String creatContent() {
        StringBuffer sb = new StringBuffer();
        sb.append(getText(
                "reg.mail.content",
                new String[] { loginid, YiDuConstants.yiduConf.getString(YiDuConfig.URI),
                        Utils.convert2MD5(loginid + password + email), getText("label.system.name"),
                        YiDuConstants.yiduConf.getString(YiDuConfig.URI) }));
        sb.append("\n");
        sb.append(getText("reg.mail.content.footer"));

        return sb.toString();
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_REGEDIT;
    }

    @Override
    protected void loadData() {
    }
}