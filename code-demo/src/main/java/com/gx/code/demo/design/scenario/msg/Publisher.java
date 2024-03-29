package com.gx.code.demo.design.scenario.msg;

import lombok.Data;

@Data
public class Publisher {
    /**
     *
     */
    private String name;

    /**
     *
     */
    private Server server;

    public Publisher(String name, Server server) {
        this.name = name;
        this.server = server;
    }


    public void sendMsg(Message message) {
        message.setPublishName(this.getName());
        this.server.sendMsg(message);
    }
}
