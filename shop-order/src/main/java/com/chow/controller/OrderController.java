package com.chow.controller;

import com.chow.domain.Order;
import com.chow.domain.Product;
import com.chow.service.OrderService;
import com.chow.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        Product product = productService.findById(pid);

        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            log.error("休息中，报错了");
        }
        log.info("休息两秒");

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

//        orderService.save(order);

        return order;
    }

    @GetMapping("/order/message1")
    public String message1() {
        orderService.message();
        return "message1";
    }

    @GetMapping("/order/message2")
    public String message2() {
        orderService.message();
        return "message2";
    }
}
