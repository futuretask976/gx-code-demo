package com.gx.code.demo.design.scenario.mybatis.session;

import com.gx.code.demo.design.scenario.mybatis.config.Function;
import com.gx.code.demo.design.scenario.mybatis.config.MapperBean;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyConfiguration {
    public static ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    public Connection build(String resources) {
        try {
            InputStream stream = classLoader.getResourceAsStream(resources);
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            Element root = document.getRootElement();
            return evalDataSource(root);
        } catch (Exception e) {
            throw new RuntimeException("error occured while evaling xml " + resources);
        }
    }

    private Connection evalDataSource(Element root) throws ClassNotFoundException {
        if (!root.getName().equals("database")) {
            throw new RuntimeException("root should be <database>");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
        for (Object item : root.elements("property")) {
            Element i = (Element) item;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if (name == null || value == null) {
                throw new RuntimeException("[database]: <property> should contain name and value");
            }
            switch(name) {
                case "url" : url = value;break;
                case "username": username = value; break;
                case "password": password = value; break;
                case "driverClassName": driverClassName = value; break;
                default : throw new RuntimeException("[database]: <property> unknown name");
            }
        }
        Class.forName(driverClassName);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private String getValue(Element node) {
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }

    @SuppressWarnings("rawtypes")
    public MapperBean readMapper(String path) {
        MapperBean mapper = new MapperBean();
        try {
            InputStream stream = classLoader.getResourceAsStream(path);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(stream);
            Element root = document.getRootElement();
            mapper.setInterfaceName(root.attributeValue("nameSpace").trim());
            List<Function> list = new ArrayList<Function>();
            for (Iterator rootIter = root.elementIterator(); rootIter.hasNext();) {
                Function fun = new Function();
                Element e = (Element) rootIter.next();
                String sqltype = e.getName().trim();
                String funcName = e.attributeValue("id").trim();
                String sql = e.getText().trim();
                String resultType = e.attributeValue("resultType").trim();
                fun.setSqlType(sqltype);
                fun.setFuncName(funcName);
                Object newInstance = null;
                try {
                    newInstance = Class.forName(resultType).newInstance();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                fun.setResultType(newInstance);
                fun.setSql(sql);
                list.add(fun);
            }
            mapper.setList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapper;
    }
}
