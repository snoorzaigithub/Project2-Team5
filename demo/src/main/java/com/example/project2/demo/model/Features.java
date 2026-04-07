package com.example.project2.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Features {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private boolean whiteboard;
    
    private boolean blackboard;

    private boolean projector;

    private boolean computer;

    private boolean labortory;
}
