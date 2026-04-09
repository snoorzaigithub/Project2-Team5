package com.example.project2.demo;

import com.example.project2.demo.model.User;
import com.example.project2.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new User("admin", "admin123", "ADMIN"));
                userRepository.save(new User("customer", "cust123", "CUSTOMER"));
            }
        };
    }
}