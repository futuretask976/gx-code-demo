package com.gx.code.demo.design.scenario.mybatis;

import com.gx.code.demo.design.scenario.mybatis.bean.User;
import com.gx.code.demo.design.scenario.mybatis.mapper.UserMapper;
import com.gx.code.demo.design.scenario.mybatis.session.MySQLSession;

public class MyBatisDemo {
    public static void main(String args[]) {
        MySQLSession sqlSession = new MySQLSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById("1");
        System.out.println(user);
    }
}
