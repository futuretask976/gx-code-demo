package com.gx.code.demo.concurrent.thread;

public class ThreadLocalDemo {
    public static void main(String args[]) {
        ThreadLocal l = new ThreadLocal();
        l.set("abc");

        ThreadLocal m = new ThreadLocal();
        m.set("def");

        System.out.println(l.get());
        System.out.println(m.get());
    }
}
