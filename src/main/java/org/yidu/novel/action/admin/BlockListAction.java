package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 区块列表Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "blockList")
public class BlockListAction extends AbstractAdminListBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -4110412379794700028L;

    /**
     * 功能名称。
     */
    public static final String NAME = "blockList";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    private int blockno;

    List<TSystemBlock> blockList = new ArrayList<TSystemBlock>();

    public int getBlockno() {
        return this.blockno;
    }

    public void setBlockno(int blockno) {
        this.blockno = blockno;
    }

    public List<TSystemBlock> getBlockList() {
        return blockList;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        // 初始化类型选项
        initCollections(new String[] { "collectionProperties.block.type" });
        SystemBlockSearchBean searchBean = new SystemBlockSearchBean();

        if (StringUtils.isEmpty(pagination.getSortColumn())) {
            pagination.setSortColumn("blockno");
        }
        searchBean.setPagination(pagination);
        // 总件数设置
        pagination.setPreperties(systemBlockService.getCount(searchBean));
        blockList = systemBlockService.find(searchBean);

        // Setting number of records in the particular page
        pagination.setPageRecords(blockList.size());
        logger.debug("loadData normally end.");
    }

    public String delete() throws Exception {
        logger.debug("del start.");

        TSystemBlock systemBlock = systemBlockService.getByNo(blockno);
        systemBlock.setDeleteflag(true);
        systemBlock.setModifyuserno(LoginManager.getLoginUser().getUserno());
        systemBlock.setModifytime(new Date());
        systemBlockService.save(systemBlock);

        loadData();
        logger.debug("del normally end.");
        return SUCCESS;
    }

}
