package com.security.repository;

import com.security.entity.OAuthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthClientRepository extends JpaRepository<OAuthClientDetails, String> {
    
    Optional<OAuthClientDetails> findByClientId(String clientId);

}
