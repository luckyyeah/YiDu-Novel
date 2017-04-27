package org.yidu.novel.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.service.BookcaseService;
import org.yidu.novel.service.OrderService;
import org.yidu.novel.service.ChapterService;
import org.yidu.novel.service.MessageService;
import org.yidu.novel.service.ReviewService;
import org.yidu.novel.service.SubscribeService;
import org.yidu.novel.service.SystemBlockService;
import org.yidu.novel.service.UserService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;

/**
 * 
 * <p>
 * 易读系统画面基类<br>
 * 定义各种服务
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Results({
        @Result(name = AbstractBaseAction.FREEMARKER_ERROR, location = "/themes/${themeName}/pc/error.ftl", type = "freemarker"),
        @Result(name = AbstractBaseAction.FREEMARKER_MESSAGE, type = "freemarker", location = "/themes/${themeName}/pc/message.ftl"),
        @Result(name = AbstractBaseAction.FREEMARKER, type = "freemarker", location = "/themes/${themeName}/pc/${tempName}.ftl"),
        @Result(name = AbstractBaseAction.MOBILE_FREEMARKER_ERROR, location = "/themes/${themeName}/wap/error.ftl", type = "freemarker"),
        @Result(name = AbstractBaseAction.MOBILE_FREEMARKER_MESSAGE, type = "freemarker", location = "/themes/${themeName}/wap/message.ftl"),
        @Result(name = AbstractBaseAction.MOBILE_FREEMARKER, type = "freemarker", location = "/themes/${themeName}/wap/${tempName}.ftl"),
        @Result(name = AbstractBaseAction.SINGLE_FREEMARKER, type = "freemarker", location = "/themes/${themeName}/single/${tempName}.ftl"),
        @Result(name = AbstractBaseAction.SINGLE_FREEMARKER_ERROR, type = "freemarker", location = "/themes/${themeName}/single/error.ftl"),
        @Result(name = AbstractBaseAction.ADMIN_ERROR, location = "/WEB-INF/error.jsp", type = "dispatcher"),
        @Result(name = AbstractBaseAction.JSON_RESULT, type = "json"),
        @Result(name = AbstractBaseAction.GO_TOP, location = org.yidu.novel.action.IndexAction.URL, type = "redirect"),
        @Result(name = AbstractBaseAction.GOTO_LOGIN, location = org.yidu.novel.action.LoginAction.URL, type = "redirect"),
        @Result(name = AbstractBaseAction.GOTO_WX_LOGIN, location ="${forwardUrl}", type = "redirect"),
        @Result(name = AbstractBaseAction.REDIRECT, location = "${backUrl}", type = "redirect"),
        @Result(name = AbstractBaseAction.HTTPHEADER404, type = "httpheader", params = { "error", "404" }) })
public abstract class AbstractBaseAction extends ActionSupport implements ValidationWorkflowAware {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1L;
    /**
     * JSON结果类型
     */
    protected static final String JSON_RESULT = "json";
    /**
     * 返回主页结果类型
     */
    public static final String GO_TOP = "GOTO_Top";
    /**
     * 跳转登录页结果类型
     */
    public static final String GOTO_LOGIN = "GOTO_Login";
    /**
     * 跳转登录页结果类型
     */
    public static final String GOTO_WX_LOGIN = "GOTO_WX_Login";
    /**
     * 重定向结果类型
     */
    public static final String REDIRECT = "redirect";
    /**
     * 消息结果类型
     */
    public static final String MESSAGE = "message";
    /**
     * HTTP HEADER类型
     */
    public static final String HTTPHEADER404 = "httpheader404";
    /**
     * 管理系的错误结果类型
     */
    public static final String ADMIN_ERROR = "adminerror";
    /**
     * freemarker结果类型
     */
    public static final String FREEMARKER = "freemarker";
    /**
     * freemarker错误结果类型
     */
    public static final String FREEMARKER_ERROR = "freemarker_error";
    /**
     * freemarker消息结果类型
     */
    public static final String FREEMARKER_MESSAGE = "freemarker_message";
    /**
     * 手机freemarker结果类型
     */
    public static final String MOBILE_FREEMARKER = "mobile_freemarker";
    /**
     * 手机freemarker错误结果类型
     */
    public static final String MOBILE_FREEMARKER_ERROR = "mobile_freemarker_error";
    /**
     * 手机freemarker消息结果类型
     */
    public static final String MOBILE_FREEMARKER_MESSAGE = "mobile_freemarker_message";

    /**
     * 单本freemarker结果类型
     */
    public static final String SINGLE_FREEMARKER = "single_freemarker";

    /**
     * 单本freemarker错误结果类型
     */
    public static final String SINGLE_FREEMARKER_ERROR = "single_freemarker_error";

    /**
     * 输出log
     */
    protected Log logger = LogFactory.getLog(this.getClass());

    /**
     * 用户关联操作服务
     */
    protected UserService userService;

    /**
     * 小说关联操作服务
     */
    protected ArticleService articleService;
    /**
     * 章节关联操作服务
     */
    protected ChapterService chapterService;
    /**
     * 书签关联操作服务
     */
    protected BookcaseService bookcaseService;
    /**
     * 区块关联操作服务
     */
    protected SystemBlockService systemBlockService;
    /**
     * 消息关联操作服务
     */
    protected MessageService messageService;
    /**
     * 评论关联操作服务
     */
    protected ReviewService reviewService;

    /**
     * 订阅关联操作服务
     */
    protected SubscribeService subscribeService;

    /**
     * 章节购买服务
     */
    protected OrderService orderService;
    
    /**
     * 
     * 设置userService
     * 
     * 
     * @param userService
     *            userService
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 
     * 设置articleService
     * 
     * 
     * @param articleService
     *            articleService
     */
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 
     * 设置chapterService
     * 
     * 
     * @param chapterService
     *            chapterService
     */
    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    /**
     * 
     * 设置bookcaseService
     * 
     * 
     * @param bookcaseService
     *            bookcaseService
     */
    @Autowired
    public void setBookcaseService(BookcaseService bookcaseService) {
        this.bookcaseService = bookcaseService;
    }

    /**
     * 
     * 设置systemBlockService
     * 
     * 
     * @param systemBlockService
     *            systemBlockService
     */
    @Autowired
    public void setSystemBlockService(SystemBlockService systemBlockService) {
        this.systemBlockService = systemBlockService;
    }

    /**
     * 
     * 设置messageService
     * 
     * 
     * @param messageService
     *            messageService
     */
    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 
     * 设置reviewService
     * 
     * 
     * @param reviewService
     *            reviewService
     */
    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * 
     * 设置subscribeService
     * 
     * 
     * @param subscribeService
     *            subscribeService
     */
    @Autowired
    public void setSubscribeService(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
    public String getInputResultName() {
        return Action.INPUT;
    }

    /**
     * 获得系统相对路径
     * 
     * @return 系统相对路径
     */
    public String getContextPath() {
        return ServletActionContext.getServletContext().getContextPath();
    }

    /**
     * 获得系统相对路径
     * 
     * @return 系统相对路径
     */
    public String getRequestUrl() {
        return ServletActionContext.getRequest().getRequestURL().toString();
    }

    /**
     * 回退URL
     */
    private String backUrl;

    /**
     * 获取回退URL
     * 
     * @return 回退URL
     */
    public String getBackUrl() {
        return backUrl;
    }

    /**
     * 设置回退URL
     * 
     * @param backUrl
     *            回退URL
     */
    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    /**
     * 回退处理
     * 
     * @return 回退字符串
     * @throws Exception
     *             异常
     */
    @SkipValidation
    public String back() throws Exception {
        return REDIRECT;
    }

    /**
     * 配置属性集合
     */
    protected Map<String, LinkedMap> collections = new HashMap<String, LinkedMap>();

    /**
     * 获取集合
     * 
     * @return 获取集合
     */

    public Map<String, LinkedMap> getCollections() {

        return collections;
    }

    /**
     * 从property中读取内容转成Map
     * 
     * @param keys
     *            property里的键
     */
    public void initCollections(String[] keys) {
        for (String key : keys) {
            LinkedMap pulldown = new LinkedMap();
            String value = getText(key);
            String[] items = value.split(",");
            for (String item : items) {
                String[] property = item.split(":");
                if (property.length == 2) {
                    pulldown.put(property[0], property[1]);
                }
            }
            collections.put(key, pulldown);
        }
    }
    public List<Map<String,String>> getPropList(String[] keys) {
    	List<Map<String,String>>  propList = new ArrayList<Map<String,String>>(); 
        for (String key : keys) {
            LinkedMap pulldown = new LinkedMap();
            String value = getText(key);
            String[] items = value.split(",");
            for (String item : items) {
                String[] property = item.split(":");
                Map<String,String> mapProp =new HashMap<String,String>();
                if (property.length == 2) {
                	mapProp.put(property[0], property[1]);
                	propList.add(mapProp);
                }
            }
        }
        return propList;
    }
}
