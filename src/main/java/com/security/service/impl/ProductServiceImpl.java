package com.security.service.impl;

import com.security.entity.Product;
import com.security.repository.ProductRepository;
import com.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
    
    @Override
    public List<Product> findALL() {
        return productRepository.findAll();
    }
}
