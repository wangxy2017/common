/**
 * @Description: 分页模型(用一句话描述该文件做什么)
 * @author wangxy
 * @date 2019/1/29 10:40
 * @version V1.0
 */
package com.wxy.common.entity;

import java.util.List;

public class PageModel<T> {

    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 页面尺寸
     */
    private int pageSize;
    /**
     * 总数据条数
     */
    private long total;
    /**
     * 数据列表
     */
    private List<T> list;


    public PageModel() {

    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
