package com.wxy.common.entity;

import java.util.Random;
import java.util.UUID;

/**
 * @Author wxy
 * @Date 19-6-27 下午7:20
 * @Description TODO
 **/
public class AutoValues {

    private static final Random r = new Random();

    /**
     * 整型
     *
     * @return
     */
    public static int nextInt() {
        return r.nextInt(Integer.MAX_VALUE);
    }

    /**
     * 字符串
     *
     * @return
     */
    public static String nextStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 小数
     *
     * @return
     */
    public static double nextDouble() {
        return r.nextDouble();
    }

    /**
     * 浮点数
     *
     * @return
     */
    public static float nextFloat() {
        return r.nextFloat();
    }
}
