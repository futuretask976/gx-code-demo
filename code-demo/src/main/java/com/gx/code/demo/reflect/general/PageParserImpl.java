package com.gx.code.demo.reflect.general;

public class PageParserImpl extends PageParser<Long> {
    @Override
    public void parse(String arg1, String arg2, Long arg3) {
        System.out.println("here is PageParserImpl#parse");
    }
}
