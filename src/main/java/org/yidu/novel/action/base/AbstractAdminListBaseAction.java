package org.yidu.novel.action.base;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Pagination;

/**
 * 
 * <p>
 * 管理画面的列表类处理的基类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public abstract class AbstractAdminListBaseAction extends AbstractAdminBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 5250455993870220163L;

    /**
     * 初始化Pagination，默认每页显示1件
     */
    protected Pagination pagination = new Pagination(YiDuConstants.yiduConf.getInt(YiDuConfig.NUMBER_PER_PAGE), 1);

    /**
     * 获取 pagination
     * 
     * @return pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 
     * 设置pagination
     * 
     * 
     * @param pagination
     *            pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
