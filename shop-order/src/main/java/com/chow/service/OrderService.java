package com.chow.service;

import com.chow.domain.Order;

public interface OrderService {

    public void save(Order order);

    public String message(String name);

    public Order createOrder(Integer pid);
}
