package com.wxy.common.tool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author wxy
 * @Date 19-6-27 下午5:26
 * @Description SerializeUtils测试
 **/
@Slf4j
public class SerializeUtilsTest {
    /**
     * 序列化
     */
    @Test
    public void TestSerialize() {
        byte[] bytes = SerializeUtils.serialize(Arrays.asList("1", "2", "3"));
        log.debug("序列化结果 = {}", bytes);
    }

    /**
     * 反序列化
     */
    @Test
    public void TestUnserialize() {
        byte[] bytes = SerializeUtils.serialize(Arrays.asList("1", "2", "3"));
        Object unserialize = SerializeUtils.unserialize(bytes);
        log.debug("反序列化结果 = {}", unserialize);
    }

}
