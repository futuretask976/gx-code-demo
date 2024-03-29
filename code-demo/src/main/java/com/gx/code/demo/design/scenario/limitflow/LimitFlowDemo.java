package com.gx.code.demo.design.scenario.limitflow;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * 几种不同的限流算法说明可以参考：
 *     https://www.rstk.cn/news/136292
 *     1.html?action=onClick
 *     https://www.jb51.net/article/279790.htm#_label0
 */
public class LimitFlowDemo {
    public static void main(String args[]) {
//        LimitFlowAlgorithm limitByFixedWindow = new LimitByFixedWindow(0);
//        List<Thread> testThreads4limitByFixedWindow = Lists.newArrayList();
//        for (int i = 0; i < 100; i++) {
//            Thread t = new LimitFlowTestThread(limitByFixedWindow, "algo1_" + i);
//            testThreads4limitByFixedWindow.add(t);
//        }
//
//        for (int i = 0; i < 100; i++) {
//            testThreads4limitByFixedWindow.get(i).start();
//        }

        TreeMap<Long, Long> treeMap = new TreeMap<>();
        for (long i = 0; i < 9999; i++) {
            treeMap.put(i, i + 1);
        }

        Long begin = System.currentTimeMillis();
        long sum = treeMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey() >= 0)
                .mapToLong(Map.Entry::getValue)
                .sum();
        Long end = System.currentTimeMillis();
        System.out.println("consume=" + (end - begin) + ", sum=" + sum);
    }
}

class LimitFlowTestThread extends Thread {
    private LimitFlowAlgorithm limitFlowAlgorithm = null;

    private String threadName = null;

    public LimitFlowTestThread(LimitFlowAlgorithm limitFlowAlgorithm, String threadName) {
        this.limitFlowAlgorithm = limitFlowAlgorithm;
        this.threadName = threadName;
    }

    public void run() {
        System.out.println("threadName=" + threadName + ", " + limitFlowAlgorithm.tryAcquire());
    }
}

interface LimitFlowAlgorithm {
    boolean tryAcquire();

    String getAlgorithmName();
}

/**
 * 每秒钟重置计数器为 0。
 *
 * 固定窗口最大的优点在于易于实现；并且内存占用小，我们只需要存储时间窗口中的计数即可；
 * 它能够确保处理更多的最新请求，不会因为旧请求的堆积导致新请求被饿死。
 * 当然也面临着临界问题，当两个窗口交界处，瞬时流量可能为 2n。
 */
class LimitByFixedWindow implements LimitFlowAlgorithm {
    /**
     * 每秒限制请求数
     */
    private final long permitsPerSecond;

    /**
     * 上一个窗口的开始时间
     */
    public long timestamp = System.currentTimeMillis();

    /**
     * 计数器
     */
    private int counter;

    public LimitByFixedWindow(long permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        // 窗口内请求数量小于阈值，更新计数放行，否则拒绝请求
        if (now - timestamp < 1000) {
            if (counter < permitsPerSecond) {
                counter++;
                return true;
            } else {
                return false;
            }
        }
        // 时间窗口过期，重置计数器和时间戳
        counter = 0;
        timestamp = now;
        return true;
    }

    @Override
    public String getAlgorithmName() {
        return "LimitByFixedWindow";
    }
}

/**
 * 为了防止瞬时流量，可以把固定窗口近一步划分成多个格子，
 * 每次向后移动一小格，而不是固定窗口大小，这就是滑动窗口（Sliding Window）。
 *
 * 滑动窗口解决了计数器中的瞬时流量高峰问题，其实计数器算法也是滑动窗口的一种，只不过窗口没有进行更细粒度单元的划分。
 * 对比计数器可见，当窗口划分的粒度越细，则流量控制更加精准和严格。
 * 不过当窗口中流量到达阈值时，流量会瞬间切断，
 * 在实际应用中我们要的限流效果往往不是把流量一下子掐断，而是让流量平滑地进入系统当中。
 */
class LimitBySlidingWindow implements LimitFlowAlgorithm {
    /**
     * 每分钟限制请求数
     */
    private long permitsPerMinute;

    /**
     * 计数器, k-为当前窗口的开始时间值秒，value为当前窗口的计数
     */
    private TreeMap<Long, Integer> counters;

    public LimitBySlidingWindow(long permitsPerMinute) {
        this.permitsPerMinute = permitsPerMinute;
        this.counters = new TreeMap<>();
    }

    @Override
    public synchronized boolean tryAcquire() {
        // 获取当前时间的所在的子窗口值； 10s一个窗口
        long currentWindowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / 10 * 10;
        // 获取当前窗口的请求总量
        int currentWindowCount = getCurrentWindowCount(currentWindowTime);
        if (currentWindowCount >= permitsPerMinute) {
            return false;
        }
        // 计数器 + 1
        counters.merge(currentWindowTime, 1, Integer::sum);
        return true;
    }

    /**
     * 获取当前窗口中的所有请求数（并删除所有无效的子窗口计数器）
     *
     * @param currentWindowTime 当前子窗口时间
     * @return 当前窗口中的计数
     */
    private int getCurrentWindowCount(long currentWindowTime) {
        // 计算出窗口的开始位置时间
        long startTime = currentWindowTime - 50;
        int result = 0;

        // 遍历当前存储的计数器，删除无效的子窗口计数器，并累加当前窗口中的所有计数器之和
        Iterator<Map.Entry<Long, Integer>> iterator = counters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> entry = iterator.next();
            if (entry.getKey() < startTime) {
                iterator.remove();
            } else {
                result += entry.getValue();
            }
        }
        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "LimitBySlidingWindow";
    }
}

