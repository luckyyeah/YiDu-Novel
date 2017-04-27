package org.yidu.novel.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ArticleListAction;
import org.yidu.novel.action.ChapterListAction;
import org.yidu.novel.action.DownloadAction;
import org.yidu.novel.action.InfoAction;
import org.yidu.novel.action.ReaderAction;
import org.yidu.novel.action.ReviewListAction;
import org.yidu.novel.action.user.BookcaseAction;
import org.yidu.novel.action.user.SubscribeAction;
import org.yidu.novel.action.user.VoteAction;
import org.yidu.novel.cache.CategoryCacheManager;
import org.yidu.novel.cache.SingleBookManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.TagDTO;
import org.yidu.novel.entity.base.BaseTArticle;
import org.yidu.novel.utils.Utils;

public class TArticle extends BaseTArticle {
    private static final long serialVersionUID = 1L;

    /* [CONSTRUCTOR MARKER BEGIN] */
    public TArticle() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public TArticle(int articleno) {
        super(articleno);
    }

    /* [CONSTRUCTOR MARKER END] */

    public Boolean getFullflag() {
        return isFullflag();
    }

    public Boolean getFirstflag() {
        return isFirstflag();
    }

    public Boolean getAuthorflag() {
        return isAuthorflag();
    }

    public Boolean getDeleteflag() {
        return isDeleteflag();
    }

    public Boolean getUsecustomizeinfotitle() {
        return isUsecustomizeinfotitle();
    }

    public Boolean getUsecustomizelisttitle() {
        return isUsecustomizelisttitle();
    }

    /**
     * 获取HTML格式的小说简介信息
     * 
     * @return HTML格式的小说简介信息
     */
    public String getIntroForHtml() {
        if (getIntro() != null) {
            // 替换换行和空格
            String html = StringEscapeUtils.escapeHtml4(getIntro());
            return html.replaceAll("\r\n", "<br/>").replaceAll("\n", "<br/>").replaceAll("\\s", "&nbsp;");
        }
        return null;
    }

    /**
     * 获得小说简介的缩略
     * 
     * @return 小说简介的缩略
     */
    public String getIntroOmit() {
        if (getIntro() != null && getIntro().length() > 60) {
            return getIntro().substring(0, 60) + "...";
        }
        return getIntro();
    }

    public String getIntroOmit55() {
        if (getIntro() != null && getIntro().length() > 55) {
            return getIntro().substring(0, 55) + "...";
        }
        return getIntro();
    }
    
    public String getIntroOmit30() {
        if (getIntro() != null && getIntro().length() > 30) {
            return getIntro().substring(0, 30) + "...";
        }
        return getIntro();
    }
    
    /**
     * 获得子目录 <br>
     * 默认是小说号/1000
     * 
     * @return 子目录
     */
    public Integer getSubdir() {
        return getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
    }

    /**
     * 获得最新章节名字的缩略
     * 
     * @return 最新章节名字的缩略
     */
    public String getLastchapterOmit() {
        if (getLastchapter() != null && getLastchapter().length() > 10) {
            return getLastchapter().substring(0, 10);
        }
        return getLastchapter();
    }

