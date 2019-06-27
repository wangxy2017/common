package com.wxy.common.tool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-6-27 下午4:58
 * @Description CodeUtils测试
 **/
@Slf4j
public class CodeUtilsTest {

    /**
     * 获取随机字符串
     */
    @Test
    public void TestRandom() {
        log.debug("获取随机字符串：{}", CodeUtils.random(6));
    }
}
