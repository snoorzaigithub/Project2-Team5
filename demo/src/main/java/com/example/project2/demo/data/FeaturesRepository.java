package com.example.project2.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project2.demo.model.Features;

public interface FeaturesRepository extends JpaRepository<Features, Long> {
    List<Features> findByRoomId(Long roomId);

    void deleteByRoomId(Long roomId);
}
