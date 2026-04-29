package com.example.project2.demo.service;

import com.example.project2.demo.model.Rooms;
import com.example.project2.demo.data.RoomsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Rooms saveRoom(Rooms room) {
        return roomsRepository.save(room);
    }

    public Rooms createRoom(Rooms room) {
        return roomsRepository.save(room);
    }

    public Rooms updateRoom(Rooms room) {
        return roomsRepository.save(room);
    }

    public void deleteRoom(Long id) {
        roomsRepository.deleteById(id);
    }

    public void bookRoom(Long id) {
        Rooms room = roomsRepository.findById(id).orElseThrow();
        room.setBooked(true);
        roomsRepository.save(room);
    }
}