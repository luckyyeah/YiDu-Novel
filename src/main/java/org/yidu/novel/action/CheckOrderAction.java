package org.yidu.novel.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.AjaxServiceAction.ReturnCode;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.JsonInfoDTO;
import org.yidu.novel.entity.TChargeOrder;
import org.yidu.novel.entity.TOrder;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * 
 * <p>
 * 检查用户登录状态
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "checkorder")
public class CheckOrderAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5991997032217966675L;

    /**
     * 功能名称。
     */
    public static final String NAME = "checkorder";

    
    /**
     * 订单号
     */
    private String orderno;
    
    /**
     * JSON数据的DTO
     */
    private JsonInfoDTO dto = new JsonInfoDTO();
    
    public String getOrderno() {
		return orderno;
	}



	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
    public JsonInfoDTO getData() {
        return dto;
    }
	/**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    @SkipValidation
    @Override
    public String execute() {
        logger.debug("execute check order start.");
        List<TOrder>  orderList= orderService.findOrder(orderno);
        if(orderList.size()>0){
        	  TChargeOrder chargeOrder= orderService.findChargeOrderByOrderNo(orderno);
        	  TOrder order= orderList.get(0);
        	  if(chargeOrder!=null){
        	        TUser user = new TUser();
        	       //int fee= order.getTotalfee();
        	        //测试
        	        int fee= order.getTotalfee()*1000;
        	        List<Map<String,String>> feeList = getPropList(new String[] { "collectionProperties.pay.fee" }); 
        	        for(Map<String,String>feeMap:feeList){
        	        	if(feeMap.get(String.valueOf(fee/100))!=null){
        	        		fee+=Integer.parseInt(feeMap.get(String.valueOf(fee/100)));
        	        				
        	        	}
        	        }
        	        //修改金额
        	        user = userService.getByNo(LoginManager.getLoginUser().getUserno());
        	        user.setChargefee(user.getChargefee()+fee);
        	        // 注册用户登录
        	        this.userService.save(user);
        	        chargeOrder.setStatus(ReturnCode.SUCCESS);
        	        orderService.save(chargeOrder);
        	        // 登录处理
        	        LoginManager.doLogin(user);
        	  }
            dto.setCode(ReturnCode.SUCCESS);
           
        } else {
        	dto.setCode(ReturnCode.FAILED);
        }
        logger.debug("execute check order end.");
        return JSON_RESULT;
    }



    @Override
    public void loadData() {
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