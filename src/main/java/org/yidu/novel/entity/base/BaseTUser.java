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

public abstract class BaseTUser  implements Serializable {

	public static String REF = "TUser";
	public static String PROP_USERNO = "userno";
	public static String PROP_BRANCH = "branch";
	public static String PROP_REGDATE = "regdate";
	public static String PROP_LINENO = "lineno";
	public static String PROP_TYPE = "type";
	public static String PROP_PASSWORD = "password";
	public static String PROP_LOGINID = "loginid";
	public static String PROP_SUBCATEGORY = "subcategory";
	public static String PROP_REALNAME = "realname";
	public static String PROP_DELETEFLAG = "deleteflag";
	public static String PROP_ACTIVEDFLAG = "activedflag";
	public static String PROP_ALIPAYACOUNT = "alipayacount";
	public static String PROP_QQ = "qq";
	public static String PROP_CATEGORY = "category";
	public static String PROP_MAILTOKEN = "mailtoken";
	public static String PROP_MODIFYUSERNO = "modifyuserno";
	public static String PROP_EMAIL = "email";
	public static String PROP_LASTLOGIN = "lastlogin";
	public static String PROP_MOBILENO = "mobileno";
	public static String PROP_VOTECOUNT = "votecount";
	public static String PROP_OPENID = "openid";
	public static String PROP_USERNAME = "username";
	public static String PROP_ID = "id";
	public static String PROP_MODIFYTIME = "modifytime";
	public static String PROP_BANKNO = "bankno";
	public static String PROP_SEX = "sex";
	public static String PROP_CHARGE_FEE = "chargefee";
	public static String PROP_HEADIMGURL = "headimgurl";
	// constructors
	public BaseTUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTUser (int userno) {
		this.setUserno(userno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int userno;

	// fields
	private java.lang.String loginid;
	private java.lang.String password;
	private java.lang.String username;
	private java.lang.String email;
	private java.util.Date regdate;
	private java.lang.Short sex;
	private java.lang.String qq;
	private java.util.Date lastlogin;
	private java.lang.String lineno;
	private java.lang.Short type;
	private java.lang.Integer votecount;
	private java.lang.Boolean deleteflag;
	private java.lang.String realname;
	private java.lang.String id;
	private java.lang.String mobileno;
	private java.lang.String branch;
	private java.lang.String bankno;
	private java.lang.String alipayacount;
	private java.lang.Integer category;
	private java.lang.Integer subcategory;
	private java.lang.Integer modifyuserno;
	private java.util.Date modifytime;
	private java.lang.String openid;
	private java.lang.Boolean activedflag;
	private java.lang.String mailtoken;
	private java.lang.Integer chargefee;
	
	private java.lang.String headimgurl;
	

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="userno"
     */
	public int getUserno () {
		return userno;
	}

	/**
	 * Set the unique identifier of this class
	 * @param userno the new ID
	 */
	public void setUserno (int userno) {
		this.userno = userno;
		this.hashCode = Integer.MIN_VALUE;
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
	 * Return the value associated with the column: password
	 */
	public java.lang.String getPassword () {
		return password;
	}

	/**
	 * Set the value related to the column: password
	 * @param password the password value
	 */
	public void setPassword (java.lang.String password) {
		this.password = password;
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
	 * Return the value associated with the column: email
	 */
	public java.lang.String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: email
	 * @param email the email value
	 */
	public void setEmail (java.lang.String email) {
		this.email = email;
	}



	/**
	 * Return the value associated with the column: regdate
	 */
	public java.util.Date getRegdate () {
		return regdate;
	}

	/**
	 * Set the value related to the column: regdate
	 * @param regdate the regdate value
	 */
	public void setRegdate (java.util.Date regdate) {
		this.regdate = regdate;
	}



	/**
	 * Return the value associated with the column: sex
	 */
	public java.lang.Short getSex () {
		return sex;
	}

	/**
	 * Set the value related to the column: sex
	 * @param sex the sex value
	 */
	public void setSex (java.lang.Short sex) {
		this.sex = sex;
	}



	/**
	 * Return the value associated with the column: qq
	 */
	public java.lang.String getQq () {
		return qq;
	}

	/**
	 * Set the value related to the column: qq
	 * @param qq the qq value
	 */
	public void setQq (java.lang.String qq) {
		this.qq = qq;
	}



	/**
	 * Return the value associated with the column: lastlogin
	 */
	public java.util.Date getLastlogin () {
		return lastlogin;
	}

	/**
	 * Set the value related to the column: lastlogin
	 * @param lastlogin the lastlogin value
	 */
	public void setLastlogin (java.util.Date lastlogin) {
		this.lastlogin = lastlogin;
	}



	/**
	 * Return the value associated with the column: lineno
	 */
	public java.lang.String getLineno () {
		return lineno;
	}

	/**
	 * Set the value related to the column: lineno
	 * @param lineno the lineno value
	 */
	public void setLineno (java.lang.String lineno) {
		this.lineno = lineno;
	}



	/**
	 * Return the value associated with the column: type
	 */
	public java.lang.Short getType () {
		return type;
	}

	/**
	 * Set the value related to the column: type
	 * @param type the type value
	 */
	public void setType (java.lang.Short type) {
		this.type = type;
	}



	/**
	 * Return the value associated with the column: votecount
	 */
	public java.lang.Integer getVotecount () {
		return votecount;
	}

	/**
	 * Set the value related to the column: votecount
	 * @param votecount the votecount value
	 */
	public void setVotecount (java.lang.Integer votecount) {
		this.votecount = votecount;
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
	 * Return the value associated with the column: realname
	 */
	public java.lang.String getRealname () {
		return realname;
	}

	/**
	 * Set the value related to the column: realname
	 * @param realname the realname value
	 */
	public void setRealname (java.lang.String realname) {
		this.realname = realname;
	}



	/**
	 * Return the value associated with the column: id
	 */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the value related to the column: id
	 * @param id the id value
	 */
	public void setId (java.lang.String id) {
		this.id = id;
	}



	/**
	 * Return the value associated with the column: mobileno
	 */
	public java.lang.String getMobileno () {
		return mobileno;
	}

	/**
	 * Set the value related to the column: mobileno
	 * @param mobileno the mobileno value
	 */
	public void setMobileno (java.lang.String mobileno) {
		this.mobileno = mobileno;
	}



	/**
	 * Return the value associated with the column: branch
	 */
	public java.lang.String getBranch () {
		return branch;
	}

	/**
	 * Set the value related to the column: branch
	 * @param branch the branch value
	 */
	public void setBranch (java.lang.String branch) {
		this.branch = branch;
	}



	/**
	 * Return the value associated with the column: bankno
	 */
	public java.lang.String getBankno () {
		return bankno;
	}

	/**
	 * Set the value related to the column: bankno
	 * @param bankno the bankno value
	 */
	public void setBankno (java.lang.String bankno) {
		this.bankno = bankno;
	}



	/**
	 * Return the value associated with the column: alipayacount
	 */
	public java.lang.String getAlipayacount () {
		return alipayacount;
	}

	/**
	 * Set the value related to the column: alipayacount
	 * @param alipayacount the alipayacount value
	 */
	public void setAlipayacount (java.lang.String alipayacount) {
		this.alipayacount = alipayacount;
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
	 * Return the value associated with the column: openid
	 */
	public java.lang.String getOpenid () {
		return openid;
	}

	/**
	 * Set the value related to the column: openid
	 * @param openid the openid value
	 */
	public void setOpenid (java.lang.String openid) {
		this.openid = openid;
	}



	/**
	 * Return the value associated with the column: activedflag
	 */
	public java.lang.Boolean isActivedflag () {
		return activedflag;
	}

	/**
	 * Set the value related to the column: activedflag
	 * @param activedflag the activedflag value
	 */
	public void setActivedflag (java.lang.Boolean activedflag) {
		this.activedflag = activedflag;
	}



	/**
	 * Return the value associated with the column: mailtoken
	 */
	public java.lang.String getMailtoken () {
		return mailtoken;
	}

	/**
	 * Set the value related to the column: mailtoken
	 * @param mailtoken the mailtoken value
	 */
	public void setMailtoken (java.lang.String mailtoken) {
		this.mailtoken = mailtoken;
	}




	public java.lang.Integer getChargefee() {
		return chargefee;
	}

	public void setChargefee(java.lang.Integer chargefee) {
		this.chargefee = chargefee;
	}

	public java.lang.String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(java.lang.String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.yidu.novel.entity.TUser)) return false;
		else {
			org.yidu.novel.entity.TUser tUser = (org.yidu.novel.entity.TUser) obj;
			return (this.getUserno() == tUser.getUserno());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getUserno();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}