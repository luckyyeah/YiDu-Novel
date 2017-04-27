package org.yidu.novel.action.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;
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
public class ChapterEditAction extends AbstractUserBaseAction {
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
    private Date publishtime;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    /**
     * 获取articleno
     * 
     * @return articleno
     */
    public Integer getArticleno() {
        return articleno;
    }

    /**
     * 
     * 设置articleno
     * 
     * 
     * @param articleno
     *            articleno
     */
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
        return isvip ? 2 : 1;
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

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getPublishtimeStr() {
        if (Utils.isDefined(publishtime)) {
            return sdf.format(publishtime);
        } else {
            return "";
        }
    }

    public void setPublishtimeStr(String publishtimeStr) {
        if (StringUtils.isNotEmpty(publishtimeStr)) {
            try {
                this.publishtime = sdf.parse(publishtimeStr);
            } catch (ParseException e) {
                this.addFieldError(publishtimeStr, getText("errors.format.date"));
            }
        }
    }

    @Override
    public String getBackUrl() {
        return ChapterListAction.URL + "?articleno=" + articleno;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化种别下拉列表选项
        initCollections(new String[] { "collectionProperties.chapter.isvip" });
        // 编辑
        if (chapterno != 0) {
            TChapter chapter = chapterService.getByNo(chapterno);
            if (Utils.isDefined(chapter)) {

                TArticle article = articleService.getByNo(chapter.getArticleno());

                if (article == null) {
                    addActionError(getText("errors.not.exsits.article"));
                    return;
                }
                if (!checkRight(article)) {
                    addActionError(getText("errors.right"));
                    return;
                }

                BeanUtils.copyProperties(chapter, this);
                content = Utils.getContext(chapter, false);
            } else {
                addActionError(getText("errors.not.exsits.chapter"));
            }
        }
        logger.debug("loadData normally end.");
    }

    public String save() {
        logger.debug("save start.");
        // 初始化种别下拉列表选项
        initCollections(new String[] { "collectionProperties.chapter.isvip" });

        TChapter chapter = new TChapter();
        TArticle article = articleService.getByNo(articleno);

        if (article == null) {
            addActionError(getText("errors.not.exsits.article"));
            return FREEMARKER_ERROR;
        }
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

        if (!checkRight(article)) {
            addActionError(getText("errors.right"));
            return FREEMARKER_ERROR;
        }

        BeanUtils.copyProperties(this, chapter, new String[] { "articleno", "articlename", "postdate" });
        chapter.setSize(StringUtils.length(content));

        chapter.setModifytime(new Date());
        chapter.setModifyuserno(LoginManager.getLoginUser().getUserno());

        chapterService.save(chapter);

        // TODO 更新小说字数，最新章节等信息

        try {
            Utils.saveContext(articleno, chapterno, content);
        } catch (Exception e) {
            addActionError(e.getMessage());
            return FREEMARKER_ERROR;
        }
        logger.debug("save normally end.");
        return REDIRECT;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_AUTHER_CHAPTER_EDIT;
    }

    @Override
    public String getTempName() {
        return "user/chapterEdit";
    }
}
