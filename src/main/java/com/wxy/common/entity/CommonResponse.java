package com.wxy.common.entity;

import lombok.Data;

/**
 * @Author wangxy
 * @Date 2019/6/17 11:16
 * @Description 响应实体类
 **/
@Data
public class CommonResponse {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    public CommonResponse() {
    }

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
