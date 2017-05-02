package org.yidu.novel.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;
import org.yidu.novel.utils.WeixinUtils;

/**
 * 
 * <p>
 * 检查用户登录状态
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class WXLoginUserAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5991997032217966675L;

    /**
     * 功能名称。
     */
    public static final String NAME = "wxlogin";


    
    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    /**
     * 跳转URL
     */
    private String forwardUrl;
    /**
     * 微信认证code
     */
    private String code;


    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	@SkipValidation
    @Override
    @Action(value = "getopenid")
    public String execute() {
        logger.debug("execute normally end.");
        System.out.println("code="+code);
       String openid= WeixinUtils.getOpenIdByCode(code);
       System.out.println("openid="+openid);
       if(openid==null){
    	   openid=" ";
       }
       TUser user =userService.findByOpenid(openid);
       if(user==null){
    	     user = new TUser();
    	     String rand= Utils.getRandomString(0,999999,10);
    	     user.setOpenid(openid);
    	     user.setChargefee(0);
           user.setLoginid("wx_"+rand);
           user.setDeleteflag(false);
           user.setRegdate(new Date());
           user.setLastlogin(new Date());
           user.setPassword(Utils.convert2MD5(openid));
           user.setType(YiDuConstants.UserType.NORMAL_USER);
           user.setActivedflag(true);
    	     userService.save(user);
       } 
       // 正常登录
       LoginManager.doLogin(user);
       // 更新用户最后登录时间
       user.setLastlogin(new Date());
       Cookie cookie = CookieUtils.addUserCookie(user);
       // 添加cookie到response中
       ServletActionContext.getResponse().addCookie(cookie);
		 
        return GO_TOP;
    }
	 
    /**
     * 获得用户信息
     * 
     * @return 用户信息
     */
    public TUser getData() {
        return LoginManager.getLoginUser();
    }

    @Override
    public void loadData() {
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @Override
    public String getTempName() {
        // TODO Auto-generated method stub
        return null;
    }
}