package com.example.project2.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int numberOfSeats;

    @ElementCollection
    private List<LocalDateTime> notreserved = new ArrayList<>();

    @ElementCollection
    private List<LocalDateTime> reserved = new ArrayList<>();

    @OneToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    private List<Features> features = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<LocalDateTime> getReserved() {
        return reserved;
    }

    public void setReserved(List<LocalDateTime> reserved) {
        this.reserved = reserved;
    }

    public List<LocalDateTime> getNotreserved() {
        return notreserved;
    }

    public void setNotreserved(List<LocalDateTime> notreserved) {
        this.notreserved = notreserved;
    }

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }
}