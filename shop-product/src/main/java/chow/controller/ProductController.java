package chow.controller;

import chow.service.ProductService;
import com.chow.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductController {

    @Autowired
    private ProductService productService;

    public Product product(@PathVariable("pid") Integer pid) {
        Product product = productService.findByPid(pid);
        return product;
    }
}
