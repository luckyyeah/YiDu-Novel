package org.yidu.novel.bean;

import java.util.Date;

public class BookcaseSearchBean extends BaseSearchBean{

    private int caseno;
    private Integer articleno;
    private String articlename;
    private Integer category;
    private Integer userno;
    private String username;
    private Integer chapterid;
    private String chaptername;
    private Date lastvisit;
    private Date createtime;

    public int getCaseno() {
        return this.caseno;
    }

    public void setCaseno(int caseno) {
        this.caseno = caseno;
    }

    public Integer getArticleno() {
        return this.articleno;
    }

    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }

    public String getArticlename() {
        return this.articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getUserno() {
        return this.userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getChapterid() {
        return this.chapterid;
    }

    public void setChapterid(Integer chapterid) {
        this.chapterid = chapterid;
    }

    public String getChaptername() {
        return this.chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public Date getLastvisit() {
        return this.lastvisit;
    }

    public void setLastvisit(Date lastvisit) {
        this.lastvisit = lastvisit;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
