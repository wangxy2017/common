package com.wxy.common.entity;

import com.wxy.common.junit.AutoValues;
import com.wxy.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
        CommonResponse response = new CommonResponse(AutoValues.nextInt(), AutoValues.nextStr(), AutoValues.nextStr());
        log.debug("response = {}", response);
    }
}
