package com.gx.code.demo.concurrent.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CallThreadDemo {
    private static class MyRunable implements Runnable {
        int index = 0;
        int max = 4 * 2;
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        {
            for (int i = 0; i < 4; i++) {
                map.put(i, new ArrayList<Integer>());
            }
        }

        boolean isRunning = true;

        public synchronized void run() {
            while (true) {
                int name = Integer.valueOf(Thread.currentThread().getName());
                while (index % 4 != name) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (!isRunning) {
                        return;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                for (int i = 0, arrIndex = index; i < 4; i++) {
                    try {
                        if (arrIndex-- < 0) {
                            break;
                        }
                        List<Integer> list = map.get(i);
                        if (list.size() < max) {
                            list.add(name + 1);
                        }
                    } catch (Exception e) {
                    }
                }
                System.err.println("A:" + map.get(0));
                System.err.println("B:" + map.get(1));
                System.err.println("C:" + map.get(2));
                System.err.println("D:" + map.get(3));
                System.err.println("-----------------------");

                if (map.get(map.size() - 1).size() == max) {
                    isRunning = false;
                    this.notifyAll();
                    return;
                } else {
                    index++;
                }
                this.notifyAll();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runable = new MyRunable();
        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(runable);
            t.setName(String.valueOf(i));
            t.start();
        }
    }
}