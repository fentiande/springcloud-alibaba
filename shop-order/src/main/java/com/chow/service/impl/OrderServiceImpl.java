package com.chow.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.chow.config.CommonBlockhandler;
import com.chow.config.CommonFallback;
import com.chow.dao.OrderDao;
import com.chow.dao.TxLogDao;
import com.chow.domain.Order;
import com.chow.domain.Product;
import com.chow.domain.TxLog;
import com.chow.service.OrderService;
import com.chow.service.ProductService;
import io.netty.channel.DefaultChannelId;
import io.seata.spring.annotation.GlobalTransactional;
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

    @Autowired
    private ProductService productService;

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



    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Order createOrder(Integer pid) {
        Product product = productService.findById(pid);

        if (product.getPid() == -200) {
            Order order = new Order();
            order.setOid(-400L);
            order.setPname("下单失败");
            return order;
        }

        /*try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            log.error("休息中，报错了");
        }
        log.info("休息两秒");*/

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);

        // 减库存
        productService.reduceInventory(pid, order.getNumber());

        // 向mq投递消息
        DefaultChannelId.newInstance();
        rocketMQTemplate.convertAndSend("order-topic", order);

        return order;
    }
}
