package com.example.demo.config;

import com.example.demo.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JwtFilterConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public JwtFilterConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return jwtAuthFilter;
    }

    public Class<? extends UsernamePasswordAuthenticationFilter> beforeFilter() {
        return UsernamePasswordAuthenticationFilter.class;
    }
}
