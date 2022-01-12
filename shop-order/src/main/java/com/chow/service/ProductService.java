package com.chow.service;

import com.chow.domain.Order;
import com.chow.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-product")
public interface ProductService {

    @GetMapping("/product/{pid}")
    public Product findById(@PathVariable("pid") Integer pid);
}
