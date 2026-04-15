package com.example.project2.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project2.demo.model.Rooms;
import com.example.project2.demo.data.RoomsRepository;

@Service
public class RoomsService {
    private final RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public List<Rooms> getAllRooms() {
        return roomsRepository.findAll();
    }

    public Rooms getRoomById(Long id) {
        return roomsRepository.findById(id).orElse(null);
    }

    public Rooms createRoom(Rooms room) {
        if (room.getReserved() == null) {
            room.setReserved(new ArrayList<>());
        }
        if (room.getNotreserved() == null) {
            room.setNotreserved(new ArrayList<>());
        }
        return roomsRepository.save(room);
    }

    public Rooms updateRoom(Rooms updatedRoom) {
        return createRoom(updatedRoom);
    }

    public void deleteRoom(Long id) {
        roomsRepository.deleteById(id);
    }
}
