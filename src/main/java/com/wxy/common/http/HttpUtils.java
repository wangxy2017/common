package com.wxy.common.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author wangxy
 * @Date 2019/6/17 17:20
 * @Description http请求工具类
 **/
@Slf4j
public class HttpUtils {

    // 默认配置
    private static RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
            .setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();

    public static String doGet(String url, Map<String, String> headers, boolean isHttps) {
        return http("get", url, null, headers, isHttps);
    }

    public static String doDelete(String url, Map<String, String> headers, boolean isHttps) {
        return http("delete", url, null, headers, isHttps);
    }

    public static String doPost(String url, String body, Map<String, String> headers, boolean isHttps) {
        return http("post", url, body, headers, isHttps);
    }

    private static String http(String method, String url, String body,
                               Map<String, String> headers, boolean isHttps) {
        long start = System.currentTimeMillis();
        try {
            log.info("请求入参：method = {},url = {},body = {},headers = {},isHttps = {}", method, url, body, headers, isHttps);
            HttpClient httpClient;
            if (isHttps) {
                httpClient = createSSLClientDefault();
            } else {
                httpClient = HttpClients.createDefault();
            }
            if ("post".equalsIgnoreCase(method)) {
                HttpPost post = new HttpPost(url);
                post.setConfig(requestConfig);
                if (!MapUtils.isEmpty(headers)) {
                    headers.forEach(post::setHeader);
                }
                post.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
                HttpResponse response = httpClient.execute(post);
                return parseRes(response);
            } else if ("get".equalsIgnoreCase(method)) {
                HttpGet get = new HttpGet(url);
                get.setConfig(requestConfig);
                if (!MapUtils.isEmpty(headers)) {
                    headers.forEach(get::setHeader);
                }
                HttpResponse response = httpClient.execute(get);
                return parseRes(response);
            } else if ("delete".equalsIgnoreCase(method)) {
                HttpDelete delete = new HttpDelete(url);
                delete.setConfig(requestConfig);
                if (!MapUtils.isEmpty(headers)) {
                    headers.forEach(delete::setHeader);
                }
                HttpResponse response = httpClient.execute(delete);
                return parseRes(response);
            } else {
                throw new RuntimeException("Unsupported request method");
            }
        } catch (IOException e) {
            throw new RuntimeException("请求失败：" + url);
        } finally {
            log.info("请求耗时：{}", System.currentTimeMillis() - start + " ms");
        }
    }

    /**
     * 解析响应结果
     *
     * @param response
     * @return
     * @throws IOException
     */
    private static String parseRes(HttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
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
}
