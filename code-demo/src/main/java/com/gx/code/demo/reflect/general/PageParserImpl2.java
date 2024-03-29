package com.gx.code.demo.reflect.general;

import java.util.List;

public class PageParserImpl2 extends PageParser2 {
    private List<String> test;

    private List test2;

    @Override
    public void parse(String arg1, String arg2, Integer arg3) {
        System.out.println("here is PageParserImpl#parse");
    }
}
