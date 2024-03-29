package com.gx.code.demo.concurrent.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        /** 用于起跑的 latch **/
        final CountDownLatch cdOrder = new CountDownLatch(1);
        /** 用于计算成绩的 latch **/
        final CountDownLatch cdAnswer = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("选手" + Thread.currentThread().getName() + "正在等待裁判发布口令");

                        /** 准备一起起跑 **/
                        cdOrder.await();

                        System.out.println("选手" + Thread.currentThread().getName() + "已接受裁判口令");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("选手" + Thread.currentThread().getName() + "到达终点");

                        /** 表示当前选手到达了终点 **/
                        cdAnswer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("裁判"+Thread.currentThread().getName()+"即将发布口令");
            /** 命令起跑 **/
            cdOrder.countDown();

            System.out.println("裁判"+Thread.currentThread().getName()+"已发送口令，正在等待所有选手到达终点");

            /** 等待所有选手到达终点 **/
            cdAnswer.await();

            System.out.println("所有选手都到达终点");
            System.out.println("裁判"+Thread.currentThread().getName()+"汇总成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
