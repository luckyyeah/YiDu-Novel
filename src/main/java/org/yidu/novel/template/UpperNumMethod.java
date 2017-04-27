package org.yidu.novel.template;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * <p>
 * Freemarker自定义方法 <br>
 * 将数字转换成大写汉字
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class UpperNumMethod implements TemplateMethodModel {

    /**
     * 执行方法
     * 
     * @param argList
     *            参加列表
     * @return 格式化后的字符串
     * @throws TemplateModelException
     *             模版异常
     */
    @Override
    public Object exec(@SuppressWarnings("rawtypes") List argList) throws TemplateModelException {
        // 限定方法中必须且只能传递一个参数
        if (argList.size() != 1) {
            throw new TemplateModelException("Wrong arguments!");
        }
        // String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        String[] u = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        char[] str = String.valueOf(argList.get(0)).toCharArray();
        String rstr = "";
        for (int i = 0; i < str.length; i++) {
            rstr = rstr + u[Integer.parseInt(str[i] + "")];
        }
        return rstr;
    }
}
