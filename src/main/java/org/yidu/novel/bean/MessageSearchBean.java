package org.yidu.novel.bean;

public class MessageSearchBean extends BaseSearchBean {

    private Integer userno;
    private String loginid;
    private Integer touserno;
    private String tologinid;

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public Integer getTouserno() {
        return touserno;
    }

    public void setTouserno(Integer touserno) {
        this.touserno = touserno;
    }

    public String getTologinid() {
        return tologinid;
    }

    public void setTologinid(String tologinid) {
        this.tologinid = tologinid;
    }

}
