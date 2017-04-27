package org.yidu.novel.interceptor;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.action.base.AbstractAdminBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * <p>
 * 错误处理拦截器
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class ErrorInterceptor extends AbstractInterceptor {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 9085740143630189023L;
    /**
     * 未知错误的键
     */
    private static final String UNKNOWN_ERROR_KEY = "errors.unknown";
    /**
     * logger
     */
    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        try {
            String rtn = invocation.invoke();
            return rtn;
        } catch (Throwable th) {
            AbstractBaseAction action = (AbstractBaseAction) invocation.getAction();
            if (th instanceof IOException) {
                logger.debug("IOException occured! may the user stop downloading, do not care.");
                logger.error(th.getMessage(), th);
                return null;
            } else {
                logger.error(action, th);
                String errorMsg = action.getText(UNKNOWN_ERROR_KEY);
                action.addActionError(errorMsg);
            }
        }
        if (invocation.getAction() instanceof AbstractAdminBaseAction) {
            return AbstractBaseAction.ADMIN_ERROR;
        } else {
            return AbstractBaseAction.FREEMARKER_ERROR;
        }

    }
}
