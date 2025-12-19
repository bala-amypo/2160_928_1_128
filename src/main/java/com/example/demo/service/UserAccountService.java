package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findByUsername(String username);
    List<UserAccount> getAllUsers();
}
