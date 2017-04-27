package org.yidu.novel.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 用户中心Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "center")
public class CenterAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 822196809678036074L;

    private TUser user;

    public TUser getUser() {
        return user;
    }

    @Override
    protected void loadData() {
        user = LoginManager.getLoginUser();
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_USER_CENTER;
    }

    @Override
    public String getTempName() {
        return "user/center";
    }
}
