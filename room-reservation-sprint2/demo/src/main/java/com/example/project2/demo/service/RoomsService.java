package com.example.project2.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project2.demo.model.Rooms;
import com.example.project2.demo.data.RoomsRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class RoomsService {

    private static final String ROOM_MANAGER_CB = "roomManager";

    private final RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @CircuitBreaker(name = ROOM_MANAGER_CB, fallbackMethod = "getAllRoomsFallback")
    public List<Rooms> getAllRooms() {
        return roomsRepository.findAll();
    }

    @CircuitBreaker(name = ROOM_MANAGER_CB, fallbackMethod = "getRoomByIdFallback")
    public Rooms getRoomById(Long id) {
        return roomsRepository.findById(id).orElse(null);
    }

    @CircuitBreaker(name = ROOM_MANAGER_CB, fallbackMethod = "createRoomFallback")
    public Rooms createRoom(Rooms room) {
        if (room.getReserved() == null) {
            room.setReserved(new ArrayList<>());
        }
        if (room.getNotreserved() == null) {
            room.setNotreserved(new ArrayList<>());
        }
        return roomsRepository.save(room);
    }

    @CircuitBreaker(name = ROOM_MANAGER_CB, fallbackMethod = "updateRoomFallback")
    public Rooms updateRoom(Rooms updatedRoom) {
        return createRoom(updatedRoom);
    }

    @CircuitBreaker(name = ROOM_MANAGER_CB, fallbackMethod = "deleteRoomFallback")
    public void deleteRoom(Long id) {
        roomsRepository.deleteById(id);
    }


    public List<Rooms> getAllRoomsFallback(Throwable t) {
        return new ArrayList<>();
    }

    public Rooms getRoomByIdFallback(Long id, Throwable t) {
        return null;
    }

    public Rooms createRoomFallback(Rooms room, Throwable t) {
        return null;
    }

    public Rooms updateRoomFallback(Rooms updatedRoom, Throwable t) {
        return null;
    }

    public void deleteRoomFallback(Long id, Throwable t) {
        // no-op: silently absorb the failure
    }
}

