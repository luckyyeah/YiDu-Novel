package org.yidu.novel.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the t_subscribe table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="t_subscribe"
 */

public abstract class BaseTSubscribe  implements Serializable {

	public static String REF = "TSubscribe";
	public static String PROP_SUBSCRIBENO = "subscribeno";
	public static String PROP_ARTICLENO = "articleno";
	public static String PROP_USERNO = "userno";


	// constructors
	public BaseTSubscribe () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTSubscribe (int subscribeno) {
		this.setSubscribeno(subscribeno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int subscribeno;

	// fields
	private java.lang.Integer userno;
	private java.lang.Integer articleno;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="subscribeno"
     */
	public int getSubscribeno () {
		return subscribeno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param subscribeno the new ID
	 */
	public void setSubscribeno (int subscribeno) {
		this.subscribeno = subscribeno;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TSubscribe)) return false;
		else {
			org.yidu.novel.entity.TSubscribe tSubscribe = (org.yidu.novel.entity.TSubscribe) obj;
			return (this.getSubscribeno() == tSubscribe.getSubscribeno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getSubscribeno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}