package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.bean.SubscribeSearchBean;
import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.cache.ArticleCountManager;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.JsonInfoDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TBookcase;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.entity.TChapterOrder;
import org.yidu.novel.entity.TSubscribe;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 手机画面用各种ajax接口
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "ajaxService")
public class AjaxServiceAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5966399886620363535L;

    /**
     * 
     * <p>
     * 处理类型定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    class ProccessType {
        /**
         * <p>
         * 私有构造方法
         * </p>
         */
        private ProccessType() {
            // do nothing
        }

        public static final String TOP_LIST = "toplist";
        public static final String CATEGORY_LIST = "categorylist";
        public static final String CHAPTER_LIST = "chapterlist";
        public static final String LOGIN = "login";
        public static final String CHANGE_PASSWORD = "changepassword";
        public static final String BOOKCASEIS_EXISTS = "bookcaseisexists";
        public static final String BOOKCASE = "bookcase";
        public static final String DELETE_BOOKCASE = "deletebookcase";
        public static final String ADD_BOOKCASE = "addbookcase";
        public static final String DELETE_BOOKCASE_BY_ARTICLE = "deletebookcasebyarticle";
        public static final String HISTORY = "history";
        public static final String SEARCH = "search";
        public static final String REGISTER = "register";
        public static final String VOTE = "vote";
        public static final String SUBSCRIBE = "subscribe";
        public static final String BUY_CHAPTER = "buychapter";
    }

    /**
     * 
     * <p>
     * 返回代码定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.6
     * @author shinpa.you
     */
    class ReturnCode {
        /**
         * <p>
         * 私有构造方法
         * </p>
         */
        private ReturnCode() {
            // do nothing
        }

        /**
         * 返回值：正常
         */
        public static final int SUCCESS = 0;
        /**
         * 返回值：出错
         */
        public static final int FAILED = 1;
    }

    /**
     * 处理名字
     */
    private String action;
    /**
     * 登录ID
     */
    private String loginid;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 小说分类
     */
    private int category;
    /**
     * 小说编号
     */
    private int articleno;
    /**
     * 小说编号
     */
    private int chapterno;
    private int index;
    /**
     * 排序
     */
    private int sort;
    /**
     * 小说字数
     */
    private int size;
    /**
     * 类型
     */
    private int type;
    /**
     * 用户编号
     */
    private int userno;
    /**
     * 书签编号
     */
    private String bookcasenos;
    /**
     * 搜索键
     */
    private String key;
    /**
     * 长度
     */
    private int length;
    /**
     * 状态
     */
    private int status;
    
    /**
     * 状态
     */
    private int fee;
    
    /**
     * JSON数据的DTO
     */
    private JsonInfoDTO dto = new JsonInfoDTO();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    /**
     * 获取chapterno
     * 
     * @return chapterno
     */
    public int getChapterno() {
        return chapterno;
    }

    /**
     * 
     * 设置chapterno
     * 
     * 
     * @param chapterno
     *            chapterno
     */
    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public String getBookcasenos() {
        return bookcasenos;
    }

    public void setBookcasenos(String bookcasenos) {
        this.bookcasenos = bookcasenos;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public JsonInfoDTO getData() {
        return dto;
    }

    
    public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	@Override
    public String execute() {

        logger.debug("execute started. ");
        if (StringUtils.equals(action, ProccessType.CATEGORY_LIST)) {
            getCategoryList();
        } else if (StringUtils.equals(action, ProccessType.CHANGE_PASSWORD)) {
            dochangepass();
        } else if (StringUtils.equals(action, ProccessType.CHAPTER_LIST)) {
            getChapterList();
        } else if (StringUtils.equals(action, ProccessType.LOGIN)) {
            dologin();
        } else if (StringUtils.equals(action, ProccessType.TOP_LIST)) {
            getTopList();
        } else if (StringUtils.equals(action, ProccessType.BOOKCASE)) {
            getBookcase();
        } else if (StringUtils.equals(action, ProccessType.DELETE_BOOKCASE)) {
            deleteBookcase();
        } else if (StringUtils.equals(action, ProccessType.HISTORY)) {
            getHistory();
        } else if (StringUtils.equals(action, ProccessType.BOOKCASEIS_EXISTS)) {
            checkBookCaseExists();
        } else if (StringUtils.equals(action, ProccessType.ADD_BOOKCASE)) {
            addBookcase();
        } else if (StringUtils.equals(action, ProccessType.DELETE_BOOKCASE_BY_ARTICLE)) {
            deleteBookcaseByArticle();
        } else if (StringUtils.equals(action, ProccessType.SEARCH)) {
            doSearch();
        } else if (StringUtils.equals(action, ProccessType.REGISTER)) {
            register();
        } else if (StringUtils.equals(action, ProccessType.VOTE)) {
            doVote();
        } else if (StringUtils.equals(action, ProccessType.SUBSCRIBE)) {
            doSubscribe();
        } else if (StringUtils.equals(action, ProccessType.BUY_CHAPTER)) {
        	addBuyChapter();
        } else {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(getText("errors.unknown"));
        }

        logger.debug("execute normally end. ");
        return JSON_RESULT;
    }

    private void doSubscribe() {
        logger.debug("doSubscribe start.");

        if (!doCheckLogin() || !doCheckArticleno()) {
            logger.debug("doSubscribe abnormally end.");
            return;
        }

        // 检查当前登录的最大件数
        SubscribeSearchBean searchBean = new SubscribeSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        int subscribeCount = this.subscribeService.getCount(searchBean);
        if (subscribeCount > YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_SUBSCRIBE, 30)) {
            dto.setCode(ReturnCode.FAILED);
            dto.setResult(getText("errors.max.subscribe"));
            logger.debug("doSubscribe abnormally end.");
            return;
        }

        searchBean.setArticleno(articleno);
        subscribeCount = this.subscribeService.getCount(searchBean);
        if (subscribeCount > 0) {
            // 已经存在啦，算了，告诉他成功啦！哈哈
            dto.setCode(ReturnCode.SUCCESS);
            dto.setResult(getText("messages.subscribe.add.success"));
            logger.debug("the record is exists.");
            return;
        }

        TSubscribe subscribe = new TSubscribe();
        subscribe.setArticleno(articleno);
        subscribe.setUserno(LoginManager.getLoginUser().getUserno());
        this.subscribeService.save(subscribe);
        dto.setCode(ReturnCode.SUCCESS);
        dto.setResult(getText("messages.subscribe.add.success"));
        logger.debug("doSubscribe normally end.");
    }

    /**
     * <p>
     * 推荐处理
     * </p>
     */
    private void doVote() {
        logger.debug("doVote start.");

        if (!doCheckLogin() || !doCheckArticleno()) {
            logger.debug("doVote abnormally end.");
            return;
        }

        articleService.updateVoteStatistic(articleno);
        dto.setCode(ReturnCode.SUCCESS);
        dto.setResult(getText("messages.vote.success"));
        logger.debug("doVote normally end.");
    }
    /**
     * <p>
     * 推购买章节荐处理
     * </p>
     */
    private void addBuyChapter() {
        logger.debug("doBuyChapter start.");

        if (!doCheckLogin()) {
            logger.debug("doBuyChapter abnormally end.");
            return;
        }
        //添加章节订单
        TChapterOrder chapterOrder=new TChapterOrder();
        chapterOrder.setChapterno(chapterno);
        chapterOrder.setFee(fee);
        chapterOrder.setUserno(userno);
        chapterOrder.setModifytime(new Date());
        this.orderService.save(chapterOrder);
        
        TUser user = new TUser();
        //修改金额
        user = userService.getByNo(userno);
        user.setChargefee(user.getChargefee()-fee);
        // 注册用户登录
        this.userService.save(user);
        // 登录处理
        LoginManager.doLogin(user);
       //chapterOrderService.doBuyChapter(userno, chapterno, fee);

        dto.setCode(ReturnCode.SUCCESS);
        dto.setResult(getText("messages.vip.success"));
        logger.debug("doBuyChapter normally end.");
    }
    private boolean doCheckLogin() {
        if (!LoginManager.isLoginFlag()) {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(getText("errors.notLogin"));
            return false;
        }
        return true;
    }

    private boolean doCheckArticleno() {
        if (articleno == 0) {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(getText("errors.not.exsits.article"));
            return false;
        }
        return true;
    }

    /**
     * <p>
     * 用户注册
     * </p>
     */
    private void register() {

        logger.info("regedit started.");

        // ID检查
        if (StringUtils.trim(loginid).length() < 5 || StringUtils.trim(loginid).length() > 32) {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(this.getText("errors.lengthrange",
                    new String[] { "5", "32", this.getText("label.user.loginid") }));
            return;
        }
        // 密码检查
        if (StringUtils.trim(password).length() < 5 || StringUtils.trim(password).length() > 32) {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(this.getText("errors.lengthrange",
                    new String[] { "5", "32", this.getText("label.user.password") }));
            return;
        }

        // 重复检查
        UserSearchBean searchBean = new UserSearchBean();
        searchBean.setLoginid(loginid);
        searchBean.setDeleteflag(false);
        List<TUser> userList = this.userService.find(searchBean);
        if (Utils.isDefined(userList)) {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(this.getText("errors.duplicated", new String[] { this.getText("label.user.loginid") }));

            return;
        }

        TUser user = new TUser();
        user.setLoginid(loginid);
        user.setDeleteflag(false);
        user.setRegdate(new Date());
        user.setLastlogin(new Date());
        user.setPassword(Utils.convert2MD5(password));
        user.setType(YiDuConstants.UserType.NORMAL_USER);
        user.setActivedflag(true);
        // 注册用户登录
        this.userService.save(user);
        // 登录处理
        LoginManager.doLogin(user);
        dto.setCode(ReturnCode.SUCCESS);
        dto.setResult("注册成功！");
        logger.debug("regedit normally end.");
    }

    /**
     * <p>
     * 小说检索
     * </p>
     */
    private void doSearch() {
        logger.debug("doSearch start.");

        // 取章节信息
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        if (status == 1) {
            searchBean.setFullflag(true);
        }
        int count = articleService.getCount(searchBean);
        dto.setTotal(count);
        int pages = count / size;
        if (count % size > 0) {
            pages++;
        }
        dto.setPages(pages);
        dto.setSize(size);
        dto.setIndex(index);

        // 添加page信息
        Pagination pagination = new Pagination(size, index + 1);
        switch (sort) {
        case 1:
            pagination.setSortColumn(TArticle.PROP_LASTUPDATE);
            break;
        case 2:
            pagination.setSortColumn(TArticle.PROP_ALLVISIT);
            break;
        case 3:
            pagination.setSortColumn(TArticle.PROP_ALLVOTE);
            break;
        case 4:
            pagination.setSortColumn(TArticle.PROP_SIZE);
            break;
        default:
            pagination.setSortColumn(TArticle.PROP_ALLVISIT);
            break;
        }

        pagination.setSortOrder(YiDuConstants.Order.DESC);
        searchBean.setPagination(pagination);

        List<TArticle> articleList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX,
                searchBean);
        if (articleList == null || articleList.size() == 0) {
            articleList = articleService.find(searchBean);
            CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX, searchBean, articleList);
        }
        dto.setItems(articleList);

        logger.debug("doSearch normally end.");
    }

    /**
     * <p>
     * 删除小说的书签
     * </p>
     */
    private void deleteBookcaseByArticle() {
        logger.debug("deleteBookcaseByArticle start.");

        if (articleno == 0) {
            dto.setCode(ReturnCode.FAILED);
            return;
        }

        bookcaseService.getByArticlenoAndUserno(LoginManager.getLoginUser().getUserno(), articleno);

        dto.setCode(ReturnCode.SUCCESS);
        logger.debug("deleteBookcaseByArticle normally end.");
    }

    /**
     * <p>
     * 添加书签
     * </p>
     */
    private void addBookcase() {
        logger.debug("addBookcase start.");

        if (!doCheckLogin() || !doCheckArticleno()) {
            logger.debug("doVote abnormally end.");
            return;
        }

        // 检查当前登录的最大件数
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        int bookcaseCount = this.bookcaseService.getCount(searchBean);
        if (bookcaseCount > YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_BOOKCASE)) {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(getText("errors.max.bookcase"));
            return;
        }

        TBookcase bookcase = this.bookcaseService.getByArticlenoAndUserno(LoginManager.getLoginUser().getUserno(),
                articleno);
        if (bookcase == null) {
            bookcase = new TBookcase();
        }

        TArticle article = this.articleService.getByNo(articleno);
        if (Utils.isDefined(article) && article.getArticleno() != 0) {
            BeanUtils.copyProperties(article, bookcase);
        } else {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(getText("errors.not.exsits.article"));
            return;
        }

        if (chapterno != 0) {
            TChapter chapter = this.chapterService.getByNo(chapterno);
            if (chapter != null && chapter.getChapterno() != 0) {
                BeanUtils.copyProperties(chapter, bookcase);
            } else {
                dto.setCode(ReturnCode.FAILED);
                dto.setErr(getText("errors.not.exsits.chapter"));
                return;
            }
        }
        bookcase.setCreatetime(new Date());
        bookcase.setUserno(LoginManager.getLoginUser().getUserno());

        this.bookcaseService.save(bookcase);

        dto.setCode(ReturnCode.SUCCESS);
        dto.setResult(getText("messages.bookcase.add.success"));
        logger.debug("addBookcase normally end.");
    }

    /**
     * <p>
     * 检查书签是否存在
     * </p>
     */
    private void checkBookCaseExists() {
        logger.debug("checkBookCaseExists start.");

        if (articleno == 0) {
            dto.setCode(ReturnCode.FAILED);
            return;
        }

        TBookcase bookcase = bookcaseService
                .getByArticlenoAndUserno(LoginManager.getLoginUser().getUserno(), articleno);
        if (Utils.isDefined(bookcase)) {
            dto.setCode(ReturnCode.FAILED);
        }
        dto.setCode(ReturnCode.SUCCESS);
        logger.debug("checkBookCaseExists normally end.");
    }

    /**
     * <p>
     * 取得阅读历史
     * </p>
     */
    private void getHistory() {
        logger.debug("getHistory start.");
        // 获得阅读履历
        String historys = CookieUtils.getHistoryCookie(ServletActionContext.getRequest());
        if (StringUtils.isNotEmpty(historys)) {
            String[] acnos = StringUtils.split(historys, ",");
            List<String> articlenoList = new ArrayList<String>();
            for (String articleAndchapterno : acnos) {
                String[] acnoArr = StringUtils.split(articleAndchapterno, "|");
                if (acnoArr.length > 0) {
                    articlenoList.add(acnoArr[0]);
                }
            }
            if (articlenoList.size() > 0) {
                ArticleSearchBean searchBean = new ArticleSearchBean();
                searchBean.setArticlenos(StringUtils.join(articlenoList, ","));
                dto.setItems(articleService.find(searchBean));
            }
            dto.setCode(ReturnCode.SUCCESS);
        }
        logger.debug("getHistory normally end.");
    }

    /**
     * <p>
     * 删除书签
     * </p>
     */
    private void deleteBookcase() {
        logger.debug("deleteBookcase start.");
        if (StringUtils.isEmpty(StringUtils.trim(bookcasenos))) {
            dto.setCode(ReturnCode.FAILED);
            return;
        }

        bookcaseService.batchDeleteByNo(bookcasenos, LoginManager.getLoginUser().getUserno());
        dto.setCode(ReturnCode.SUCCESS);
        logger.debug("deleteBookcase normally end.");
    }

    /**
     * <p>
     * 获取书签
     * </p>
     */
    private void getBookcase() {
        logger.debug("getBookcase start.");
        BookcaseSearchBean searchBean = new BookcaseSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        // 添加page信息
        Pagination pagination = new Pagination(size, index + 1);
        switch (sort) {
        case 1:
            pagination.setSortColumn("tb.createtime");
            pagination.setSortOrder("DESC");
            break;
        case 2:
            pagination.setSortColumn("ta.lastupdate");
            pagination.setSortOrder(YiDuConstants.Order.DESC);
            break;
        case 3:
            pagination.setSortColumn("ta.articlename");
            pagination.setSortOrder(YiDuConstants.Order.ASC);
            break;
        default:
            pagination.setSortColumn(TArticle.PROP_LASTUPDATE);
            pagination.setSortOrder(YiDuConstants.Order.DESC);
            break;
        }
        searchBean.setPagination(pagination);

        dto.setItems(this.bookcaseService.findWithArticleInfo(searchBean));
        dto.setCode(ReturnCode.SUCCESS);
        logger.debug("getBookcase normally end.");
    }

    /**
     * <p>
     * 获取排行版小说列表
     * </p>
     */
    private void getTopList() {
        logger.debug("getTopList start.");

        // 取章节信息
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);

        int count = 0;
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, false)) {
            count = ArticleCountManager.getArticleCount(String.valueOf(category));
        } else {
            Object countInfo = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX,
                    searchBean);
            if (countInfo == null) {
                count = articleService.getCount(searchBean);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX, searchBean,
                        count);
            } else {
                count = Integer.parseInt(countInfo.toString());
            }
        }

        dto.setTotal(count);
        int pages = count / size;
        if (count % size > 0) {
            pages++;
        }
        dto.setPages(pages);
        dto.setSize(size);
        dto.setIndex(index);

        // 添加page信息
        Pagination pagination = new Pagination(size, index + 1);
        switch (type) {
        case 1:
            pagination.setSortColumn(TArticle.PROP_LASTUPDATE);
            break;
        case 2:
            pagination.setSortColumn(TArticle.PROP_ALLVISIT);
            break;
        case 3:
            pagination.setSortColumn(TArticle.PROP_ALLVOTE);
            break;
        case 4:
            pagination.setSortColumn(TArticle.PROP_SIZE);
            break;
        default:
            pagination.setSortColumn(TArticle.PROP_LASTUPDATE);
            break;
        }

        pagination.setSortOrder(YiDuConstants.Order.DESC);
        searchBean.setPagination(pagination);

        List<TArticle> articleList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX,
                searchBean);
        if (!Utils.isDefined(articleList)) {
            articleList = articleService.find(searchBean);
            CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX, searchBean, articleList);
        }
        dto.setItems(articleList);

        logger.debug("getTopList normally end.");
    }

    /**
     * <p>
     * 获取分类小说列表
     * </p>
     */
    private void getCategoryList() {
        logger.debug("getCategoryList start.");

        // 取章节信息
        ArticleSearchBean searchBean = new ArticleSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        int count = 0;
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, false)) {

            count = ArticleCountManager.getArticleCount(String.valueOf(category));

        } else {
            Object countInfo = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX,
                    searchBean);
            if (countInfo == null) {
                count = articleService.getCount(searchBean);
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_COUNT_PREFIX, searchBean,
                        count);
            } else {
                count = Integer.parseInt(countInfo.toString());
            }
        }
        dto.setTotal(count);
        int pages = count / size;
        if (count % size > 0) {
            pages++;
        }
        dto.setPages(pages);
        dto.setSize(size);
        dto.setIndex(index);

        // 添加page信息
        Pagination pagination = new Pagination(size, index + 1);
        pagination.setSortColumn(TArticle.PROP_LASTUPDATE);
        pagination.setSortOrder(YiDuConstants.Order.DESC);
        searchBean.setPagination(pagination);

        List<TArticle> articleList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX,
                searchBean);
        if (articleList == null || articleList.size() == 0) {
            articleList = articleService.find(searchBean);
            CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_LIST_PREFIX, searchBean, articleList);
        }
        dto.setItems(articleList);

        logger.debug("getCategoryList normally end.");
    }

    /**
     * <p>
     * 获取章节列表
     * </p>
     */
    private void getChapterList() {
        logger.debug("getChapterList start.");

        // 取章节信息
        ChapterSearchBean searchBean = new ChapterSearchBean();
        BeanUtils.copyProperties(this, searchBean);
        int count = chapterService.getCount(searchBean);
        dto.setTotal(count);
        int pages = count / size;
        if (count % size > 0) {
            pages++;
        }
        dto.setPages(pages);
        
        // 添加page信息
        Pagination pagination = new Pagination(size, index + 1);
        pagination.setSortColumn(TChapter.PROP_CHAPTERNO);
        int startPageNo =0;
        if (sort != 0) {
            pagination.setSortOrder(YiDuConstants.Order.DESC);
            startPageNo = count -pagination.getPageNumber()*pagination.getPageSize();
        } else {
            pagination.setSortOrder(YiDuConstants.Order.ASC);
            startPageNo = (pagination.getPageNumber()-1)*pagination.getPageSize()+1;
        }
        searchBean.setPagination(pagination);
        
        List<TChapter> chapterList = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_LIST_PREFIX,
                searchBean);
        if (!Utils.isDefined(chapterList)) {
            chapterList = chapterService.find(searchBean);
            if (Utils.isDefined(chapterList)) {
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_LIST_PREFIX, searchBean,
                        chapterList);
            }
        }
        //设置VIP标示80章节开始
        if(startPageNo>80){
            for(int i=0;i<chapterList.size();i++){
            	TChapter tChapter =chapterList.get(i);
            	tChapter.setIsvip(true);
            	tChapter =chapterList.set(i, tChapter);
            }
        }
        dto.setItems(chapterList);
        if (articleno != 0) {
            articleService.updateVisitStatistic(articleno);
        }
        logger.debug("getChapterList normally end.");

    }

    /**
     * <p>
     * 修改密码
     * </p>
     */
    private void dochangepass() {
        // TODO 输入检查
        TUser user = userService.getByNo(userno);
        if (StringUtils.equals(user.getPassword(), Utils.convert2MD5(password))) {
            dto.setCode(ReturnCode.FAILED);
            return;
        }

        if (StringUtils.isNotEmpty(password)) {
            user.setPassword(Utils.convert2MD5(password));
        }
        userService.save(user);
        dto.setCode(ReturnCode.SUCCESS);
        // TODO 文字检查和密码校对
    }

    /**
     * <p>
     * 用户登录
     * </p>
     */
    private void dologin() {
        logger.info("dologin start.");
        TUser user = userService.findByLoginInfo(loginid, Utils.convert2MD5(password));
        if (user != null && user.getDeleteflag() != null && !user.getDeleteflag()) {
            // 正常登录
            LoginManager.doLogin(user);
            // 更新用户最后登录时间
            user.setLastlogin(new Date());
            userService.save(user);
            Cookie cookie = CookieUtils.addUserCookie(user);
            // 添加cookie到response中
            ServletActionContext.getResponse().addCookie(cookie);
            dto.setCode(ReturnCode.SUCCESS);
            logger.debug("dologin normally end.");
        } else {
            dto.setCode(ReturnCode.FAILED);
            dto.setErr(getText("errors.login.failed"));
            logger.debug("dologin abnormally end.");
        }

    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public String getTempName() {
        return null;
    }
}
