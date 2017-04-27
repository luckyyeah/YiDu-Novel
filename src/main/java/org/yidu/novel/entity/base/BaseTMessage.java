package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_message table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_message"
 */

public abstract class BaseTMessage  implements Serializable {

	public static String REF = "TMessage";
	public static String PROP_USERNO = "userno";
	public static String PROP_CATEGORY = "category";
	public static String PROP_TOLOGINID = "tologinid";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_ISREAD = "isread";
	public static String PROP_LOGINID = "loginid";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_CONTENT = "content";
	public static String PROP_TOUSERNO = "touserno";
	public static String PROP_POSTDATE = "postdate";
	public static String PROP_TITLE = "title";
	public static String PROP_MESSAGENO = "messageno";


	// constructors
	public BaseTMessage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTMessage (int messageno) {
		this.setMessageno(messageno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int messageno;

	// fields
	private java.lang.Integer userno;
	private java.lang.String loginid;
	private java.lang.Integer touserno;
	private java.lang.String tologinid;
	private java.lang.String title;
	private java.lang.String content;
	private java.lang.Short category;
	private java.lang.Boolean isread;
	private java.util.Date postdate;
	private java.lang.Boolean deleteflag;
	private java.lang.Integer modifyuserno;
	private java.util.Date modifytime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="messageno"
     */
	public int getMessageno () {
		return messageno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param messageno the new ID
	 */
	public void setMessageno (int messageno) {
		this.messageno = messageno;
		this.hashCode = Integer.MIN_VALUE;
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
	 * Return the value associated with the column: touserno
	 */
	public java.lang.Integer getTouserno () {
		return touserno;
	}

	/**
	 * Set the value related to the column: touserno
	 * @param touserno the touserno value
	 */
	public void setTouserno (java.lang.Integer touserno) {
		this.touserno = touserno;
	}



	/**
	 * Return the value associated with the column: tologinid
	 */
	public java.lang.String getTologinid () {
		return tologinid;
	}

	/**
	 * Set the value related to the column: tologinid
	 * @param tologinid the tologinid value
	 */
	public void setTologinid (java.lang.String tologinid) {
		this.tologinid = tologinid;
	}



	/**
	 * Return the value associated with the column: title
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: title
	 * @param title the title value
	 */
	public void setTitle (java.lang.String title) {
		this.title = title;
	}



	/**
	 * Return the value associated with the column: content
	 */
	public java.lang.String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: content
	 * @param content the content value
	 */
	public void setContent (java.lang.String content) {
		this.content = content;
	}



	/**
	 * Return the value associated with the column: category
	 */
	public java.lang.Short getCategory () {
		return category;
	}

	/**
	 * Set the value related to the column: category
	 * @param category the category value
	 */
	public void setCategory (java.lang.Short category) {
		this.category = category;
	}



	/**
	 * Return the value associated with the column: isread
	 */
	public java.lang.Boolean isIsread () {
		return isread;
	}

	/**
	 * Set the value related to the column: isread
	 * @param isread the isread value
	 */
	public void setIsread (java.lang.Boolean isread) {
		this.isread = isread;
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
		if (!(obj instanceof org.yidu.novel.entity.TMessage)) return false;
		else {
			org.yidu.novel.entity.TMessage tMessage = (org.yidu.novel.entity.TMessage) obj;
			return (this.getMessageno() == tMessage.getMessageno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getMessageno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}