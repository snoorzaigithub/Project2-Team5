package com.example.project2.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project2.demo.model.User;
import com.example.project2.demo.data.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }
}
