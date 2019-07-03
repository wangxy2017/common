package com.wxy.common.tool;

import com.wxy.common.junit.AutoValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-6-27 下午7:44
 * @Description AutoValues测试
 **/
@Slf4j
public class AutoValuesTest {

    private static long count = 10000l;

    /**
     * 整数
     */
    @Test
    public void TestNextInt() {
        for (int i = 0; i <count ; i++) {
            log.debug("随机整数 = {}", AutoValues.nextInt());
        }
    }

    /**
     * 随机小数
     */
    @Test
    public void TestNextDouble() {
        for (int i = 0; i < count; i++) {
            log.debug("随机小数 = {}", AutoValues.nextDouble());
        }
    }

    /**
     * 随机字符串
     */
    @Test
    public void TestNextStr() {
        for (int i = 0; i <count ; i++) {
            log.debug("随机字符串 = {}", AutoValues.nextStr());
        }
    }

    @Test
    public void TestNextLong() {
        for (int i = 0; i < count; i++) {
            log.debug("random long = {}", AutoValues.nextLong());
        }
    }

    @Test
    public void TestNextChar() {
        for (int i = 0; i < count; i++) {
            log.debug("random char = {}", AutoValues.nextChar());
        }
    }
    @Test
    public void TestNextBool() {
        for (int i = 0; i < count; i++) {
            log.debug("random bool = {}", AutoValues.nextBool());
        }
    }
}
