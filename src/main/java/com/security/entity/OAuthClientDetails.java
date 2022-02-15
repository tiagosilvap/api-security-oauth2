package com.security.entity;

import com.security.converter.StringListConverter;
import com.security.converter.StringSetConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails implements ClientDetails {
    
    @Id
    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;
    
    @Column(name = "resource_ids")
    @Convert(converter = StringSetConverter.class)
    private Set<String> resourceIds = Collections.emptySet();
    
    @Column(name = "client_secret")
    private String clientSecret;
    
    @Column
    @Convert(converter = StringSetConverter.class)
    private Set<String> scope = Collections.emptySet();
    
    @Column(name = "authorized_grant_types")
    @Convert(converter = StringSetConverter.class)
    private Set<String> authorizedGrantTypes = Collections.emptySet();
    
    @Column(name = "web_server_redirect_uri")
    @Convert(converter = StringSetConverter.class)
    private Set<String> registeredRedirectUris;
    
    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> authorities = Collections.emptyList();
    
    @Column(name = "access_token_validity", nullable = false)
    private Integer accessTokenValiditySeconds;
    
    @Column(name = "refresh_token_validity", nullable = false)
    private Integer refreshTokenValiditySeconds;
    
    @Column(name = "autoapprove")
    @Convert(converter = StringSetConverter.class)
    private Set<String> autoApproveScopes;
    
    @Column(name = "additional_information")
    private String additionalInformation;
    
    @Override
    public List<GrantedAuthority> getAuthorities() {
        if(!CollectionUtils.isEmpty(authorities)) {
            return authorities.stream()
                    .map(authority -> {
                        Authority role = new Authority();
                        role.setName(authority);
                        return role;
                    })
                    .collect(Collectors.toList());
        }
        return null;
    }
    
    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }
    
    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }
    
    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUris;
    }
    
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new LinkedHashMap<>();
    }
    
    public void setRegisteredRedirectUri(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris == null ? null : new LinkedHashSet(registeredRedirectUris);
    }
    
    @Override
    public boolean isAutoApprove(final String scope) {
        if (isNull(autoApproveScopes)) {
            return false;
        }
        for (String auto : autoApproveScopes) {
            if (Boolean.TRUE.equals(auto) || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }
}
