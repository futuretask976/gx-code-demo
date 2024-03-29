package com.gx.code.demo.design.scenario.timewheel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 参考：https://juejin.cn/post/7083795682313633822
 */
@Data
@Slf4j
public class TimerTaskEntry implements Comparable<TimerTaskEntry> {

    volatile TimerTaskList timedTaskList;
    TimerTaskEntry next;
    TimerTaskEntry prev;
    private TimerTask timerTask;
    private long expireMs;

    public TimerTaskEntry(TimerTask timedTask, long expireMs) {
        this.timerTask = timedTask;
        this.expireMs = expireMs;
        this.next = null;
        this.prev = null;
    }

    void remove() {
        TimerTaskList currentList = timedTaskList;
        while (currentList != null) {
            currentList.remove(this);
            currentList = timedTaskList;
        }
    }

    @Override
    public int compareTo(TimerTaskEntry o) {
        return ((int) (this.expireMs - o.expireMs));
    }

}