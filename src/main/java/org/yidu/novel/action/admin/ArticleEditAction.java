package org.yidu.novel.action.admin;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

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
@Result(name = AbstractBaseAction.REDIRECT, type = AbstractBaseAction.REDIRECT, location = ArticleListAction.URL)
public class ArticleEditAction extends AbstractAdminEditBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 822196809678036074L;

    private int articleno;
    private String articlename;
    private String keywords;
    private Integer authorid;
    private String author;
    private Integer category;
    private String intro;
    private Boolean fullflag;
    private Date postdate;
    private Boolean firstflag;
    private Integer permission;
    private Boolean authorflag;
    private String agent;

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
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    private Boolean usecustomizeinfotitle;
    private String infotitle;
    private String infokeywords;
    private String infodescription;
    private Boolean usecustomizelisttitle;
    private String listtitle;
    private String listkeywords;
    private String listdescription;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Boolean getFullflag() {
        return fullflag == null ? false : fullflag;
    }

    public void setFullflag(Boolean fullflag) {
        this.fullflag = fullflag == null ? false : fullflag;;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public String getPostdateStr() {
        return sdf.format(postdate);
    }

    public Boolean getFirstflag() {
        return firstflag == null ? false : firstflag;
    }

    public void setFirstflag(Boolean firstflag) {
        this.firstflag = firstflag == null ? false : firstflag;
    }

    public Integer getPermission() {
        return permission == null ? YiDuConstants.PermissionType.UNPERMISSION : permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission == null ? YiDuConstants.PermissionType.UNPERMISSION : permission;
    }

    public Boolean getAuthorflag() {
        return authorflag == null ? false : authorflag;
    }

    public void setAuthorflag(Boolean authorflag) {
        this.authorflag = authorflag == null ? false : authorflag;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Integer getDayvisit() {
        return dayvisit == null ? 0 : dayvisit;
    }

    public void setDayvisit(Integer dayvisit) {
        this.dayvisit = dayvisit;
    }

    public Integer getWeekvisit() {
        return weekvisit == null ? 0 : weekvisit;
    }

    public void setWeekvisit(Integer weekvisit) {
        this.weekvisit = weekvisit;
    }

    public Integer getMonthvisit() {
        return monthvisit == null ? 0 : monthvisit;
    }

    public void setMonthvisit(Integer monthvisit) {
        this.monthvisit = monthvisit;
    }

    public Integer getAllvisit() {
        return allvisit == null ? 0 : allvisit;
    }

    public void setAllvisit(Integer allvisit) {
        this.allvisit = allvisit;
    }

    public Integer getDayvote() {
        return dayvote == null ? 0 : dayvote;
    }

    public void setDayvote(Integer dayvote) {
        this.dayvote = dayvote;
    }

    public Integer getWeekvote() {
        return weekvote == null ? 0 : weekvote;
    }

    public void setWeekvote(Integer weekvote) {
        this.weekvote = weekvote;
    }

    public Integer getMonthvote() {
        return monthvote == null ? 0 : monthvote;
    }

    public void setMonthvote(Integer monthvote) {
        this.monthvote = monthvote;
    }

    public Integer getAllvote() {
        return allvote == null ? 0 : allvote;
    }

    public void setAllvote(Integer allvote) {
        this.allvote = allvote;
    }

    public void setPostdateStr(String postdateStr) {
        try {
            this.postdate = sdf.parse(postdateStr);
        } catch (ParseException e) {
        
            this.postdate = new Date();
        }
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

    /**
     * 获取usecustomizeinfotitle
     * 
     * @return usecustomizeinfotitle
     */
    public Boolean getUsecustomizeinfotitle() {
        return usecustomizeinfotitle == null ? false : usecustomizeinfotitle;
    }

    /**
     * 
     * 设置usecustomizeinfotitle
     * 
     * 
     * @param usecustomizeinfotitle
     *            usecustomizeinfotitle
     */
    public void setUsecustomizeinfotitle(Boolean usecustomizeinfotitle) {
        this.usecustomizeinfotitle = usecustomizeinfotitle == null ? false : usecustomizeinfotitle;
    }

    /**
     * 获取infotitle
     * 
     * @return infotitle
     */
    public String getInfotitle() {
        return infotitle;
    }

    /**
     * 
     * 设置infotitle
     * 
     * 
     * @param infotitle
     *            infotitle
     */
    public void setInfotitle(String infotitle) {
        this.infotitle = infotitle;
    }

    /**
     * 获取infokeywords
     * 
     * @return infokeywords
     */
    public String getInfokeywords() {
        return infokeywords;
    }

    /**
     * 
     * 设置infokeywords
     * 
     * 
     * @param infokeywords
     *            infokeywords
     */
    public void setInfokeywords(String infokeywords) {
        this.infokeywords = infokeywords;
    }

    /**
     * 获取infodescription
     * 
     * @return infodescription
     */
    public String getInfodescription() {
        return infodescription;
    }

    /**
     * 
     * 设置infodescription
     * 
     * 
     * @param infodescription
     *            infodescription
     */
    public void setInfodescription(String infodescription) {
        this.infodescription = infodescription;
    }

    /**
     * 获取usecustomizelisttitle
     * 
     * @return usecustomizelisttitle
     */
    public Boolean getUsecustomizelisttitle() {
        return usecustomizelisttitle == null ? false : usecustomizelisttitle;
    }

    /**
     * 
     * 设置usecustomizelisttitle
     * 
     * 
     * @param usecustomizelisttitle
     *            usecustomizelisttitle
     */
    public void setUsecustomizelisttitle(Boolean usecustomizelisttitle) {
        this.usecustomizelisttitle = usecustomizelisttitle == null ? false : usecustomizelisttitle;
    }

    /**
     * 获取listtitle
     * 
     * @return listtitle
     */
    public String getListtitle() {
        return listtitle;
    }

    /**
     * 
     * 设置listtitle
     * 
     * 
     * @param listtitle
     *            listtitle
     */
    public void setListtitle(String listtitle) {
        this.listtitle = listtitle;
    }

    /**
     * 获取listkeywords
     * 
     * @return listkeywords
     */
    public String getListkeywords() {
        return listkeywords;
    }

    /**
     * 
     * 设置listkeywords
     * 
     * 
     * @param listkeywords
     *            listkeywords
     */
    public void setListkeywords(String listkeywords) {
        this.listkeywords = listkeywords;
    }

    /**
     * 获取listdescription
     * 
     * @return listdescription
     */
    public String getListdescription() {
        return listdescription;
    }

    /**
     * 
     * 设置listdescription
     * 
     * 
     * @param listdescription
     *            listdescription
     */
    public void setListdescription(String listdescription) {
        this.listdescription = listdescription;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化类别下拉列表选项
        initCollections(new String[] { "collectionProperties.article.category",
                "collectionProperties.article.fullflag", "collectionProperties.article.authorflag",
                "collectionProperties.article.permission", "collectionProperties.article.firstflag",
                "collectionProperties.boolean" });
        // 编辑
        if (articleno != 0) {
            TArticle article = articleService.getByNo(articleno);
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

        TArticle article = new TArticle();
        if (articleno != 0) {
            article = articleService.getByNo(articleno);
        } else {
            article.setDeleteflag(false);
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
        }

        BeanUtils.copyProperties(this, article);

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
                }
            } else {
                addActionError(getText("errors.file.type"));
            }

            if (StringUtils.equals(getArticlespicContentType(), YiDuConstants.ImgageMateType.JPG)) {
                article.setImgflag(YiDuConstants.ImageType.JPG);
            } else if (StringUtils.equals(getArticlespicContentType(), YiDuConstants.ImgageMateType.GIF)) {
                article.setImgflag(YiDuConstants.ImageType.GIF);
            } else if (StringUtils.equals(getArticlespicContentType(), YiDuConstants.ImgageMateType.PNG)) {
                article.setImgflag(YiDuConstants.ImageType.PNG);
            }
        }

        if(hasActionErrors()){
            // 初始化类别下拉列表选项
            initCollections(new String[] { "collectionProperties.article.category",
                    "collectionProperties.article.fullflag", "collectionProperties.article.authorflag",
                    "collectionProperties.article.permission", "collectionProperties.article.firstflag" });
        	return INPUT;
        }

        articleService.save(article);

        logger.debug("save normally end.");
        return REDIRECT;
    }
}
