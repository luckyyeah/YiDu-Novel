package org.yidu.novel.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.service.UserService;

/**
 * 
 * <p>
 * cookie的增加、删除、查询工具类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class CookieUtils {
    /**
     * logger
     */
    protected static Log logger = LogFactory.getLog(CookieUtils.class);
    /**
     * 用户的cookie名
     */
    public static final String USER_COOKIE = "user.cookie";
    /**
     * 阅读履历的cookie名
     */
    public static final String READ_HISTORY_COOKIE = "read.history.cookie";

    /**
     * 添加用户信息到Cookie
     * 
     * @param user
     *            用户信息
     * @return Cookie信息
     */

    public static Cookie addUserCookie(TUser user) {
        try {
            Cookie cookie = new Cookie(USER_COOKIE, URLEncoder.encode(user.getLoginid(), YiDuConstants.ENCODING_UTF_8)
                    + "," + user.getPassword() + "," + user.getType() + "," + StringUtils.isNotBlank(user.getOpenid()));
            // 默认保持两年cookie保存两周
            cookie.setMaxAge(60 * 60 * 24 * 365);
            return cookie;
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * 使用cookie信息登录
     * 
     * @param request
     *            HttpServletRequest
     * @param userService
     *            用户服务
     */
    public static void getUserCookieAndLogoin(HttpServletRequest request, UserService userService) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // 遍历cookie查找用户信息
            for (Cookie cookie : cookies) {
                if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {
                    // 使用cookie内的用户信息登录
                    String value = cookie.getValue();
                    if (StringUtils.isNotBlank(value)) {
                        String[] split = value.split(",");
                        if (split.length >= 2) {
                            TUser user = userService.findByLoginInfoByJDBC(split[0], split[1]);
                            if (user != null) {
                                LoginManager.doLogin(user);
                                // 更新用户最后登录时间
                                userService.updateLastLoginDate(user.getUserno(), new Date());
                            } else {
                                // 用户信息不存在的话，把用户cookie清掉
                                delUserCookie(request);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 删除cookie
     * 
     * @param request
     *            HttpServletRequest
     * @return Cookie信息
     */
    public static Cookie delUserCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (USER_COOKIE.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 把指定小说编号存入Cookie
     * 
     * @param articlenos
     *            小说编号
     * @return Cookie信息
     */
    public static Cookie addHistoryCookie(String articlenos) {
        Cookie cookie = new Cookie(READ_HISTORY_COOKIE, articlenos);
        // cookie保存1年
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setPath("/");
        return cookie;
    }

    // 得到cookie
    /**
     * 从HTTPRequest中取出阅读履历字符串
     * 
     * @param request
     *            HttpServletRequest
     * @return 阅读履历字符串
     */
    public static String getHistoryCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieUtils.READ_HISTORY_COOKIE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
