package com.wxy.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 分页模型(用一句话描述该文件做什么)
 * @author wangxy
 * @date 2019/1/29 10:40
 * @version V1.0
 */
@Data
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
}
