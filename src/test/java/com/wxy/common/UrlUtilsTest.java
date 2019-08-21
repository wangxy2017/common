package com.wxy.common;

import com.wxy.common.http.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-8-21 下午5:49
 * @Description TODO
 **/
@Slf4j
public class UrlUtilsTest {

    @Test
    public void TestGetHost() {
        String url = "https://www.baidu.com";
        log.info("host = {}", UrlUtils.getHost(url));
    }

    @Test
    public void TestGetUri() {
        String url = "https://www.baidu.com";
//        String url = "https://www.baidu.com/test";
//        String url = "https://www.baidu.com/test?name=aaa";
        log.info("uri = {}", UrlUtils.getUri(url));
    }

    @Test
    public void TestGetQueryStr() {
//        String url = "https://www.baidu.com";
//        String url = "https://www.baidu.com?";
//        String url = "https://www.baidu.com?name=aaa&age=11";
        String url = "https://www.baidu.com/test";
        log.info("queryStr = {}", UrlUtils.getQueryStr(url));
    }
}
