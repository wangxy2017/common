package com.wxy.common;

import com.wxy.common.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @Author wxy
 * @Date 19-6-27 下午7:05
 * @Description HttpUtils测试
 **/
@Slf4j
public class HttpUtilsTest {

    /**
     * get请求
     */
    @Test
    public void TestGet() {
        String url = "http://www.baidu.com";
        Map<String, Object> params = null;
        Map<String, String> headers = null;
        String respone = null;
        try {
            respone = HttpUtils.get(url, params, headers, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("请求结果 = {}", respone);
    }

    /**
     * https get请求
     */
    @Test
    public void TestGetHttps() {
        String url = "https://www.baidu.com";
        Map<String, Object> params = null;
        Map<String, String> headers = null;
        String respone = null;
        try {
            respone = HttpUtils.get(url, params, headers, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("请求结果 = {}", respone);
    }
}
