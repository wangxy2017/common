package com.wxy.common.tool;

/**
 * @Author wxy
 * @Date 19-8-13 下午4:28
 * @Description 计时器
 **/
public class TimeCounter {
    private static ThreadLocal<Long> start = new ThreadLocal<>();

    /**
     * 启动计时器
     */
    public static void start() {
        start.set(System.nanoTime());
    }

    /**
     * 计时
     *
     * @return
     */
    public static String counts() {
        if (start.get() == null) {
            throw new RuntimeException("请先启动计时方法:TimeCounter.start()");
        }
        return String.format("%f.ms", (System.nanoTime() - start.get()) / 1e6d);
    }

    /**
     * 清除计时
     */
    public static void clean() {
        start();
    }
}
