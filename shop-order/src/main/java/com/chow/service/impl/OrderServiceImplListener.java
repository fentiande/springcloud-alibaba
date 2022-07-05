package com.chow.service.impl;


import com.chow.dao.TxLogDao;
import com.chow.domain.Order;
import com.chow.domain.TxLog;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RocketMQTransactionListener
public class OrderServiceImplListener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private TxLogDao txLogDao;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            String txId = message.getHeaders().get("txId").toString();
            Order order = (Order)o;
            orderService.createOrder(order, txId);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String txId = message.getHeaders().get("txId").toString();
        Optional<TxLog> byId = txLogDao.findById(txId);
        if (byId.get() == null) {
            return RocketMQLocalTransactionState.ROLLBACK;
        } else {
            return RocketMQLocalTransactionState.COMMIT;
        }
    }
}
