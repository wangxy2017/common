package com.wxy.common.tool;

import com.wxy.common.junit.AutoValues;
import com.wxy.common.response.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-6-30 上午9:45
 * @Description TODO
 **/
@Slf4j
public class ResponseUtilsTest {


    @Test
    public void TestSuccess() {
        log.info("response = {}", ResponseUtils.success());
    }

    @Test
    public void TestSuccess1() {
        log.info("response = {}", ResponseUtils.success(AutoValues.nextStr()));
    }

    @Test
    public void TestError() {
        log.info("response = {}", ResponseUtils.error());
    }

    @Test
    public void TestError1() {
        log.info("response = {}", ResponseUtils.error(500, "system error"));
    }
}
