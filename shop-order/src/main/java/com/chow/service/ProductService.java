package com.chow.service;

import com.chow.domain.Product;
import com.chow.service.fallback.ProductServiceFallback;
import com.chow.service.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product",
//        fallback = ProductServiceFallback.class,
        fallbackFactory = ProductServiceFallbackFactory.class
)
public interface ProductService {

    @GetMapping("/product/{pid}")
    public Product findById(@PathVariable("pid") Integer pid);
}
