package org.yidu.novel.dto;

/**
 * 
 * <p>
 * 分类小说件数DTO
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.6
 * @author shinpa.you
 */
public class CategoryCountDTO {

    private int category;
    private int count;

    /**
     * 获取category
     * 
     * @return category
     */
    public int getCategory() {
        return category;
    }

    /**
     * 
     * 设置category
     * 
     * @param category
     *            category
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * 获取count
     * 
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * 
     * 设置count
     * 
     * @param count
     *            count
     */
    public void setCount(int count) {
        this.count = count;
    }

}
