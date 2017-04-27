package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_credit_history table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_credit_history"
 */

public abstract class BaseTCreditHistory  implements Serializable {

	public static String REF = "TCreditHistory";
	public static String PROP_ARTICLENO = "articleno";
	public static String PROP_USERNO = "userno";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_CHAPTERNAME = "chaptername";
	public static String PROP_LOGINID = "loginid";
	public static String PROP_CREDITPOINT = "creditpoint";
	public static String PROP_CHAPTERNO = "chapterno";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_CREDITHISTORYNO = "credithistoryno";
	public static String PROP_ARTICLENAME = "articlename";


	// constructors
	public BaseTCreditHistory () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTCreditHistory (int credithistoryno) {
		this.setCredithistoryno(credithistoryno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int credithistoryno;

	 java.util.Date timestamp;

	// fields
	private java.lang.Integer userno;
	private java.lang.String loginid;
	private java.lang.Integer articleno;
	private java.lang.String articlename;
	private java.lang.Integer chapterno;
	private java.lang.String chaptername;
	private java.lang.Integer creditpoint;
	private java.lang.Integer modifyuserno;
	private java.util.Date modifytime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="credithistoryno"
     */
	public int getCredithistoryno () {
		return credithistoryno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param credithistoryno the new ID
	 */
	public void setCredithistoryno (int credithistoryno) {
		this.credithistoryno = credithistoryno;
		this.hashCode = Integer.MIN_VALUE;
	}



	/**
	 * Return the value associated with the column: timestamp
	 */
	public java.util.Date getTimestamp () {
		return timestamp;
	}

	/**
	 * Set the value related to the column: timestamp
	 * @param timestamp the timestamp value
	 */
	public void setTimestamp (java.util.Date timestamp) {
		this.timestamp = timestamp;
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
	 * Return the value associated with the column: loginid
	 */
	public java.lang.String getLoginid () {
		return loginid;
	}

	/**
	 * Set the value related to the column: loginid
	 * @param loginid the loginid value
	 */
	public void setLoginid (java.lang.String loginid) {
		this.loginid = loginid;
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
	 * Return the value associated with the column: creditpoint
	 */
	public java.lang.Integer getCreditpoint () {
		return creditpoint;
	}

	/**
	 * Set the value related to the column: creditpoint
	 * @param creditpoint the creditpoint value
	 */
	public void setCreditpoint (java.lang.Integer creditpoint) {
		this.creditpoint = creditpoint;
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
		if (!(obj instanceof org.yidu.novel.entity.TCreditHistory)) return false;
		else {
			org.yidu.novel.entity.TCreditHistory tCreditHistory = (org.yidu.novel.entity.TCreditHistory) obj;
			return (this.getCredithistoryno() == tCreditHistory.getCredithistoryno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getCredithistoryno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}