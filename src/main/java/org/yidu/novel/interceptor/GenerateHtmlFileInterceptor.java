package org.yidu.novel.interceptor;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.ChapterDTO;
import org.yidu.novel.utils.StaticUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * <p>
 * 生成静态页面拦截器
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.5
 * @author shinpa.you
 */
public class GenerateHtmlFileInterceptor extends AbstractInterceptor {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4852225907669227478L;
    /**
     * logger
     */
    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public String intercept(final ActionInvocation invocation) throws Exception {
        String rtn = invocation.invoke();

        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_GENERATE_HTML_FILE, false)) {
            // 如果是阅读页的话，同时生成静态页面 并且不生成分卷阅读
            if (invocation.getAction() instanceof ReaderAction
                    && ((ReaderAction) invocation.getAction()).getToChapterno() == 0) {
                ReaderAction action = (ReaderAction) invocation.getAction();
                logger.info("going to Generate Html file." + YiDuConstants.requestUri.get());

                String templatePath = "themes/" + YiDuConstants.yiduConf.getString(YiDuConfig.THEME_NAME) + "/pc/"
                        + action.getTempName() + ".ftl";

                StaticUtils.crateHTML(ServletActionContext.getServletContext(), action, templatePath,
                        YiDuConstants.requestUri.get());

                // 判断上一章的静态页是否存在
                ChapterDTO chapter = action.getChapter();
                if (chapter.getPreChapterno() != 0) {
                    // TODO 如果章节ID和小说ID一样的话，会出现问题，将来改吧
                    String preUri = StringUtils.replaceOnce(YiDuConstants.requestUri.get(),
                            String.valueOf(chapter.getChapterno()), String.valueOf(chapter.getPreChapterno()));

                    String preChapterPath = ServletActionContext.getServletContext().getRealPath("/") + "/" + preUri;
                    File preChpaterHtml = new File(preChapterPath);
                    if (preChpaterHtml.exists() && preChpaterHtml.lastModified() < chapter.getPostdate().getTime()) {
                        // 只有当文件存在，并且最后修改时间比当前章节的发布时间小的情况才生成前一张，因为下一章已经更新啦！
                        action.setChapterno(chapter.getPreChapterno());
                        action.execute();
                        logger.info("going to Generate Html file." + preChapterPath);
                        StaticUtils.crateHTML(ServletActionContext.getServletContext(), action, templatePath, preUri);
                    }

                }

            }
        }
        return rtn;
    }

    public static String getVarNameFromMethodName(String methodName) {
        String varName = StringUtils.substringAfter(methodName, "get");
        String firstChar = StringUtils.substring(varName, 0, 1);
        varName = varName.replaceFirst(firstChar, firstChar.toLowerCase());
        return varName;
    }

}
