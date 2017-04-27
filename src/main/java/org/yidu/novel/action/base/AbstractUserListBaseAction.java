package org.yidu.novel.action.base;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Pagination;

/**
 * <p>
 * 用户列表页画面的基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public abstract class AbstractUserListBaseAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 3736781138346525060L;

    /**
     * 初始化Pagination，默认每页显示1件
     */
    protected Pagination pagination = new Pagination(YiDuConstants.yiduConf.getInt(YiDuConfig.COUNT_PER_PAGE, 20), 1);

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
