package com.gx.code.demo.design.scenario.msg;

public class MsgDemo {
    public static void main(String args[]) throws Exception {
        Server server = new Server();

        Publisher publisher = new Publisher("publisherDemo", server);
        Consumer consumer = new Consumer("consumerDemo");

        server.registerPublisher("topicDemo", publisher);
        server.registerConsumer("topicDemo", consumer);

        Message message = null;
        message = new Message("topicDemo", "bodyDemo");
        publisher.sendMsg(message);
        message = new Message("topicDemo", "bodyDemo2");
        publisher.sendMsg(message);

        Thread.sleep(1000 * 3);

        message = new Message("topicDemo", "bodyDemo3");
        publisher.sendMsg(message);
    }
}
