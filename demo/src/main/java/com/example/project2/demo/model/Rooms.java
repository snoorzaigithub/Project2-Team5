package com.example.project2.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Rooms {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private int numberOfSeats;

    //List of times that the room isnt taken

    @ElementCollection
    @CollectionTable(name = "rooms_not_reserved", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "reserved_at")
    private List<LocalDateTime> notreserved;

    // List of times the room is taken 

    @ElementCollection
    @CollectionTable(name = "rooms_reserved", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "reserved_at")
    private List<LocalDateTime> reserved;

    @OneToMany(mappedBy="room", cascade=CascadeType.ALL)
    private List<Features> features = new ArrayList<>();
}
