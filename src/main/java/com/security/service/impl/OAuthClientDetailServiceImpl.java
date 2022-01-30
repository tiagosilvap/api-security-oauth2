package com.security.service.impl;

import com.security.entity.OAuthClientDetails;
import com.security.repository.OAuthClientRepository;
import com.security.service.OAuthClientDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OAuthClientDetailServiceImpl implements OAuthClientDetailService {
    
    private final OAuthClientRepository repository;
    
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return repository.findByClientId(clientId)
                .orElseThrow(() -> new NoSuchClientException(NO_CLIENT_FOUND_WITH_ID + clientId));
    }
    
    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        List<String> authorities = clientDetails.getAuthorities().stream()
                    .map(authority -> authority.getAuthority())
                    .collect(Collectors.toList());
        
        OAuthClientDetails oAuthClientDetails = OAuthClientDetails.builder()
                .clientId(clientDetails.getClientId())
                .clientSecret(clientDetails.getClientSecret())
                .scope(clientDetails.getScope())
                .authorizedGrantTypes(clientDetails.getAuthorizedGrantTypes())
                .authorities(authorities)
                .accessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds())
                .additionalInformation("Show")
                .registeredRedirectUris(clientDetails.getRegisteredRedirectUri())
                .build();
    
        try {
            repository.save(oAuthClientDetails);
        } catch (DuplicateKeyException e) {
            throw new ClientAlreadyExistsException(CLIENT_ALREADY_EXISTS + clientDetails.getClientId(), e);
        }
    }
    
    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        repository.save((OAuthClientDetails) clientDetails);
    }
    
    @Override
    public void updateClientSecret(String clientId, String clientSecret) throws NoSuchClientException {
        
        OAuthClientDetails oAuthClientDetails = repository.findByClientId(clientId)
                .orElseThrow(() -> new NoSuchClientException(NO_CLIENT_FOUND_WITH_ID + clientId));
        
        oAuthClientDetails.setClientSecret(clientSecret);
        repository.save(oAuthClientDetails);
    }
    
    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        OAuthClientDetails oAuthClientDetails = repository.findByClientId(clientId)
                .orElseThrow(() -> new NoSuchClientException(NO_CLIENT_FOUND_WITH_ID + clientId));
        repository.delete(oAuthClientDetails);
    }
    
    @Override
    public List<ClientDetails> listClientDetails() {
        final List<OAuthClientDetails> clientDetails = repository.findAll();
        return new ArrayList(clientDetails);
    }
}
