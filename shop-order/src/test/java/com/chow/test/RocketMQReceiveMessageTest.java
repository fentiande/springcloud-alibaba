package com.chow.test;

import io.netty.channel.DefaultChannelId;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class RocketMQReceiveMessageTest {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myconsumer-group");
        consumer.setNamesrvAddr("172.16.2.120:9876");
        consumer.subscribe("myTopic", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("消息==》"+ msgs);
                msgs.forEach(msg -> {
                    System.out.println("消息体==》"+msg.getBody().toString());
                });

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        DefaultChannelId.newInstance();
        consumer.start();
        System.out.println("消费者启动成功");
    }
}
