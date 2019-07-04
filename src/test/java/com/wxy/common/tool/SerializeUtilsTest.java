package com.wxy.common.tool;

import com.wxy.common.io.SerializeUtils;
import com.wxy.common.junit.AutoValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        byte[] bytes = SerializeUtils.serialize(Arrays.asList(AutoValues.nextInt(), AutoValues.nextInt(), AutoValues.nextInt()));
        log.debug("序列化结果 = {}", bytes);
    }

    /**
     * 反序列化
     */
    @Test
    public void TestUnserialize() {
        byte[] bytes = SerializeUtils.serialize(Arrays.asList(AutoValues.nextInt(), AutoValues.nextInt(), AutoValues.nextInt()));
        Object unserialize = SerializeUtils.unserialize(bytes);
        log.debug("反序列化结果 = {}", unserialize);
    }

    @Test
    public void TestUnserialize1(){
        List<Integer> integers = Arrays.asList(AutoValues.nextInt(), AutoValues.nextInt(), AutoValues.nextInt());
        byte[] bytes = SerializeUtils.serialize(integers);
        Object unserialize = SerializeUtils.unserialize(bytes,integers.getClass());
        log.debug("反序列化结果 = {}", unserialize);
    }
}
