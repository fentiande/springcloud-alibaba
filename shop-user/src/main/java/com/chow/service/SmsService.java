package com.chow.service;

import com.chow.domain.Order;
import io.netty.channel.DefaultChannelId;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "shop-user", topic = "order-topic")
@org.springframework.core.annotation.Order(value = 2000)
public class SmsService implements RocketMQListener<Order> {

    @Override
    public void onMessage(Order order) {
        DefaultChannelId.newInstance();
        log.info("用户收到了订单请求{}，即将发送短信", order);

    }
}
