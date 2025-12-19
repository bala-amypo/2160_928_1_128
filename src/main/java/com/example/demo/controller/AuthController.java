package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAccountRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestParam String username, @RequestParam String password) {
        // Find the user by username
        UserAccount user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Optional: check password
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user);

        // Return AuthResponse
        return new AuthResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole().name() // convert enum to String
        );
    }
}
