package org.yidu.novel.constant;

/**
 * 
 * <p>
 * 易读配置文件定义
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class YiDuConfig {
    /**
     * URI
     */
    public static final String URI = "uri";
    /**
     * mobile URL
     */
    public static final String MOBILE_URI = "mobileUri";
    /**
     * content文件路径
     */
    public static final String CONTENT_FILE_PATH = "contentFilePath";
    /**
     * 是否跳过验证
     */
    public static final String SKIP_AUTH_CHECK = "skipAuthCheck";
    /**
     * 每页表示件数
     */
    public static final String COUNT_PER_PAGE = "countPerPage";
    /**
     * 是否启用缓存
     */
    public static final String CACHE_EFFECTIVE = "cacheEffective";
    /**
     * 是否启用广告
     */
    public static final String AD_EFFECTIVE = "adEffective";
    /**
     * 是否启用压缩源码
     */
    public static final String COMPRESS_EFFECTIVE = "compressEffective";
    /**
     * 是否启用伪静态
     */
    public static final String CLEAN_URL = "cleanUrl";
    /**
     * TXT文件路径
     */
    public static final String FILE_PATH = "filePath";
    /**
     * 图片文件路径
     */
    public static final String IMAGE_PATH = "iamgePath";

    /**
     * 图片相对文件路径
     */
    public static final String RELATIVE_IAMGE_PATH = "relativeIamgePath";
    /**
     * 最大书签数
     */
    public static final String MAX_BOOKCASE = "maxBookcase";

    /**
     * 每页表示记录书
     */
    public static final String NUMBER_PER_PAGE = "numberPerPage";
    /**
     * 是否启用GZIP压缩
     */
    public static final String GZIP_EFFECTIVE = "gzipEffective";
    /**
     * 是否生成静态首页
     */
    public static final String CREATE_INDEXPAGE = "createIndexPage";
    /**
     * 是否生成网站地图
     */
    public static final String CREATE_SITEMAP = "createSiteMap";
    /**
     * 模版名称
     */
    public static final String THEME_NAME = "themeName";
    /**
     * TXT文件编码
     */
    public static final String TXT_ENCODING = "txtEncoding";
    /**
     * 删除后的数据的保存天数
     */
    public static final String KEEP_DELETE_DATA_DAYS = "keepDeleteDataDays";
    /**
     * 是否用手机信息判断
     */
    public static final String JUDG_MOBILESITE_BY_DOMIAN = "judgmobilesitebydomian";
    /**
     * 是否启用手机页面
     */
    public static final String ENABLE_MOBILE_SITE = "enableMobileSite";
    /**
     * 手机页面域名
     */
    public static final String MOBILESITE_DOMIAN = "mobilesitedomian";
    /**
     * 网站地图类型
     */
    public static final String SITEMAP_TYPE = "siteMapType";
    /**
     * XML网站地图内的列表页URL（不含页号）
     */
    public static final String XML_SITEMAP_LIST_WITH_NO_PAGE_URL = "xmlSiteMapListWithNoPageURL";
    /**
     * XML网站地图内的列表页URL
     */
    public static final String XML_SITEMAP_LIST_URL = "xmlSiteMapListURL";
    /**
     * XML网站地图内的小说信息URL
     */
    public static final String XML_SITEMAP_INFO_URL = "xmlSiteMapInfoURL";
    /**
     * XML网站地图内的章节列表信息URL
     */
    public static final String XML_SITEMAP_CHATERLIST_URL = "xmlSiteMapChaterListURL";
    /**
     * XML网站地图内的章节信息URL
     */
    public static final String XML_SITEMAP_READER_URL = "xmlSiteMapReaderURL";
    /**
     * 信息页显示评论数量
     */
    public static final String REVIEW_NUM = "reviewnum";
    /**
     * 是否允许在未登录状态下发表评论
     */
    public static final String ADD_REVIEW_WITHOUT_LOGIN = "addReviewWithoutLogin";
    /**
     * 是否启用伪原创
     */
    public static final String ENABLE_PSEUDO = "enablePseudo";
    /**
     * 是否启用QQ登录
     */
    public static final String ENABLE_QQLOGIN = "enableQQLogin";
    /**
     * QQ登录的APPID
     */
    public static final String APP_ID = "app_ID";
    /**
     * QQ登录的APPKEY
     */
    public static final String APP_KEY = "app_KEY";
    /**
     * QQ登录用重定向URI
     */
    public static final String REDIRECT_URI = "redirect_URI";
    /**
     * 是否缓存小说件数
     */
    public static final String ENABLE_CACHE_ARTICLE_COUNT = "enableCacheArticleCount";
    /**
     * 重新加载小说件数时间
     */
    public static final String RELOAD_ARTICLE_COUNT_INTERVAL = "reloadArticleCountInterval";
    /**
     * 是否生成静态HTML文件
     */
    public static final String ENABLE_GENERATE_HTML_FILE = "enableGenerateHtmlFile";
    /**
     * 是否启用独立章节列表页面
     */
    public static final String ENABLE_CHAPTER_INDEX_PAGE = "enableChapterIndexPage";
    /**
     * 是否启用邮箱验证
     */
    public static final String ENABLE_MAIL_AUTH = "enableMailAuth";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    public static final String MAIL_SMTP_USERNAME = "mail.smtp.username";
    public static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";
    public static final String MAIL_SMTP_FROM = "mail.smtp.from";

    /**
     * 最大订阅数
     */
    public static final String MAX_SUBSCRIBE = "maxSubscribe";

    /**
     * 订阅的检查间隔
     */
    public static final String SEND_SUBSCRIBE_INTEVAL = "sendSubscribeInteval";

    /**
     * 订阅的检查间隔
     */
    public static final String ENABLE_SINGLE_BOOK = "enableSingleBook";

    /**
     * 是否开启图片URL优化
     */
    public static final String ENABLE_CLEAN_IMAGE_URL = "enableCleanImageUrl";

    /**
     * 根域名
     */
    public static final String ROOT_DOMAIN = "rootDomain";
    /**
     * 不包含的子域名
     */
    public static final String EXCLUDE_SUB_DOMAIN = "excludeSubDomain";
    /**
     * 小说信息重载周期
     */
    public static final String RELOAD_SINGLE_BOOK_INTERVAL = "reloadSingleBookInterval";

    /**
     * 禁止访问的文件后缀
     */
    public static final String FORBID_FILES = "forbidFiles";

    /**
     * 过滤关键字
     */
    public static final String FILTER_KEYWORD = "filterKeyWord";

    /**
     * 启用拼音url
     */
    public static final String ENABLE_PINYINURL = "enablePinyinUrl";

    /**
     * 数据库JDBC URL
     */
    public static final String JDBC_URL = "jdbc.url";
    /**
     * 数据库连接用户名
     */
    public static final String JDBC_USERNAME = "jdbc.username";
    /**
     * 数据库连接密码
     */
    public static final String JDBC_PASSWORD = "jdbc.password";
    /**
     * 数据库连接数据库名
     */
    public static final String JDBC_DBNAME = "jdbc.dbname";
    /**
     * 网站URL
     */
    public static final String URL = "label.system.url";
    /**
     * 网站名字
     */
    public static final String NAME = "label.system.name";
    /**
     * 网站标题
     */
    public static final String TITLE = "label.system.title";
    /**
     * 网站关键字
     */
    public static final String SITEKEYWORDS = "label.system.siteKeywords";
    /**
     * 网站描述
     */
    public static final String SITEDESCRIPTION = "label.system.siteDescription";
    /**
     * 网站域名
     */
    public static final String DOMAIN = "label.system.domain";
    /**
     * 网站的copyright
     */
    public static final String COPYRIGHT = "label.system.copyright";
    /**
     * 备案号
     */
    public static final String BEIANNO = "label.system.beianNo";
    /**
     * 统计代码
     */
    public static final String ANALYTICSCODE = "label.system.analyticscode";
    /**
     * 小说分类
     */
    public static final String CATEGORY = "collectionProperties.article.category";

}
