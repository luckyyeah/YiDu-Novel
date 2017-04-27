package org.yidu.novel.action.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 语言包配置Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "languageEdit")
public class LanguageEditAction extends AbstractAdminEditBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -6768164951656460867L;

    private String title;
    private String siteKeywords;
    private String siteDescription;
    private String name;
    private String url;
    private String copyright;
    private String beianNo;
    private String analyticscode;
    private String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSiteKeywords() {
        return siteKeywords;
    }

    public void setSiteKeywords(String siteKeywords) {
        this.siteKeywords = siteKeywords;
    }

    public String getSiteDescription() {
        return siteDescription;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getBeianNo() {
        return beianNo;
    }

    public void setBeianNo(String beianNo) {
        this.beianNo = beianNo;
    }

    public String getAnalyticscode() {
        return analyticscode;
    }

    public void setAnalyticscode(String analyticscode) {
        this.analyticscode = analyticscode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    protected void loadData() {
        // 设定文件初期读入
        try {
            PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("language/package.properties"));
            title = languageConf.getString(YiDuConfig.TITLE);
            siteKeywords = languageConf.getString(YiDuConfig.SITEKEYWORDS);
            siteDescription = languageConf.getString(YiDuConfig.SITEDESCRIPTION);
            name = languageConf.getString(YiDuConfig.NAME);
            url = languageConf.getString(YiDuConfig.URL);
            copyright = languageConf.getString(YiDuConfig.COPYRIGHT);
            beianNo = languageConf.getString(YiDuConfig.BEIANNO);
            analyticscode = languageConf.getString(YiDuConfig.ANALYTICSCODE);
            analyticscode = languageConf.getString(YiDuConfig.ANALYTICSCODE);
            category = languageConf.getString(YiDuConfig.CATEGORY);
        } catch (ConfigurationException e) {
            logger.error(e);
        }
    }

    public String save() {

        // 设定文件初期读入
        try {
            PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("language/package.properties"));

            languageConf.setProperty(YiDuConfig.TITLE, Utils.escapePropterties(title));
            languageConf.setProperty(YiDuConfig.SITEKEYWORDS, Utils.escapePropterties(siteKeywords));
            languageConf.setProperty(YiDuConfig.SITEDESCRIPTION, Utils.escapePropterties(siteDescription));
            languageConf.setProperty(YiDuConfig.NAME, Utils.escapePropterties(name));
            languageConf.setProperty(YiDuConfig.URL, Utils.escapePropterties(url));
            languageConf.setProperty(YiDuConfig.COPYRIGHT, Utils.escapePropterties(copyright));
            languageConf.setProperty(YiDuConfig.BEIANNO, Utils.escapePropterties(beianNo));
            languageConf.setProperty(YiDuConfig.ANALYTICSCODE, Utils.escapePropterties(analyticscode));
            languageConf.setProperty(YiDuConfig.CATEGORY, Utils.escapePropterties(category));

            File languageFile = new File(languageConf.getPath());
            OutputStream out = new FileOutputStream(languageFile);
            languageConf.save(out);

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
