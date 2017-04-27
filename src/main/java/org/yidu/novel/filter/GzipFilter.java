package org.yidu.novel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * Gzip过滤器
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class GzipFilter implements Filter {
    /**
     * 过滤器配置
     */
    private FilterConfig filterConfig = null;

    /**
     * 构造方法
     * 
     * @return 过滤器配置
     */
    protected final FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        if (!YiDuConstants.yiduConf.getBoolean(YiDuConfig.GZIP_EFFECTIVE, false)) {
            chain.doFilter(req, res);
        } else {
            if (req instanceof HttpServletRequest) {
                if (!((HttpServletRequest) req).getRequestURI().startsWith("/download")) {
                    // download以外的请求做Gzip处理
                    HttpServletRequest request = (HttpServletRequest) req;
                    HttpServletResponse response = (HttpServletResponse) res;
                    String ae = request.getHeader("accept-encoding");
                    if (ae != null && ae.indexOf("gzip") != -1) {
                        GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
                        chain.doFilter(req, wrappedResponse);
                        wrappedResponse.finishResponse();
                        return;
                    }
                }
            }
            chain.doFilter(req, res);
        }
    }
}
