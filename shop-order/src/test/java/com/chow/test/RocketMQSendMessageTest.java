package com.chow.test;

import io.netty.channel.DefaultChannelId;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class RocketMQSendMessageTest {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        producer.setNamesrvAddr("172.16.2.120:9876");
        DefaultChannelId.newInstance();
        producer.start();
        Message message = new Message("myTopic", "myTag", ("Test RocketMQ Message").getBytes());
        SendResult result = producer.send(message, 100000);
        System.out.println(result);
        producer.shutdown();
    }
}
