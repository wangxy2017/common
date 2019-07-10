package com.wxy.common;

import com.wxy.common.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-6-27 下午4:55
 * @Description DateUtils测试
 **/
@Slf4j
public class DateUtilsTest {

    /**
     * 获取UTC时间
     */
    @Test
    public void TestUtcTime() {
        log.debug("获取UTC时间 = {}", DateUtils.utcTime());
    }
}
