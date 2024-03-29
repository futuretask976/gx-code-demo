package com.gx.code.demo.design.scenario.mybatis.config;

import lombok.Data;

import java.util.List;

@Data
public class MapperBean {
    private String interfaceName;
    private List<Function> list;
}
