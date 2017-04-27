package org.yidu.novel.action.user;

import java.io.File;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 用户编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "regiauthor")
public class RegiAuthorAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 6332048762572310141L;

    /**
     * 功能名称。
     */
    public static final String NAME = "regiauthor";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    private File sample;
    private String sampleContentType;
    private String sampleFileName;

    public File getSample() {
        return sample;
    }

    public void setSample(File sample) {
        this.sample = sample;
    }

    public String getSampleContentType() {
        return sampleContentType;
    }

    public void setSampleContentType(String sampleContentType) {
        this.sampleContentType = sampleContentType;
    }

    public String getSampleFileName() {
        return sampleFileName;
    }

    public void setSampleFileName(String sampleFileName) {
        this.sampleFileName = sampleFileName;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_REGI_AUTHOR;
    }

    @Override
    public String getTempName() {
        return "user/regiauthor";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");

        logger.debug("loadData normally end.");
    }

    /**
     * <p>
     * 保存画面的内容
     * </p>
     * 
     * @return
     * @throws Exception
     */
    public String register() {
        logger.debug("register start.");
        if (ArrayUtils.contains(YiDuConstants.ALLOW_SAMPLE_TYPES, getSampleContentType())) {
            try {
                // saveArticlespic();
            } catch (Exception e) {
                addActionError(getText("errors.file.save"));
                return FREEMARKER;
            }
        } else {
            addActionError(getText("errors.file.type"));
            return FREEMARKER;
        }

        addActionMessage(getText("messages.save.success"));
        logger.debug("register normally end.");
        return FREEMARKER;
    }
}
