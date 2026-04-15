package com.example.project2.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Features {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private boolean whiteboard;
    
    private boolean blackboard;

    private boolean projector;

    private boolean computer;

    private boolean laboratory;

    @ManyToOne
    @JoinColumn(name="rooms_id")
    private Rooms room;

    @Transient
    public Long getRoomId() {
        return room != null ? room.getId() : null;
    }
}
