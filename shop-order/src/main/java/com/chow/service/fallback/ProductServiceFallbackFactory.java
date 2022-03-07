package com.chow.service.fallback;

import com.chow.domain.Product;
import com.chow.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product findById(Integer pid) {
                log.error("{}", throwable);
                Product product = new Product();
                product.setPid(-200);
                return product;
            }
        };
    }
}
