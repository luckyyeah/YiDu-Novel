package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_article table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_article"
 */

public abstract class BaseTArticle  implements Serializable {

	public static String REF = "TArticle";
	public static String PROP_ARTICLENO = "articleno";
	public static String PROP_AUTHORID = "authorid";
	public static String PROP_FULLFLAG = "fullflag";
	public static String PROP_CREATEUSERNO = "createuserno";
	public static String PROP_LISTDESCRIPTION = "listdescription";
	public static String PROP_PUBLICFLAG = "publicflag";
	public static String PROP_LASTCHAPTER = "lastchapter";
	public static String PROP_AUTHOR = "author";
	public static String PROP_SUBCATEGORY = "subcategory";
	public static String PROP_MONTHVISIT = "monthvisit";
	public static String PROP_ALLVOTE = "allvote";
	public static String PROP_DAYVOTE = "dayvote";
	public static String PROP_DAYVISIT = "dayvisit";
	public static String PROP_USECUSTOMIZEINFOTITLE = "usecustomizeinfotitle";
	public static String PROP_FIRSTFLAG = "firstflag";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_LISTTITLE = "listtitle";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_INITIAL = "initial";
	public static String PROP_KEYWORDS = "keywords";
	public static String PROP_POSTDATE = "postdate";
	public static String PROP_SIZE = "size";
	public static String PROP_LASTCHAPTERNO = "lastchapterno";
	public static String PROP_LASTUPDATE = "lastupdate";
	public static String PROP_USECUSTOMIZELISTTITLE = "usecustomizelisttitle";
	public static String PROP_PERMISSION = "permission";
	public static String PROP_AGENT = "agent";
	public static String PROP_WEEKVISIT = "weekvisit";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_WEEKVOTE = "weekvote";
	public static String PROP_LISTKEYWORDS = "listkeywords";
	public static String PROP_INFODESCRIPTION = "infodescription";
	public static String PROP_ALLVISIT = "allvisit";
	public static String PROP_IMGFLAG = "imgflag";
	public static String PROP_CATEGORY = "category";
	public static String PROP_INFOTITLE = "infotitle";
	public static String PROP_MONTHVOTE = "monthvote";
	public static String PROP_PINYINHEADCHAR = "pinyinheadchar";
	public static String PROP_INFOKEYWORDS = "infokeywords";
	public static String PROP_INTRO = "intro";
	public static String PROP_PINYIN = "pinyin";
	public static String PROP_CHAPTERS = "chapters";
	public static String PROP_CREATETIME = "createtime";
	public static String PROP_ARTICLENAME = "articlename";
	public static String PROP_AUTHORFLAG = "authorflag";


