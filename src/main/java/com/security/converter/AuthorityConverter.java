//package com.security.converter;
//
//import com.security.repository.AuthorityRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Configuration
//@RequiredArgsConstructor
//public class AuthorityConverter {
//
//    private final AuthorityRepository authorityRepository;
//
//    @Bean
//    public List<String> convertToString(List<GrantedAuthority> authorities) {
//        if(!CollectionUtils.isEmpty(authorities)) {
//            return authorities.stream()
//                    .map(authority -> authority.getAuthority())
//                    .collect(Collectors.toList());
//        }
//        return null;
//    }
//
//    @Bean
//    public List<GrantedAuthority> convertToGrantedAuthority(List<String> authorities) {
//        if(!CollectionUtils.isEmpty(authorities)) {
//            return authorities.stream()
//                    .map(authority ->
//                        authorityRepository.findByName(authority)
//                                .orElseThrow(() -> new IllegalArgumentException("Authority not found")))
//                    .collect(Collectors.toList());
//        }
//        return null;
//    }
//}
