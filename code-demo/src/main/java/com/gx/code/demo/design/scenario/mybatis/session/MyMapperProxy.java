package com.gx.code.demo.design.scenario.mybatis.session;

import com.gx.code.demo.design.scenario.mybatis.config.Function;
import com.gx.code.demo.design.scenario.mybatis.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyMapperProxy implements InvocationHandler {
    private MySQLSession mySQLSession;
    private MyConfiguration myConfiguration;

    public MyMapperProxy(MyConfiguration myConfiguration, MySQLSession mySQLSession) {
        this.myConfiguration = myConfiguration;
        this.mySQLSession = mySQLSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean readMapper = myConfiguration.readMapper("UserMapper.xml");
        if (!method.getDeclaringClass().getName().equals(readMapper.getInterfaceName())) {
            return null;
        }
        List<Function> list = readMapper.getList();
        if (null != list || 0 != list.size()) {
            for (Function function : list) {
                if (method.getName().equals(function.getFuncName())) {
                    return mySQLSession.selectOne(function.getSql(), String.valueOf(args[0]));
                }
            }
        }
        return null;
    }
}
