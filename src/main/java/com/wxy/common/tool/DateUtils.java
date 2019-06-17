package com.wxy.common.tool;

import java.time.LocalDateTime;

/**
 * @Author wangxy
 * @Date 2019/6/17 10:29
 * @Description 日期工具类
 **/
public class DateUtils {
    /**
     * 获取标准 UTC 时间
     * @return
     */
    public static String utcTime() {
        String utcTime = LocalDateTime.now().minusHours(8).toString();
        utcTime = utcTime.lastIndexOf(".") == -1 ? utcTime + "Z" : utcTime.substring(0, utcTime.lastIndexOf(".")) + "Z";
        return utcTime;
    }
}
