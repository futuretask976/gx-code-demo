package com.gx.code.demo.design.scenario.msg;

import lombok.Data;

@Data
public class Consumer {
    private String name;

    public Consumer(String name) {
        this.name = name;
    }

    public void push(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("message cannot be null");
        }

        this.handleMsg(message);
    }

    private void handleMsg(Message msg) {
        System.out.println("received: " +msg.getBody());
    }
}
