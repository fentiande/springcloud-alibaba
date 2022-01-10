package com.chow.dao;

import com.chow.domain.Product;
import com.chow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
