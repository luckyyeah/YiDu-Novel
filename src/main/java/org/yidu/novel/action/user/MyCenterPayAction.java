package org.yidu.novel.action.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 章节编辑Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "centerpay")
public class MyCenterPayAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -6064353669030314155L;
    /**
     * 功能名称。
     */
    public static final String NAME = "centerpay";

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
    private List<Map<String,String>> feeList;   
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





	public List<Map<String, String>> getFeeList() {
		return feeList;
	}

	public void setFeeList(List<Map<String, String>> feeList) {
		this.feeList = feeList;
	}

	@Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化种别下拉列表选项
       // initCollections(new String[] { "collectionProperties.pay.fee" });
        feeList = getPropList(new String[] { "collectionProperties.pay.fee" }); 

        logger.debug("loadData normally end.");
    }



	@Override
	public int getPageType() {
		// TODO Auto-generated method stub
		return YiDuConstants.Pagetype.PAGE_USER_CHARGE;
	}

	  @Override
	    public String getTempName() {
		  	if(chapterno==0){
		  		return "user/centerpay";
		  	} else {
		  		 return "user/pay";
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
}
