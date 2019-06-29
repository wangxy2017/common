package com.wxy.common.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @Author wxy
 * @Date 19-6-27 下午7:20
 * @Description AutoValues
 **/
@Slf4j
public class AutoValues {

    private static final Random r = new Random();

    private static final char[] SEQUENCES = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890".toCharArray();

    /**
     * 整型
     *
     * @return
     */
    public static int nextInt() {
        return r.nextInt();
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

    /**
     * long
     *
     * @return
     */
    public static long nextLong() {
        return r.nextLong();
    }

    /**
     * date
     *
     * @return
     */
    public static Date nextDate() {
        return new Date();
    }

    /**
     * char
     *
     * @return
     */
    public static char nextChar() {
        return SEQUENCES[r.nextInt(SEQUENCES.length)];
    }

    /**
     * bool
     *
     * @return
     */
    public static boolean nextBool() {
        return r.nextBoolean();
    }
}
