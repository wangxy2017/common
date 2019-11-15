package com.wxy.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author wangxy
 * @Date 2019/6/17 11:16
 * @Description 响应实体类
 **/
@Data
@AllArgsConstructor
public class ApiResponse {

    private Integer code;

    private String msg;

    private Object data;

    public static ApiResponse success() {
        return new ApiResponse(1, "success", null);
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(1, "success", data);
    }

    public static ApiResponse error() {
        return new ApiResponse(0, "error", null);
    }
}
