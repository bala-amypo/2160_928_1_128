package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository repo;

    @Override
    public UserAccount register(UserAccount user) {
        return repo.save(user);
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return repo.findAll();
    }
}
