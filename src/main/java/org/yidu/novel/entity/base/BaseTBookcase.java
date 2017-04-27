package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_bookcase table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_bookcase"
 */

public abstract class BaseTBookcase  implements Serializable {

	public static String REF = "TBookcase";
	public static String PROP_USERNO = "userno";
	public static String PROP_ARTICLENO = "articleno";
	public static String PROP_LASTVISIT = "lastvisit";
	public static String PROP_CATEGORY = "category";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_CHAPTERNAME = "chaptername";
	public static String PROP_BOOKCASENO = "bookcaseno";
	public static String PROP_USERNAME = "username";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_CHAPTERNO = "chapterno";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_CREATETIME = "createtime";
	public static String PROP_ARTICLENAME = "articlename";


	// constructors
	public BaseTBookcase () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTBookcase (int bookcaseno) {
		this.setBookcaseno(bookcaseno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int bookcaseno;

	// fields
	private java.lang.Integer articleno;
	private java.lang.String articlename;
	private java.lang.Integer category;
	private java.lang.Integer userno;
	private java.lang.String username;
	private java.lang.Integer chapterno;
	private java.lang.String chaptername;
	private java.util.Date lastvisit;
	private java.util.Date createtime;
	private java.lang.Boolean deleteflag;
	private java.lang.Integer modifyuserno;
	private java.util.Date modifytime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="bookcaseno"
     */
	public int getBookcaseno () {
		return bookcaseno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param bookcaseno the new ID
	 */
	public void setBookcaseno (int bookcaseno) {
		this.bookcaseno = bookcaseno;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: articleno
	 */
	public java.lang.Integer getArticleno () {
		return articleno;
	}

	/**
	 * Set the value related to the column: articleno
	 * @param articleno the articleno value
	 */
	public void setArticleno (java.lang.Integer articleno) {
		this.articleno = articleno;
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
	 * Return the value associated with the column: userno
	 */
	public java.lang.Integer getUserno () {
		return userno;
	}

	/**
	 * Set the value related to the column: userno
	 * @param userno the userno value
	 */
	public void setUserno (java.lang.Integer userno) {
		this.userno = userno;
	}



	/**
	 * Return the value associated with the column: username
	 */
	public java.lang.String getUsername () {
		return username;
	}

	/**
	 * Set the value related to the column: username
	 * @param username the username value
	 */
	public void setUsername (java.lang.String username) {
		this.username = username;
	}



	/**
	 * Return the value associated with the column: chapterno
	 */
	public java.lang.Integer getChapterno () {
		return chapterno;
	}

	/**
	 * Set the value related to the column: chapterno
	 * @param chapterno the chapterno value
	 */
	public void setChapterno (java.lang.Integer chapterno) {
		this.chapterno = chapterno;
	}



	/**
	 * Return the value associated with the column: chaptername
	 */
	public java.lang.String getChaptername () {
		return chaptername;
	}

	/**
	 * Set the value related to the column: chaptername
	 * @param chaptername the chaptername value
	 */
	public void setChaptername (java.lang.String chaptername) {
		this.chaptername = chaptername;
	}



	/**
	 * Return the value associated with the column: lastvisit
	 */
	public java.util.Date getLastvisit () {
		return lastvisit;
	}

	/**
	 * Set the value related to the column: lastvisit
	 * @param lastvisit the lastvisit value
	 */
	public void setLastvisit (java.util.Date lastvisit) {
		this.lastvisit = lastvisit;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TBookcase)) return false;
		else {
			org.yidu.novel.entity.TBookcase tBookcase = (org.yidu.novel.entity.TBookcase) obj;
			return (this.getBookcaseno() == tBookcase.getBookcaseno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getBookcaseno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}