package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_chapter table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_chapter"
 */

public abstract class BaseTChapter  implements Serializable {

	public static String REF = "TChapter";
	public static String PROP_ARTICLENO = "articleno";
	public static String PROP_PUBLISHTIME = "publishtime";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_CHAPTERNAME = "chaptername";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_ISVIP = "isvip";
	public static String PROP_CHAPTERNO = "chapterno";
	public static String PROP_CHAPTERTYPE = "chaptertype";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_POSTDATE = "postdate";
	public static String PROP_SIZE = "size";
	public static String PROP_ARTICLENAME = "articlename";
	public static String PROP_FEE = "fee";

	// constructors
	public BaseTChapter () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTChapter (int chapterno) {
		this.setChapterno(chapterno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int chapterno;

	// fields
	private java.lang.Integer articleno;
	private java.lang.String articlename;
	private java.lang.Short chaptertype;
	private java.lang.String chaptername;
	private java.lang.Integer size;
	private java.lang.Boolean isvip;
	private java.util.Date postdate;
	private java.lang.Boolean deleteflag;
	private java.util.Date publishtime;
	private java.lang.Integer modifyuserno;
	private java.util.Date modifytime;
  private  java.lang.Integer fee;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="chapterno"
     */
	public int getChapterno () {
		return chapterno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param chapterno the new ID
	 */
	public void setChapterno (int chapterno) {
		this.chapterno = chapterno;
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
	 * Return the value associated with the column: chaptertype
	 */
	public java.lang.Short getChaptertype () {
		return chaptertype;
	}

	/**
	 * Set the value related to the column: chaptertype
	 * @param chaptertype the chaptertype value
	 */
	public void setChaptertype (java.lang.Short chaptertype) {
		this.chaptertype = chaptertype;
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
	 * Return the value associated with the column: isvip
	 */
	public java.lang.Boolean isIsvip () {
		return isvip;
	}

	/**
	 * Set the value related to the column: isvip
	 * @param isvip the isvip value
	 */
	public void setIsvip (java.lang.Boolean isvip) {
		this.isvip = isvip;
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
	 * Return the value associated with the column: publishtime
	 */
	public java.util.Date getPublishtime () {
		return publishtime;
	}

	/**
	 * Set the value related to the column: publishtime
	 * @param publishtime the publishtime value
	 */
	public void setPublishtime (java.util.Date publishtime) {
		this.publishtime = publishtime;
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
		if (!(obj instanceof org.yidu.novel.entity.TChapter)) return false;
		else {
			org.yidu.novel.entity.TChapter tChapter = (org.yidu.novel.entity.TChapter) obj;
			return (this.getChapterno() == tChapter.getChapterno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getChapterno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

	public java.lang.Integer getFee() {
		return fee;
	}

	public void setFee(java.lang.Integer fee) {
		this.fee = fee;
	}


}