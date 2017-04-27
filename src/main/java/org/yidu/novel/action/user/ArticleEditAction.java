package org.yidu.novel.action.user;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

/**
 * <p>
 * 小说编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "articleEdit")
public class ArticleEditAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 822196809678036074L;

    private int articleno;
    private String articlename;
    private String keywords;
    private Integer category;
    private String intro;
    private Boolean fullflag;
    private Integer permission;
    private Boolean firstflag;

    private Integer dayvisit;
    private Integer weekvisit;
    private Integer monthvisit;
    private Integer allvisit;
    private Integer dayvote;
    private Integer weekvote;
    private Integer monthvote;
    private Integer allvote;

    private File articlespic;
    private String articlespicContentType;
    private String articlespicFileName;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public String getArticlename() {
        return articlename;
    }

    // 必須
    @RequiredStringValidator(message = "${getText(\"errors.required.input\","
            + " {getText(\"label.user.article.articlename\")})}")
    // 长度
    @StringLengthFieldValidator(maxLength = "50", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.article.articlename\")})}")
    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getKeywords() {
        return keywords;
    }

    // 长度
    @StringLengthFieldValidator(maxLength = "100", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.article.keywords\")})}")
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取category
     * 
     * @return category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 
     * 设置category
     * 
     * 
     * @param category
     *            category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getIntro() {
        return intro;
    }

    // 长度
    @StringLengthFieldValidator(maxLength = "500", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.article.intro\")})}")
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Boolean getFullflag() {
        return fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Boolean getFirstflag() {
        return firstflag;
    }

    public void setFirstflag(Boolean firstflag) {
        this.firstflag = firstflag;
    }

    public Integer getDayvisit() {
        return dayvisit;
    }

    public void setDayvisit(Integer dayvisit) {
        this.dayvisit = dayvisit;
    }

    public Integer getWeekvisit() {
        return weekvisit;
    }

    public void setWeekvisit(Integer weekvisit) {
        this.weekvisit = weekvisit;
    }

    public Integer getMonthvisit() {
        return monthvisit;
    }

    public void setMonthvisit(Integer monthvisit) {
        this.monthvisit = monthvisit;
    }

    public Integer getAllvisit() {
        return allvisit;
    }

    public void setAllvisit(Integer allvisit) {
        this.allvisit = allvisit;
    }

    public Integer getDayvote() {
        return dayvote;
    }

    public void setDayvote(Integer dayvote) {
        this.dayvote = dayvote;
    }

    public Integer getWeekvote() {
        return weekvote;
    }

    public void setWeekvote(Integer weekvote) {
        this.weekvote = weekvote;
    }

    public Integer getMonthvote() {
        return monthvote;
    }

    public void setMonthvote(Integer monthvote) {
        this.monthvote = monthvote;
    }

    public Integer getAllvote() {
        return allvote;
    }

    public void setAllvote(Integer allvote) {
        this.allvote = allvote;
    }

    public File getArticlespic() {
        return articlespic;
    }

    public void setArticlespic(File articlespic) {
        this.articlespic = articlespic;
    }

    public String getArticlespicContentType() {
        return articlespicContentType;
    }

    public void setArticlespicContentType(String articlespicContentType) {
        this.articlespicContentType = articlespicContentType;
    }

    public String getArticlespicFileName() {
        return articlespicFileName;
    }

    public void setArticlespicFileName(String articlespicFileName) {
        this.articlespicFileName = articlespicFileName;
    }

    @Override
    public String getBackUrl() {
        return ArticleListAction.URL;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category",
                "collectionProperties.article.fullflag", "collectionProperties.article.firstflag",
                "collectionProperties.article.permission" });

        // 编辑
        if (articleno != 0) {
            TArticle article = articleService.getByNo(articleno);
            if (article == null) {
                addActionError(getText("errors.not.exsits.article"));
                return;
            }
            if (!checkRight(article)) {
                addActionError(getText("errors.right"));
                return;
            }

            BeanUtils.copyProperties(article, this);

        }
        logger.debug("loadData normally end.");
    }

    /**
     * <p>
     * 保存画面的内容
     * </p>
     * 
     * @return 结果，画面
     */
    public String save() {
        logger.debug("save start.");

        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category",
                "collectionProperties.article.fullflag", "collectionProperties.article.firstflag",
                "collectionProperties.article.permission" });

        TArticle article = new TArticle();
        if (articleno == 0) {
            // 小说名重复检查
            ArticleSearchBean searchBean = new ArticleSearchBean();
            searchBean.setArticlename(articlename);
            searchBean.setPageType(ArticleSearchBean.PageType.authorPage);
            int count = articleService.getCount(searchBean);
            if (count > 0) {
                addActionError(this.getText("errors.duplicated",
                        new String[] { this.getText("label.user.article.articlename") }));
                return FREEMARKER;
            }

            String pinyin = Utils.getPinYin(articlename);
            TArticle articletemp = articleService.findByPinyinRegularRxpressions(pinyin);
            if (Utils.isDefined(articletemp)) {
                // 存在的话
                if (StringUtils.equals(articletemp.getPinyin(), pinyin)) {
                    // 如果存在相同拼音的就在后面加数字
                    pinyin = pinyin + 2;
                } else {
                    // TODO 此处多线程的话会有问题，后台同时操作的时候应该不多！暂时不对应
                    int suffixNumber = Integer.valueOf(StringUtils.substring(articletemp.getPinyin(), pinyin.length(),
                            articletemp.getPinyin().length()));
                    pinyin = pinyin + (suffixNumber + 1);
                }
            }
            article.setPinyin(pinyin);

            TUser user = LoginManager.getLoginUser();
            article.setAuthorid(user.getUserno());
            article.setAuthor(user.getUsername());
            article.setDayvisit(0);
            article.setDayvote(0);
            article.setWeekvisit(0);
            article.setWeekvote(0);
            article.setMonthvisit(0);
            article.setMonthvote(0);
            article.setAllvisit(0);
            article.setAllvote(0);
            article.setSize(0);
            article.setChapters(0);
            // TODO 首字母
            article.setPostdate(new Date());
            article.setDeleteflag(false);
            article.setPublicflag(0);
        } else {
            article = articleService.getByNo(articleno);

            if (article == null) {
                addActionError(getText("errors.not.exsits.article"));
                return FREEMARKER_ERROR;
            }

            if (!checkRight(article)) {
                addActionError(getText("errors.right"));
                return FREEMARKER_ERROR;
            }
        }

        BeanUtils.copyProperties(this, article, new String[] { "articleno", "dayvisit", "weekvisit", "monthvisit",
                "allvisit", "dayvote", "weekvote", "monthvote", "allvote" });

        article.setModifytime(new Date());
        article.setModifyuserno(LoginManager.getLoginUser().getUserno());

        articleService.save(article);

        // 保存图片文件
        if (Utils.isDefined(articlespic)) {
            if (ArrayUtils.contains(YiDuConstants.ALLOW_PIC_TYPES, getArticlespicContentType())) {
                try {
                    Utils.saveArticlespic(article.getArticleno(), articlespic, articlespicFileName);
                } catch (Exception e) {
                    addActionError(getText("errors.file.save"));
                    return FREEMARKER;
                }
            } else {
                addActionError(getText("errors.file.type"));
                return FREEMARKER;
            }

            if (StringUtils.equals(getArticlespicContentType(), YiDuConstants.ImgageMateType.JPG)) {
                article.setImgflag(YiDuConstants.ImageType.JPG);
            } else if (StringUtils.equals(getArticlespicContentType(), YiDuConstants.ImgageMateType.GIF)) {
                article.setImgflag(YiDuConstants.ImageType.GIF);
            } else if (StringUtils.equals(getArticlespicContentType(), YiDuConstants.ImgageMateType.PNG)) {
                article.setImgflag(YiDuConstants.ImageType.PNG);
            }
        }

        logger.debug("save normally end.");
        return REDIRECT;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_AUTHER_ARTICLE_EDIT;
    }

    @Override
    public String getTempName() {
        return "user/articleEdit";
    }
}
