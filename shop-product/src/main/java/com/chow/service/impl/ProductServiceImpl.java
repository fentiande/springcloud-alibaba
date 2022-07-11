package com.chow.service.impl;

import com.chow.dao.ProductDao;
import com.chow.service.ProductService;
import com.chow.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    @Transactional
    public void reduceInventory(Integer pid, Integer number) {
        Product product = productDao.findById(pid).get();
        product.setStock(product.getStock() - number);

        // 模拟异常
        int i = 1 / 0;

        productDao.save(product);
    }
}
