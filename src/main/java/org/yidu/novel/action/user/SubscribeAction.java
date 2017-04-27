package org.yidu.novel.action.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.bean.SubscribeSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.SubscribeDTO;
import org.yidu.novel.entity.TSubscribe;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 小说订阅管理Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class SubscribeAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 366181195078436796L;

    /**
     * 功能名称。
     */
    public static final String NAME = "subscribe";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 订阅编号
     */
    private int subscribeno;

    /**
     * 小说编号
     */
    private int articleno;
    /**
     * 小说列表
     */
    private List<SubscribeDTO> subscribeList = new ArrayList<SubscribeDTO>();

    /**
     * 获取subscribeno
     * 
     * @return subscribeno
     */
    public int getSubscribeno() {
        return subscribeno;
    }

    /**
     * 
     * 设置subscribeno
     * 
     * 
     * @param subscribeno
     *            subscribeno
     */
    public void setSubscribeno(int subscribeno) {
        this.subscribeno = subscribeno;
    }

    /**
     * 获取articleno
     * 
     * @return articleno
     */
    public int getArticleno() {
        return articleno;
    }

    /**
     * 
     * 设置articleno
     * 
     * 
     * @param articleno
     *            articleno
     */
    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    /**
     * 获取subscribeList
     * 
     * @return subscribeList
     */
    public List<SubscribeDTO> getSubscribeList() {
        return subscribeList;
    }

    /**
     * 
     * 设置subscribeList
     * 
     * 
     * @param subscribeList
     *            subscribeList
     */
    public void setSubscribeList(List<SubscribeDTO> subscribeList) {
        this.subscribeList = subscribeList;
    }

    public int getMaxSubscribeNum() {
        return YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_SUBSCRIBE);
    }

    public int getSubscribeNum() {
        return Utils.isDefined(subscribeList) ? subscribeList.size() : 0;
    }

    @Override
    public String getTempName() {
        return "user/subscribe";
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_USER_SUBSCRIBE;
    }

    @Override
    protected void loadData() {
        SubscribeSearchBean searchBean = new SubscribeSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        subscribeList = this.subscribeService.findAllData(searchBean);
    }

    @Override
    public String getBackUrl() {
        return URL;
    }

    @Transactional
    public String add() {
        // 小说号如果没有的话，终止登录，返回错误画面
        if (articleno == 0) {
            addActionError(getText("errors.not.exsits.article"));
            return FREEMARKER_ERROR;
        }

        // 检查当前登录的最大件数
        SubscribeSearchBean searchBean = new SubscribeSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        int subscribeCount = this.subscribeService.getCount(searchBean);
        if (subscribeCount > YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_SUBSCRIBE, 30)) {
            addActionError(getText("errors.max.subscribe"));
            return FREEMARKER_ERROR;
        }

        searchBean.setArticleno(articleno);
        subscribeCount = this.subscribeService.getCount(searchBean);
        if (subscribeCount > 0) {
            // 已经存在啦，算了，告诉他成功啦！哈哈
            addActionMessage(getText("messages.proccess.success"));
            return FREEMARKER_MESSAGE;
        }

        TSubscribe subscribe = new TSubscribe();
        subscribe.setArticleno(articleno);
        subscribe.setUserno(LoginManager.getLoginUser().getUserno());

        this.subscribeService.save(subscribe);
        this.loadData();
        addActionMessage(getText("messages.proccess.success"));
        return FREEMARKER_MESSAGE;
    }

    @Transactional
    public String delete() {
        if (subscribeno != 0) {
            TSubscribe subscribe = this.subscribeService.getByNo(subscribeno);
            if (subscribe.getUserno() == LoginManager.getLoginUser().getUserno()) {
                this.subscribeService.deleteByNo(subscribeno);
            } else {
                addActionError(getText("errors.unauthority.bookcase"));
                return FREEMARKER_ERROR;
            }
        }
        return REDIRECT;
    }
}
