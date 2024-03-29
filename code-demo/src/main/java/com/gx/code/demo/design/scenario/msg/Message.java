package com.gx.code.demo.design.scenario.msg;

import lombok.Data;

@Data
public class Message {
    /**
     *
     */
    private String publishName;

    /**
     *
     */
    private String topic;

    /**
     *
     */
    private String body;

    public Message(String topic, String body) {
        this.topic = topic;
        this.body = body;
    }
}
