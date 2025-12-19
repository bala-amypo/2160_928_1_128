package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.UserAccount;

public interface UserAccountService {
    UserAccount register(UserAccount user);
    Optional<UserAccount> findByEmail(String email);
    List<UserAccount> getAllUsers();
}
