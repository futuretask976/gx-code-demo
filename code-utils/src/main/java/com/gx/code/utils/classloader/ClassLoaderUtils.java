package com.gx.code.utils.classloader;

import org.apache.commons.lang3.StringUtils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class ClassLoaderUtils {
    public static boolean compileSrcFile(String srcFolderPath, String srcFileName) {
        if (StringUtils.isBlank(srcFolderPath) || StringUtils.isBlank(srcFileName)) {
            throw new IllegalArgumentException("input param is invalid: " + srcFolderPath + ", " + srcFileName);
        }
        File srcFile = new File(srcFolderPath + getPathSeperator() + srcFileName
                + JavaFileObject.Kind.SOURCE.extension);
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("input param is invalid: " + srcFolderPath + ", " + srcFileName);
        }

        // 获取 JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 获取 StandardJavaFileManager
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(
                null, null, null);

        // 编译 Java源文件
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(
                Arrays.asList(srcFile));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                null, null, null, compilationUnits);
        boolean success = task.call();

        return success;
    }

    public static Class loadClass(String classFolderPath, String className)
            throws MalformedURLException, ClassNotFoundException {
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(classFolderPath).toURL()});
        Class<?> clazz = classLoader.loadClass(className);
        return clazz;
    }

    public static Class loadClassByCustomizedClassLoader(String className,
            URLClassLoader customizedClassLoader) throws ClassNotFoundException {
        Class<?> clazz = customizedClassLoader.loadClass(className);
        return clazz;
    }

    public static Class loadClassByCustomizedClassLoader(String className,
            CustomizedClassLoader customizedClassLoader, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = customizedClassLoader.loadClass(className, resolve);
        return clazz;
    }

    public static String getPathSeperator() {
        return "\\";
    }
}
