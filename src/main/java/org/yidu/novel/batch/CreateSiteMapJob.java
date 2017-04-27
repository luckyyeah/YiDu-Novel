package org.yidu.novel.batch;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.action.SiteMapAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.constant.YiDuConstants.SiteMapType;
import org.yidu.novel.dto.CategoryCountDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.service.ChapterService;
import org.yidu.novel.utils.DateUtils;
import org.yidu.novel.utils.FileUtils;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 生成静态网站地图
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class CreateSiteMapJob extends QuartzJobBean {
    /**
     * 输出log
     */
    private Log log = LogFactory.getLog(this.getClass());
    /**
     * 优先级 1
     */
    private static final String PRIORITY_1 = "1";

    /**
     * 优先级 0.9
     */
    private static final String PRIORITY_09 = "0.9";

    /**
     * 优先级 0.8
     */
    private static final String PRIORITY_08 = "0.8";

    /**
     * 优先级 0.7
     */
    private static final String PRIORITY_07 = "0.7";

    /**
     * 优先级 0.5
     */
    private static final String PRIORITY_05 = "0.5";

    /**
     * 更新频率-总是更新
     */
    private static final String CHANGEFREQ_ALWAYS = "always";
    /**
     * 更新频率-每天更新
     */
    private static final String CHANGEFREQ_DAILY = "daily";

    /**
     * 更新频率-每月更新
     */
    private static final String CHANGEFREQ_MONTHLY = "monthly";

    /**
     * 每个文件默认的URL数量
     */
    private static final int COUNT_PER_FILE = 40000;

    /**
     * 网站地图路径
     */
    private static final String SITEMAP_DIR = "sitemap";
    /**
     * 小说关联操作服务
     */
    private ArticleService articleService;
    /**
     * 章节关联操作服务
     */
    private ChapterService chapterService;

    /**
     * 手机移动地图的文件前缀
     */
    private String mobileSitemapPrefix = "mobile_";

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

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.debug("CreateSiteMapJob start.");
        boolean createSiteMapFlag = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CREATE_SITEMAP, false);
        if (createSiteMapFlag) {
            String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
            String sitemapUri = uri + (StringUtils.endsWith(uri, "/") ? "" : "/") + SITEMAP_DIR + "/";
            try {
                String currentPath = CreateSiteMapJob.class.getClassLoader().getResource("").getPath();
                File f = new File(currentPath).getParentFile().getParentFile();
                currentPath = f.getAbsolutePath();
                log.debug(currentPath);
                String sitemapDir = currentPath + "/" + SITEMAP_DIR + "/";

                log.debug("sitemap dir: " + sitemapDir);
                if (!new File(sitemapDir).exists()) {
                    new File(sitemapDir).mkdirs();
                }

                if (SiteMapType.XML.getName().equalsIgnoreCase(
                        YiDuConstants.yiduConf.getString(YiDuConfig.SITEMAP_TYPE))) {
                    createXmlSiteMap(sitemapDir, sitemapUri, false);
                    createXmlSiteMap(sitemapDir, sitemapUri, true);
                } else {
                    String responseBody = Utils.getContentFromUri(uri + SiteMapAction.URL);
                    if (StringUtils.isNotBlank(responseBody)) {
                        File destFile = new File(sitemapDir + "/index.html");
                        FileUtils.writeFile(destFile, responseBody, false);
                    }
                }
                log.debug("CreateSiteMapJob normally end.");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private void createXmlSiteMap(String sitemapDir, String sitemapUri, boolean isCreateMobileSiteMap) {

        List<String> articleListUrlList = ceateIndexAndListBaiduXmlSiteMap(sitemapDir, sitemapUri,
                isCreateMobileSiteMap);
        List<String> infoUrlList = ceateInfoBaiduXmlSiteMap(sitemapDir, sitemapUri, isCreateMobileSiteMap);
        List<String> chapterListUrlList = new ArrayList<String>();
        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false)) {
            chapterListUrlList = ceateChapterListBaiduXmlSiteMap(sitemapDir, sitemapUri, isCreateMobileSiteMap);
        }
        List<String> chapterUrlList = ceateReaderBaiduXmlSiteMap(sitemapDir, sitemapUri, isCreateMobileSiteMap);

        // 生成sitemap索引文件
        StringBuffer indexBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        indexBuffer.append("<sitemapindex>\n");
        // 添加List页的sitemap
        for (String url : articleListUrlList) {
            appendIndex(indexBuffer, url);
        }
        // 添加info页的sitemap
        for (String url : infoUrlList) {
            appendIndex(indexBuffer, url);
        }
        // 添加c页的sitemap
        for (String url : chapterListUrlList) {
            appendIndex(indexBuffer, url);
        }
        // 添加reader页的sitemap
        for (String url : chapterUrlList) {
            appendIndex(indexBuffer, url);
        }
        indexBuffer.append("</sitemapindex>");

        String fileName = sitemapDir + "sitemap.xml";
        if (isCreateMobileSiteMap) {
            fileName = sitemapDir + mobileSitemapPrefix + "sitemap.xml";
        }
        FileUtils.writeFile(new File(fileName), indexBuffer.toString(), false);
    }

    /**
     * 制作小说列表和主页的网站地图<br>
     * 分类合计不会超过50000行，所以这里就不分文件啦
     * 
     * @param sitemapUri
     * 
     * @param 文件列表
     */
    private List<String> ceateIndexAndListBaiduXmlSiteMap(String sitemapDir, String sitemapUri,
            boolean isCreateMobileSiteMap) {

        StringBuffer sb = new StringBuffer();
        sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<urlset>\n");
        String siteUrl = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        if (isCreateMobileSiteMap) {
            siteUrl = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        }
        // 添加主页
        sb.append(createURL(siteUrl, new Date(), CHANGEFREQ_ALWAYS, PRIORITY_1));
        // 获取各个分类小说件数
        List<CategoryCountDTO> categoryCountList = articleService.getCountPerCategory();
        if (Utils.isDefined(categoryCountList)) {
            for (CategoryCountDTO categoryCount : categoryCountList) {
                // 计算分页数
                int pages = categoryCount.getCount() / YiDuConstants.yiduConf.getInt(YiDuConfig.COUNT_PER_PAGE, 15);
                if (categoryCount.getCount() % YiDuConstants.yiduConf.getInt(YiDuConfig.COUNT_PER_PAGE, 15) > 0) {
                    pages++;
                }
                for (int i = 1; i <= pages; i++) {
                    String url = "";
                    if (i == 1) {
                        url = MessageFormat.format(
                                YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_LIST_WITH_NO_PAGE_URL),
                                categoryCount.getCategory());
                    } else {
                        url = MessageFormat.format(YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_LIST_URL),
                                categoryCount.getCategory(), i);
                    }
                    sb.append(createURL(YiDuConstants.yiduConf.getString(YiDuConfig.URI) + url, new Date(),
                            CHANGEFREQ_ALWAYS, PRIORITY_08));
                }
            }
        }
        sb.append("</urlset>");
        String fileName = sitemapDir + "sitemap_list.xml";
        if (isCreateMobileSiteMap) {
            fileName = sitemapDir + mobileSitemapPrefix + "sitemap_list.xml";
        }
        FileUtils.writeFile(new File(fileName), sb.toString(), false);
        List<String> urlList = new ArrayList<String>();
        fileName = sitemapUri + "sitemap_list.xml";
        if (isCreateMobileSiteMap) {
            fileName = sitemapUri + mobileSitemapPrefix + "sitemap_list.xml";
        }
        urlList.add(fileName);
        return urlList;
    }

    /**
     * 制作小说介绍页的网站地图<br>
     * 
     * @param sitemapUri
     * 
     * @param 文件列表
     */
    private List<String> ceateInfoBaiduXmlSiteMap(String sitemapDir, String sitemapUri, boolean isCreateMobileSiteMap) {
        int count = articleService.getCount(new ArticleSearchBean());
        int files = count / COUNT_PER_FILE;
        if (count % COUNT_PER_FILE > 0) {
            files++;
        }

        List<String> urlList = new ArrayList<String>();
        String fileNameFormat = "sitemap_info_{0}.xml";
        if (isCreateMobileSiteMap) {
            fileNameFormat = mobileSitemapPrefix + "sitemap_info_{0}.xml";
        }
        String fileName = "";
        for (int i = 1; i <= files; i++) {
            StringBuffer sb = new StringBuffer();
            sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<urlset>\n");
            ArticleSearchBean searchBean = new ArticleSearchBean();
            Pagination pagination = new Pagination(COUNT_PER_FILE, i);
            searchBean.setPagination(pagination);
            List<TArticle> articleList = articleService.find(searchBean);
            for (TArticle article : articleList) {
                sb.append(createURL(constructURL(article, isCreateMobileSiteMap), article.getLastupdate(),
                        CHANGEFREQ_DAILY, PRIORITY_09));
            }
            sb.append("</urlset>");
            fileName = MessageFormat.format(sitemapDir + fileNameFormat, i);
            FileUtils.writeFile(new File(fileName), sb.toString(), false);
            urlList.add(MessageFormat.format(sitemapUri + fileNameFormat, i));
        }
        return urlList;
    }

    private List<String> ceateChapterListBaiduXmlSiteMap(String sitemapDir, String sitemapUri,
            boolean isCreateMobileSiteMap) {
        int count = articleService.getCount(new ArticleSearchBean());
        int files = count / COUNT_PER_FILE;
        if (count % COUNT_PER_FILE > 0) {
            files++;
        }

        List<String> urlList = new ArrayList<String>();
        String fileNameFormat = "sitemap_chapterList_{0}.xml";
        if (isCreateMobileSiteMap) {
            fileNameFormat = mobileSitemapPrefix + "sitemap_chapterList_{0}.xml";
        }
        String fileName = "";
        for (int i = 1; i <= files; i++) {
            StringBuffer sb = new StringBuffer();
            sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<urlset>\n");
            ArticleSearchBean searchBean = new ArticleSearchBean();
            Pagination pagination = new Pagination(COUNT_PER_FILE, i);
            searchBean.setPagination(pagination);
            List<TArticle> articleList = articleService.find(searchBean);
            for (TArticle article : articleList) {
                sb.append(createURL(constructChapterListURL(article, isCreateMobileSiteMap), article.getLastupdate(),
                        CHANGEFREQ_DAILY, PRIORITY_07));
            }
            sb.append("</urlset>");
            fileName = MessageFormat.format(sitemapDir + fileNameFormat, i);
            FileUtils.writeFile(new File(fileName), sb.toString(), false);
            urlList.add(MessageFormat.format(sitemapUri + fileNameFormat, i));
        }
        return urlList;
    }

    private List<String> ceateReaderBaiduXmlSiteMap(String sitemapDir, String sitemapUri, boolean isCreateMobileSiteMap) {

        int count = chapterService.getCount(new ChapterSearchBean());
        int files = count / COUNT_PER_FILE;
        if (count % COUNT_PER_FILE > 0) {
            files++;
        }

        List<String> urlList = new ArrayList<String>();
        String fileNameFormat = "sitemap_reader_{0}.xml";
        if (isCreateMobileSiteMap) {
            fileNameFormat = mobileSitemapPrefix + "sitemap_reader_{0}.xml";
        }
        String fileName = "";
        for (int i = 1; i <= files; i++) {
            StringBuffer sb = new StringBuffer();
            sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<urlset>\n");
            ChapterSearchBean searchBean = new ChapterSearchBean();
            Pagination pagination = new Pagination(COUNT_PER_FILE, i);
            pagination.setSortColumn(TChapter.PROP_CHAPTERNO);
            pagination.setSortOrder("ASC");
            searchBean.setPagination(pagination);
            List<TChapter> chapterList = chapterService.find(searchBean);
            for (TChapter chapter : chapterList) {
                sb.append(createURL(constructURL(chapter, isCreateMobileSiteMap), chapter.getPostdate(),
                        CHANGEFREQ_MONTHLY, PRIORITY_05));
            }
            sb.append("</urlset>");
            fileName = MessageFormat.format(sitemapDir + fileNameFormat, i);
            FileUtils.writeFile(new File(fileName), sb.toString(), false);
            urlList.add(MessageFormat.format(sitemapUri + fileNameFormat, i));
        }
        return urlList;
    }

    /**
     * 向sitemap索引文件中添加sitemap文件索引
     * 
     * @param fileIndex
     *            文件索引
     * @param indexBuffer
     *            StringBuffer
     * @param uri
     *            路径
     */
    private void appendIndex(StringBuffer indexBuffer, String url) {
        indexBuffer.append("\t<sitemap>\n");
        indexBuffer.append("\t\t<loc>" + url + "</loc>\n");
        indexBuffer.append("\t\t<lastmod>" + DateUtils.format(new Date()) + "</lastmod>\n");
        indexBuffer.append("\t</sitemap>\n");
    }

    /**
     * 向sitemap文件中添加List页&lt;url&gt;xxx&lt;/url&gt;
     * 
     * @param url
     *            url
     * @param lastmod
     *            最后更新时间
     * @param changefreq
     *            更新频率
     * @param priority
     *            优先级
     * @return List页URL
     */
    private String createURL(String url, Date lastmod, String changefreq, String priority) {
        StringBuffer sb = new StringBuffer("\t<url>\n");
        sb.append("\t\t<loc>" + url + "</loc>\n");
        sb.append("\t\t<lastmod>" + DateUtils.format(lastmod) + "</lastmod>\n");
        sb.append("\t\t<changefreq>" + changefreq + "</changefreq>\n");
        sb.append("\t\t<priority>" + priority + "</priority>\n");
        sb.append("\t</url>\n");
        return sb.toString();
    }

    /**
     * 构造小说阅读页URL
     * 
     * @param chapter
     *            章节信息
     * @return 小说阅读页URL
     */
    private String constructURL(TChapter chapter, boolean isCreateMobileSiteMap) {
        String loc = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_READER_URL);
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        if (isCreateMobileSiteMap) {
            uri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        }
        int subDir = chapter.getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
        int articleNo = chapter.getArticleno();
        int chapterNo = chapter.getChapterno();

        if (StringUtils.contains(loc, "{pinyin}")) {
            TArticle article = articleService.getByNo(articleNo);
            loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo))
                    .replace("{chapter_no}", String.valueOf(chapterNo))
                    .replace("{pinyin}", String.valueOf(article.getPinyin()));
        } else {
            loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo))
                    .replace("{chapter_no}", String.valueOf(chapterNo));
        }
        if (!uri.endsWith("/") && !loc.startsWith("/")) {
            uri += "/";
        }
        return uri + loc;
    }

    /**
     * 构造小说信息页URL
     * 
     * @param article
     *            小说信息
     * @return 小说信息页URL
     */
    private String constructURL(TArticle article, boolean isCreateMobileSiteMap) {
        String loc = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_INFO_URL);
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        if (isCreateMobileSiteMap) {
            uri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        }
        int subDir = article.getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
        int articleNo = article.getArticleno();
        // /info/{sub_dir}/{article_no}.html
        loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo))
                .replace("{pinyin}", String.valueOf(article.getPinyin()));
        if (!uri.endsWith("/") && !loc.startsWith("/")) {
            uri += "/";
        }
        return uri + loc;
    }

    /**
     * 构造章节列表信息页URL
     * 
     * @param article
     *            小说信息
     * @return 章节列表页URL
     */
    private String constructChapterListURL(TArticle article, boolean isCreateMobileSiteMap) {
        String loc = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_CHATERLIST_URL);
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        if (isCreateMobileSiteMap) {
            uri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        }
        int subDir = article.getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
        int articleNo = article.getArticleno();
        // /info/{sub_dir}/{article_no}.html
        loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo))
                .replace("{pinyin}", String.valueOf(article.getPinyin()));
        if (!uri.endsWith("/") && !loc.startsWith("/")) {
            uri += "/";
        }
        return uri + loc;
    }

}