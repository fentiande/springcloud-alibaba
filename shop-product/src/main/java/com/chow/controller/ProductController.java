package com.chow.controller;

import com.chow.service.ProductService;
import com.chow.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/api1/demo1")
    public String demo1() {
        return "demo";
    }

    @GetMapping("/product/api1/demo2")
    public String demo2() {
        return "demo";
    }

    @GetMapping("/product/api2/demo1")
    public String demo3() {
        return "demo";
    }

    @GetMapping("/product/api2/demo2")
    public String demo4() {
        return "demo";
    }

    @GetMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        Product product = productService.findByPid(pid);
        log.info("product:" + pid);
        return product;
    }
}
