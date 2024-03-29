package com.gx.code.demo.reflect.general;

public abstract class PageParser<T> {
    public void preParse(String arg1) {
        // TODO
    }

    public abstract void parse(String arg1, String arg2, T arg3);
}
