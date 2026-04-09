package com.example.project2.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    
    private String username;
    
    private String password;

    //Simple check to see if someone is an admin or not
    private boolean admin;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Rooms> reservations = new ArrayList<>();
}
