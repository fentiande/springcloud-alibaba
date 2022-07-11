package com.chow.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.chow.domain.Order;
import com.chow.domain.Product;
import com.chow.service.ProductService;
import com.chow.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @DubboReference
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        /*Product product = productService.findById(pid);

        if (product.getPid() == -200) {
            Order order = new Order();
            order.setOid(-400L);
            order.setPname("下单失败");
            return order;
        }

        *//*try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            log.error("休息中，报错了");
        }
        log.info("休息两秒");*//*

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

//        orderService.save(order);

        // 向mq投递消息
        DefaultChannelId.newInstance();
        rocketMQTemplate.convertAndSend("order-topic", order);

        return order;*/
        return orderService.createOrder(pid);
    }

    int i = 0;
    @GetMapping("/order/message1")
    public String message1() {
        i++;
        if (i % 3 == 0) {
//            throw new RuntimeException();
        }
        return "message1";
    }

    @GetMapping("/order/message2")
    public String message2() {
        return orderService.message("xx");
    }

    @GetMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name, Integer age) {
        return "message3"+name+age;
    }

    @GetMapping("/order1/prod/{pid}")
    public Order order1(@PathVariable("pid") Integer pid) {
        Product product = productService.findByPid(pid);

        if (product.getPid() == -200) {
            Order order = new Order();
            order.setOid(-400L);
            order.setPname("下单失败");
            return order;
        }

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        orderService.createOrderBefore(order);

        return order;
    }
}
