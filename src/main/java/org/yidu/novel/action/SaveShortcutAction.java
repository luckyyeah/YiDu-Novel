package org.yidu.novel.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 保存快捷方式到桌面Action
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */

@Result(name = "download", type = "stream", params = { "inputName", "is", "contentType",
        "application/x-download; charset=UTF-8", "contentLength", "${length}", "contentDisposition",
        "attachment; filename =${downloadFileName}" })
@Action(value = "saveShortcut")
public class SaveShortcutAction extends AbstractPublicBaseAction {

    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1982119718178098202L;

    /**
     * 功能名称。
     */
    public static final String NAME = "download";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 返回结果值
     */
    public static final String RESULT_DOWNLOAD = "download";

    /**
     * 文件长度
     * */
    private long length;
    /**
     * 输出流
     * */
    private InputStream is;

    /**
     * 获得下载文件名
     * 
     * @return 下载文件名
     */
    public String getDownloadFileName() {
        try {
            return URLEncoder.encode(getText("label.system.name") + ".url", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        return getText("label.system.url") + ".url";
    }

    /**
     * 获取文件长度
     * 
     * @return 文件长度
     */
    public long getLength() {
        return length;
    }

    /**
     * 获取InputStream
     * 
     * @return InputStream
     */
    public InputStream getIs() {
        return is;
    }

    @Override
    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        String shortcutStr = "[InternetShortcut]\nURL=" + getText("label.system.url");
        is = new ByteArrayInputStream(shortcutStr.getBytes());
        length = shortcutStr.length();
        logger.debug("execute normally end.");
        return RESULT_DOWNLOAD;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @Override
    public String getTempName() {
        // TODO Auto-generated method stub
        return null;
    }

}
