package com.wxy.common.tool;

/**
 * @Author wxy
 * @Date 19-8-13 下午4:28
 * @Description 计时器
 **/
public class TimeCounter {

    private Long time;// 开始时间

    private TimeCounter timeCounter;// 当前计时器实例

    public TimeCounter() {
        timeCounter = this;
    }

    /**
     * 启动计时器
     */
    public TimeCounter start() {
        time = System.nanoTime();
        return timeCounter;
    }

    /**
     * 计时
     *
     * @return
     */
    public double counts() {
        if (time == null) {
            throw new RuntimeException("请先启动计时方法:TimeCounter.start()");
        }
        return (System.nanoTime() - time) / 1e6d;
    }
}
