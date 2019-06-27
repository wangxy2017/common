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
        page.setPageNum(1);
        page.setPageSize(10);
        page.setTotal(100);
        page.setList(Arrays.asList("1", "2", "3"));
        log.debug("page = {}", page);
    }
}
