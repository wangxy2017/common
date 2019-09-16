package com.wxy.common.response;

import lombok.Data;

/**
 * @Author wangxy
 * @Date 2019/6/17 11:16
 * @Description 响应实体类
 **/
@Data
public class ApiResponse {
    private Integer code;
    private String msg;
    private Object data;

    private ApiResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private static final Integer ERROR_CODE = -1;
    private static final String ERROR_MSG = "ERROR";
    private static final Integer SUCCESS_CODE = 1;
    private static final String SUCCESS_MSG = "SUCCESS";

    public static ApiResponse success() {
        return new ApiResponse(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static ApiResponse error() {
        return new ApiResponse(ERROR_CODE, ERROR_MSG, null);
    }

    public static ApiResponse error(Integer code, String msg) {
        return new ApiResponse(code, msg, null);
    }
}
