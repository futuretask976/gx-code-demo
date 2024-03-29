package com.gx.code.utils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.codec.binary.Base64;

public class GeneralUtils {
    public static void printProperty(Object o, String methodNameStart) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class claz = o.getClass();
        Method[] methods = claz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().startsWith(methodNameStart)) {
                Object object = methods[i].invoke(o, null);
                System.out.println(methods[i].getName() + ": " + object);
            }
        }

        while (claz.getGenericSuperclass() != null) {
            claz = claz.getSuperclass();
            if (claz.getName().equals(Object.class.getName())) {
                continue;
            }
            methods = claz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().startsWith(methodNameStart)) {
                    Object object = methods[i].invoke(o, null);
                    System.out.println(methods[i].getName() + ": " + object);
                }
            }
        }
    }

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);

        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeBase64String(buffer);

    }
}
