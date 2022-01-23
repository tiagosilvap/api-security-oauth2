package com.security.controller;

import com.security.components.IAuthenticationFacade;
import com.security.entity.Product;
import com.security.entity.enums.AttendancePermissions;
import com.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    
    private final ProductService productService;
    private final IAuthenticationFacade authenticationFacade;
    
    @RequestMapping("/{id}")
    @RolesAllowed(AttendancePermissions.VIEW_PRODUCT)
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        System.err.println(getLoggedUser());
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
    
    private UserDetails getLoggedUser() {
        return (UserDetails) authenticationFacade.getAuthentication().getPrincipal();
    }
}
