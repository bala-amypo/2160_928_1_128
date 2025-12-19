package com.example.demo.security;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    private String secret = "secret"; // simple secret
    private long validityInMs = 3600000;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
