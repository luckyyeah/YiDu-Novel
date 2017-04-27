package org.yidu.novel.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

/**
 * <p>
 * 用户编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "useredit")
public class UserEditAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 8182483310788301445L;

    /**
     * 功能名称。
     */
    public static final String NAME = "useredit";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    private String loginid;
    private String password;
    private String repassword;
    private String email;
    private String qq;

    private String username;
    private String realname;
    private Short sex;
    private String id;
    private String mobileno;
    private String branch;
    private String bankno;
    private String alipayacount;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

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

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
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

    public String getUsername() {
        return username;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "50", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.username\")})}")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "10", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.realname\")})}")
    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getId() {
        return id;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "18", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.id\")})}")
    public void setId(String id) {
        this.id = id;
    }

    public String getMobileno() {
        return mobileno;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "11", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.mobileno\")})}")
    @RegexFieldValidator(regexExpression = YiDuConstants.Regex.NUMBER, message = "${getText(\"errors.format.number\", {getText('label.user.mobileno')})}")
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getBranch() {
        return branch;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "50", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.branch\")})}")
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBankno() {
        return bankno;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "20", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.bankno\")})}")
    @RegexFieldValidator(regexExpression = YiDuConstants.Regex.NUMBER, message = "${getText(\"errors.format.number\", {getText('label.user.bankno')})}")
    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getAlipayacount() {
        return alipayacount;
    }

    // 长度检查
    @StringLengthFieldValidator(maxLength = "50", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.alipayacount\")})}")
    public void setAlipayacount(String alipayacount) {
        this.alipayacount = alipayacount;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_USER_USEREDIT;
    }

    @Override
    public String getTempName() {
        return "user/useredit";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        int userno = LoginManager.getLoginUser().getUserno();
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.user.sex", "collectionProperties.user.type" });
        // 编辑
        TUser user = userService.getByNo(userno);
        BeanUtils.copyProperties(user, this);

        logger.debug("loadData normally end.");
    }

    /**
     * <p>
     * 保存画面的内容
     * </p>
     * 
     * @return
     * @throws Exception
     */
    public String save() {
        logger.debug("save start.");
        // 密码检查
        if (!StringUtils.equals(password, repassword)) {
            addActionError(getText("errors.repassword"));
            return FREEMARKER;
        }
        TUser user = new TUser();
        int userno = LoginManager.getLoginUser().getUserno();
        user = userService.getByNo(userno);
        // 构造忽略项目，作者信息一旦填写将不能更改，作者相关信息不为空的话，从更新项目里排除掉
        List<String> ignoreProperties = new ArrayList<String>();
        ignoreProperties.add("loginid");
        ignoreProperties.add("regdate");
        ignoreProperties.add("lastlogin");

        // 密码，没添的话默认不修改
        if (StringUtils.isEmpty(password)) {
            ignoreProperties.add("password");
        }
        // 真是姓名
        if (StringUtils.isNotEmpty(user.getRealname())) {
            ignoreProperties.add("realname");
        }
        // 笔名
        if (StringUtils.isNotEmpty(user.getUsername())) {
            ignoreProperties.add("username");
        }
        // 性别
        if (Utils.isDefined(user.getSex())) {
            ignoreProperties.add("sex");
        }
        // 身份证号
        if (StringUtils.isNotEmpty(user.getId())) {
            ignoreProperties.add("id");
        }
        // 开户行
        if (StringUtils.isNotEmpty(user.getBranch())) {
            ignoreProperties.add("branch");
        }
        // 银行帐号
        if (StringUtils.isNotEmpty(user.getBankno())) {
            ignoreProperties.add("bankno");
        }
        // 支付宝帐号
        if (StringUtils.isNotEmpty(user.getAlipayacount())) {
            ignoreProperties.add("alipayacount");
        }
        BeanUtils.copyProperties(this, user, ignoreProperties.toArray(new String[] {}));
        // 密码，没添的话默认不修改
        if (StringUtils.isNotEmpty(password)) {
            user.setPassword(Utils.convert2MD5(password));
        }
        user.setModifytime(new Date());
        user.setModifyuserno(LoginManager.getLoginUser().getUserno());

        userService.save(user);
        addActionMessage(getText("messages.save.success"));
        loadData();
        logger.debug("save normally end.");
        return FREEMARKER;
    }
}
