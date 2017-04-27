package org.yidu.novel.utils;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * <p>
 * 生成静态页面用
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class FreeMarkertUtil {

    /**
     * 输出log
     */
    private static Log logger = LogFactory.getLog(FreeMarkertUtil.class);

    /**
     * freemarker配置
     */
    private static Configuration config = new Configuration();

    private FreeMarkertUtil() {
        // do nothing
    }

    /**
     * 处理模版
     * 
     * @param templateName
     *            模板名字
     * @param root
     *            模板根 用于在模板内输出结果集
     * @param out
     *            输出对象 具体输出到哪里
     */
    public static void processTemplate(String templateName, Map<?, ?> root, Writer out) {
        try {
            // 获得模板
            Template template = config.getTemplate(templateName, "utf-8");
            // 生成文件（这里是我们是生成html）
            template.process(root, out);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (TemplateException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                out.close();
                out = null;
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 初始化模板配置
     * 
     * @param servletContext
     *            javax.servlet.ServletContext
     * @param templateDir
     *            模板位置
     */
    public static void initConfig(ServletContext servletContext, String templateDir) {
        config.setDefaultEncoding("utf-8");
        config.setServletContextForTemplateLoading(servletContext, templateDir);
        config.setObjectWrapper(new DefaultObjectWrapper());
    }

    /**
     * 初始化模板配置
     * 
     * @param templateDir
     *            模板位置
     * @throws IOException
     *             IO异常
     */
    public static void initConfig(String templateDir) throws IOException {
        // 设置FreeMarker的模版文件位置
        config.setDirectoryForTemplateLoading(new File(templateDir));
    }
}
