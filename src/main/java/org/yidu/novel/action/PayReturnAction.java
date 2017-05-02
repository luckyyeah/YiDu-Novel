package org.yidu.novel.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TOrder;

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
@Action(value = "payreturn")
public class PayReturnAction extends AbstractPublicBaseAction implements ServletResponseAware{
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5966399886620363535L;

   
    /**
     * 订单号
     */
    private String out_trade_no;
   
    /**
     * 平台订单号
     */
    private String transaction_id;
   
    
    /**
     * 交易金额
     */
    private int total_fee;
 
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
    
	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
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

	@Override
    public String execute() {

        logger.debug("execute payreturn started. ");
        TOrder tOrder =new TOrder();
        tOrder.setOrderno(out_trade_no);
        tOrder.setTotalfee(total_fee);
        tOrder.setTimeend(time_end);
        tOrder.setTransactionid(transaction_id);
        tOrder.setPaytype(pay_type);
        tOrder.setAttach(attach);
        tOrder.setModifytime(new Date());
        orderService.save(tOrder);
        logger.debug("execute payreturn normally end. ");
        //回调输出
				try {
					response.getWriter().write("success");
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
