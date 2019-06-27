package com.wxy.common.tool;

import com.wxy.common.entity.AutoValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-6-27 下午7:44
 * @Description AutoValues测试
 **/
@Slf4j
public class AutoValuesTest {
    /**
     * 整数
     */
    @Test
    public void TestNextInt() {
        log.debug("随机整数 = {}", AutoValues.nextInt());
    }

    /**
     * 随机小数
     */
    @Test
    public void TestNextDouble() {
        log.debug("随机小数 = {}", AutoValues.nextDouble());
    }

    /**
     * 随机字符串
     */
    @Test
    public void TestNextStr() {
        log.debug("随机字符串 = {}", AutoValues.nextStr());
    }
}
