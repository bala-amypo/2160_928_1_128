package com.example.demo.security;

import org.springframework.security.core.userdetails.*;

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) {
        throw new UsernameNotFoundException("User not found");
    }
}
