package com.gx.code.demo.design.scenario.msg;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private Map<String, Queue<Message>> queueMap = new HashMap<>();

    private Map<String, Publisher> publisherMap = new HashMap<>();

    private Map<String, Set<Consumer>> consumerMap = new HashMap<>();

    private ExecutorService executorService4PushMsg = Executors.newCachedThreadPool();

    public void registerPublisher(String topic, Publisher publisher) {
        if (publisherMap.containsKey(topic)) {
            throw new IllegalStateException("topic has been registered");
        }
        publisherMap.put(topic, publisher);

        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        queueMap.put(topic, queue);

        Set<Consumer> consumerSet = new HashSet<>();
        consumerMap.put(topic, consumerSet);

        ObserveQueueThread observerMsgQueueThread = new ObserveQueueThread(
                topic, queue, consumerSet);
        executorService4PushMsg.submit(observerMsgQueueThread);
    }

    public void registerConsumer(String topic, Consumer consumer) {
        if (consumerMap.containsKey(topic)) {
            Set<Consumer> consumerList = consumerMap.get(topic);
            consumerList.add(consumer);
        } else {
            Set<Consumer> consumerList = new HashSet<>();
            consumerList.add(consumer);
            consumerMap.put(topic, consumerList);
        }
    }

    public void sendMsg(Message msg) {
        if (!publisherMap.containsKey(msg.getTopic())) {
            throw new IllegalArgumentException("topic has not been registered");
        }
        Publisher publisher = publisherMap.get(msg.getTopic());
        if (!publisher.getName().equals(msg.getPublishName())) {
            throw new IllegalArgumentException("topic is not from registered publisher");
        }

        Queue<Message> queue = queueMap.get(msg.getTopic());
        queue.add(msg);
    }
}
