package com.wxy.common.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.UUID;

/**
 * @Author wxy
 * @Date 19-6-27 下午5:05
 * @Description CommonResponse测试
 **/
@Slf4j
public class CommonResponseTest {

    /**
     * 实例化
     */
    @Test
    public void TestInstance() {
        CommonResponse response = new CommonResponse(0, "success", UUID.randomUUID().toString());
        log.debug("response = {}", response);
    }
}