/**
 * 这并不是一个完整的漏桶算法的实现，以上代码中只是对流量是否会被抛弃进行校验，
 * 即 tryAcquire 返回 true 表示漏桶未满，否则表示漏桶已满丢弃请求。
 *
 * 想要以恒定的速率漏出流量，通常还应配合一个FIFO队列来实现，当tryAcquire返回true时，将请求入队，
 * 然后再以固定频率从队列中取出请求进行处理。示例代码如下：https://www.jb51.net/article/279790.htm#_label0
 */
class LimitByLeakyBucket implements LimitFlowAlgorithm {
    // 桶的容量
    private final int capacity;

    // 漏出速率
    private final int permitsPerSecond;

    // 剩余水量
    private long leftWater;

    // 上次注入时间
    private long timeStamp = System.currentTimeMillis();

    public LimitByLeakyBucket(int permitsPerSecond, int capacity) {
        this.capacity = capacity;
        this.permitsPerSecond = permitsPerSecond;
    }

    @Override
    public synchronized boolean tryAcquire() {
        // 计算剩余水量
        long now = System.currentTimeMillis();
        long timeGap = (now - timeStamp) / 1000;
        leftWater = Math.max(0, leftWater - timeGap * permitsPerSecond);
        timeStamp = now;

        // 如果未满，则放行；否则限流
        if (leftWater < capacity) {
            leftWater += 1;
            return true;
        }
        return false;
    }

    @Override
    public String getAlgorithmName() {
        return "LimitByLeakyBucket";
    }
}

/**
 * 如何在够限制流量速率的前提下，又能够允许突发流量呢？令牌桶算法了解一下！
 * 令牌桶算法是以恒定速率向令牌桶发送令牌，请求到达时，尝试从令牌桶中拿令牌，只有拿到令牌才能够放行，否则将会被拒绝。
 *
 * 需要主意的是，非常容易被想到的实现是生产者消费者模式；用一个生产者线程定时向阻塞队列中添加令牌，
 * 而试图通过限流器的线程则作为消费者线程，只有从阻塞队列中获取到令牌，才允许通过限流器。
 * 由于线程调度的不确定性，在高并发场景时，定时器误差非常大，同时定时器本身会创建调度线程，也会对系统的性能产生影响。
 */
class LimitByTokenBucket implements LimitFlowAlgorithm {
    /**
     * 令牌桶的容量「限流器允许的最大突发流量」
     */
    private final long capacity;

    /**
     * 令牌发放速率
     */
    private final long generatedPerSeconds;

    /**
     * 最后一个令牌发放的时间
     */
    long lastTokenTime = System.currentTimeMillis();

    /**
     * 当前令牌数量
     */
    private long currentTokens;

    public LimitByTokenBucket(long generatedPerSeconds, int capacity) {
        this.generatedPerSeconds = generatedPerSeconds;
        this.capacity = capacity;
    }

    /**
     * 尝试获取令牌
     *
     * @return true表示获取到令牌，放行；否则为限流
     */
    @Override
    public synchronized boolean tryAcquire() {
        /**
         * 计算令牌当前数量
         * 请求时间在最后令牌是产生时间相差大于等于额1s（为啥时1s？因为生成令牌的最小时间单位时s），则
         * 1. 重新计算令牌桶中的令牌数
         * 2. 将最后一个令牌发放时间重置为当前时间
         */
        long now = System.currentTimeMillis();
        if (now - lastTokenTime >= 1000) {
            long newPermits = (now - lastTokenTime) / 1000 * generatedPerSeconds;
            currentTokens = Math.min(currentTokens + newPermits, capacity);
            lastTokenTime = now;
        }
        if (currentTokens > 0) {
            currentTokens--;
            return true;
        }
        return false;
    }

    @Override
    public String getAlgorithmName() {
        return "LimitByTokenBucket";
    }
}


/**
 * 滑动日志是一个比较“冷门”，但是确实好用的限流算法。滑动日志限速算法需要记录请求的时间戳，
 * 通常使用有序集合来存储，我们可以在单个有序集合中跟踪用户在一个时间段内所有的请求。
 * 假设我们要限制给定T时间内的请求不超过N，我们只需要存储最近T时间之内的请求日志，
 * 每当请求到来时判断最近T时间内的请求总数是否超过阈值。
 */
class LimitBySlidingLog implements LimitFlowAlgorithm {
    /**
     * 每分钟限制请求数
     */
    private static final long PERMITS_PER_MINUTE = 60;

    /**
     * 请求日志计数器, k-为请求的时间（秒），value当前时间的请求数量
     */
    private final TreeMap<Long, Integer> requestLogCountMap = new TreeMap<>();

    @Override
    public synchronized boolean tryAcquire() {
        // 最小时间粒度为s
        long currentTimestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        // 获取当前窗口的请求总数
        int currentWindowCount = getCurrentWindowCount(currentTimestamp);
        if (currentWindowCount >= PERMITS_PER_MINUTE) {
            return false;
        }
        // 请求成功，将当前请求日志加入到日志中
        requestLogCountMap.merge(currentTimestamp, 1, Integer::sum);
        return true;
    }

    /**
     * 统计当前时间窗口内的请求数
     *
     * @param currentTime 当前时间
     * @return -
     */
    private int getCurrentWindowCount(long currentTime) {
        // 计算出窗口的开始位置时间
        long startTime = currentTime - 59;
        // 遍历当前存储的计数器，删除无效的子窗口计数器，并累加当前窗口中的所有计数器之和
        return requestLogCountMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey() >= startTime)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    @Override
    public String getAlgorithmName() {
        return "LimitByTokenBucket";
    }
}


