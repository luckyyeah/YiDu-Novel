package org.yidu.novel.template;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * <p>
 * Freemarker自定义方法 <br>
 * 实现response.encodeURL(url)功能
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class EncodeURLMethod implements TemplateMethodModel {

    /**
     * HttpServletResponse
     */
    private HttpServletResponse response;

    /**
     * 带参数的构造函数
     * 
     * @param response
     *            HttpServletResponse对象
     */
    public EncodeURLMethod(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 执行方法
     * 
     * @param argList
     *            方法参数列表
     * @return Object 方法返回值
     * @throws TemplateModelException
     *             模版错误
     */
    @SuppressWarnings("rawtypes")
    public Object exec(List argList) throws TemplateModelException {
        // 限定方法中必须且只能传递一个参数
        if (argList.size() != 1) {
            throw new TemplateModelException("Wrong arguments!");
        }
        // 返回response.encodeURL执行结果
        return response.encodeURL((String) argList.get(0));
    }
}
