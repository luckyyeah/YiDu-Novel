package org.yidu.novel.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
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
@Action(value = "vip")
public class ChapterOrderAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -6064353669030314155L;
    /**
     * 功能名称。
     */
    public static final String NAME = "vip";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    private int chapterno;
    private Integer articleno;
    private String articlename;
    private String volumeid;
    private String chaptername;
    private String content;
    private Integer size;
    private Boolean isvip;
    private Date postdate;
    private Integer fee;
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

    public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
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
        }
        logger.debug("loadData normally end.");
    }



	@Override
	public int getPageType() {
		// TODO Auto-generated method stub
		return YiDuConstants.Pagetype.PAGE_BUY_VIP;
	}

	@Override
	public String getTempName() {
		// TODO Auto-generated method stub
		return this.NAME;
	}
    /**
     * 获取小说子目录
     * 
     * @return 小说子目录
     */
    @Deprecated
    public int getSubDir() {
        return articleno / YiDuConstants.SUB_DIR_ARTICLES;
    }  
}
