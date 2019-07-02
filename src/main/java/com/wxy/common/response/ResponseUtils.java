package com.wxy.common.response;

import com.wxy.common.response.CommonResponse;

/**
 * @Author wxy
 * @Date 19-6-30 上午9:31
 * @Description ResponseUtils
 **/
public class ResponseUtils {

    public static final int SUCCESS_CODE = 1;

    public static final int ERROR_CODE = 0;

    public static CommonResponse success() {
        return new CommonResponse(SUCCESS_CODE, "success", null);
    }

    public static CommonResponse success(Object data) {
        return new CommonResponse(SUCCESS_CODE, "success", data);
    }

    public static CommonResponse error() {
        return new CommonResponse(ERROR_CODE, "error", null);
    }

    public static CommonResponse error(Integer code, String message) {
        return new CommonResponse(code, message, null);
    }

    public static CommonResponse error(String message) {
        return new CommonResponse(ERROR_CODE, message, null);
    }
}
