package com.chow.service;

import com.chow.domain.Product;

public interface ProductServiceFale {

    public Product findByPid(Integer pid);

    void reduceInventory(Integer pid, Integer number);
}
