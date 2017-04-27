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

public abstract class BaseTChargeOrder  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4396210650782946228L;
	/**
	 * 
	 */

	public static String REF = "TChargeOrder";
	public static String PROP_CHARGEORDERNO = "chargeorderno";
	public static String PROP_USERNO = "userno";
	public static String PROP_ORDERNO = "orderno";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_FEE = "fee";
	public static String PROP_STATUS = "status";	
	// constructors
	public BaseTChargeOrder () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTChargeOrder (int chargeorderno) {
		this.setChargeorderno(chargeorderno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int chargeorderno;

	// fields

	private java.lang.Integer userno;
	private String orderno;
	private java.lang.Integer fee;
	private java.util.Date modifytime;
	private java.lang.Integer status;


	public int getChargeorderno() {
		return chargeorderno;
	}

	public void setChargeorderno(int chargeorderno) {
		this.chargeorderno = chargeorderno;
		this.hashCode = Integer.MIN_VALUE;
	}

	public java.lang.Integer getUserno() {
		return userno;
	}

	public void setUserno(java.lang.Integer userno) {
		this.userno = userno;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
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

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TChargeOrder)) return false;
		else {
			org.yidu.novel.entity.TChargeOrder tChargeOrder = (org.yidu.novel.entity.TChargeOrder) obj;
			return (this.getChargeorderno() == tChargeOrder.getChargeorderno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getChargeorderno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}