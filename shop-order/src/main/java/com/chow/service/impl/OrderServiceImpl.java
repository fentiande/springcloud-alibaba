package com.chow.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.chow.config.CommonBlockhandler;
import com.chow.config.CommonFallback;
import com.chow.dao.OrderDao;
import com.chow.dao.TxLogDao;
import com.chow.domain.Order;
import com.chow.domain.TxLog;
import com.chow.service.OrderService;
import io.netty.channel.DefaultChannelId;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private TxLogDao txLogDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    int i =0;

    @SentinelResource(value = "message",
            blockHandlerClass = CommonBlockhandler.class, blockHandler = "blockHandler",
            fallbackClass = CommonFallback.class, fallback = "fallback")
    public String message(String name) {
        i++;
        if (i % 2 == 0) {
            throw new RuntimeException("报错了");
        }
        return "mesage";
    }

    public void createOrderBefore(Order order) {
        String txId = UUID.randomUUID().toString();

        DefaultChannelId.newInstance();
        rocketMQTemplate.sendMessageInTransaction("tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId", txId).build(), order);
    }

    public void createOrder(Order order, String txId) {

        orderDao.save(order);

        TxLog txLog = new TxLog();
        txLog.setTxId(txId);
        txLog.setDate(new Date());
        txLogDao.save(txLog);
    }


}
