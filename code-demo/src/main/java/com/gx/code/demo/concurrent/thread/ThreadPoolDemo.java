package com.gx.code.demo.concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolDemo extends Thread {
    public static void main(String[] arg) throws InterruptedException {
        ExecutorService es1 = Executors.newFixedThreadPool(1);
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        testOrder(es1);
        TimeUnit.SECONDS.sleep(1);
        testOrder(es2);
    }

    private static void testOrder(ExecutorService es) throws InterruptedException{
        List<Integer> submit = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        IntStream.range(0,100).forEach((i) -> {
            submit.add(i);
            es.submit(() -> {
                result.add(i);
            });
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(submit);
        System.out.println(result);
    }
}