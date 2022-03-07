package com.chow.service.fallback;

import com.chow.domain.Product;
import com.chow.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceFallback implements ProductService {

    @Override
    public Product findById(Integer pid) {
        Product product = new Product();
        product.setPid(-200);
        return product;
    }
}
