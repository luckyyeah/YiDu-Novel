/*
 * Create Author  : huajie.duan
 * Create Date    : 2015-07-30
 * Project        : venus
 * File Name      : WeixinUtils.java
 *
 * Copyright (c) 2010-2015 by Shanghai XinChuDong Co., Ltd.
 * All rights reserved.
 *
 */
package org.yidu.novel.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;

/**
 * 功能描述:  <p>
 *
 * @author : huajie.duan <p>
 * @version 1.0 2015-07-30
 * @since venus 1.0
 */
public class WeixinUtils {
    private static Logger logger = Logger.getLogger(WeixinUtils.class);

    public static String getAccessTokenFromWeixin() throws Exception {
        String body = HttpRequest.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
                Const.WX_APP_ID + "&secret=" + Const.WX_APP_SECRET).body();
        Map result = JSON.parseObject(body, Map.class);
        String accessToken = (String) result.get("access_token");
        if (accessToken == null) {
            throw new Exception(result.toString());
        }
        return accessToken;
    }


    public static String nonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String buildOAuthUrl(String url) throws UnsupportedEncodingException {
        String encodedUrl = URLEncoder.encode(url, "UTF8");
        return new StringBuilder().append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
                .append(Const.WX_APP_ID).append("&redirect_uri=").append(encodedUrl)
                .append("&response_type=code&scope=snsapi_base&state=1#wechat_redirect").toString();
    }


    /**
     * Give body, parse result to `result` map
     *
     * @param body
     * @param result
     * @throws DocumentException
     */
    public static void parseWxMessage(String body, Map<String, String> result) throws DocumentException {
        Element root = DocumentHelper.parseText(body).getRootElement();
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element element = (Element) i.next();
            result.put(element.getName(), element.getText());
        }
    }

    /**
     * Warning: This function need to visit weixin websites
     *
     * @return
     */
    public static String getOpenIdByCode(String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", Const.WX_APP_ID);
        params.put("secret", Const.WX_APP_SECRET);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String body = HttpRequest.get("https://api.weixin.qq.com/sns/oauth2/access_token", params, true).body();

        Map result = JSON.parseObject(body, Map.class);
        String openId = (String) result.get("openid");
        if (openId == null) {
            logger.error(body);
        }
        return openId;
    }

}
