package org.yidu.novel.action.user;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TMessage;
import org.yidu.novel.utils.LoginManager;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 
 * <p>
 * 消息管理Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "messageEdit")
public class MessageEditAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 6707140588808286899L;

    private String title;

    private String content;

    /**
     * 获取title
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * 设置title
     * 
     * 
     * @param title
     *            title
     */
    @Validations(
    // 必須
    requiredStrings = { @RequiredStringValidator(message = "${getText(\"errors.required.input\", "
            + "{getText(\"label.user.message.title\")})}") }, stringLengthFields = { @StringLengthFieldValidator(maxLength = "32", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.message.title\")})}") })
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取content
     * 
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * 设置content
     * 
     * 
     * @param content
     *            content
     */
    @Validations(
    // 必須
    requiredStrings = { @RequiredStringValidator(message = "${getText(\"errors.required.input\", "
            + "{getText(\"label.user.message.content\")})}") }, stringLengthFields = { @StringLengthFieldValidator(maxLength = "255", message = "${getText(\"errors.maxlength\", "
            + "{ {maxLength},getText(\"label.user.message.content\")})}") })
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getTempName() {
        return "user/messageEdit";
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_ADD_MESSAGE;
    }

    @Override
    protected void loadData() {
    }

    @Transactional
    public String add() {
        // 初始化消息
        TMessage message = new TMessage();
        message.setContent(content);
        message.setTitle(title);
        message.setDeleteflag(false);
        message.setTouserno(0);
        message.setLoginid(LoginManager.getLoginUser().getLoginid());
        message.setUserno(LoginManager.getLoginUser().getUserno());
        message.setPostdate(new Date());
        message.setModifyuserno(LoginManager.getLoginUser().getUserno());
        message.setModifytime(new Date());
        // 保存
        messageService.save(message);
        // 返回成功
        addActionMessage(getText("messages.addmessage.success"));
        return FREEMARKER_MESSAGE;
    }
}
