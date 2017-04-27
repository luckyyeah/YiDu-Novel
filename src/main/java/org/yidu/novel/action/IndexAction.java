package org.yidu.novel.action;

import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 首页Action
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class IndexAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5991997032217966675L;

    /**
     * 功能名称。
     */
    public static final String NAME = "index";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    @Override
    public String getTempName() {
        return "index";
    }

    @Override
    public void loadData() {
    }

    @Override
    protected String getBlockKey() {
        return CacheManager.CacheKeyPrefix.CACHE_KEY_INDEX_BLOCK;
    }

    @Override
    protected Short getBlockTarget() {
        return YiDuConstants.BlockTarget.INDEX;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_INDEX;
    }
}
