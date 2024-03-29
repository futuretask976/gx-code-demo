package com.gx.code.demo.design.scenario.mybatis.session;

import com.gx.code.demo.design.scenario.mybatis.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyExecutor implements Executor {
    // 这里的 MyConfigurtation 和 MySQLSession 中的重复了，可以用单例优化
    private MyConfiguration myConfiguration = new MyConfiguration();

    @Override
    public <T> T query(String sql, Object parameter) {
        Connection connection = getConnection();
        ResultSet set = null;
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, parameter.toString());
            set = pre.executeQuery();
            User u = new User();
            while(set.next()) {
                u.setId(set.getString(1));
                u.setUserName(set.getString(2));
                u.setPassword(set.getString(3));
            }
            return (T) u;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private Connection getConnection() {
        try {
            Connection connection = myConfiguration.build("config.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
