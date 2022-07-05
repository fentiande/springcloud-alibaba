package com.chow.test;

import com.chow.OrderApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //同步消息
    @Test
    public void testSyncSend() {
        SendResult result = rocketMQTemplate.syncSend("myTopic:tag", "这是一条同步消息", 100000);
        System.out.println(result);
    }

    //同步消息
    @Test
    public void testAsyncSend() throws InterruptedException {
        rocketMQTemplate.asyncSend("myTopic:tag", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("开始回调1");
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("开始回调2");
                System.out.println(e);
            }
        });
        System.out.println("方法执行完毕，进程开始休眠");
        Thread.sleep(100000);
    }

    //同步消息
    @Test
    public void testOneWay() {
        // 无法设置超时时间，可能导致报错及无法使用
        rocketMQTemplate.sendOneWay("test-topic-1", "这是一条单向消息");
    }

    //同步消息
    @Test
    public void testSyncSendOrderly() {
        for (int i = 0; i < 10; i++) {
            SendResult result = rocketMQTemplate.syncSendOrderly("myTopic:tag", "这是一条同步消息", "xxx", 100000);
            System.out.println(result);
        }

    }
}
