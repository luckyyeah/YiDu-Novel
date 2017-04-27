package org.yidu.novel.action.base;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 公共画面的基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public abstract class AbstractPublicBaseAction extends AbstractPublicAndUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 4900892616460135567L;
    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "";

    /**
     * 获取回退URL
     * 
     * @return 回退URL
     */
    public String getBackUrl() {
        return LoginManager.getAndCleanReferer();
    }

    /**
     * 取得当前页的类型
     * 
     * <pre>
     * 1:主页
     * 2：小说列表
     * 3：小说介绍页
     * 4：小说阅读页
     * 11：登录页
     * 99：其他页
     * </pre>
     * 
     * @return 当前页的类型
     */
    public abstract int getPageType();

    /**
     * 取得表示用的模版名称
     * 
     * @return 模版名称
     */
    public abstract String getTempName();

    /**
     * 
     * <p>
     * 获取当前登录状态
     * </p>
     * 
     * @return 当前登录状态
     */
    public boolean getLoginFlag() {
        return LoginManager.isLoginFlag();
    }

    /**
     * 
     * <p>
     * 获取当前登录用户
     * </p>
     * 
     * @return 当前登录用户
     */
    public TUser getLoginUser() {
        return LoginManager.getLoginUser();
    }

    /**
     * 获取是否开启了章节列表页标识
     * 
     * @return 是否开启了章节列表页标识
     */
    public boolean getEnableChapterIndexPage() {
        return YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false);
    }

}
