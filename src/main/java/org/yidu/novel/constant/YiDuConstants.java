package org.yidu.novel.constant;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 
 * <p>
 * 易读静态定义
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class YiDuConstants {
    /**
     * 易读配置文件
     */
    public static PropertiesConfiguration yiduConf;
    /**
     * 伪静态配置文件
     */
    public static PropertiesConfiguration pseudoConf;
    /**
     * 请求URI
     */
    public static ThreadLocal<String> requestUri = new ThreadLocal<String>();

    /**
     * 单本小说标识
     */
    public static ThreadLocal<Boolean> singleBookFlag = new ThreadLocal<Boolean>();

    /**
     * 单本小说URI
     */
    public static ThreadLocal<String> singleBookServerName = new ThreadLocal<String>();

    /**
     * 排行榜名字MAP
     */
    public static final Map<String, String> TOP_NAME_MAP = new LinkedHashMap<String, String>() {
        private static final long serialVersionUID = -2355068040470822368L;
        {
            put("lastupdate", "最近更新");
            put("allvisit", "总点击榜");
            put("allvote", "总推荐榜");
            put("monthvisit", "月点击榜");
            put("monthvote", "月推荐榜");
            put("weekvisit", "周点击榜");
            put("weekvote", "周推荐榜");
            put("dayvisit", "日点击榜");
            put("dayvote", "日推荐榜");
            put("postdate", "最新入库");
            put("size", "字数排行");
        }
    };

    /**
     * 排行榜名字MAP
     */
    public static final Map<String, String> MOBILE_TOP_NAME_MAP = new LinkedHashMap<String, String>() {
        private static final long serialVersionUID = -2355068040470822368L;
        {
            put("lastupdate", "更新榜");
            put("allvisit", "总点击");
            put("allvote", "总推荐");
            put("monthvisit", "月点击");
            put("monthvote", "月推荐");
            put("weekvisit", "周点击");
            put("weekvote", "周推荐");
            put("dayvisit", "日点击");
            put("dayvote", "日推荐");
            put("postdate", "入库榜");
            put("size", "字数榜");
        }
    };

    /**
     * UTF-8字符
     */
    public static final String ENCODING_UTF_8 = "UTF-8";
    /**
     * GBK字符
     */
    public static final String ENCODING_GBK = "GBK";

    /**
     * 小说子目录的小说数
     */
    public static final int SUB_DIR_ARTICLES = 1000;

    /**
     * 
     * <p>
     * 用户分组
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class UserGroup {

        /**
         * 游客
         */
        public static final int GUEST = 1;
        /**
         * 系统管理员
         */
        public static final int ADMIN = 2;
        /**
         * 普通会员
         */
        public static final int NORMARL = 3;
        /**
         * 高级会员
         */
        public static final int AD = 4;
        /**
         * VIP会员
         */
        public static final int VIP = 5;
        /**
         * 专栏作家
         */
        public static final int AUTHOR = 6;
        /**
         * 驻站作家
         */
        public static final int AUTHOR2 = 7;
        /**
         * 初级版主
         */
        public static final int KONGBU = 8;
        /**
         * 中级版主
         */
        public static final int JUBEN = 9;
        /**
         * 高级版主
         */
        public static final int OTHER = 10;
    }

    /**
     * 
     * <p>
     * ResponseStatus
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class ResponseStatus {

        /**
         * 成功
         */
        public static final int SUCCESS = 200;
        /**
         * 失败
         */
        public static final int FAILED = 400;

    }

    /**
     * 
     * <p>
     * 区块标识
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class BlockTarget {
        /**
         * 小说列表页
         */
        public static final short ARTICLE_LIST = 1;
        /**
         * 小说详细页
         */
        public static final short ARTICLE_INFO = 2;
        /**
         * 章节列表页
         */
        public static final short CHAPTER_LIST = 3;
        /**
         * 阅读页
         */
        public static final short READER_PAGE = 4;
        /**
         * 用户详细页
         */
        public static final short USER_DETAIL = 5;
        /**
         * 主页
         */
        public static final short INDEX = 6;
        /**
         * 排行榜
         */
        public static final short TOP_LIST = 7;
        /**
         * 全站
         */
        public static final short ALL_SITE = 99;

    }

    /**
     * 
     * <p>
     * 图片类型
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class ImageType {
        /**
         * JPG
         */
        public static final short JPG = 1;
        /**
         * GIF
         */
        public static final short GIF = 2;
        /**
         * PNG
         */
        public static final short PNG = 3;
    }

    /**
     * 
     * <p>
     * 区块类型
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class BlockType {
        /**
         * 小说列表
         */
        public static final short ARTICLE_LIST = 10;
        /**
         * 自定义小说列表
         */
        public static final short CUSTONIZE_ARTICLE_LIST = 20;
        /**
         * HTML类型
         */
        public static final short HTML = 30;

        /**
         * 随机类型
         */
        public static final short RANDOM_LIST = 40;

        /**
         * 推荐类型
         */
        public static final short RECOMMEND_LIST = 50;

        /**
         * 相关小说
         */
        public static final short RELATIVE_LIST = 60;
        
        /**
         * 相同作者小说
         */
        public static final short SAME_AUTHOR_LIST = 70;

    }

    /**
     * 
     * <p>
     * 页面类型
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class Pagetype {

        /**
         * 1:主页
         * */
        public static final int PAGE_INDEX = 1;
        /**
         * 2：小说列表
         */
        public static final int PAGE_ARTICLE_LIST = 2;
        /**
         * 3：小说介绍页
         */
        public static final int PAGE_ARTICLE_INFO = 3;

        /**
         * 60：章节列表页
         */
        public static final int PAGE_CHAPTER_LIST = 60;

        /**
         * 4：小说阅读页
         */
        public static final int PAGE_READER = 4;
        /**
         * 5：搜索页
         */
        public static final int PAGE_SEARCH = 5;

        /**
         * 手机分类
         */
        public static final int PAGE_CATEGORY = 6;
        /**
         * 手机排行
         */
        public static final int PAGE_TOP = 7;

        /**
         * 8：评论页
         */
        public static final int PAGE_REVIEW = 8;

        /**
         * 9：用户信息页
         */
        public static final int PAGE_USER_INFO = 9;

        /**
         * 11：登录页
         */
        public static final int PAGE_LOGIN = 11;
        /**
         * 11：注册页
         */
        public static final int PAGE_REGEDIT = 12;

        /**
         * 13：邮件验证页
         */
        public static final int PAGE_MAIL_VALIDATE = 13;

        /**
         * 订阅
         */
        public static final int PAGE_USER_SUBSCRIBE = 20;
        /**
         * 书架
         */
        public static final int PAGE_USER_BOOKCASE = 21;
        /**
         * 消息管理
         */
        public static final int PAGE_USER_MESSAGE = 22;
        /**
         * 资料编辑
         */
        public static final int PAGE_USER_USEREDIT = 23;
        /**
         * 申请作者
         */
        public static final int PAGE_REGI_AUTHOR = 24;
        /**
         * 用户中心
         */
        public static final int PAGE_USER_CENTER = 25;
        /**
         * 手机书架
         */
        public static final int PAGE_BOOKCASE = 26;
        /**
         * 消息添加
         */
        public static final int PAGE_ADD_MESSAGE = 27;
        /**
         * 小说列表
         */
        public static final int PAGE_AUTHER_ARTICLE_LIST = 30;
        /**
         * 小说编辑
         */
        public static final int PAGE_AUTHER_ARTICLE_EDIT = 31;
        /**
         * 章节列表
         */
        public static final int PAGE_AUTHER_CHAPTER_LIST = 32;
        /**
         * 章节编辑
         */
        public static final int PAGE_AUTHER_CHAPTER_EDIT = 33;
        /**
         * 章节编辑
         */
        public static final int PAGE_AUTHER_BILL_DETAIL = 40;
        
        /**
         * 购买VIP
         */
        public static final int PAGE_BUY_VIP = 50;
        
        /**
         * 用户充值
         */
        public static final int PAGE_USER_CHARGE = 51;
        
        /**
         * * 99：其他页
         */
        public static final int PAGE_OTHERS = 99;

    }

    /**
     * <p>
     * 用户类型定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class UserType {

        /**
         * 普通用户
         */
        public static final short NORMAL_USER = 10;
        /**
         * 作家
         */
        public static final int AUTHER = 20;
        /**
         * VIP
         */
        public static final int VIP = 25;
        /**
         * 管理员
         */
        public static final int ADMINISTRATOR = 30;
        /**
         * 编辑
         */
        public static final int EDITOR = 40;

    }

    /**
     * <p>
     * 小说授权级别定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.6
     * @author shinpa.you
     */
    public static final class PermissionType {

        /**
         * 暂未授权
         */
        public static final int UNPERMISSION = 4;

    }

    /**
     * 前缀
     */
    private static final String PREFIX = "yidu.Novel.";

    /**
     * 用户名
     */
    public static final String LOGINUSER = PREFIX + "LoginUser";
    /**
     * 当前页的LOCALE
     */
    public static final String CURRENTPAGELOCALE = "CURRENT_PAGE_LOCALE";

    /**
     * 正则表达式
     */
    public static final class Regex {
        /**
         * 日期（ 「YYYY/MM/DD」）
         */
        public static final String DATE = "^(?:((?!0000)[0-9]{4}/(?:(?:0[1-9]|1[0-2])/(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])/(?:29|30)|(?:0[13578]|1[02])/31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)/02/29)?)$";
        /**
         * 半角英数字
         */
        public static final String ALPHANUMERIC = "^[A-Za-z0-9]*$";
        /**
         * 半角数字
         */
        public static final String NUMBER = "^\\d*$";
        /**
         * 半角数字
         */
        public static final String EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
    }

    /**
     * 
     * <p>
     * 图片Mate类型定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class ImgageMateType {
        /**
         * JPG的Mate类型
         */
        public static final String JPG = "image/jpeg";
        /**
         * PNG的Mate类型
         */
        public static final String PNG = "image/png";
        /**
         * GIF的Mate类型
         */
        public static final String GIF = "image/gif";

    }

    /**
     * 支持的图片类型
     */
    public static final String[] ALLOW_PIC_TYPES = new String[] { ImgageMateType.JPG, ImgageMateType.PNG,
            ImgageMateType.GIF };

    /**
     * 支持的Sample类型
     */
    public static final String[] ALLOW_SAMPLE_TYPES = new String[] { "text/plain", "application/kswps" };

    /**
     * 网站地图类型
     */
    public enum SiteMapType {
        /**
         * HTML和XML定义
         */
        HTML("html"), XML("xml");
        /**
         * 名字
         */
        private String name;

        /**
         * 设置网站地图类型
         * 
         * @param name
         *            地图类型
         */
        private SiteMapType(String name) {
            this.name = name;
        }

        /**
         * 获取网站地图类型
         * 
         * @return 地图类型
         */
        public String getName() {
            return name;
        }
    }

    /**
     * 
     * <p>
     * 排序
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class Order {
        /**
         * 降序
         */
        public static final String DESC = "DESC";
        /**
         * 升序
         */
        public static final String ASC = "ASC";
    }

    public static final class CacheCountType {
        public static final String FULLFLAG = "fullflag";
        public static final String ALL = "all";
        public static final String TAG = "tag";
        public static final String AUTHOR = "author";
    }

}