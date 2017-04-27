package org.yidu.novel.action.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractUserListBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 章节列表Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "chapterList")
public class ChapterListAction extends AbstractUserListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 4594082209171084359L;

    /**
     * 功能名称。
     */
    public static final String NAME = "chapterList";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 小说号
     */
    private int articleno;

    /**
     * 章节号
     */
    private int chapterno;

    private TArticle article = new TArticle();

    private List<TChapter> chapterList = new ArrayList<TChapter>();

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public TArticle getArticle() {
        return article;
    }

    public void setArticle(TArticle article) {
        this.article = article;
    }

    public List<TChapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<TChapter> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    public String getBackUrl() {
        return URL;
    }

    @Override
    protected void loadData() {
        article = articleService.getByNo(articleno);
        if (!checkRight(article)) {
            addActionError(getText("errors.right"));
            return;
        }
        ChapterSearchBean searchBean = new ChapterSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        chapterList = chapterService.find(searchBean);

    }

    public String delete() throws Exception {
        TChapter chapter = chapterService.getByNo(chapterno);
        if (Utils.isDefined(chapter)) {

            articleno = chapter.getArticleno();
            TArticle article = articleService.getByNo(chapter.getArticleno());
            if (!checkRight(article)) {
                addActionError(getText("errors.right"));
                return FREEMARKER_ERROR;
            }
            // 更新删除标志
            chapter.setDeleteflag(true);
            // 保存
            chapterService.save(chapter);
        }

        return REDIRECT;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_AUTHER_CHAPTER_LIST;
    }

    @Override
    public String getTempName() {
        return "user/chapterList";
    }

}
