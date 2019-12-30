package com.wxy.common.http;

/**
 * @Author wxy
 * @Date 19-8-12 上午10:36
 * @Description TODO Url处理工具
 **/
public class UrlUtils {

    public static String getHost(String url) {
        String[] split = split(url);
        return split[1] == null ? "" : split[1];
    }

    public static String getPort(String url) {
        String host = getHost(url);
        String port = "";
        if (host.indexOf(":") > 0) {
            port = host.substring(host.indexOf(":") + ":".length());
        }
        return port;
    }

    public static String getUri(String url) {
        String[] split = split(url);
        return split[2] == null ? "" : split[2];
    }

    public static String getQueryString(String url) {
        String[] split = split(url);
        return split[3] == null ? "" : split[3];
    }

    /**
     * <scheme>://<host>/<path>?<query>#<fragment>
     *
     * @param url
     * @return
     */
    private static String[] split(String url) {
        String[] arr = new String[5];
        if (url != null) {
            if (url.indexOf("://") > 0) {
                arr[0] = url.substring(0, url.indexOf("://"));
                String u = url.substring(url.indexOf("://") + "://".length());
                if (u.indexOf("/") > 0) {
                    arr[1] = u.substring(0, u.indexOf("/"));
                    String uu = u.substring(u.indexOf("/") + "/".length());
                    if (uu.indexOf("?") > 0) {
                        arr[2] = "/" + uu.substring(0, uu.indexOf("?"));
                        String uuu = uu.substring(uu.indexOf("?") + "?".length());
                        if (uuu.indexOf("#") > 0) {
                            arr[3] = uuu.substring(0, uuu.indexOf("#"));
                            arr[4] = uuu.substring(uuu.indexOf("#") + "#".length());
                        } else {
                            arr[3] = uuu;
                        }
                    } else {
                        arr[2] = "/" + uu;
                    }
                } else {
                    arr[1] = u;
                }
            }
        }
        return arr;
    }
}
