package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 用户列表Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "userList")
public class UserListAction extends AbstractAdminListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 8182483310788301445L;

    /**
     * 功能名称。
     */
    public static final String NAME = "userList";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    private int userno;

    private List<TUser> userList = new ArrayList<TUser>();

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public List<TUser> getUserList() {
        return userList;
    }

    public void setUserList(List<TUser> userList) {
        this.userList = userList;
    }

    @Override
    protected void loadData() {
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.user.sex", "collectionProperties.user.type" });
        UserSearchBean searchBean = new UserSearchBean();
        if (StringUtils.isEmpty(pagination.getSortColumn())) {
            pagination.setSortColumn("userno");
        }
        searchBean.setPagination(pagination);
        // 总件数设置
        pagination.setPreperties(userService.getCount(searchBean));
        userList = userService.find(searchBean);
        // Setting number of records in the particular page
        pagination.setPageRecords(userList.size());
    }

    public String delete() throws Exception {
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.user.sex", "collectionProperties.user.type" });

        TUser user = userService.getByNo(userno);
        user.setDeleteflag(true);
        user.setModifyuserno(LoginManager.getLoginUser().getUserno());
        user.setModifytime(new Date());
        userService.save(user);

        loadData();
        return INPUT;
    }

}
