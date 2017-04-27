package org.yidu.novel.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.base.AbstractAdminBaseAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;

/**
 * 
 * <p>
 * 语言设定拦截器
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class SetLanguageInterceptor extends AbstractInterceptor {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 7671199059328089342L;
    /**
     * logger
     */
    private final Log logger = LogFactory.getLog(this.getClass());
    /**
     * 管理画面的LOCALE
     */
    public static final String ADMIN_PAGE_LOCALE = "WW_TRANS_I18N_LOCALE";

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);

        Locale locale = (Locale) session.getAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE);
        // Locale adminpagelocale = (Locale)
        // session.getAttribute(ADMIN_PAGE_LOCALE);
        // if (locale == null && adminpagelocale == null) {
        if (locale == null) {
            if (request.getLocale().equals(Locale.CHINA) || request.getLocale().equals(Locale.CHINESE)) {
                session.setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, Locale.CHINA);
                logger.debug("Language is set to Chinese. from IP <" + request.getRemoteAddr() + ">");
            } else if (request.getLocale().equals(Locale.JAPAN) || request.getLocale().equals(Locale.JAPANESE)) {
                session.setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, Locale.JAPAN);
                logger.debug("Language is set to Japanese. from IP <" + request.getRemoteAddr() + ">");
            } else {
                session.setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, Locale.US);
                logger.debug("Language is set to English.from IP <" + request.getRemoteAddr() + ">");
            }
        }
        // else if (adminpagelocale != null) {
        // session.setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE,
        // adminpagelocale);
        // }
        if (invocation.getAction() instanceof AbstractAdminBaseAction
                && (locale == null || !locale.equals(Locale.CHINA))) {
            // 使用中文界面
            // session.setAttribute(ADMIN_PAGE_LOCALE,
            // session.getAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE));
            session.setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, Locale.CHINA);
            logger.debug("because access page is admin page. Language is set to Chinese. from IP <"
                    + request.getRemoteAddr() + ">");
        }
        return invocation.invoke();
    }
}