	// constructors
	public BaseTArticle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTArticle (int articleno) {
		this.setArticleno(articleno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int articleno;

	// fields
	private java.lang.String articlename;
	private java.lang.String pinyin;
	private java.lang.String pinyinheadchar;
	private java.lang.Character initial;
	private java.lang.String keywords;
	private java.lang.Integer authorid;
	private java.lang.String author;
	private java.lang.Integer category;
	private java.lang.Integer subcategory;
	private java.lang.String intro;
	private java.lang.Integer lastchapterno;
	private java.lang.String lastchapter;
	private java.lang.Integer chapters;
	private java.lang.Integer size;
	private java.lang.Boolean fullflag;
	private java.lang.Short imgflag;
	private java.lang.String agent;
	private java.lang.Boolean firstflag;
	private java.lang.Integer permission;
	private java.lang.Boolean authorflag;
	private java.util.Date postdate;
	private java.util.Date lastupdate;
	private java.lang.Integer dayvisit;
	private java.lang.Integer weekvisit;
	private java.lang.Integer monthvisit;
	private java.lang.Integer allvisit;
	private java.lang.Integer dayvote;
	private java.lang.Integer weekvote;
	private java.lang.Integer monthvote;
	private java.lang.Integer allvote;
	private java.lang.Boolean deleteflag;
	private java.lang.Integer publicflag;
	private java.lang.Integer createuserno;
	private java.util.Date createtime;
	private java.lang.Integer modifyuserno;
	private java.util.Date modifytime;
	private java.lang.Boolean usecustomizeinfotitle;
	private java.lang.String infotitle;
	private java.lang.String infokeywords;
	private java.lang.String infodescription;
	private java.lang.Boolean usecustomizelisttitle;
	private java.lang.String listtitle;
	private java.lang.String listkeywords;
	private java.lang.String listdescription;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="articleno"
     */
	public int getArticleno () {
		return articleno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param articleno the new ID
	 */
	public void setArticleno (int articleno) {
		this.articleno = articleno;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: articlename
	 */
	public java.lang.String getArticlename () {
		return articlename;
	}

	/**
	 * Set the value related to the column: articlename
	 * @param articlename the articlename value
	 */
	public void setArticlename (java.lang.String articlename) {
		this.articlename = articlename;
	}



	/**
	 * Return the value associated with the column: pinyin
	 */
	public java.lang.String getPinyin () {
		return pinyin;
	}

	/**
	 * Set the value related to the column: pinyin
	 * @param pinyin the pinyin value
	 */
	public void setPinyin (java.lang.String pinyin) {
		this.pinyin = pinyin;
	}



	/**
	 * Return the value associated with the column: pinyinheadchar
	 */
	public java.lang.String getPinyinheadchar () {
		return pinyinheadchar;
	}

	/**
	 * Set the value related to the column: pinyinheadchar
	 * @param pinyinheadchar the pinyinheadchar value
	 */
	public void setPinyinheadchar (java.lang.String pinyinheadchar) {
		this.pinyinheadchar = pinyinheadchar;
	}



	/**
	 * Return the value associated with the column: initial
	 */
	public java.lang.Character getInitial () {
		return initial;
	}

	/**
	 * Set the value related to the column: initial
	 * @param initial the initial value
	 */
	public void setInitial (java.lang.Character initial) {
		this.initial = initial;
	}



	/**
	 * Return the value associated with the column: keywords
	 */
	public java.lang.String getKeywords () {
		return keywords;
	}

	/**
	 * Set the value related to the column: keywords
	 * @param keywords the keywords value
	 */
	public void setKeywords (java.lang.String keywords) {
		this.keywords = keywords;
	}



	/**
	 * Return the value associated with the column: authorid
	 */
	public java.lang.Integer getAuthorid () {
		return authorid;
	}

	/**
	 * Set the value related to the column: authorid
	 * @param authorid the authorid value
	 */
	public void setAuthorid (java.lang.Integer authorid) {
		this.authorid = authorid;
	}



	/**
	 * Return the value associated with the column: author
	 */
	public java.lang.String getAuthor () {
		return author;
	}

	/**
	 * Set the value related to the column: author
	 * @param author the author value
	 */
	public void setAuthor (java.lang.String author) {
		this.author = author;
	}



	/**
	 * Return the value associated with the column: category
	 */
	public java.lang.Integer getCategory () {
		return category;
	}

	/**
	 * Set the value related to the column: category
	 * @param category the category value
	 */
	public void setCategory (java.lang.Integer category) {
		this.category = category;
	}



	/**
	 * Return the value associated with the column: subcategory
	 */
	public java.lang.Integer getSubcategory () {
		return subcategory;
	}

	/**
	 * Set the value related to the column: subcategory
	 * @param subcategory the subcategory value
	 */
	public void setSubcategory (java.lang.Integer subcategory) {
		this.subcategory = subcategory;
	}



	/**
	 * Return the value associated with the column: intro
	 */
	public java.lang.String getIntro () {
		return intro;
	}

	/**
	 * Set the value related to the column: intro
	 * @param intro the intro value
	 */
	public void setIntro (java.lang.String intro) {
		this.intro = intro;
	}



	/**
	 * Return the value associated with the column: lastchapterno
	 */
	public java.lang.Integer getLastchapterno () {
		return lastchapterno;
	}

	/**
	 * Set the value related to the column: lastchapterno
	 * @param lastchapterno the lastchapterno value
	 */
	public void setLastchapterno (java.lang.Integer lastchapterno) {
		this.lastchapterno = lastchapterno;
	}



	/**
	 * Return the value associated with the column: lastchapter
	 */
	public java.lang.String getLastchapter () {
		return lastchapter;
	}

	/**
	 * Set the value related to the column: lastchapter
	 * @param lastchapter the lastchapter value
	 */
	public void setLastchapter (java.lang.String lastchapter) {
		this.lastchapter = lastchapter;
	}



	/**
	 * Return the value associated with the column: chapters
	 */
	public java.lang.Integer getChapters () {
		return chapters;
	}

	/**
	 * Set the value related to the column: chapters
	 * @param chapters the chapters value
	 */
	public void setChapters (java.lang.Integer chapters) {
		this.chapters = chapters;
	}



	/**
	 * Return the value associated with the column: size
	 */
	public java.lang.Integer getSize () {
		return size;
	}

	/**
	 * Set the value related to the column: size
	 * @param size the size value
	 */
	public void setSize (java.lang.Integer size) {
		this.size = size;
	}



	/**
	 * Return the value associated with the column: fullflag
	 */
	public java.lang.Boolean isFullflag () {
		return fullflag;
	}

	/**
	 * Set the value related to the column: fullflag
	 * @param fullflag the fullflag value
	 */
	public void setFullflag (java.lang.Boolean fullflag) {
		this.fullflag = fullflag;
	}



	/**
	 * Return the value associated with the column: imgflag
	 */
	public java.lang.Short getImgflag () {
		return imgflag;
	}

	/**
	 * Set the value related to the column: imgflag
	 * @param imgflag the imgflag value
	 */
	public void setImgflag (java.lang.Short imgflag) {
		this.imgflag = imgflag;
	}



	/**
	 * Return the value associated with the column: agent
	 */
	public java.lang.String getAgent () {
		return agent;
	}

	/**
	 * Set the value related to the column: agent
	 * @param agent the agent value
	 */
	public void setAgent (java.lang.String agent) {
		this.agent = agent;
	}



	/**
	 * Return the value associated with the column: firstflag
	 */
	public java.lang.Boolean isFirstflag () {
		return firstflag;
	}

	/**
	 * Set the value related to the column: firstflag
	 * @param firstflag the firstflag value
	 */
	public void setFirstflag (java.lang.Boolean firstflag) {
		this.firstflag = firstflag;
	}



	/**
	 * Return the value associated with the column: permission
	 */
	public java.lang.Integer getPermission () {
		return permission;
	}

	/**
	 * Set the value related to the column: permission
	 * @param permission the permission value
	 */
	public void setPermission (java.lang.Integer permission) {
		this.permission = permission;
	}



	/**
	 * Return the value associated with the column: authorflag
	 */
	public java.lang.Boolean isAuthorflag () {
		return authorflag;
	}

	/**
	 * Set the value related to the column: authorflag
	 * @param authorflag the authorflag value
	 */
	public void setAuthorflag (java.lang.Boolean authorflag) {
		this.authorflag = authorflag;
	}



	/**
	 * Return the value associated with the column: postdate
	 */
	public java.util.Date getPostdate () {
		return postdate;
	}

	/**
	 * Set the value related to the column: postdate
	 * @param postdate the postdate value
	 */
	public void setPostdate (java.util.Date postdate) {
		this.postdate = postdate;
	}



	/**
	 * Return the value associated with the column: lastupdate
	 */
	public java.util.Date getLastupdate () {
		return lastupdate;
	}

	/**
	 * Set the value related to the column: lastupdate
	 * @param lastupdate the lastupdate value
	 */
	public void setLastupdate (java.util.Date lastupdate) {
		this.lastupdate = lastupdate;
	}



	/**
	 * Return the value associated with the column: dayvisit
	 */
	public java.lang.Integer getDayvisit () {
		return dayvisit;
	}

	/**
	 * Set the value related to the column: dayvisit
	 * @param dayvisit the dayvisit value
	 */
	public void setDayvisit (java.lang.Integer dayvisit) {
		this.dayvisit = dayvisit;
	}



	/**
	 * Return the value associated with the column: weekvisit
	 */
	public java.lang.Integer getWeekvisit () {
		return weekvisit;
	}

	/**
	 * Set the value related to the column: weekvisit
	 * @param weekvisit the weekvisit value
	 */
	public void setWeekvisit (java.lang.Integer weekvisit) {
		this.weekvisit = weekvisit;
	}



	/**
	 * Return the value associated with the column: monthvisit
	 */
	public java.lang.Integer getMonthvisit () {
		return monthvisit;
	}

	/**
	 * Set the value related to the column: monthvisit
	 * @param monthvisit the monthvisit value
	 */
	public void setMonthvisit (java.lang.Integer monthvisit) {
		this.monthvisit = monthvisit;
	}



	/**
	 * Return the value associated with the column: allvisit
	 */
	public java.lang.Integer getAllvisit () {
		return allvisit;
	}

	/**
	 * Set the value related to the column: allvisit
	 * @param allvisit the allvisit value
	 */
	public void setAllvisit (java.lang.Integer allvisit) {
		this.allvisit = allvisit;
	}



	/**
	 * Return the value associated with the column: dayvote
	 */
	public java.lang.Integer getDayvote () {
		return dayvote;
	}

	/**
	 * Set the value related to the column: dayvote
	 * @param dayvote the dayvote value
	 */
	public void setDayvote (java.lang.Integer dayvote) {
		this.dayvote = dayvote;
	}



	/**
	 * Return the value associated with the column: weekvote
	 */
	public java.lang.Integer getWeekvote () {
		return weekvote;
	}

	/**
	 * Set the value related to the column: weekvote
	 * @param weekvote the weekvote value
	 */
	public void setWeekvote (java.lang.Integer weekvote) {
		this.weekvote = weekvote;
	}



	/**
	 * Return the value associated with the column: monthvote
	 */
	public java.lang.Integer getMonthvote () {
		return monthvote;
	}

	/**
	 * Set the value related to the column: monthvote
	 * @param monthvote the monthvote value
	 */
	public void setMonthvote (java.lang.Integer monthvote) {
		this.monthvote = monthvote;
	}



	/**
	 * Return the value associated with the column: allvote
	 */
	public java.lang.Integer getAllvote () {
		return allvote;
	}

	/**
	 * Set the value related to the column: allvote
	 * @param allvote the allvote value
	 */
	public void setAllvote (java.lang.Integer allvote) {
		this.allvote = allvote;
	}



	/**
	 * Return the value associated with the column: deleteflag
	 */
	public java.lang.Boolean isDeleteflag () {
		return deleteflag;
	}

	/**
	 * Set the value related to the column: deleteflag
	 * @param deleteflag the deleteflag value
	 */
	public void setDeleteflag (java.lang.Boolean deleteflag) {
		this.deleteflag = deleteflag;
	}



	/**
	 * Return the value associated with the column: publicflag
	 */
	public java.lang.Integer getPublicflag () {
		return publicflag;
	}

	/**
	 * Set the value related to the column: publicflag
	 * @param publicflag the publicflag value
	 */
	public void setPublicflag (java.lang.Integer publicflag) {
		this.publicflag = publicflag;
	}



	/**
	 * Return the value associated with the column: createuserno
	 */
	public java.lang.Integer getCreateuserno () {
		return createuserno;
	}

	/**
	 * Set the value related to the column: createuserno
	 * @param createuserno the createuserno value
	 */
	public void setCreateuserno (java.lang.Integer createuserno) {
		this.createuserno = createuserno;
	}



	/**
	 * Return the value associated with the column: createtime
	 */
	public java.util.Date getCreatetime () {
		return createtime;
	}

	/**
	 * Set the value related to the column: createtime
	 * @param createtime the createtime value
	 */
	public void setCreatetime (java.util.Date createtime) {
		this.createtime = createtime;
	}



	/**
	 * Return the value associated with the column: modifyuserno
	 */
	public java.lang.Integer getModifyuserno () {
		return modifyuserno;
	}

	/**
	 * Set the value related to the column: modifyuserno
	 * @param modifyuserno the modifyuserno value
	 */
	public void setModifyuserno (java.lang.Integer modifyuserno) {
		this.modifyuserno = modifyuserno;
	}



	/**
	 * Return the value associated with the column: modifytime
	 */
	public java.util.Date getModifytime () {
		return modifytime;
	}

	/**
	 * Set the value related to the column: modifytime
	 * @param modifytime the modifytime value
	 */
	public void setModifytime (java.util.Date modifytime) {
		this.modifytime = modifytime;
	}



	/**
	 * Return the value associated with the column: usecustomizeinfotitle
	 */
	public java.lang.Boolean isUsecustomizeinfotitle () {
		return usecustomizeinfotitle;
	}

	/**
	 * Set the value related to the column: usecustomizeinfotitle
	 * @param usecustomizeinfotitle the usecustomizeinfotitle value
	 */
	public void setUsecustomizeinfotitle (java.lang.Boolean usecustomizeinfotitle) {
		this.usecustomizeinfotitle = usecustomizeinfotitle;
	}



	/**
	 * Return the value associated with the column: infotitle
	 */
	public java.lang.String getInfotitle () {
		return infotitle;
	}

	/**
	 * Set the value related to the column: infotitle
	 * @param infotitle the infotitle value
	 */
	public void setInfotitle (java.lang.String infotitle) {
		this.infotitle = infotitle;
	}



	/**
	 * Return the value associated with the column: infokeywords
	 */
	public java.lang.String getInfokeywords () {
		return infokeywords;
	}

	/**
	 * Set the value related to the column: infokeywords
	 * @param infokeywords the infokeywords value
	 */
	public void setInfokeywords (java.lang.String infokeywords) {
		this.infokeywords = infokeywords;
	}



	/**
	 * Return the value associated with the column: infodescription
	 */
	public java.lang.String getInfodescription () {
		return infodescription;
	}

	/**
	 * Set the value related to the column: infodescription
	 * @param infodescription the infodescription value
	 */
	public void setInfodescription (java.lang.String infodescription) {
		this.infodescription = infodescription;
	}



	/**
	 * Return the value associated with the column: usecustomizelisttitle
	 */
	public java.lang.Boolean isUsecustomizelisttitle () {
		return usecustomizelisttitle;
	}

	/**
	 * Set the value related to the column: usecustomizelisttitle
	 * @param usecustomizelisttitle the usecustomizelisttitle value
	 */
	public void setUsecustomizelisttitle (java.lang.Boolean usecustomizelisttitle) {
		this.usecustomizelisttitle = usecustomizelisttitle;
	}



	/**
	 * Return the value associated with the column: listtitle
	 */
	public java.lang.String getListtitle () {
		return listtitle;
	}

	/**
	 * Set the value related to the column: listtitle
	 * @param listtitle the listtitle value
	 */
	public void setListtitle (java.lang.String listtitle) {
		this.listtitle = listtitle;
	}



	/**
	 * Return the value associated with the column: listkeywords
	 */
	public java.lang.String getListkeywords () {
		return listkeywords;
	}

	/**
	 * Set the value related to the column: listkeywords
	 * @param listkeywords the listkeywords value
	 */
	public void setListkeywords (java.lang.String listkeywords) {
		this.listkeywords = listkeywords;
	}



	/**
	 * Return the value associated with the column: listdescription
	 */
	public java.lang.String getListdescription () {
		return listdescription;
	}

	/**
	 * Set the value related to the column: listdescription
	 * @param listdescription the listdescription value
	 */
	public void setListdescription (java.lang.String listdescription) {
		this.listdescription = listdescription;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TArticle)) return false;
		else {
			org.yidu.novel.entity.TArticle tArticle = (org.yidu.novel.entity.TArticle) obj;
			return (this.getArticleno() == tArticle.getArticleno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getArticleno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}