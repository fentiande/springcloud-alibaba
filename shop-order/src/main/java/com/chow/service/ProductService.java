package com.chow.service;

import com.chow.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-product"
//        fallback = ProductServiceFallback.class,
//        fallbackFactory = ProductServiceFallbackFactory.class
)
public interface ProductService {

    @GetMapping("/product/{pid}")
    public Product findById(@PathVariable("pid") Integer pid);

    @GetMapping("/product/reduceInventory")
    void reduceInventory(@RequestParam("pid")Integer pid, @RequestParam("number")Integer number);
}
