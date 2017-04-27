package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;

/**
 * <p>
 * 用户信息展示
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "userInfo")
public class UserInfoAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 8182483310788301445L;

    /**
     * 功能名称。
     */
    public static final String NAME = "userInfo";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    /**
     * 用户编号
     */
    private int userno;
    /**
     * 用户信息
     */
    private TUser user;

    /**
     * 获取 userno
     * 
     * @return userno
     */
    public int getUserno() {
        return userno;
    }

    /**
     * 
     * 设置userno
     * 
     * 
     * @param userno
     *            userno
     */
    public void setUserno(int userno) {
        this.userno = userno;
    }

    /**
     * 获取 user
     * 
     * @return user
     */
    public TUser getUser() {
        return user;
    }

    /**
     * 
     * 设置user
     * 
     * 
     * @param user
     *            user
     */
    public void setUser(TUser user) {
        this.user = user;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_USER_INFO;
    }

    @Override
    public String getTempName() {
        return "userInfo";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.user.sex", "collectionProperties.user.type" });
        // 编辑
        user = userService.getByNo(userno);
        logger.debug("loadData normally end.");
    }
}
