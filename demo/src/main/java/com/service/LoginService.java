package com.example.project2.demo.service;

import com.example.project2.demo.model.User;
import com.example.project2.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValidCustomer(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent() &&
                user.get().getPassword().equals(password) &&
                user.get().getRole().equals("CUSTOMER");
    }

    public boolean isValidAdmin(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent() &&
                user.get().getPassword().equals(password) &&
                user.get().getRole().equals("ADMIN");
    }
}