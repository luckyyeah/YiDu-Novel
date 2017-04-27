package org.yidu.novel.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 手机搜索Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "mobileSearch")
public class MobileSearchAction extends AbstractPublicListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4215796997609788238L;
    /**
     * 检索关键字
     */
    private String key;

    /**
     * 获取 key
     * 
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * 
     * 设置key
     * 
     * 
     * @param key
     *            key
     */
    public void setKey(String key) {
        this.key = StringUtils.trim(key);
    }

    @Override
    public String getTempName() {
        return "search";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        logger.debug("normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_SEARCH;
    }

}
