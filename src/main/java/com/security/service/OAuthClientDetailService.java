package com.security.service;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;

public interface OAuthClientDetailService extends ClientDetailsService, ClientRegistrationService {
    
    String NO_CLIENT_FOUND_WITH_ID = "No client found with id: ";
    String CLIENT_ALREADY_EXISTS = "Client already exists: ";
    String ACCESS_DENIED = "Access is denied";
    
}
