package org.yidu.novel.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;

import com.opensymphony.xwork2.Action;

/**
 * 
 * <p>
 * 下载处理Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Results(value = {
        @Result(name = "download", type = "stream", params = { "inputName", "is", "contentType",
                "application/octet-stream; charset=UTF-8", "contentLength", "${length}", "contentDisposition",
                "attachment; filename =${downloadFileName}" }),
        @Result(name = Action.ERROR, type = "httpheader", params = { "status", "404" }) })
public class DownloadAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -6265156751627551469L;

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
     * 下载文件名
     * */
    private String downloadFileName;
    /**
     * 小说号
     */
    private int articleno;
    /**
     * 章节号
     */
    private int chapterno;

    /**
     * 文件长度
     * */
    private long length;
    /**
     * 输出流
     * */
    private InputStream is;

    /**
     * 获取下载文件名
     * 
     * @return 下载文件名
     */
    public String getDownloadFileName() {
        return downloadFileName;
    }

    /**
     * 设置下载文件名
     * 
     * @param downloadFileName
     *            下载文件名
     */
    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    /**
     * 获取下载文件长度
     * 
     * @return 下载文件长度
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

    /**
     * 获取 articleno
     * 
     * @return articleno
     */
    public int getArticleno() {
        return articleno;
    }

    /**
     * 
     * 设置articleno
     * 
     * 
     * @param articleno
     *            articleno
     */
    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    /**
     * 获取 chapterno
     * 
     * @return chapterno
     */
    public int getChapterno() {
        return chapterno;
    }

    /**
     * 
     * 设置chapterno
     * 
     * 
     * @param chapterno
     *            chapterno
     */
    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    @Override
    @SkipValidation
    public String execute() {
        logger.debug("execute start.");

        // 小说编号没有指定的话，直接退出
        if (articleno == 0) {
            addActionError(getText("errors.not.exsits.article"));
            logger.warn("the articleno was not been set. abort download.");
            logger.debug("execute abnormally end.");
            return FREEMARKER_ERROR;
        }
        TArticle article = articleService.getByNo(articleno);
        try {
			setDownloadFileName(URLEncoder.encode(article.getArticlename() + ".txt", "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			setDownloadFileName(articleno + ".txt");
		}
        long size = 0;
        try {
            Vector<InputStream> vector = new Vector<InputStream>();
            ChapterSearchBean searchBean = new ChapterSearchBean();
            searchBean.setArticleno(articleno);
            List<TChapter> chapterList = this.chapterService.find(searchBean);
            File dir = new File(YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH) + "/"
                    + (articleno / YiDuConstants.SUB_DIR_ARTICLES) + "/" + articleno);
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();

                // 做成文件Map
                Map<String, File> fileMap = new HashMap<String, File>();
                for (File file : files) {
                    fileMap.put(file.getName(), file);
                }

                for (TChapter chapter : chapterList) {
                    int chapterno = chapter.getChapterno();
                    File file = fileMap.get(chapterno + ".txt");
                    if (file != null) {
                        // 添加一个换行符
                        String chaptername = chapter.getChaptername() + "\n";
                        size += chaptername.length();
                        size += file.length();
                        // convert String into InputStream
                        InputStream chapternameis = new ByteArrayInputStream(
                                chaptername.getBytes(YiDuConstants.ENCODING_GBK));
                        vector.addElement(chapternameis);
                        vector.addElement(new FileInputStream(file));
                    }
                }
            }
            Enumeration<InputStream> enumeration = vector.elements();
            is = new SequenceInputStream(enumeration);
            length = size;
        } catch (Exception e) {
            logger.error(getText("YIDU99999"), e);
            addActionError(getText("YIDU99999"));
            return FREEMARKER_ERROR;
        }
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
