package org.yidu.novel.bean;

import org.yidu.novel.utils.Pagination;

/**
 * 
 * <p>
 * 检索条件基类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class BaseSearchBean {
    /**
     * 翻页信息
     */
    private Pagination pagination;

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
