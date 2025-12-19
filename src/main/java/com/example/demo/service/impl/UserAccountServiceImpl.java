package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository repo;

    @Override
    public UserAccount findByEmail(String email) {
        // Using Optional to avoid compilation error
        Optional<UserAccount> optionalUser = Optional.ofNullable(repo.findByEmail(email));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return optionalUser.get();
    }

    @Override
    public UserAccount findByUsername(String username) {
        Optional<UserAccount> optionalUser = Optional.ofNullable(repo.findByUsername(username));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with username: " + username);
        }
        return optionalUser.get();
    }
}
