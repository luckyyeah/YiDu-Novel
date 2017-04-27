package org.yidu.novel.action.admin;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 章节编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "chapterEdit")
@Result(name = AbstractBaseAction.REDIRECT, type = AbstractBaseAction.REDIRECT, location = ChapterListAction.URL
        + "?articleno=${articleno}")
public class ChapterEditAction extends AbstractAdminEditBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -6064353669030314155L;

    private int chapterno;
    private Integer articleno;
    private String articlename;
    private String volumeid;
    private String chaptername;
    private String content;
    private Integer size;
    private Boolean isvip;
    private Date postdate;

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public Integer getArticleno() {
        return articleno;
    }

    public void setArticleno(Integer articleno) {
        this.articleno = articleno;
    }

    public void setArticleno(String articleno) {
        this.articleno = Integer.parseInt(articleno);
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getVolumeid() {
        return volumeid;
    }

    public void setVolumeid(String volumeid) {
        this.volumeid = volumeid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public int getVip() {
        return isvip != null && isvip ? 2 : 1;
    }

    public void setVip(int vip) {
        this.isvip = vip == 2;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化种别下拉列表选项
        initCollections(new String[] { "collectionProperties.chapter.isvip" });
        if (chapterno == 0 && articleno == 0) {
            addActionError(getText("errors.unknown"));
            return;
        }

        // 编辑
        if (chapterno != 0) {
            TChapter chapter = chapterService.getByNo(chapterno);
            BeanUtils.copyProperties(chapter, this);
            content = Utils.getContext(chapter, false);
        } else {
            // 追加的话取小说信息
            TArticle article = articleService.getByNo(articleno);
            BeanUtils.copyProperties(article, this);
        }
        logger.debug("loadData normally end.");
    }

    public String save() {
        logger.debug("save start.");
        // 初始化种别下拉列表选项
        initCollections(new String[] { "collectionProperties.chapter.isvip" });

        TChapter chapter = new TChapter();
        TArticle article = articleService.getByNo(articleno);
        if (chapterno != 0) {
            // 获取章节信息
            chapter = chapterService.getByNo(chapterno);
        } else {
            // 获取小说信息
            chapter.setArticleno(articleno);
            chapter.setArticlename(article.getArticlename());
            chapter.setChaptertype((short) 0);
            chapter.setDeleteflag(false);
            chapter.setPostdate(new Date());
        }
        BeanUtils.copyProperties(this, chapter, new String[] { "articleno", "articlename" });
        chapter.setSize(StringUtils.length(content));
        chapter.setPostdate(new Date());
        chapter.setModifytime(new Date());
        chapter.setModifyuserno(LoginManager.getLoginUser().getUserno());
        chapterService.save(chapter);

        try {
            Utils.saveContext(articleno, chapter.getChapterno(), content);
        } catch (Exception e) {
            addActionError(e.getMessage());
            return INPUT;
        }
        // 最新章节等信息
        if (chapterno == 0) {
            // 更新
            article.setLastchapterno(chapter.getChapterno());
            article.setLastchapter(chapter.getChaptername());
            article.setLastupdate(new Date());
        }
        // 更新小说字数
        article.setSize(this.chapterService.getChapterCount(articleno));

        article.setModifytime(new Date());
        article.setModifyuserno(LoginManager.getLoginUser().getUserno());

        this.articleService.save(article);

        logger.debug("save normally end.");
        return REDIRECT;
    }
}
