package org.yidu.novel.dto;

import java.util.List;

/**
 * 
 * <p>
 * 手机页面用的JSON信息DTO
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class JsonInfoDTO {

    /**
     * 总数
     */
    private int total;
    /**
     * 页数
     */
    private int pages;
    /**
     * index
     * 
     */
    private int index;
    /**
     * 大小
     */
    private int size;
    /**
     * 表示内容
     */
    private List<?> items;
    /**
     * 处理结果
     */
    private String result;
    /**
     * 错误
     */
    private String err;
    /**
     * 处理代码
     */
    private int code;

    /**
     * 获取 total
     * 
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 
     * 设置total
     * 
     * 
     * @param total
     *            total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取 pages
     * 
     * @return pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * 
     * 设置pages
     * 
     * 
     * @param pages
     *            pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * 获取 index
     * 
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * 
     * 设置index
     * 
     * 
     * @param index
     *            index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 获取 size
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * 
     * 设置size
     * 
     * 
     * @param size
     *            size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 获取 items
     * 
     * @return items
     */
    public List<?> getItems() {
        return items;
    }

    /**
     * 
     * 设置items
     * 
     * 
     * @param items
     *            items
     */
    public void setItems(List<?> items) {
        this.items = items;
    }

    /**
     * 获取 result
     * 
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     * 
     * 设置result
     * 
     * 
     * @param result
     *            result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取 err
     * 
     * @return err
     */
    public String getErr() {
        return err;
    }

    /**
     * 
     * 设置err
     * 
     * 
     * @param err
     *            err
     */
    public void setErr(String err) {
        this.err = err;
    }

    /**
     * 获取 code
     * 
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * 
     * 设置code
     * 
     * 
     * @param code
     *            code
     */
    public void setCode(int code) {
        this.code = code;
    }

}
