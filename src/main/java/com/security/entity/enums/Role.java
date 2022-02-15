package com.security.entity.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");
    
    private String authority;
    
    Role(String authority) {
        this.authority = authority;
    }
    
    public String getAuthority() {
        return authority;
    }
}
