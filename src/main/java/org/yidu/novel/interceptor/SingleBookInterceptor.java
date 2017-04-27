package org.yidu.novel.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * 
 * <p>
 * 手机判别用拦截器
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class SingleBookInterceptor extends AbstractInterceptor {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -8192961773909614182L;
    /**
     * 单本页面前缀
     */
    private static final String RESULT_CODE_SUFFIX_MOBILE = "single_";
    /**
     * logger
     */
    private final Log logger = LogFactory.getLog(SingleBookInterceptor.class);

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        logger.debug("SingleBookInterceptor start.");

        // 禁止单本功能
        if (!YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_SINGLE_BOOK, false)) {
            logger.debug("SingleBookInterceptor normally end.");
            return invocation.invoke();
        }

        invocation.addPreResultListener(new PreResultListener() {
            public void beforeResult(ActionInvocation invocation, String resultCode) {
                // 如果是单本的话，改变返回值
                if (YiDuConstants.singleBookFlag.get() != null && YiDuConstants.singleBookFlag.get()) {
                    invocation.setResultCode(RESULT_CODE_SUFFIX_MOBILE + resultCode);
                    return;
                }
            }
        });
        logger.debug("AuthCheckInterceptor normally end.");
        return invocation.invoke();
    }
}
