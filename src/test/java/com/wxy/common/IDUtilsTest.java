package com.wxy.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-6-27 下午4:50
 * @Description IDUtils测试
 **/
@Slf4j
public class IDUtilsTest {

    /**
     * 获取 UUID
     */
    @Test
    public void TestGetUUID() {
        log.debug("获取UUID = {}", IDUtils.getUUID());
    }

    /**
     * 获取图片名
     */
    @Test
    public void TestGenImageName() {
        log.debug("获取图片名 = {}", IDUtils.genImageName());
    }
}
