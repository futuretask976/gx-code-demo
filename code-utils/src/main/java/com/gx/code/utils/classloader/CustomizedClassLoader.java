package com.gx.code.utils.classloader;

import javax.tools.JavaFileObject;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomizedClassLoader extends URLClassLoader {
    private static final String PATH_SEPERATOR_4_WIN = "\\";

    private String classFolderPath;

    public CustomizedClassLoader(String classFolderPath) throws MalformedURLException {
        super(new URL[]{getURL(classFolderPath)});
        this.classFolderPath = classFolderPath;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String className)
            throws ClassNotFoundException {
        // System.out.println("CustomizedClassLoader#findClass entering");
        String classFilePath = classFolderPath + PATH_SEPERATOR_4_WIN + className
                + JavaFileObject.Kind.CLASS.extension;

        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(classFilePath);
            outputStream = new ByteArrayOutputStream();
            int temp = 0;
            while((temp = inputStream.read()) != -1){
                outputStream.write(temp);
            }
        } catch (Exception e) {
            throw new ClassNotFoundException();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                throw new IllegalStateException();
            }
        }
        byte[] bytes = outputStream.toByteArray();
        return defineClass(className, bytes,0,bytes.length);
    }

    private static URL getURL(String classFolderPath) throws MalformedURLException {
        File classFolder = new File(classFolderPath);
        if (!classFolder.exists()) {
            throw new IllegalArgumentException("classFolderPath not exist: " + classFolderPath);
        }
        return classFolder.toURL();
    }
}
