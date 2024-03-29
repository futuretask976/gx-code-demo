package com.gx.code.utils.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

public class ClassLoaderDemo {
    public static void main(String args[]) throws Exception {
        testLoadClass();
    }

    public static void showClassLoader() throws Exception {
        System.out.println(ClassLoaderDemo.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());

        String srcFolderPath = ".\\gx-internal\\src\\main\\resources\\classloader";
        URLClassLoader customizedClassLoader = new CustomizedClassLoader(srcFolderPath);
        System.out.println(customizedClassLoader);
        System.out.println(customizedClassLoader.getParent());
    }

    public static void testLoadClass() throws Exception {
        String srcFileName = "DynamicClass";
        String srcFolderPathVer1 = ".\\gx-internal\\src\\main\\resources\\classloader\\ver1";
        ClassLoaderUtils.compileSrcFile(srcFolderPathVer1, srcFileName);

        // CustomizedClassLoader customizedClassLoader = new CustomizedClassLoader(srcFolderPathVer1);
        Class clazz0 = ClassLoaderUtils.loadClassByCustomizedClassLoader(srcFileName,
                new CustomizedClassLoader(srcFolderPathVer1));
        Class clazz1 = ClassLoaderUtils.loadClassByCustomizedClassLoader(srcFileName,
                new CustomizedClassLoader(srcFolderPathVer1), true);

        Field field0 = clazz0.getField("str");
        Field field1 = clazz1.getField("str");
        System.out.println(">>>step 0 -> field1.get(clazz1): " + field0.get(clazz1));
        System.out.println(">>>step 0 -> field0.get(clazz0): " + field1.get(clazz0));

        Object instance = clazz1.getDeclaredConstructor().newInstance();
        Method method = clazz1.getDeclaredMethod("printMessage");
        // method.invoke(instance);

        System.out.println(">>>step 1 -> field1.get(clazz1): " + field0.get(clazz1));
        System.out.println(">>>step 1 -> field0.get(clazz0): " + field1.get(clazz0));

        // 重新来一次
//        String srcFolderPathVer2 = ".\\gx-internal\\src\\main\\resources\\classloader\\ver2";
//        ClassLoaderHelper.compileSrcFile(srcFolderPathVer2, srcFileName);
//
//        URLClassLoader customizedClassLoader2 = new CustomizedClassLoader(srcFolderPathVer2);
//        Class clazz2 = ClassLoaderHelper.loadClassByCustomizedClassLoader(
//                srcFolderPathVer2, srcFileName, customizedClassLoader2);
//
//        Object instance2 = clazz2.getDeclaredConstructor().newInstance();
//        Method method2 = clazz2.getDeclaredMethod("printMessage");
//        method2.invoke(instance2);
    }
}
