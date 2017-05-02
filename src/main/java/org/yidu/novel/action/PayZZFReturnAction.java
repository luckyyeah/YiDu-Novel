package org.yidu.novel.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TOrder;
import org.yidu.novel.utils.DateUtils;

/**
 * 
 * <p>
 * 手机画面用各种ajax接口
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "zzfpayreturn")
public class PayZZFReturnAction extends AbstractPublicBaseAction implements ServletResponseAware{
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5966399886620363535L;

   
    /**
     * 订单号
     */
    private String cporderid;
   
    /**
     * 平台订单号
     */
    private String orderid;
   
    
    /**
     * 交易金额
     */
    private int appfee;
 
    /**
     * 支付完成时间
     */
    private String time_end;
    
    /**
     * 透传参数
     */
    private String attach;
    
    /**
     * 交易金额
     */
    private int status;
    
    private String feemode;
    /**
     * 交易金额
     */
    private int pay_type;
    
    
    private javax.servlet.http.HttpServletResponse response;
    // 获得HttpServletResponse对象
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }      
    


	public String getCporderid() {
		return cporderid;
	}



	public void setCporderid(String cporderid) {
		this.cporderid = cporderid;
	}



	public String getOrderid() {
		return orderid;
	}



	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}



	public int getAppfee() {
		return appfee;
	}



	public void setAppfee(int appfee) {
		this.appfee = appfee;
	}



	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}



	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}



	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPay_type() {
		return pay_type;
	}

	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}

	public String getFeemode() {
		return feemode;
	}



	public void setFeemode(String feemode) {
		this.feemode = feemode;
	}



	@Override
    public String execute() {

        logger.debug("execute payreturn started. ");
        TOrder tOrder =new TOrder();
        tOrder.setOrderno(cporderid);
        tOrder.setTotalfee(appfee);
        tOrder.setTimeend(DateUtils.getTimes());
        tOrder.setTransactionid(orderid);
        if("1000200010000000".equals(feemode)){
        	pay_type=1;
        } else if("1000200020000000".equals(feemode)) {
        	pay_type=2;
        }
        tOrder.setPaytype(pay_type);
        tOrder.setAttach(attach);
        tOrder.setModifytime(new Date());
        orderService.save(tOrder);
        logger.debug("execute payreturn normally end. ");
        //回调输出
				try {
					response.getWriter().write("0");
					response.getWriter().flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        return null;
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public String getTempName() {
        return null;
    }
    
}
