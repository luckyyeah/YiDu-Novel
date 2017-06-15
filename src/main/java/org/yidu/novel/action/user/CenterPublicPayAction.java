package org.yidu.novel.action.user;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.PayReturnBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChargeOrder;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.DateUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;
import org.yidu.novel.utils.WeixinUtils;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 章节编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */

public class CenterPublicPayAction extends AbstractPublicBaseAction {
	/**
	 * 串行化版本统一标识符
	 */
	private static final long serialVersionUID = -6064353669030314155L;
	/**
	 * 功能名称。
	 */
	public static final String NAME = "scanpay";
	public static final String SCAN_REQUEST_URL = "http://paytest.bhwjq.com/lpay/pay/gateway";
	public static final String MCH_ID = "10009";
	public static final String CALLBACK_URL = "http://m.qpk8.cn/paycallback";
	/**
	 * 访问URL。
	 */
	public static final String URL = NAMESPACE + "/" + NAME;
	private int chapterno;
	private Integer articleno;
	private String articlename;
	private String volumeid;
	private String chaptername;
	private Date postdate;
	private Integer chargefee;
	private String paytype;
	private String codeimgurl;
	private String orderno;

	public int getChapterno() {
		return chapterno;
	}

	public void setChapterno(int chapterno) {
		this.chapterno = chapterno;
	}

	public Integer getArticleno() {
		return articleno;
	}

	public void setArticleno(Integer articleno) {
		this.articleno = articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = Integer.parseInt(articleno);
	}

	public String getArticlename() {
		return articlename;
	}

	public void setArticlename(String articlename) {
		this.articlename = articlename;
	}

	public String getVolumeid() {
		return volumeid;
	}

	public void setVolumeid(String volumeid) {
		this.volumeid = volumeid;
	}

	public String getChaptername() {
		return chaptername;
	}

	public void setChaptername(String chaptername) {
		this.chaptername = chaptername;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public Integer getChargefee() {
		return chargefee;
	}

	public void setChargefee(Integer chargefee) {
		this.chargefee = chargefee;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getCodeimgurl() {
		return codeimgurl;
	}

	public void setCodeimgurl(String codeimgurl) {
		this.codeimgurl = codeimgurl;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	@SkipValidation
    @Override
    @Action(value = "centerpublicpay")
    public String execute() {
		String rand = Utils.getRandomString(0, 999999, 6);
		String time = DateUtils.getTimes();
		orderno = paytype.toString() + time + rand;
		String url= SCAN_REQUEST_URL+"?mch_id="+MCH_ID;
		url+="&total_fee="+String.valueOf(chargefee*100);
		url+="&out_trade_no="+orderno;
		url+="&pay_type="+paytype;
		url+="&callback_url="+CALLBACK_URL;
		//this.setCodeimgurl(payReturnBean.getCode_img_url());
		TChargeOrder tChargeOrder = new TChargeOrder();
		tChargeOrder.setFee(chargefee * 100);
		tChargeOrder.setOrderno(orderno);
		tChargeOrder.setUserno(LoginManager.getLoginUser().getUserno());
		tChargeOrder.setStatus(-1);
		tChargeOrder.setModifytime(new Date());
		orderService.save(tChargeOrder);
		HttpSession session = LoginManager.getSession(false);
		if (Utils.isDefined(session)) {
			session.setAttribute("paypreurl", "/user/center");
		}
		this.setForwardUrl(url);
		logger.debug("loadData normally end.");
		   
        return GOTO_REDIRECT;
    }
    @Override
	protected void loadData() {
    	  /*logger.debug("loadData start.");
		// 初始化种别下拉列表选项
		// initCollections(new String[] { "collectionProperties.pay.fee" });
		// feeList = getPropList(new String[] { "collectionProperties.pay.fee"
		// });
		String rand = Utils.getRandomString(0, 999999, 6);
		String time = DateUtils.getTimes();
		orderno = paytype.toString() + time + rand;
		Map<String, String> params = new HashMap<String, String>();
		params.put("mch_id", MCH_ID);
		params.put("total_fee", String.valueOf(chargefee * 100));
		// params.put("total_fee",String.valueOf(chargefee/10));
		params.put("out_trade_no", orderno);
		params.put("pay_type", paytype);
		String returnData = Utils.doPost(SCAN_REQUEST_URL, params, "utf-8");
		logger.debug("returnData="+returnData);
		String url= SCAN_REQUEST_URL+"?mch_id="+MCH_ID;
		url+="&total_fee="+String.valueOf(chargefee * 100);
		url+="&out_trade_no="+orderno;
		url+="&pay_type="+paytype;
		if(returnData!=null&&!"".equals(returnData)){
		PayReturnBean payReturnBean = JSON.parseObject(returnData, PayReturnBean.class);
		if ("0".equals(payReturnBean.getResult_code())) {
			this.setCodeimgurl(payReturnBean.getCode_img_url());
			TChargeOrder tChargeOrder = new TChargeOrder();
			tChargeOrder.setFee(chargefee * 100);
			tChargeOrder.setOrderno(orderno);
			tChargeOrder.setUserno(LoginManager.getLoginUser().getUserno());
			tChargeOrder.setStatus(-1);
			tChargeOrder.setModifytime(new Date());
			orderService.save(tChargeOrder);
			this.setForwardUrl(payReturnBean.getCode_url());
		} else {
			this.setForwardUrl(url);
			this.setCodeimgurl("");
		}
		}
		logger.debug("loadData normally end.");*/
	}



    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @Override
    public String getTempName() {
        // TODO Auto-generated method stub
        return null;
    }
}
