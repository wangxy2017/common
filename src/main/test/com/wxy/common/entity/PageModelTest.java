package com.wxy.common.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author wxy
 * @Date 19-6-27 下午5:15
 * @Description PageModel测试
 **/
@Slf4j
public class PageModelTest {

    /**
     * 实例化
     */
    @Test
    public void TestInstance() {
        PageModel<Object> page = new PageModel<>();
        page.setPageNum(AutoValues.nextInt());
        page.setPageSize(AutoValues.nextInt());
        page.setTotal(AutoValues.nextInt());
        page.setList(Arrays.asList(AutoValues.nextInt(), AutoValues.nextInt(), AutoValues.nextInt()));
        log.debug("page = {}", page);
    }
}
