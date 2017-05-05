package org.yidu.novel.action.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.PayReturnBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChargeOrder;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.DateUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.MD5;
import org.yidu.novel.utils.Utils;



/**
 * <p>
 * 章节编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "centerzzfpay")
public class CenterZZFWebPayAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -6064353669030314155L;
    /**
     * 功能名称。
     */
    public static final String NAME = "centerzzfypay";
    public static final String PAY_REQUEST_URL = "http://pay.csl2016.cn:8000/sp/theThirdPayWapEntrance.e?";
    public static final String MCH_ID = "1000100020001174";
    public static final String SECRET_KEY = "BB7F30347DC144DDA1D754AE0827D62D";
    public static final String APP_ID = "3060";
    public static final String QN = "zyap3060_56449_100";
    public static final String CURRENCY_WX = "1000200010000000";
    public static final String CURRENCY_ALI = "1000200020000000"; 
    public static final String PAY_MODE_WX = "5";
    public static final String PAY_MODE_ALI= "2";
    public static final String NOTIFY_URL = "http://m.qpk8.cn/payzzfcallback";
    
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

	@Override
    public String execute() {
        logger.debug("loadData start.");
        // 初始化种别下拉列表选项
       // initCollections(new String[] { "collectionProperties.pay.fee" });
        //feeList = getPropList(new String[] { "collectionProperties.pay.fee" }); 
        String rand= Utils.getRandomString(0,999999,6);
        String time= DateUtils.getTimes();
        orderno = paytype.toString()+time+rand;
				Map<String, String> params =new HashMap<String, String>();
				String url = PAY_REQUEST_URL;
			  url += "partnerId="+MCH_ID;
				String fee= String.valueOf(chargefee*100);
				//params.put("money",String.valueOf(chargefee*100));
			  url += "&money="+fee;
				url += "&appId="+APP_ID;
				url += "&qn="+QN;
				String times= DateUtils.getTimes();
				url += "&times="+times;
				String currency="";
				if("d".equals(paytype)){
					url += "&currency="+CURRENCY_ALI;
					currency= CURRENCY_ALI;
					url += "&paymode="+PAY_MODE_ALI;
				} else {
					url += "&currency="+CURRENCY_WX;
					currency= CURRENCY_WX;
					url += "&paymode="+PAY_MODE_WX;					
				}
				url += "&appFeeName="+"VIP";
				url += "&cpparam="+orderno;
				String sign =MD5.md5(MCH_ID+APP_ID+currency+fee+times+SECRET_KEY);
				url += "&sign="+sign;
				url += "&notifyUrl="+NOTIFY_URL;
			  //PayReturnBean payReturnBean=  JSON.parseObject(returnData, PayReturnBean.class);
			//  if("0".equals(payReturnBean.getResult_code())){
				int bookcharegefee= 0;
        List<Map<String,String>> feeList = getPropList(new String[] { "collectionProperties.pay.fee" }); 
        for(Map<String,String>feeMap:feeList){
        	if(feeMap.get(String.valueOf(chargefee))!=null){
        		bookcharegefee+=Integer.parseInt(feeMap.get(String.valueOf(chargefee)));
        				
        	}
        }
        bookcharegefee+=chargefee*100;
			   TChargeOrder tChargeOrder =new TChargeOrder();
			   tChargeOrder.setFee(bookcharegefee);
			   tChargeOrder.setOrderno(orderno);
			   tChargeOrder.setUserno(LoginManager.getLoginUser().getUserno());
			   tChargeOrder.setStatus(-1);
			   tChargeOrder.setModifytime(new Date());
			   orderService.save(tChargeOrder);
			  
		        HttpSession session =  LoginManager.getSession(false);
		        if (Utils.isDefined(session)) {
		        	session.setAttribute("paypreurl", "/user/center");
		        }
			   this.setForwardUrl(url);
        logger.debug("web pay normally end.");
        return GOTO_REDIRECT;
    }



	@Override
	public int getPageType() {
		// TODO Auto-generated method stub
		return YiDuConstants.Pagetype.PAGE_USER_CHARGE;
	}

	  @Override
	    public String getTempName() {
	        
	        if(chapterno==0){
		  		return "user/centerscanpay";
		  	} else {
		  		return "user/scanpay";
		  	}
	    }
    /**
     * 获取小说子目录
     * 
     * @return 小说子目录
     */
    @Deprecated
    public int getSubDir() {
        return articleno / YiDuConstants.SUB_DIR_ARTICLES;
    }

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		
	}  
}
