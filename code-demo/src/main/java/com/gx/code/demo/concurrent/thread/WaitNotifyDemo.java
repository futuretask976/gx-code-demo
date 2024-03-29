package com.gx.code.demo.concurrent.thread;

public class WaitNotifyDemo {
    public static void main(String args[]) throws InterruptedException {
        LockObj lockObj = new LockObj("tester");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 entering");
                try {
                    synchronized (lockObj) {
                        lockObj.wait(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                synchronized (lockObj) {
                    lockObj.notify();
                }
                System.out.println("t2 exiting");
            }
        });
        t2.start();
    }
}

class LockObj {
    private String name;

    public LockObj(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
