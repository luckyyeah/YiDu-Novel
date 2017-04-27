package org.yidu.novel.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.yidu.novel.utils.SecurityCode;
import org.yidu.novel.utils.SecurityImage;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * <p>
 * 生成验证码
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Results({ @Result(name = "success", type = "stream", params = { "contentType", "image/jpeg", "inputName",
        "imageStream", "bufferSize", "4096" }) })
public class SecurityCodeImageAction extends ActionSupport implements SessionAware {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1496691731440581303L;
    /**
     * 图片流
     */
    private ByteArrayInputStream imageStream;
    /**
     * session域
     */
    private Map<String, Object> session;

    /**
     * 获取 imageStream
     * 
     * @return imageStream
     */
    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    /**
     * 
     * 设置imageStream
     * 
     * 
     * @param imageStream
     *            imageStream
     */
    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    /**
     * 获取 session
     * 
     * @return session
     */
    public Map<String, Object> getSession() {
        return session;
    }

    /**
     * 
     * 设置session
     * 
     * 
     * @param session
     *            session
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Action("imagecode")
    @Override
    public String execute() throws Exception {
        // 如果开启Hard模式，可以不区分大小写
        // String securityCode =
        // SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard,
        // false).toLowerCase();

        // 获取默认难度和长度的验证码
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        // 放入session中
        session.put("securityCode", securityCode);
        return SUCCESS;
    }
}
