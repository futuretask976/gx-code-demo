package com.gx.code.demo.design.pattern.singleton;

public enum SingletonByEnum {
    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }
}
