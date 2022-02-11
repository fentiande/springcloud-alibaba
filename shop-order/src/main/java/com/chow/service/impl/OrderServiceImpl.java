package com.chow.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
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

    @SentinelResource("message")
    public void message() {
        System.out.println("mesage");
    }
}
