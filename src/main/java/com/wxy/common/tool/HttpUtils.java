package com.wxy.common.tool;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author wangxy
 * @Date 2019/6/17 17:20
 * @Description http请求工具类
 **/
public class HttpUtils {
    public static String get(String url, Map<String, Object> params, Map<String, String> headers, boolean isHttps) throws IOException {
        return http("GET", url, params, null, headers, isHttps);
    }

    public static String post(String url, Map<String, Object> params, Map<String, String> headers, boolean isHttps) throws IOException {
        return http("POST", url, params, null, headers, isHttps);
    }

    public static String http(String method, String url, Map<String, Object> params, String paramsStr,
                              Map<String, String> headers, boolean isHttps) throws IOException {
        HttpClient httpClient;
        if (isHttps) {
            httpClient = createSSLClientDefault();
        } else {
            httpClient = HttpClients.createDefault();
        }
        if ("post".equalsIgnoreCase(method)) {
            HttpPost post = new HttpPost(url);
            headers.forEach(post::setHeader);
            if (params != null) {
                post.setEntity(new StringEntity(buildUrlParams(params)));
            } else if (paramsStr != null) {
                post.setEntity(new StringEntity(paramsStr, "utf-8"));
            }
            return parseRes(httpClient.execute(post), "uft-8");
        } else {
            if (params != null) {
                if (!url.contains("?")) {
                    url += "?";
                }
                url += buildUrlParams(params);
            } else if (paramsStr != null) {
                if (!url.contains("?")) {
                    url += "?";
                }
                url += paramsStr;
            }
            HttpGet get = new HttpGet(url);
            if (headers != null) {
                headers.forEach(get::setHeader);
            }
            return parseRes(httpClient.execute(get), "uft-8");
        }
    }

    /**
     * 解析响应结果
     *
     * @param response
     * @param charSet
     * @return
     * @throws IOException
     */
    private static String parseRes(HttpResponse response, String charSet) throws IOException {
        if (response != null) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, charSet);
            }
        }
        return null;
    }

    /**
     * 创建https
     *
     * @return
     */
    private static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, (x509Certificates, s) -> true).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * map to urlEncode 参数
     *
     * @param params
     * @return
     * @throws IOException
     */
    private static String buildUrlParams(Map<String, Object> params) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(URLEncoder.encode(entry.getKey(), "utf-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue().toString(), "utf-8"));
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
