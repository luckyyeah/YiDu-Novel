package org.yidu.novel.action.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 系统相关配置Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "configEdit")
public class ConfigEditAction extends AbstractAdminEditBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -6768164951656460867L;

    /**
     * uri
     */
    private String uri;
    /**
     * mobile uri
     */
    private String mobileUri;
    /**
     * TXT文件路径
     */
    private String filePath;
    /**
     * 图片文件路径
     */
    private String relativeIamgePath;
    
    /**
     * 图片文件根路径
     */
    private String rootImagePath;
    /**
     * 模版名
     */
    private String themeName;
    /**
     * 是否开启缓存
     */
    private boolean cacheEffective;
    /**
     * 是否开启伪静态
     */
    private boolean cleanUrl;
    /**
     * 是否开启Gzip压缩
     */
    private boolean gzipEffective;
    /**
     * 是否开启广告
     */
    private boolean adEffective;
    /**
     * 每页表示件数
     */
    private int countPerPage;

    /**
     * 可以添加最大书签数
     */

    private int maxBookcase;
    
    private boolean compressEffective;

    /**
     * 是否生成静态首页
     */
    private boolean createIndexPage;
    /**
     * 是否生成站点地图
     */
    private boolean createSiteMap;
    /**
     * 站点地图类型
     */
    private String siteMapType;
    /**
     * 列表站点地图
     */
    private String xmlSiteMapListWithNoPageURL;
    /**
     * 列表站点地图
     */
    private String xmlSiteMapListURL;
    /**
     * 简介页站点地图
     */
    private String xmlSiteMapInfoURL;
    /**
     * 章节列表站点地图
     */
    private String xmlSiteMapChaterListURL;
    /**
     * 阅读页站点地图
     */
    private String xmlSiteMapReaderURL;
    /**
     * 存放TXT的编码
     */
    private String txtEncoding;
    /**
     * 数据库URL
     */
    private String dburl;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 是否支持未登录发表评论
     */
    private boolean addReviewWithoutLogin;
    /**
     * 是否开启手机页面
     */
    private boolean enableMobileSite;
    /**
     * 是否使用域名作为手机页面的判断标准
     */
    private boolean judgmobilesitebydomian;
    /**
     * 手机站点域名
     */
    private String mobilesitedomian;

    /**
     * 是否启用伪原创
     */
    private boolean enablePseudo;

    private boolean enableQQLogin;

    private String appId;

    private String appKey;

    private boolean enableCacheArticleCount;

    private boolean enableChapterIndexPage;
    /**
     * 是否启用触发式静态
     */
    private boolean enableGenerateHtmlFile;
    /**
     * 是否启用邮箱认证
     */
    private boolean enableMailAuth = true;
    /**
     * 邮箱是否需要认证
     */
    private boolean mailSmtpAuth = true;
    /**
     * 邮箱是否启用Starttls
     */
    private boolean mailSmtpStarttlsEnable = true;
    /**
     * 邮箱SmtpHost
     */
    private String mailSmtpHost;
    /**
     * 邮箱SmtpPort
     */
    private int mailSmtpPort;
    /**
     * 邮箱SmtpUsername
     */
    private String mailSmtpUsername;
    /**
     * 邮箱SmtpPassword
     */
    private String mailSmtpPassword;
    /**
     * 邮箱SmtpFrom
     */
    private String mailSmtpFrom;
    /**
     * 最大订阅数
     */
    private int maxSubscribe;
    /**
     * 订阅的检查间隔 这里要和batch的启动周期设置成一样的(单位：分)
     */
    private int sendSubscribeInteval;
    /**
     * 是否启用泛解析单本功能
     */
    private boolean enableSingleBook;
    /**
     * 根域名，用于泛解析
     */
    private String rootDomain;
    /**
     * 不包含的子域名
     */
    private String excludeSubDomain;
    /**
     * 重新加载小说信息周期(单位：分)
     */
    private int reloadSingleBookInterval;

    /**
     * 是否开启图片URL优化
     */
    private boolean enableCleanImageUrl;

    /**
     * 过滤关键字
     */
    private String filterKeyWord;

    /**
     * 启用拼音URL
     */
    private boolean enablePinyinUrl;

    /**
     * 获取uri
     * 
     * @return uri
     */
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMobileUri() {
        return mobileUri;
    }

    public void setMobileUri(String mobileUri) {
        this.mobileUri = mobileUri;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRelativeIamgePath() {
        return relativeIamgePath;
    }

    public void setRelativeIamgePath(String relativeIamgePath) {
        this.relativeIamgePath = relativeIamgePath;
    }

    public String getRootImagePath() {
		return rootImagePath;
	}

	public void setRootImagePath(String rootImagePath) {
		this.rootImagePath = rootImagePath;
	}

	public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public boolean isCacheEffective() {
        return cacheEffective;
    }

    public void setCacheEffective(boolean cacheEffective) {
        this.cacheEffective = cacheEffective;
    }

    public boolean isCleanUrl() {
        return cleanUrl;
    }

    public void setCleanUrl(boolean cleanUrl) {
        this.cleanUrl = cleanUrl;
    }

    public boolean isGzipEffective() {
        return gzipEffective;
    }

    public void setGzipEffective(boolean gzipEffective) {
        this.gzipEffective = gzipEffective;
    }

    public boolean isAdEffective() {
        return adEffective;
    }

    public void setAdEffective(boolean adEffective) {
        this.adEffective = adEffective;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getMaxBookcase() {
        return maxBookcase;
    }

    public void setMaxBookcase(int maxBookcase) {
        this.maxBookcase = maxBookcase;
    }

    public boolean isCompressEffective() {
		return compressEffective;
	}

	public void setCompressEffective(boolean compressEffective) {
		this.compressEffective = compressEffective;
	}

	public boolean isCreateIndexPage() {
        return createIndexPage;
    }

    public void setCreateIndexPage(boolean createIndexPage) {
        this.createIndexPage = createIndexPage;
    }

    public boolean isCreateSiteMap() {
        return createSiteMap;
    }

    public void setCreateSiteMap(boolean createSiteMap) {
        this.createSiteMap = createSiteMap;
    }

    /**
     * 获取siteMapType
     * 
     * @return siteMapType
     */
    public String getSiteMapType() {
        return siteMapType;
    }

    /**
     * 
     * 设置siteMapType
     * 
     * 
     * @param siteMapType
     *            siteMapType
     */
    public void setSiteMapType(String siteMapType) {
        this.siteMapType = siteMapType;
    }

    /**
     * 获取xmlSiteMapListWithNoPageURL
     * 
     * @return xmlSiteMapListWithNoPageURL
     */
    public String getXmlSiteMapListWithNoPageURL() {
        return xmlSiteMapListWithNoPageURL;
    }

    /**
     * 
     * 设置xmlSiteMapListWithNoPageURL
     * 
     * 
     * @param xmlSiteMapListWithNoPageURL
     *            xmlSiteMapListWithNoPageURL
     */
    public void setXmlSiteMapListWithNoPageURL(String xmlSiteMapListWithNoPageURL) {
        this.xmlSiteMapListWithNoPageURL = xmlSiteMapListWithNoPageURL;
    }

    /**
     * 获取xmlSiteMapListURL
     * 
     * @return xmlSiteMapListURL
     */
    public String getXmlSiteMapListURL() {
        return xmlSiteMapListURL;
    }

    /**
     * 
     * 设置xmlSiteMapListURL
     * 
     * 
     * @param xmlSiteMapListURL
     *            xmlSiteMapListURL
     */
    public void setXmlSiteMapListURL(String xmlSiteMapListURL) {
        this.xmlSiteMapListURL = xmlSiteMapListURL;
    }

    /**
     * 获取xmlSiteMapInfoURL
     * 
     * @return xmlSiteMapInfoURL
     */
    public String getXmlSiteMapInfoURL() {
        return xmlSiteMapInfoURL;
    }

    /**
     * 
     * 设置xmlSiteMapInfoURL
     * 
     * 
     * @param xmlSiteMapInfoURL
     *            xmlSiteMapInfoURL
     */
    public void setXmlSiteMapInfoURL(String xmlSiteMapInfoURL) {
        this.xmlSiteMapInfoURL = xmlSiteMapInfoURL;
    }

    /**
     * 获取xmlSiteMapChaterListURL
     * 
     * @return xmlSiteMapChaterListURL
     */
    public String getXmlSiteMapChaterListURL() {
        return xmlSiteMapChaterListURL;
    }

    /**
     * 
     * 设置xmlSiteMapChaterListURL
     * 
     * 
     * @param xmlSiteMapChaterListURL
     *            xmlSiteMapChaterListURL
     */
    public void setXmlSiteMapChaterListURL(String xmlSiteMapChaterListURL) {
        this.xmlSiteMapChaterListURL = xmlSiteMapChaterListURL;
    }

    /**
     * 获取xmlSiteMapReaderURL
     * 
     * @return xmlSiteMapReaderURL
     */
    public String getXmlSiteMapReaderURL() {
        return xmlSiteMapReaderURL;
    }

    /**
     * 
     * 设置xmlSiteMapReaderURL
     * 
     * 
     * @param xmlSiteMapReaderURL
     *            xmlSiteMapReaderURL
     */
    public void setXmlSiteMapReaderURL(String xmlSiteMapReaderURL) {
        this.xmlSiteMapReaderURL = xmlSiteMapReaderURL;
    }

    public String getTxtEncoding() {
        return txtEncoding;
    }

    public void setTxtEncoding(String txtEncoding) {
        this.txtEncoding = txtEncoding;
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAddReviewWithoutLogin() {
        return addReviewWithoutLogin;
    }

    public void setAddReviewWithoutLogin(boolean addReviewWithoutLogin) {
        this.addReviewWithoutLogin = addReviewWithoutLogin;
    }

    public boolean isEnableMobileSite() {
        return enableMobileSite;
    }

    public void setEnableMobileSite(boolean enableMobileSite) {
        this.enableMobileSite = enableMobileSite;
    }

    public boolean isJudgmobilesitebydomian() {
        return judgmobilesitebydomian;
    }

    public void setJudgmobilesitebydomian(boolean judgmobilesitebydomian) {
        this.judgmobilesitebydomian = judgmobilesitebydomian;
    }

    public String getMobilesitedomian() {
        return mobilesitedomian;
    }

    public void setMobilesitedomian(String mobilesitedomian) {
        this.mobilesitedomian = mobilesitedomian;
    }

    public boolean isEnablePseudo() {
        return enablePseudo;
    }

    public void setEnablePseudo(boolean enablePseudo) {
        this.enablePseudo = enablePseudo;
    }

    public boolean isEnableQQLogin() {
        return enableQQLogin;
    }

    public void setEnableQQLogin(boolean enableQQLogin) {
        this.enableQQLogin = enableQQLogin;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * 获取enableCacheArticleCount
     * 
     * @return enableCacheArticleCount
     */
    public boolean isEnableCacheArticleCount() {
        return enableCacheArticleCount;
    }

    /**
     * 
     * 设置enableCacheArticleCount
     * 
     * 
     * @param enableCacheArticleCount
     *            enableCacheArticleCount
     */
    public void setEnableCacheArticleCount(boolean enableCacheArticleCount) {
        this.enableCacheArticleCount = enableCacheArticleCount;
    }

    /**
     * 获取enableChapterIndexPage
     * 
     * @return enableChapterIndexPage
     */
    public boolean isEnableChapterIndexPage() {
        return enableChapterIndexPage;
    }

    /**
     * 
     * 设置enableChapterIndexPage
     * 
     * 
     * @param enableChapterIndexPage
     *            enableChapterIndexPage
     */
    public void setEnableChapterIndexPage(boolean enableChapterIndexPage) {
        this.enableChapterIndexPage = enableChapterIndexPage;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return enableGenerateHtmlFileを戻します。
     */
    public boolean isEnableGenerateHtmlFile() {
        return enableGenerateHtmlFile;
    }

    /**
     * <p>
     * TODO enableGenerateHtmlFileを設定。
     * </p>
     * 
     * @param enableGenerateHtmlFile
     *            enableGenerateHtmlFileを設定。
     */
    public void setEnableGenerateHtmlFile(boolean enableGenerateHtmlFile) {
        this.enableGenerateHtmlFile = enableGenerateHtmlFile;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return enableMailAuthを戻します。
     */
    public boolean isEnableMailAuth() {
        return enableMailAuth;
    }

    /**
     * <p>
     * TODO enableMailAuthを設定。
     * </p>
     * 
     * @param enableMailAuth
     *            enableMailAuthを設定。
     */
    public void setEnableMailAuth(boolean enableMailAuth) {
        this.enableMailAuth = enableMailAuth;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return mailSmtpAuthを戻します。
     */
    public boolean isMailSmtpAuth() {
        return mailSmtpAuth;
    }

    /**
     * <p>
     * TODO mailSmtpAuthを設定。
     * </p>
     * 
     * @param mailSmtpAuth
     *            mailSmtpAuthを設定。
     */
    public void setMailSmtpAuth(boolean mailSmtpAuth) {
        this.mailSmtpAuth = mailSmtpAuth;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return mailSmtpStarttlsEnableを戻します。
     */
    public boolean isMailSmtpStarttlsEnable() {
        return mailSmtpStarttlsEnable;
    }

    /**
     * <p>
     * TODO mailSmtpStarttlsEnableを設定。
     * </p>
     * 
     * @param mailSmtpStarttlsEnable
     *            mailSmtpStarttlsEnableを設定。
     */
    public void setMailSmtpStarttlsEnable(boolean mailSmtpStarttlsEnable) {
        this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return mailSmtpHostを戻します。
     */
    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    /**
     * <p>
     * TODO mailSmtpHostを設定。
     * </p>
     * 
     * @param mailSmtpHost
     *            mailSmtpHostを設定。
     */
    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return mailSmtpPortを戻します。
     */
    public int getMailSmtpPort() {
        return mailSmtpPort;
    }

    /**
     * <p>
     * TODO mailSmtpPortを設定。
     * </p>
     * 
     * @param mailSmtpPort
     *            mailSmtpPortを設定。
     */
    public void setMailSmtpPort(int mailSmtpPort) {
        this.mailSmtpPort = mailSmtpPort;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return mailSmtpUsernameを戻します。
     */
    public String getMailSmtpUsername() {
        return mailSmtpUsername;
    }

    /**
     * <p>
     * TODO mailSmtpUsernameを設定。
     * </p>
     * 
     * @param mailSmtpUsername
     *            mailSmtpUsernameを設定。
     */
    public void setMailSmtpUsername(String mailSmtpUsername) {
        this.mailSmtpUsername = mailSmtpUsername;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return mailSmtpPasswordを戻します。
     */
    public String getMailSmtpPassword() {
        return mailSmtpPassword;
    }

    /**
     * <p>
     * TODO mailSmtpPasswordを設定。
     * </p>
     * 
     * @param mailSmtpPassword
     *            mailSmtpPasswordを設定。
     */
    public void setMailSmtpPassword(String mailSmtpPassword) {
        this.mailSmtpPassword = mailSmtpPassword;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return mailSmtpFromを戻します。
     */
    public String getMailSmtpFrom() {
        return mailSmtpFrom;
    }

    /**
     * <p>
     * TODO mailSmtpFromを設定。
     * </p>
     * 
     * @param mailSmtpFrom
     *            mailSmtpFromを設定。
     */
    public void setMailSmtpFrom(String mailSmtpFrom) {
        this.mailSmtpFrom = mailSmtpFrom;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return maxSubscribeを戻します。
     */
    public int getMaxSubscribe() {
        return maxSubscribe;
    }

    /**
     * <p>
     * TODO maxSubscribeを設定。
     * </p>
     * 
     * @param maxSubscribe
     *            maxSubscribeを設定。
     */
    public void setMaxSubscribe(int maxSubscribe) {
        this.maxSubscribe = maxSubscribe;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return sendSubscribeIntevalを戻します。
     */
    public int getSendSubscribeInteval() {
        return sendSubscribeInteval;
    }

    /**
     * <p>
     * TODO sendSubscribeIntevalを設定。
     * </p>
     * 
     * @param sendSubscribeInteval
     *            sendSubscribeIntevalを設定。
     */
    public void setSendSubscribeInteval(int sendSubscribeInteval) {
        this.sendSubscribeInteval = sendSubscribeInteval;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return enableSingleBookを戻します。
     */
    public boolean isEnableSingleBook() {
        return enableSingleBook;
    }

    /**
     * <p>
     * TODO enableSingleBookを設定。
     * </p>
     * 
     * @param enableSingleBook
     *            enableSingleBookを設定。
     */
    public void setEnableSingleBook(boolean enableSingleBook) {
        this.enableSingleBook = enableSingleBook;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return rootDomainを戻します。
     */
    public String getRootDomain() {
        return rootDomain;
    }

    /**
     * <p>
     * TODO rootDomainを設定。
     * </p>
     * 
     * @param rootDomain
     *            rootDomainを設定。
     */
    public void setRootDomain(String rootDomain) {
        this.rootDomain = rootDomain;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return excludeSubDomainを戻します。
     */
    public String getExcludeSubDomain() {
        return excludeSubDomain;
    }

    /**
     * <p>
     * TODO excludeSubDomainを設定。
     * </p>
     * 
     * @param excludeSubDomain
     *            excludeSubDomainを設定。
     */
    public void setExcludeSubDomain(String excludeSubDomain) {
        this.excludeSubDomain = excludeSubDomain;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return reloadSingleBookIntervalを戻します。
     */
    public int getReloadSingleBookInterval() {
        return reloadSingleBookInterval;
    }

    /**
     * <p>
     * TODO reloadSingleBookIntervalを設定。
     * </p>
     * 
     * @param reloadSingleBookInterval
     *            reloadSingleBookIntervalを設定。
     */
    public void setReloadSingleBookInterval(int reloadSingleBookInterval) {
        this.reloadSingleBookInterval = reloadSingleBookInterval;
    }

    /**
     * 获取enableCleanImageUrl
     * 
     * @return enableCleanImageUrl
     */
    public boolean isEnableCleanImageUrl() {
        return enableCleanImageUrl;
    }

    /**
     * 
     * 设置enableCleanImageUrl
     * 
     * 
     * @param enableCleanImageUrl
     *            enableCleanImageUrl
     */
    public void setEnableCleanImageUrl(boolean enableCleanImageUrl) {
        this.enableCleanImageUrl = enableCleanImageUrl;
    }

    /**
     * 获取filterKeyWord
     * 
     * @return filterKeyWord
     */
    public String getFilterKeyWord() {
        return filterKeyWord;
    }

    /**
     * 
     * 设置filterKeyWord
     * 
     * 
     * @param filterKeyWord
     *            filterKeyWord
     */
    public void setFilterKeyWord(String filterKeyWord) {
        this.filterKeyWord = filterKeyWord;
    }

    /**
     * 获取enablePinyinUrl
     * 
     * @return enablePinyinUrl
     */
    public boolean isEnablePinyinUrl() {
        return enablePinyinUrl;
    }

    /**
     * 
     * 设置enablePinyinUrl
     * 
     * 
     * @param enablePinyinUrl
     *            enablePinyinUrl
     */
    public void setEnablePinyinUrl(boolean enablePinyinUrl) {
        this.enablePinyinUrl = enablePinyinUrl;
    }

    @Override
    protected void loadData() {
        initCollections(new String[] { "collectionProperties.boolean" });
        uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        mobileUri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        filePath = YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH);
        relativeIamgePath = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH);
        rootImagePath =  YiDuConstants.yiduConf.getString(YiDuConfig.ROOT_IMAGE_PATH);
        cacheEffective = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CACHE_EFFECTIVE, true);
        cleanUrl = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CLEAN_URL, true);
        gzipEffective = YiDuConstants.yiduConf.getBoolean(YiDuConfig.GZIP_EFFECTIVE, true);
        adEffective = YiDuConstants.yiduConf.getBoolean(YiDuConfig.AD_EFFECTIVE, true);
        countPerPage = YiDuConstants.yiduConf.getInt(YiDuConfig.COUNT_PER_PAGE, 15);
        maxBookcase = YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_BOOKCASE, 15);
        themeName = YiDuConstants.yiduConf.getString(YiDuConfig.THEME_NAME);
        createIndexPage = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CREATE_INDEXPAGE, true);
        createSiteMap = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CREATE_SITEMAP, true);
        siteMapType = YiDuConstants.yiduConf.getString(YiDuConfig.SITEMAP_TYPE);
        xmlSiteMapListWithNoPageURL = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_LIST_WITH_NO_PAGE_URL);
        xmlSiteMapListURL = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_LIST_URL);
        xmlSiteMapInfoURL = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_INFO_URL);
        xmlSiteMapChaterListURL = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_CHATERLIST_URL);
        xmlSiteMapReaderURL = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_READER_URL);
        compressEffective = YiDuConstants.yiduConf.getBoolean(YiDuConfig.COMPRESS_EFFECTIVE,false);
        txtEncoding = YiDuConstants.yiduConf.getString(YiDuConfig.TXT_ENCODING);

        addReviewWithoutLogin = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ADD_REVIEW_WITHOUT_LOGIN, true);
        enableMobileSite = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_MOBILE_SITE, true);
        judgmobilesitebydomian = YiDuConstants.yiduConf.getBoolean(YiDuConfig.JUDG_MOBILESITE_BY_DOMIAN, true);
        mobilesitedomian = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILESITE_DOMIAN);
        enablePseudo = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PSEUDO, false);
        enableQQLogin = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_QQLOGIN, false);
        enableCacheArticleCount = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, false);
        enableChapterIndexPage = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false);
        enableGenerateHtmlFile = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_GENERATE_HTML_FILE, false);
        enableMailAuth = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_MAIL_AUTH, false);
        mailSmtpAuth = YiDuConstants.yiduConf.getBoolean(YiDuConfig.MAIL_SMTP_AUTH, false);
        mailSmtpStarttlsEnable = YiDuConstants.yiduConf.getBoolean(YiDuConfig.MAIL_SMTP_STARTTLS_ENABLE, false);
        mailSmtpHost = YiDuConstants.yiduConf.getString(YiDuConfig.MAIL_SMTP_HOST);
        mailSmtpPort = YiDuConstants.yiduConf.getInt(YiDuConfig.MAIL_SMTP_PORT, 25);
        mailSmtpUsername = YiDuConstants.yiduConf.getString(YiDuConfig.MAIL_SMTP_USERNAME);
        mailSmtpPassword = YiDuConstants.yiduConf.getString(YiDuConfig.MAIL_SMTP_PASSWORD);
        mailSmtpFrom = YiDuConstants.yiduConf.getString(YiDuConfig.MAIL_SMTP_FROM);
        maxSubscribe = YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_SUBSCRIBE, 30);
        sendSubscribeInteval = YiDuConstants.yiduConf.getInt(YiDuConfig.SEND_SUBSCRIBE_INTEVAL, 15);
        enableSingleBook = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_SINGLE_BOOK, false);
        rootDomain = YiDuConstants.yiduConf.getString(YiDuConfig.ROOT_DOMAIN);
        excludeSubDomain = YiDuConstants.yiduConf.getString(YiDuConfig.EXCLUDE_SUB_DOMAIN);
        reloadSingleBookInterval = YiDuConstants.yiduConf.getInt(YiDuConfig.RELOAD_SINGLE_BOOK_INTERVAL, 120);
        enableCleanImageUrl = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CLEAN_IMAGE_URL, false);
        filterKeyWord = YiDuConstants.yiduConf.getString(YiDuConfig.FILTER_KEYWORD);
        enablePinyinUrl = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false);

        // 设定文件初期读入
        try {
            PropertiesConfiguration jdbcConf = new PropertiesConfiguration("jdbc.properties");

            dburl = jdbcConf.getString(YiDuConfig.JDBC_URL);
            username = jdbcConf.getString(YiDuConfig.JDBC_USERNAME);
            password = jdbcConf.getString(YiDuConfig.JDBC_PASSWORD);

            PropertiesConfiguration qqconnectConfig = new PropertiesConfiguration("qqconnectconfig.properties");
            appId = qqconnectConfig.getString(YiDuConfig.APP_ID);
            appKey = qqconnectConfig.getString(YiDuConfig.APP_KEY);

        } catch (ConfigurationException e) {
            logger.error(e);
        }

    }

    public String save() {

        YiDuConstants.yiduConf.setProperty(YiDuConfig.URI, uri);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MOBILE_URI, mobileUri);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.FILE_PATH, filePath);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.RELATIVE_IAMGE_PATH, relativeIamgePath);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.CACHE_EFFECTIVE, cacheEffective);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.CLEAN_URL, cleanUrl);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.GZIP_EFFECTIVE, gzipEffective);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.AD_EFFECTIVE, adEffective);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.COUNT_PER_PAGE, countPerPage);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAX_BOOKCASE, maxBookcase);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.THEME_NAME, themeName);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.CREATE_INDEXPAGE, createIndexPage);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.CREATE_SITEMAP, createSiteMap);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.SITEMAP_TYPE, siteMapType);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.XML_SITEMAP_LIST_WITH_NO_PAGE_URL, xmlSiteMapListWithNoPageURL);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.XML_SITEMAP_LIST_URL, xmlSiteMapListURL);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.XML_SITEMAP_INFO_URL, xmlSiteMapInfoURL);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.XML_SITEMAP_CHATERLIST_URL, xmlSiteMapChaterListURL);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.XML_SITEMAP_READER_URL, xmlSiteMapReaderURL);

        YiDuConstants.yiduConf.setProperty(YiDuConfig.TXT_ENCODING, txtEncoding);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.COMPRESS_EFFECTIVE,compressEffective);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ADD_REVIEW_WITHOUT_LOGIN, addReviewWithoutLogin);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_MOBILE_SITE, enableMobileSite);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.JUDG_MOBILESITE_BY_DOMIAN, judgmobilesitebydomian);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MOBILESITE_DOMIAN, mobilesitedomian);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_PSEUDO, enablePseudo);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_QQLOGIN, enableQQLogin);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, enableCacheArticleCount);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, enableChapterIndexPage);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_GENERATE_HTML_FILE, enableGenerateHtmlFile);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_MAIL_AUTH, enableMailAuth);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAIL_SMTP_AUTH, mailSmtpAuth);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAIL_SMTP_STARTTLS_ENABLE, mailSmtpStarttlsEnable);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAIL_SMTP_HOST, mailSmtpHost);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAIL_SMTP_PORT, mailSmtpPort);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAIL_SMTP_USERNAME, mailSmtpUsername);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAIL_SMTP_PASSWORD, mailSmtpPassword);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAIL_SMTP_FROM, mailSmtpFrom);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAX_SUBSCRIBE, maxSubscribe);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.SEND_SUBSCRIBE_INTEVAL, sendSubscribeInteval);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_SINGLE_BOOK, enableSingleBook);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ROOT_DOMAIN, rootDomain);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.EXCLUDE_SUB_DOMAIN, excludeSubDomain);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.RELOAD_SINGLE_BOOK_INTERVAL, reloadSingleBookInterval);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_CLEAN_IMAGE_URL, enableCleanImageUrl);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.FILTER_KEYWORD, Utils.escapePropterties(filterKeyWord));
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_PINYINURL, enablePinyinUrl);

        try {
            File yiduConfFile = new File(YiDuConstants.yiduConf.getPath());
            OutputStream out = new FileOutputStream(yiduConfFile);
            YiDuConstants.yiduConf.save(out);

            PropertiesConfiguration jdbcConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("jdbc.properties"));

            jdbcConf.setProperty(YiDuConfig.JDBC_URL, dburl);
            jdbcConf.setProperty(YiDuConfig.JDBC_USERNAME, username);
            jdbcConf.setProperty(YiDuConfig.JDBC_PASSWORD, password);

            File jdbcFile = new File(jdbcConf.getPath());
            out = new FileOutputStream(jdbcFile);
            jdbcConf.save(out);

            PropertiesConfiguration qqconnectConfig = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("qqconnectconfig.properties"));

            qqconnectConfig.setProperty(YiDuConfig.APP_ID, appId);
            qqconnectConfig.setProperty(YiDuConfig.APP_KEY, appKey);
/*            qqconnectConfig.setProperty(YiDuConfig.REDIRECT_URI, YiDuConstants.yiduConf.getString(YiDuConfig.URI)
                    + QQLoginAction.URL);*/

            File qqconnectConfigFile = new File(qqconnectConfig.getPath());
            out = new FileOutputStream(qqconnectConfigFile);
            qqconnectConfig.save(out);

        } catch (Exception e) {
            addActionError(e.getMessage());
            logger.error(e);
            return ADMIN_ERROR;
        }
        loadData();
        addActionMessage(getText("messages.save.success"));
        return INPUT;
    }
}
