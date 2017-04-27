package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_user table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_user"
 */

public abstract class BaseTChapterOrder  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4396210650782946228L;
	/**
	 * 
	 */

	public static String REF = "TChapterOrder";
	public static String PROP_CHAPTERORDER_NO = "chapterorderno";
	public static String PROP_USERNO = "userno";
	public static String PROP_CHAPTERNO = "chapterno";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_FEE = "fee";
	// constructors
	public BaseTChapterOrder () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTChapterOrder (int chapterorderno) {
		this.setChapterorderno(chapterorderno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int chapterorderno;

	// fields

	private java.lang.Integer userno;
	private java.lang.Integer chapterno;
	private java.lang.Integer fee;
	private java.util.Date modifytime;
 


	public int getChapterorderno() {
		return chapterorderno;
	}

	public void setChapterorderno(int chapterorderno) {
		this.chapterorderno = chapterorderno;
		this.hashCode = Integer.MIN_VALUE;
	}

	public java.lang.Integer getUserno() {
		return userno;
	}

	public void setUserno(java.lang.Integer userno) {
		this.userno = userno;
	}

	public java.lang.Integer getChapterno() {
		return chapterno;
	}

	public void setChapterno(java.lang.Integer chapterno) {
		this.chapterno = chapterno;
	}

	public java.lang.Integer getFee() {
		return fee;
	}

	public void setFee(java.lang.Integer fee) {
		this.fee = fee;
	}

	public java.util.Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(java.util.Date modifytime) {
		this.modifytime = modifytime;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TChapterOrder)) return false;
		else {
			org.yidu.novel.entity.TChapterOrder tChapterOrder = (org.yidu.novel.entity.TChapterOrder) obj;
			return (this.getChapterorderno() == tChapterOrder.getChapterorderno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getChapterorderno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}