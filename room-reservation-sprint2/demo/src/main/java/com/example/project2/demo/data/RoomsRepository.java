package com.example.project2.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.demo.model.Rooms;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
}