    /**
     * 获得最后更新时间
     * 
     * @return 最后更新时间
     */
    public String getLastupdateMin() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        return sdf1.format(getLastupdate());
    }

    /**
     * 获得小说类别名字
     * 
     * @return 小说类别名字
     */
    public String getCategoryStr() {
    	return CategoryCacheManager.getCategoryInfoById(String.valueOf(getCategory()));
    }

    /**
     * 获得图片URL
     * 
     * @return 图片URL
     */
    public String getImgUrl() {
        String fileName = "";
        if (getImgflag() == null) {
            fileName = "nocover.jpg";
        } else {
            switch (getImgflag()) {
            case 1:
                fileName = getArticleno() + "s.jpg";
                break;
            case 2:
                fileName = getArticleno() + "s.gif";
                break;
            case 3:
                fileName = getArticleno() + "s.png";
                break;
            case 10:
                fileName = getArticleno() + "l.jpg";
                break;
            default:
                fileName = "nocover.jpg";
                break;
            }
        }
        String imgUrl = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH) + "/";
        if (StringUtils.equals("nocover.jpg", fileName)) {
            imgUrl = imgUrl + fileName;
        } else {
            if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CLEAN_IMAGE_URL, false)) {
                imgUrl = imgUrl + getArticleno() / YiDuConstants.SUB_DIR_ARTICLES + "-" + getArticleno() + "-"
                        + Utils.convert2MD5(imgUrl + fileName) + fileName;
            } else {
                imgUrl = imgUrl + getArticleno() / YiDuConstants.SUB_DIR_ARTICLES + "/" + getArticleno() + "/"
                        + fileName;
            }
        }
        return imgUrl;
    }

    /**
     * 获取小说介绍页URL
     * 
     * @return 小说介绍页URL
     */
    public String getUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        String url = response.encodeURL(InfoAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());

        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
            url = response.encodeURL(InfoAction.URL + "?pinyin=" + getPinyin());
        }
        return url;
    }

    /**
     * 获取作者列表URL
     * 
     * @return 作者列表URL
     */
    public String getAuthorUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ArticleListAction.URL + "?author=" + getAuthor());
    }

    /**
     * 获取分类列表URL
     * 
     * @return 分类列表URL
     */
    public String getCategoryListUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ArticleListAction.URL + "?category=" + getCategory());
    }

    /**
     * 获取最新章节URL
     * 
     * @return 最新章节URL
     */
    public String getLastChapterUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        String url = response.encodeURL(ReaderAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno()
                + "&chapterno=" + getLastchapterno());

        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
            url = response.encodeURL(ReaderAction.URL + "?pinyin=" + getPinyin() + "&chapterno=" + getLastchapterno());
        }
        return url;
    }

    /**
     * 获取最新章节URL
     * 
     * @return 最新章节URL
     */
    public String getThumbnailLastChapterUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReaderAction.URL + "?chapterno=" + getLastchapterno());
    }

    /**
     * 获取加入书签URL
     * 
     * @return 加入书签URL
     */
    public String getAddBookcaseUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(BookcaseAction.URL + "!add?articleno=" + getArticleno());
    }

    /**
     * 获取推荐小说URL
     * 
     * @return 推荐小说URL
     */
    public String getAddVoteUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(VoteAction.URL + "?articleno=" + getArticleno());
    }

    /**
     * 获取下载小说URL
     * 
     * @return 下载小说URL
     */
    public String getDownloadUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(DownloadAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());
    }

    /**
     * 获取小说评价URL
     * 
     * @return 小说评价URL
     */
    public String getReviewUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(ReviewListAction.URL + "?subdir=" + getSubdir() + "&articleno=" + getArticleno());
    }

    /**
     * 获取章节列表URL
     * 
     * @return 分类列表URL
     */
    public String getChapterListUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        String url = response.encodeURL(ChapterListAction.URL + "?subdir=" + getSubdir() + "&articleno="
                + getArticleno());

        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PINYINURL, false)) {
            url = response.encodeURL(ChapterListAction.URL + "?pinyin=" + getPinyin());
        }
        return url;
    }

    /**
     * 获取订阅URL
     * 
     * @return 订阅URL
     */
    public String getSubscribeUrl() {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response.encodeURL(SubscribeAction.URL + "!add?articleno=" + getArticleno());
    }

    /**
     * 获取小说介绍页拼音形式的URL
     * 
     * @return 小说介绍页拼音形式的URL
     */
    public String getSingleBookUrl() {
        String pinyin = SingleBookManager.getPinYinHeadChar(getArticleno());
        if (StringUtils.isNotBlank(pinyin)) {
            return "http://" + pinyin + "." + YiDuConstants.yiduConf.getString(YiDuConfig.ROOT_DOMAIN);
        } else {
            return YiDuConstants.yiduConf.getString(YiDuConfig.ROOT_DOMAIN);
        }
    }

    /**
     * 获取小说tag的URL
     * 
     * @return 小说介绍页拼音形式的URL
     */
    public List<TagDTO> getTagList() {
        List<String> tags = Utils.getKeyWords(getArticlename());
        List<TagDTO> tagList = new ArrayList<TagDTO>();
        HttpServletResponse response = ServletActionContext.getResponse();
        for (String tag : tags) {
            TagDTO tagdto = new TagDTO();
            tagdto.setTag(tag);
            tagdto.setUrl(response.encodeURL(ArticleListAction.URL + "?tag=" + tag));
            tagList.add(tagdto);
        }
        return tagList;
    }

    /**
     * 
     * <p>
     * 小说类别定义
     * </p>
     * Copyright(c) 2014 YiDu-Novel. All rights reserved.
     * 
     * @version 1.1.9
     * @author shinpa.you
     */
    public static final class CatetoryType {

        /**
         * 1|玄幻魔法
         */
        public static final int XUANHUAN = 1;
        /**
         * 2|武侠修真
         */
        public static final int WUXIA = 2;
        /**
         * 3|都市言情
         */
        public static final int YANXIN = 3;
        /**
         * 4|历史军事
         */
        public static final int JUSHI = 4;
        /**
         * 5|侦探推理
         */
        public static final int ZHENTAN = 5;
        /**
         * 6|网游动漫
         */
        public static final int WANGYOU = 6;
        /**
         * 7|科幻灵异
         */
        public static final int KEHUAN = 7;
        /**
         * 8|恐怖灵异
         */
        public static final int KONGBU = 8;
        /**
         * 9|剧本其他
         */
        public static final int JUBEN = 9;
        /**
         * 10|其他类型
         */
        public static final int OTHER = 10;
    }
}