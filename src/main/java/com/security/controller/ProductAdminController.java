package com.security.controller;

import com.security.components.AuthenticationFacade;
import com.security.entity.Product;
import com.security.entity.Usuario;
import com.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class ProductAdminController {
    
    private final ProductService productService;
    private final AuthenticationFacade authenticationFacade;
    
    @RequestMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        System.out.println(getLoggedUser());
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
    
    private Usuario getLoggedUser() {
        return (Usuario) authenticationFacade.getAuthentication().getPrincipal();
    }
    
}
