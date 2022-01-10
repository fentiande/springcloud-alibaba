package com.chow.service.impl;

import com.chow.dao.ProductDao;
import com.chow.service.ProductService;
import com.chow.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
