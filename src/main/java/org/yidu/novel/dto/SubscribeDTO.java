package org.yidu.novel.dto;

import org.yidu.novel.entity.TArticle;

public class SubscribeDTO extends TArticle {

    private static final long serialVersionUID = -9120931969014388341L;

    private int subscribeno;

    private int userno;

    private String loginid;

    private String email;

    /**
     * 获取subscribeno
     * 
     * @return subscribeno
     */
    public int getSubscribeno() {
        return subscribeno;
    }

    /**
     * 
     * 设置subscribeno
     * 
     * 
     * @param subscribeno
     *            subscribeno
     */
    public void setSubscribeno(int subscribeno) {
        this.subscribeno = subscribeno;
    }

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

}
