package org.yidu.novel.batch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.action.IndexAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 生成静态首页
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class CreateIndexJob extends QuartzJobBean {
    /**
     * 输出log
     */
    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.debug("CreateIndexJob start.");
        boolean createindexFlag = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CREATE_INDEXPAGE, false);
        if (createindexFlag) {
            // try {
            //
            // Map<String, Object> root = new HashMap<String, Object>();
            //
            // //root.put("encodeURL", new );
            //
            //
            // String currentPath =
            // CreateSiteMapJob.class.getClassLoader().getResource("").getPath();
            // // 减16的字符是WEB-INF/classes/
            // String webRootPath = currentPath.substring(0,
            // currentPath.length() - 16);
            // FreeMarkertUtil.initConfig(webRootPath + "themes/"
            // + YiDuConstants.yiduConf.getString(YiDuConfig.THEME_NAME));
            // Writer out = new OutputStreamWriter(new
            // FileOutputStream(webRootPath + "index.html"), "UTF-8");
            //
            // // 生成html文件
            // FreeMarkertUtil.processTemplate("index.ftl", root, out);
            // logger.debug("CreateIndexJob normally end.");
            // } catch (Exception e) {
            // logger.error(e);
            // logger.debug("CreateIndexJob abnormally end.");
            // }

            // 通过HTTP CLient去获取主页内容，然后保存到html文件中
            String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
            String responseBody = Utils.getContentFromUri(uri + IndexAction.URL);
            try {
                String currentPath = CreateSiteMapJob.class.getClassLoader().getResource("").getPath();
                // 减16的字符是WEB-INF/classes/
                String webRootPath = currentPath.substring(0, currentPath.length() - 16);
                if (StringUtils.isNotBlank(responseBody)) {
                    OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(new File(webRootPath
                            + "index.html")), YiDuConstants.ENCODING_UTF_8);
                    outputStream.write(responseBody);
                    outputStream.flush();
                    outputStream.close();
                }
                logger.debug("CreateIndexJob normally end.");
            } catch (Exception e) {
                logger.error(e);
                logger.debug("CreateIndexJob abnormally end.");
            }
        }
    }
}