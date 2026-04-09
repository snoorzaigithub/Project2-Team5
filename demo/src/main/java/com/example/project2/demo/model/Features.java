package com.example.project2.demo.model;

import jakarta.persistence.*;

@Entity
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean whiteboard;
    private boolean blackboard;
    private boolean projector;
    private boolean computer;
    private boolean labortory;

    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private Rooms room;

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public boolean isLabortory() {
        return labortory;
    }

    public void setLabortory(boolean labortory) {
        this.labortory = labortory;
    }

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public boolean isBlackboard() {
        return blackboard;
    }

    public void setBlackboard(boolean blackboard) {
        this.blackboard = blackboard;
    }

    public boolean isWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(boolean whiteboard) {
        this.whiteboard = whiteboard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}