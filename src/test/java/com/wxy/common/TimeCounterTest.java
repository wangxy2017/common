package com.wxy.common;

import com.wxy.common.tool.TimeCounter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author wxy
 * @Date 19-8-13 下午4:35
 * @Description TODO
 **/
@Slf4j
public class TimeCounterTest {

    @Test
    public void test() throws InterruptedException {
        TimeCounter.start();
        Thread.sleep(1000);
        log.info("计时：{}", TimeCounter.counts());
    }
}
