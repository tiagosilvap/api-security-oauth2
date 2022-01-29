package com.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailService extends UserDetailsService {
    String INVALID_USER = "Invalid user";
}
