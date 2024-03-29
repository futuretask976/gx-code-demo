package com.gx.code.demo.reflect.proxy;

public class ExampleClass implements ExampleInterface {
    @Override
    public String print(String paramA, int paramB, boolean paramC) {
        String rtn = paramA + "->" + paramB + "->" + paramC;
        System.out.println(rtn);
        return rtn;
    }
}
