package com.chow.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chow.config.CommonBlockhandler;
import com.chow.config.CommonFallback;
import com.chow.dao.OrderDao;
import com.chow.service.OrderService;
import com.chow.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

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




}
