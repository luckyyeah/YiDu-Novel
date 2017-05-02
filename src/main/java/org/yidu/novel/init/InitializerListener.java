package org.yidu.novel.init;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.impl.StdScheduler;
import org.springframework.web.context.WebApplicationContext;
import org.yidu.novel.cache.ArticleCountManager;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.cache.CategoryCacheManager;
import org.yidu.novel.cache.SingleBookManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 程序初始化和结束的代码
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class InitializerListener implements ServletContextListener {
    /**
     * 串行化版本统一标识符
     */
    protected Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            // 设定文件初期读入
            PropertiesConfiguration yiduConf = new PropertiesConfiguration("yidu.properties");
            // 设定文件自动更新
            FileChangedReloadingStrategy reloadStrategy = new FileChangedReloadingStrategy();
            yiduConf.setReloadingStrategy(reloadStrategy);
            YiDuConstants.yiduConf = yiduConf;

            // 加载伪原创设置
            YiDuConstants.pseudoConf = new PropertiesConfiguration("pseudo.properties");
            YiDuConstants.pseudoConf.setReloadingStrategy(reloadStrategy);

            // 初始化缓存
            CacheManager.initCacheManager();

            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CACHE_ARTICLE_COUNT, false)) {
                // 初始化小说件数MAP
                ArticleCountManager.initArticleCountManager();
            }

            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_SINGLE_BOOK, false)) {
                // 初始化小说拼音和编号映射件数MAP
                SingleBookManager.initSingleBookManager();
            }
            
            // 初始化分类信息MAP
            CategoryCacheManager.initCategoryCacheManager();

            logger.info("Initialize successfully.");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO 销毁处理还不完全，不能正常关闭
        logger.info("going to destroy context .");
        // 销毁缓存
        CacheManager.dispose();
        // 销毁小说件数
        ArticleCountManager.dispose();
        // 小说单本小说
        SingleBookManager.dispose();

        // 销毁spring quartz
        WebApplicationContext webApplicationContext = (WebApplicationContext) arg0.getServletContext().getAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        StdScheduler schedulerFactory = (StdScheduler) webApplicationContext.getBean("schedulerFactory");

        // 关闭schedulerFactory
        if (schedulerFactory != null) {

            schedulerFactory.shutdown();
        }

        // 休息2秒，等待结束
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }

        // This manually deregisters JDBC driver
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.debug(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                logger.error(String.format("Error deregistering driver %s", driver), e);
            }
        }
    }
}
