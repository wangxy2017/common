package com.wxy.common.http;

/**
 * @Author wxy
 * @Date 19-8-12 上午10:36
 * @Description TODO
 **/
public class UrlUtils {
    public static String getHost(String url) {
        return url.split("//")[1].split("/")[0];
    }

    public static String getUri(String url) {
        String u = url.split("//")[1];
        return u.indexOf("/") > 0 ? u.substring(u.indexOf("/"), u.indexOf("?") > 0 ? u.indexOf("?" + 1) : u.length()) : "/";
    }

    public static String getQueryStr(String url) {
        return url.indexOf("?") > 0 ? url.substring(url.indexOf("?") + 1) : null;
    }
}
