package com.gx.code.demo.design.scenario.mybatis.session;

public interface Executor {
    public <T> T query(String statement, Object parameter);
}
