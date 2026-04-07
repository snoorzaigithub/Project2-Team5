package com.example.project2.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class user {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    
    private String username;
    
    private String password;

    //Simple check to see if someone is an admin or not
    private boolean admin;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Rooms> reservations = new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Rooms> getReservations() {
        return reservations;
    }

    public void setReservations(List<Rooms> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
