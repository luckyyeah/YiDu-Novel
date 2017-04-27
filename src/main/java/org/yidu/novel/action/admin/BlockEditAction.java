package org.yidu.novel.action.admin;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.utils.LoginManager;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 
 * <p>
 * 区块编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "blockEdit")
@Result(name = AbstractBaseAction.REDIRECT, type = AbstractBaseAction.REDIRECT, location = BlockListAction.URL)
public class BlockEditAction extends AbstractAdminEditBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -94899397547008502L;

    private int blockno;
    private String blockname;
    private String blockid;

    /*
     * 10:aritcleList 20:custerartileList 20:html
     */

    private Integer category;
    private Short type;
    private String sortcol;
    private Boolean isasc;
    private Boolean isfinish;
    private Integer limitnum;
    private String content;

    /*
     * "1:articleList 2:info 3:contentList 4:reader 5:user page"
     */
    private Short target;

    public int getBlockno() {
        return this.blockno;
    }

    public void setBlockno(int blockno) {
        this.blockno = blockno;
    }

    public String getBlockid() {
        return blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public String getBlockname() {
        return this.blockname;
    }

    @Validations(
    // 必须
            requiredStrings = { @RequiredStringValidator(message = "${getText(\"errors.required.input\", "
                    + "{getText(\"label.admin.block.edit.blockname\")})}") })
    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 获取category
     * 
     * @return category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 
     * 设置category
     * 
     * 
     * @param category
     *            category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSortcol() {
        return this.sortcol;
    }

    public void setSortcol(String sortcol) {
        this.sortcol = sortcol;
    }

    public Boolean getIsasc() {
        return this.isasc == null ? false : isasc;
    }

    public void setIsasc(Boolean isasc) {
        this.isasc = isasc;
    }

    public Boolean getIsfinish() {
        return this.isfinish == null ? false : isfinish;
    }

    public void setIsfinish(Boolean isfinish) {
        this.isfinish = isfinish;
    }

    public Integer getLimitnum() {
        return this.limitnum;
    }

    public void setLimitnum(Integer limitnum) {
        this.limitnum = limitnum;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getTarget() {
        return this.target;
    }

    public void setTarget(Short target) {
        this.target = target;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category", "collectionProperties.block.type",
                "collectionProperties.block.target", "collectionProperties.block.sortCol",
                "collectionProperties.boolean" });
        // 编辑
        if (blockno != 0) {
            TSystemBlock systemBlock = systemBlockService.getByNo(blockno);
            BeanUtils.copyProperties(systemBlock, this);
        }
        logger.debug("loadData normally end.");
    }

    /**
     * 
     * <p>
     * 保存画面的内容
     * </p>
     * 
     * @return
     */
    public String save() {
        logger.debug("save start.");
        // 初始化下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category", "collectionProperties.block.type",
                "collectionProperties.block.target", "collectionProperties.block.sortCol",
                "collectionProperties.boolean" });
        TSystemBlock systemBlock = new TSystemBlock();
        if (blockno != 0) {
            systemBlock = systemBlockService.getByNo(blockno);
        } else {
            systemBlock.setDeleteflag(false);
        }
        BeanUtils.copyProperties(this, systemBlock);
        systemBlock.setModifytime(new Date());
        systemBlock.setModifyuserno(LoginManager.getLoginUser().getUserno());
        systemBlockService.save(systemBlock);
        logger.debug("save normally end.");
        return REDIRECT;
    }

}
