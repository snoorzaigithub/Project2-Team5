package com.example.project2.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private user user;

    private int numberOfSeats;

    //List of times that the room isnt taken

    private List<LocalDateTime> notreserved;

    // List of times the room is taken 

    private List<LocalDateTime> reserved;

    @OneToMany(mappedBy="room", cascade=CascadeType.ALL)
    private List<Features> features = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
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
