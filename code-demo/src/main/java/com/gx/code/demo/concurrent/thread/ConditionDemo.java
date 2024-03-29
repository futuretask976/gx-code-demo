package com.gx.code.demo.concurrent.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    public static void main(String args[]) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition con = lock.newCondition();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 entering");
                lock.lock();
                try {
                    con.await(4000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println("t1 exiting");
            }
        });
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 entering");
                lock.lock();
                try {
                    con.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println("t2 exiting");
            }
        });
        t2.start();
    }
}

class LockObj4Condition {
    private String name;

    public LockObj4Condition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
