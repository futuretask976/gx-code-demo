package com.gx.code.demo.design.scenario.msg;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class ObserveQueueThread extends Thread {
    /**
     *
     */
    private String topic;

    /**
     *
     */
    private BlockingQueue<Message> queue;

    /**
     *
     */
    private Set<Consumer> consumerSet;

    public ObserveQueueThread(String topic, BlockingQueue<Message> queue, Set<Consumer> consumerSet) {
        if (topic == null || "".equals(topic.trim()) || queue == null || consumerSet == null) {
            throw new IllegalArgumentException("arguments is illegal");
        }

        this.topic = topic;
        this.queue = queue;
        this.consumerSet = consumerSet;
    }

    public void run() {
        try {
            Message message = queue.take();
            while(message != null) {
                for (Consumer consumer : consumerSet) {
                    consumer.push(message);
                }
                message = queue.take();
            }
        } catch (Exception e) {
            // do nothing here now
        }
    }
}
