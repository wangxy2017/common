package com.wxy.common.http;

import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author wxy
 * @Date 19-9-18 下午1:50
 * @Description TODO
 **/
public class OkHttpUtils {
    private static final int READ_TIMEOUT = 100;
    private static final int CONNECT_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * 同步get请求
     *
     * @param url
     * @param headers
     * @param params
     * @param isHttps
     * @return
     */
    public static String doGetSync(String url, Map<String, String> headers, Map<String, Object> params, boolean isHttps) {
        try {
            OkHttpClient client = createOKHttpClient(isHttps);
            Request.Builder builder = new Request.Builder();
            if (params != null && !params.isEmpty()) {
                url += url.contains("?") ? "&" : "?";
                url += buildUrlParams(params);
            }
            builder.url(url);
            if (headers != null && !headers.isEmpty()) {
                headers.forEach(builder::addHeader);
            }
            Request request = builder.get().build();
            Response response = client.newCall(request).execute();
            return parseRes(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("发送请求失败");
        }
    }

    /**
     * 同步post请求
     *
     * @param url
     * @param headers
     * @param body
     * @param isHttps
     * @return
     */
    public static String doPostSync(String url, Map<String, String> headers, String body, boolean isHttps) {
        try {
            OkHttpClient client = createOKHttpClient(isHttps);
            Request.Builder builder = new Request.Builder();
            builder.url(url).post(RequestBody.create(MEDIA_TYPE_JSON, body));
            if (headers != null && !headers.isEmpty()) {
                headers.forEach(builder::addHeader);
            }
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            return parseRes(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("发送请求失败");
        }
    }

    /**
     * 创建 OkHttpClient
     *
     * @param isHttps
     * @return
     */
    private static OkHttpClient createOKHttpClient(boolean isHttps) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);//读取超时
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);//连接超时
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);//写入超时
        //支持HTTPS请求，跳过证书验证
        if (isHttps) {
            try {
                TrustManager[] trustAllCerts = buildTrustManagers();
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier((hostname, session) -> true);
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
        }
        return builder.build();
    }

    /**
     * 创建证书
     *
     * @return
     */
    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
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
            sb.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8.name()));
            sb.append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 解析响应结果
     *
     * @param response
     * @return
     * @throws IOException
     */
    private static String parseRes(Response response) throws IOException {
        String result = "";
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }
        if (response.body() != null) {
            result = response.body().string();
        }
        return result;
    }
}
