package com.gx.code.utils.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ExecutorServiceUtils {
    /**
     *
     * @return
     */
    public static ThreadPoolExecutor newDefaultThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                100,
                500,
                10000,
                TimeUnit.DAYS,
                new ArrayBlockingQueue<Runnable>(1000),
                new ThreadFactoryBuilder().setNameFormat("catapult_thread_%d").build(),
                (r, executor) -> System.out.println("ThreadExecutors rejected"));
        return threadPoolExecutor;
    }

    /**
     * 创建一个可缓存的线程池，核心线程数为0，当线程池中的线程数量超过了运行任务所需要的线程数，那么可以回收空闲的线程，默认每60s回收
     * 同时当任务增加的时候，线程池又可以创建新的线程来处理任务
     * @return
     */
    public static ExecutorService newCachedThreadPoolExecutor() {
        // ExecutorService threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    /**
     * 创建一个可缓存的线程池，核心线程数为0，当线程池中的线程数量超过了运行任务所需要的线程数，
     * 那么可以回收空闲的线程，默认每60s回收，同时当任务增加的时候，线程池又可以创建新的线程来处理任务
     * @param threadFactory
     * @return
     */
    public static ExecutorService newCachedThreadPoolExecutor(ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                threadFactory);
    }

    /**
     * 当向线程池提交任务时，如果线程池中有空闲的线程则会直接执行任务，如果没有空闲线程则会将任务放入阻塞队列中等待，
     * 有空闲线程的时候再去执行阻塞队列中的线程
     * 当线程池达到最大线程数时，线程数量会保持不变，一旦某个线程出现异常，线程池会补充一个线程。提交到线程池的任务过多可能会导致内存溢出
     * @param nThreads
     * @param threadFactory
     * @return
     */
    public static ExecutorService newFixedThreadPoolExecutor(int nThreads, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                threadFactory);
    }

    /**
     * 创建一个可以周期性执行任务的线程池，也能够定时执行任务
     * @param corePoolSize
     * @return
     */
    public static ScheduledExecutorService newScheduledThreadPoolExecutor(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    /**
     * 创建一个可以周期性执行任务的线程池，也能够定时执行任务
     * @param corePoolSize
     * @param threadFactory
     * @return
     */
    public static ScheduledExecutorService newScheduledThreadPoolExecutor(
            int corePoolSize, ThreadFactory threadFactory) {
        return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
    }

    /**
     * 创建只有一个工作线程的线程池，相当于只有一个线程在工作。能够保证任务按照先进先出的顺序，或者优先级顺序执行任务
     * 就像单线程在串行执行任务一样，但是也有些区别，如果这个唯一的线程出现了异常，线程池会创建一个新的线程来代替它
     * @param threadFactory
     * @return
     */
    public static ExecutorService newSingleThreadPoolExecutor(ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                threadFactory);
    }

    /**
     * 创建只有一个工作线程，并且支持定时，周期性执行任务的线程池
     * @param threadFactory
     * @return
     */
    public static ScheduledExecutorService newScheduledThreadPoolExecutor(ThreadFactory threadFactory) {
        return new ScheduledThreadPoolExecutor(1, threadFactory);
    }
}
