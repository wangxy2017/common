package com.wxy.common.tool;

import java.util.Random;
import java.util.UUID;

/**
 * @Author wangxy
 * @Date 2019/6/17 15:35
 * @Description ID生成工具
 **/
public class IDUtils {
    /**
     * 获取 UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成图片名
     *
     * @return
     */
    public static String genImageName() {
        return System.currentTimeMillis() + String.format("%03d", new Random().nextInt(999));
    }
}
