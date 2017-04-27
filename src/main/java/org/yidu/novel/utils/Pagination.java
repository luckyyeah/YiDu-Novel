package org.yidu.novel.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * 翻页管理工具类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class Pagination {
    /**
     * 每页表示件数
     */
    private int pageSize = 0;
    /**
     * 页号
     */
    private int pageNumber = 0;
    /**
     * 排序列名
     */
    private String sortColumn = null;
    /**
     * 排列顺序
     */
    private String sortOrder = "ASC";
    /**
     * 总记录数
     */
    private int totalRecords = 0;
    /**
     * 当前页记录数
     */
    private int pageRecords = 0;
    /**
     * 开始记录号
     */
    private int start = 0;
    /**
     * 结束记录号
     */
    private int end = 0;
    /**
     * 总页数
     */
    private int totalPages = 0;
    /**
     * 排序的CSS Class
     */
    private String sortClass = null;
    /**
     * 页数列表
     */
    private List<Integer> pageNumberList = new ArrayList<Integer>();

    /**
     * 判断是否有上一页
     * 
     * @return 存在上一页标识
     */
    public boolean getPreviousFlag() {
        if (pageNumber > 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否有下一页
     * 
     * @return 存在下一页标识
     */
    public boolean getNextFlag() {
        if (totalPages == pageNumber) {
            return false;
        }
        return true;
    }

    /**
     * 设置件数
     * 
     * 
     * @param count
     *            件数
     */
    public void setPreperties(int count) {
        totalRecords = count;
        totalPages = (int) Math.ceil(((double) count / (double) pageSize));
        start = ((pageSize * pageNumber) - pageSize);
        end = pageSize;
        if (pageSize == 0) {
            start = 0;
            end = count;
            totalPages = 1;
        }
        if (sortOrder.equals("ASC")) {
            sortClass = "order1";

        } else {
            sortClass = "order2";
        }
    }

    /**
     * 构造翻页工具类
     * 
     * @param pageSize
     *            每页记录书
     * @param pageNumber
     *            页号
     */
    public Pagination(int pageSize, int pageNumber) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /**
     * 获得每页记录数
     * 
     * @return 每页记录数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页记录数
     * 
     * @param pageSize
     *            每页记录数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获得页号
     * 
     * @return 页号
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置页号
     * 
     * @param pageNumber
     *            页号
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 获得总记录数
     * 
     * @return 总记录数
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * 设置总记录数
     * 
     * @param totalRecords
     *            总记录数
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * 获得当前页记录数
     * 
     * @return 当前页记录数
     */
    public int getPageRecords() {
        return pageRecords;
    }

    /**
     * 设置当前页记录数
     * 
     * @param pageRecords
     *            当前页记录数
     */
    public void setPageRecords(int pageRecords) {
        this.pageRecords = pageRecords;
    }

    /**
     * 获得开始记录数
     * 
     * @return 开始记录数
     */
    public int getStart() {
        if (start != 0) {
            return start;
        } else {
            return (pageNumber - 1) * pageSize;
        }
    }

    /**
     * 获得开始记录数
     * 
     * @param start
     *            开始记录数
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * 获得结束记录数
     * 
     * @return 结束记录数
     */
    public int getEnd() {
        if (end != 0) {
            return end;
        } else {
            return pageNumber * pageSize;
        }
    }

    /**
     * 获得结束记录数
     * 
     * @param end
     *            结束记录数
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * 获得总页数
     * 
     * @return 总页数
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * 设置总页数
     * 
     * @param totalPages
     *            总页数
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 获得排列顺序
     * 
     * @return 排列顺序
     */
    public String getSortOrder() {
        return (sortOrder == null) ? "" : sortOrder;
    }

    /**
     * 设置排列顺序
     * 
     * @param sortOrder
     *            排列顺序
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获得排列字段
     * 
     * @return 排列字段
     */
    public String getSortColumn() {
        return (sortColumn == null) ? "" : sortColumn;
    }

    /**
     * 设置排列字段
     * 
     * @param sortColumn
     *            排列字段
     */
    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    /**
     * 获取排序css Class
     * 
     * @return sortClass
     */
    public String getSortClass() {
        return sortClass;
    }

    /**
     * 设置排序css Class
     * 
     * @param sortClass
     *            css Class
     */

    public void setSortClass(String sortClass) {
        this.sortClass = sortClass;
    }

    /**
     * 获得排序信息
     * 
     * @return 排序信息
     */
    public String getSortInfo() {
        String pageinfo = "";
        if (sortColumn != null && sortColumn.length() > 0) {
            pageinfo += " ORDER BY " + sortColumn + " " + sortOrder;
        }
        return pageinfo;
    }

    /**
     * 获得页号列表
     * 
     * @return 页号列表
     */
    public List<Integer> getPageNumberList() {

        if (totalPages <= 10) {
            // 10页之内
            for (int i = 1; i <= totalPages; i++) {
                pageNumberList.add(i);
            }
        } else {
            if (pageNumber < 6) {
                for (int i = 1; i <= 10; i++) {
                    pageNumberList.add(i);
                }
            } else if (totalPages - pageNumber > 5) {
                for (int i = pageNumber - 5; i < pageNumber + 5; i++) {
                    pageNumberList.add(i);
                }
            } else {
                for (int i = totalPages - 9; i <= totalPages; i++) {
                    pageNumberList.add(i);
                }
            }
        }
        return pageNumberList;
    }

    @Override
    public String toString() {
        return "Pagination [pageSize=" + pageSize + ", pageNumber=" + pageNumber + ", sortColumn=" + sortColumn
                + ", sortOrder=" + sortOrder + ", totalRecords=" + totalRecords + ", pageRecords=" + pageRecords
                + ", start=" + start + ", end=" + end + ", totalPages=" + totalPages + ", sortClass=" + sortClass + "]";
    }

}
