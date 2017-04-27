package org.yidu.novel.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.AjaxServiceAction.ReturnCode;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.JsonInfoDTO;
import org.yidu.novel.entity.TChapterOrder;
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
@Action(value = "checkviporder")
public class CheckVipOrderAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5991997032217966675L;

    /**
     * 功能名称。
     */
    public static final String NAME = "checkviporder";

    
    /**
     * 小说编号
     */
    private int chapterno;
    
    /**
     * JSON数据的DTO
     */
    private JsonInfoDTO dto = new JsonInfoDTO();
    
    
    public int getChapterno() {
		return chapterno;
	}



	public void setChapterno(int chapterno) {
		this.chapterno = chapterno;
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
        logger.debug("execute checkviporder start.");
        List<TChapterOrder>  vipOrderList= orderService.findVipOrder(LoginManager.getLoginUser().getUserno(),chapterno);
        if(vipOrderList.size()>0){
        		dto.setCode(ReturnCode.SUCCESS);
           
        } else {
        	dto.setCode(ReturnCode.FAILED);
        }
        logger.debug("execute checkviporder end.");
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