package com.gx.code.demo.design.scenario.mybatis.session;

import java.lang.reflect.Proxy;

public class MySQLSession {
    private Executor executor = new MyExecutor();

    private MyConfiguration myConfiguration = new MyConfiguration();

    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    public <T> T getMapper(Class<T> claz) {
        // 动态代理
        return (T) Proxy.newProxyInstance(claz.getClassLoader(), new Class[]{claz},
                new MyMapperProxy(myConfiguration, this));
    }
}
