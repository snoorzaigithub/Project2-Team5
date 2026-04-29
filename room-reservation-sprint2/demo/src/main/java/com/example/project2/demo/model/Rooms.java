package com.example.project2.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int numberOfSeats;

    private boolean elevatorAccess;
    private boolean projector;
    private boolean whiteboard;
    private boolean computerAccess;
    private boolean largeTable;
    private boolean quietRoom;

    private boolean booked;
}