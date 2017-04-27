package org.yidu.novel.action.base;

import org.yidu.novel.bean.ResponseBean;

/**
 * <p>
 * JSON处理的基类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public abstract class JsonBaseAction extends AbstractBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回数据
     */
    protected ResponseBean<?> res;

    @Override
    public String execute() {
        res = loadJsonData();
        return JSON_RESULT;
    }

    /**
     * 加载Json数据
     * 
     * @return Json数据
     */
    protected abstract ResponseBean<?> loadJsonData();

    /**
     * 取得返回数据
     * 
     * @return 返回数据
     */
    public ResponseBean<?> getData() {
        return res;
    }

}
