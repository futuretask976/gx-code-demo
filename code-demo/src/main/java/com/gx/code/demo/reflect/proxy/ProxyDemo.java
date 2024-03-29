package com.gx.code.demo.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String args[]) throws InstantiationException, IllegalAccessException {
        ProxyDemo.class.newInstance().test();
    }

    public void test() {
        ExampleInterface proxy = (ExampleInterface) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[] { ExampleInterface.class },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // method param
                        String className = method.getDeclaringClass().getName();
                        String methodName = method.getName();
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        Object[] parameters = args;

                        // Object rtn = method.invoke(proxy, args);
                        // return rtn;
                        return "098";
                    }
                });
        String rtn = proxy.print("abc", 1, false);
        System.out.println("ProxyDemo#test rtn=" + rtn);
    }
}
