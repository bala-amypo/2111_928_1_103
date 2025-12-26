package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor Injection (IOC)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // --------------------------------------------------
    // REGISTER USER
    // --------------------------------------------------
    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Default role if not provided
        if (user.getRole() == null) {
            user.setRole("ANALYST");
        }

        return userRepository.save(user);
    }

    // --------------------------------------------------
    // FIND USER BY EMAIL
    // --------------------------------------------------
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
