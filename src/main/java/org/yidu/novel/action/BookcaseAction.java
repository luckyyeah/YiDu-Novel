package org.yidu.novel.action;

import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 书架管理Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class BookcaseAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 366181195078436796L;

    /**
     * 获得模版名字
     * 
     * @return 模版名字
     */
    @Override
    public String getTempName() {
        return "bookcase";
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_BOOKCASE;
    }

    @Override
    protected void loadData() {
    }
}
