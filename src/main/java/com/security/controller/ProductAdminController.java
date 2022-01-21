package com.security.controller;

import com.security.components.IAuthenticationFacade;
import com.security.model.Product;
import com.security.model.Usuario;
import com.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/product")
public class ProductAdminController {
    
    private final ProductService productService;
    private final IAuthenticationFacade authenticationFacade;
    
    @RequestMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
    
    private UserDetails getLoggedUser() {
        return (UserDetails) authenticationFacade.getAuthentication().getPrincipal();
    }
    
}
