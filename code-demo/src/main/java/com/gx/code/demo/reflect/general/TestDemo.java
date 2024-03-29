package com.gx.code.demo.reflect.general;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestDemo {

    public static void main(String args[]) {
        PageParserImpl pageParserImpl = new PageParserImpl();
        Type genericSuperclass = pageParserImpl.getClass().getGenericSuperclass();
        System.out.printf("genericSuperclass=%s\n", genericSuperclass);
        System.out.printf("genericSuperclass instanceof=%s\n", genericSuperclass instanceof ParameterizedType);
        Type[] pageVoClassTypes = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        System.out.printf("pageVoClassTypes=%s\n", pageVoClassTypes[0]);


        PageParserImpl2 pageParserImpl2 = new PageParserImpl2();
        Type genericSuperclass2 = pageParserImpl2.getClass().getGenericSuperclass();
        System.out.printf("genericSuperclass2=%s\n", genericSuperclass2);
        System.out.printf("genericSuperclass2 instanceof=%s\n", genericSuperclass2 instanceof ParameterizedType);


        Field[] fields = pageParserImpl2.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.printf("field name=%s, instanceof=%s\n", field.getName(),
                    field.getGenericType() instanceof ParameterizedType);
        }
    }
}
