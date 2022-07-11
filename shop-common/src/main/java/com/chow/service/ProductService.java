package com.chow.service;

import com.chow.domain.Product;

public interface ProductService {
    Product findByPid(Integer pid);

    void reduceInventory(Integer pid, Integer number);
}
