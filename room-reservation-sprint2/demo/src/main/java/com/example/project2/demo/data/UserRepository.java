package com.example.project2.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
