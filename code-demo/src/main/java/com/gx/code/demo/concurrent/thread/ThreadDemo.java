package com.gx.code.demo.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo extends Thread {
    public static void main(String[] arg){
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        String ooo = "ooo";

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("111");
                    synchronized (this) {
                        System.out.println("abc");
                        // this.join();
                        System.out.println("def");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
//                    System.out.println("ghi");
//                    this.join();
                    System.out.println("222");
                    synchronized (thread1) {
                        System.out.println("jkl");
                        thread1.join();
                        System.out.println("mno");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                try {
//                    System.out.println("ghi");
//                    this.join();
                    System.out.println("333");
                    synchronized (thread1) {
                        System.out.println("pqr");
                        thread1.join();
                        System.out.println("stu");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executorService.submit(thread2);
        // executorService.submit(thread1);
        thread3.start();
        thread1.start();
        // thread2.start();

    }
}