package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.RoleType;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
public AuthResponse login(@RequestParam String username) {
    User user = userRepository.findByUsername(username);

    // Example: generate JWT token here
    String jwtToken = jwtUtil.generateToken(user);

    return new AuthResponse(
            jwtToken,
            user.getId(),
            user.getEmail(),
            user.getRole().name()  // convert enum to String
    );
}

}
