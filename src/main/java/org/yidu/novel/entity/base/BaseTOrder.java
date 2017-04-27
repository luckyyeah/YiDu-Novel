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

public abstract class BaseTOrder  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4396210650782946228L;
	/**
	 * 
	 */

	public static String REF = "TOrder";
	public static String PROP_ORDERID = "orderid";
	public static String PROP_ORDERNO = "orderno";
	public static String PROP_TRANSACTIONID = "transactionid";
	public static String PROP_TIMEEND = "timeend";
	public static String PROP_ATTACH = "attach";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_TOTALFEE = "totalfee";
	public static String PROP_PAYTYPE = "paytype";
	// constructors
	public BaseTOrder () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTOrder (int orderid) {
		this.setOrderid(orderid);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int orderid;

	// fields

	private String orderno;
	private String transactionid;
	private String timeend;
	private String attach;
	private java.lang.Integer chapterno;
	private java.lang.Integer totalfee;
	private java.lang.Integer paytype;
	private java.util.Date modifytime;
 

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
		this.hashCode = Integer.MIN_VALUE;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getTimeend() {
		return timeend;
	}

	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public java.lang.Integer getChapterno() {
		return chapterno;
	}

	public void setChapterno(java.lang.Integer chapterno) {
		this.chapterno = chapterno;
	}

	public java.lang.Integer getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(java.lang.Integer totalfee) {
		this.totalfee = totalfee;
	}

	public java.lang.Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(java.lang.Integer paytype) {
		this.paytype = paytype;
	}

	public java.util.Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(java.util.Date modifytime) {
		this.modifytime = modifytime;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TOrder)) return false;
		else {
			org.yidu.novel.entity.TOrder tOrder = (org.yidu.novel.entity.TOrder) obj;
			return (this.getOrderid()== tOrder.getOrderid());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getOrderid();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}