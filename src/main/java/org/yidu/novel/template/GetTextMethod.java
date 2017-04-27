package org.yidu.novel.template;

import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * <p>
 * Freemarker自定义方法 <br>
 * 实现getText(i18n)功能
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class GetTextMethod implements TemplateMethodModel {

    private static PropertiesConfiguration languageConf;

    private PropertiesConfiguration getProperties() {
        if (languageConf == null) {
            try {
                languageConf = new PropertiesConfiguration(Thread.currentThread().getContextClassLoader()
                        .getResource("language/package.properties"));
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return languageConf;
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
        // 返回getText执行结果
        return getProperties().getProperty((String) argList.get(0));
    }
}
