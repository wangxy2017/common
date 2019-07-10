package com.wxy.common;

import com.wxy.common.tool.CaptchaUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

/**
 * @Author wxy
 * @Date 19-6-27 下午7:44
 * @Description CaptchaUtils测试
 **/
@Slf4j
public class CaptchaUtilsTest {

    @Test
    public void TestGenerate() {
        Map<String, String> data = CaptchaUtils.generate();
        log.info("data = {}", data);
    }

    @Test
    public void TestBatchGenerate(){
        for (int i = 0; i <10 ; i++) {
            Map<String, String> data = CaptchaUtils.generate();
            log.info("data = {}", data);
        }
    }
}
