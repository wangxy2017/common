package com.wxy.common.tool;

/**
 * @Author wxy
 * @Date 19-8-13 下午4:28
 * @Description 计时器
 **/
public class TimeCounter {

    private static ThreadLocal<Long> timeLocal = new ThreadLocal<>();

    /**
     * 启动计时器
     */
    public static void start() {
        timeLocal.set(System.nanoTime());
    }

    /**
     * 计时
     *
     * @return
     */
    public static double counts() {
        if (timeLocal.get() == null) {
            throw new RuntimeException("请先启动计时方法:TimeCounter.start()");
        }
        return (System.nanoTime() - timeLocal.get()) / 1e6d;
    }

    /**
     * 停止
     */
    public static void stop() {
        timeLocal.remove();
    }

    /**
     * 清除计时
     */
    public static void clean() {
        timeLocal.set(System.nanoTime());
    }
}
