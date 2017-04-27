package org.yidu.novel.bean;

import java.util.Date;

public class UserSearchBean extends BaseSearchBean {
    /**
     * 用户ID
     */
    private int userno;
    /**
     * 登录ID
     */
    private String loginid;
    /**
     * 登录密码
     */
    private String password;

    /**
     * 邮箱验证码
     */
    private String mailtoken;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮件地址
     */
    private String email;
    /**
     * 性别
     */
    private int sex;
    /**
     * 删除标识
     */
    private Boolean deleteflag;
    /**
     * 激活标识
     */
    private Boolean activedflag;

    private Date modifytime;

    private Date regdate;

    /**
     * 获取userno
     * 
     * @return userno
     */
    public int getUserno() {
        return userno;
    }

    /**
     * 
     * 设置userno
     * 
     * 
     * @param userno
     *            userno
     */
    public void setUserno(int userno) {
        this.userno = userno;
    }

    /**
     * 获取loginid
     * 
     * @return loginid
     */
    public String getLoginid() {
        return loginid;
    }

    /**
     * 
     * 设置loginid
     * 
     * 
     * @param loginid
     *            loginid
     */
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    /**
     * 获取password
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * 设置password
     * 
     * 
     * @param password
     *            password
     */
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

    /**
     * 获取username
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * 设置username
     * 
     * 
     * @param username
     *            username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取email
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * 设置email
     * 
     * 
     * @param email
     *            email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取sex
     * 
     * @return sex
     */
    public int getSex() {
        return sex;
    }

    /**
     * 
     * 设置sex
     * 
     * 
     * @param sex
     *            sex
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * 获取deleteflag
     * 
     * @return deleteflag
     */
    public Boolean getDeleteflag() {
        return deleteflag;
    }

    /**
     * 
     * 设置deleteflag
     * 
     * 
     * @param deleteflag
     *            deleteflag
     */
    public void setDeleteflag(Boolean deleteflag) {
        this.deleteflag = deleteflag;
    }

    /**
     * 获取activedflag
     * 
     * @return activedflag
     */
    public Boolean getActivedflag() {
        return activedflag;
    }

    /**
     * 
     * 设置activedflag
     * 
     * 
     * @param activedflag
     *            activedflag
     */
    public void setActivedflag(Boolean activedflag) {
        this.activedflag = activedflag;
    }

    /**
     * 获取modifytime
     * 
     * @return modifytime
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 
     * 设置modifytime
     * 
     * 
     * @param modifytime
     *            modifytime
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 获取regdate
     * 
     * @return regdate
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * 
     * 设置regdate
     * 
     * 
     * @param regdate
     *            regdate
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

}
