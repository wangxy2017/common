package com.wxy.common;

import com.wxy.common.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
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
        String get = HttpUtils.get("http://192.168.1.2:9010/list", new HashMap<>(), null, true);
        log.info("响应结果：{}", get);
    }

    /**
     * https get请求
     */
    @Test
    public void TestGetHttps() {
    }
}
