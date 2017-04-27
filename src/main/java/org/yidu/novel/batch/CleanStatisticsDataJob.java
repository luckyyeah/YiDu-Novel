package org.yidu.novel.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.service.ArticleService;

/**
 * 
 * <p>
 * 清理统计信息
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class CleanStatisticsDataJob extends QuartzJobBean {
    /**
     * 输出log
     */
    private Log logger = LogFactory.getLog(this.getClass());

    /**
     * 小说关联操作服务
     */

    private ArticleService articleService;

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

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.debug("CleanStatisticsDataJob start.");
        try {
            articleService.cleanStatistics();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.debug("CleanStatisticsDataJob normally end.");
    }
}