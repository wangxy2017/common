package com.wxy.common.tool;

/**
 * @Author wangxy
 * @Date 2019/6/17 11:55
 * @Description TODO
 **/
public class CodeUtils {
    /**
     * 生成指定长度随机字符串
     *
     * @param length
     * @return
     */
    public static String random(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }
}
