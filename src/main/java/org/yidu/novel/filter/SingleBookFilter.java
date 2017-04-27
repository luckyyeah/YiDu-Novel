package org.yidu.novel.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.yidu.novel.action.InfoAction;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.cache.SingleBookManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 实现泛解析，单本小说功能
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.5
 * @author shinpa.you
 */
public class SingleBookFilter implements Filter {

    /**
     * logger
     */
    private final Log logger = LogFactory.getLog(this.getClass());

    private static final String regex = "^/(\\d*\\.html)$";

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        YiDuConstants.singleBookFlag.set(false);
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if (StringUtils.endsWithAny(uri, "css", "js", "jpg", "png", "gif")) {
            // 静态资源直接跳过
            logger.debug("ignore static resource." + uri);
            chain.doFilter(request, response);
        } else {
            // 非静态资源时，根据具体的信息去做判别，时候需要标识单本
            String rootDomian = YiDuConstants.yiduConf.getString(YiDuConfig.ROOT_DOMAIN);
            String aname = StringUtils.substringBeforeLast(req.getServerName(), "." + rootDomian);
            logger.debug("aname : " + aname);
            if (StringUtils.isNotBlank(aname)) {
                int articleno = SingleBookManager.getArticleno(aname);
                if (articleno != 0) {
                    // TODO 即便找到对应的小说，也只做首页和阅读页，其他全部404吧
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(uri);
                    boolean matchFlag = false;
                    String newURI = "";
                    if (m.find()) {
                        // 阅读页
                        newURI = ReaderAction.URL + "?chapterno="
                                + StringUtils.substringBeforeLast(m.group(1), ".html");
                        matchFlag = true;
                    } else if (StringUtils.equals(uri, "/") || StringUtils.isEmpty(uri)) {
                        // 首页
                        newURI = InfoAction.URL + "?articleno=" + articleno;
                        matchFlag = true;
                    } else {
                        // 返回404
                        YiDuConstants.singleBookFlag.set(true);
                        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                    logger.debug("newURI str: " + newURI);
                    if (matchFlag) {
                        YiDuConstants.singleBookFlag.set(true);
                        YiDuConstants.singleBookServerName.set(req.getServerName());
                        req.getRequestDispatcher(newURI).forward(request, response);
                    }
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
