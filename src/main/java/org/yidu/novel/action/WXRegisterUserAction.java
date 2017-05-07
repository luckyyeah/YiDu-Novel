package org.yidu.novel.action;

import java.util.Date;
import java.util.Map;

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
public class WXRegisterUserAction extends AbstractPublicBaseAction {
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
    @Action(value = "wxregisteruser")
    public String execute() {
        logger.debug("execute normally end.");
        System.out.println("code="+code);
        Map accessTokenInfo= WeixinUtils.getAccessTokenByCode(code);
        String accessToken =null;
        String openid = null;
        String refreshToken = null;
        if(accessTokenInfo!=null){
         accessToken = (String) accessTokenInfo.get("access_token");
         openid = (String) accessTokenInfo.get("openid");
         refreshToken = (String) accessTokenInfo.get("refresh_token");
         accessToken= WeixinUtils.getRefreshAccessToken(refreshToken);
        }
       
       System.out.println("openid="+openid +" access_token=" +accessToken);
       
       Map userInfo= WeixinUtils.getUserInfo(accessToken, openid);
       if(openid==null){
    	   openid=" ";
       }
       String nickname =null;
       String headimgurl =null;
       String sex = null;
       String unionid = null;
       if(userInfo!=null){
	        nickname = (String) userInfo.get("nickname");
	        headimgurl = (String) userInfo.get("headimgurl");
	        sex = String.valueOf(userInfo.get("sex"));
	        unionid = (String) userInfo.get("unionid");
       }
       System.out.println("nickname=" +nickname);
       System.out.println("headimgurl=" +headimgurl);
       TUser user =userService.findByOpenid(openid);
       if(user==null){
    	     user = new TUser();
    	     String rand= Utils.getRandomString(0,999999,10);
    	     user.setOpenid(openid);
    	     user.setUsername(nickname);
/*    	     if(sex!=null){
    	    	 user.setSex(Short.parseShort(sex));
    	     }*/
    	     user.setHeadimgurl(headimgurl);
    	     user.setChargefee(0);
           user.setLoginid(openid);
           user.setDeleteflag(false);
           user.setRegdate(new Date());
           user.setLastlogin(new Date());
           user.setPassword(Utils.convert2MD5(openid));
           user.setType(YiDuConstants.UserType.NORMAL_USER);
           user.setActivedflag(true);
           user.setBranch("2");
    	     userService.save(user);
       } else{
    	    user.setUsername(nickname);
/*   	     if(sex!=null){
	    	   user.setSex(Short.parseShort(sex));
	       }*/
   	     user.setHeadimgurl(headimgurl);
   	     user.setBranch("3");
   	     userService.save(user);
       }
       // 正常登录
       LoginManager.doLogin(user);
       // 更新用户最后登录时间
       user.setLastlogin(new Date());
       Cookie cookie = CookieUtils.addUserCookie(user);
       // 添加cookie到response中
       ServletActionContext.getResponse().addCookie(cookie);
       System.out.println("register end" );
        return GOTO_REDIRECT;
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