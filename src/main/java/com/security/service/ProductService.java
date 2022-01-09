package com.security.service;

import com.security.model.Product;
import com.security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
    
    public List<Product> findALL() {
        return productRepository.findAll();
    }
}
