package org.yidu.novel.bean;

public class ReviewSearchBean extends BaseSearchBean {

    private int reviweno;
    private int articleno;
    private int userno;
    private String articlename;
    private String loginid;
    private String chaptername;

    public int getReviweno() {
        return reviweno;
    }

    public void setReviweno(int reviweno) {
        this.reviweno = reviweno;
    }

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

}
