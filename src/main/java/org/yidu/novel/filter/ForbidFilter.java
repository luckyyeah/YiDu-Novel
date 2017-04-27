package org.yidu.novel.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 屏蔽掉访问模版文件
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.5
 * @author shinpa.you
 */
public class ForbidFilter implements Filter {

    /**
     * logger
     */
    private static Log logger = LogFactory.getLog(ForbidFilter.class);

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI().toString();

        List<Object> forbidFiles = YiDuConstants.yiduConf.getList(YiDuConfig.FORBID_FILES);
        for (Object object : forbidFiles) {
            if (StringUtils.endsWith(uri, (String) object) && !StringUtils.endsWith(uri, "robots.txt")) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                // 模拟不存在 返回404
                httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
                logger.warn("the template file was been request :" + uri);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

}
