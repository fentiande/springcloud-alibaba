package com.chow.service.fallback;

/*@Slf4j
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
}*/
