package org.yidu.novel.action.base;

import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 用户画面的基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public abstract class AbstractUserBaseAction extends AbstractPublicAndUserBaseAction {

    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 4900892616460135567L;
    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "/user";

    /**
     * 获得页面类型
     * 
     * @return 页面类型
     */
    public abstract int getPageType();

    /**
     * 获得模版名字
     * 
     * @return 模版名字
     */
    public abstract String getTempName();

    /**
     * 获得登录用户信息
     * 
     * @return 登录用户信息
     */
    public TUser getUser() {
        return LoginManager.getLoginUser();
    }

    /**
     * 检查小说权限
     * 
     * @param article
     *            小说信息
     * 
     * @return 是否有小说的操作权限
     */
    protected boolean checkRight(TArticle article) {
        boolean hasRihgtFlag = false;
        TUser user = LoginManager.getLoginUser();
        // 作者
        if (user.getType() == YiDuConstants.UserType.AUTHER && article.getAuthorid() == user.getUserno()) {
            hasRihgtFlag = true;
        }
        // TODO 编辑
        if (user.getType() == YiDuConstants.UserType.EDITOR && article.getCategory() == user.getUserno()) {
            hasRihgtFlag = true;
        }
        return hasRihgtFlag;
    }

}
