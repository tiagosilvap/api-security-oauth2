package com.security.service;

import com.security.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product findById(Long id);
    List<Product> findALL();
}
