package com.wxy.common.http;

import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
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
    private final static int READ_TIMEOUT = 100;
    private final static int CONNECT_TIMEOUT = 60;
    private final static int WRITE_TIMEOUT = 60;
    private static final String CHARSET = "UTF-8";// 字符集

    public static void http(String method, String url, Map<String, Object> params,
                            Map<String, String> headers, boolean isHttps) {
    }

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

}
