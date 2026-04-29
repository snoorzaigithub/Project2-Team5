package com.example.room_manager.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.room_manager.Client.RoomAccessClient;
import com.example.room_manager.Model.Room;

@Service
public class RoomManagerService {
    @Autowired
    private RoomAccessClient client;
    
    public List<Room> getAllRooms(){
        return client.getAll();
    }

    public Room createRoom(Room room){
        if(room.getCapacity() <= 0){
            throw new RuntimeException("Capacity cannot be less than zero");
        }

        room.setStatus("Available");

        return client.create(room);
    }

    public void deleteRoom(Long id){
        client.delete(id);
    }
}
