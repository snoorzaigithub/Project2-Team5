package com.example.project2.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.project2.demo.data.RoomsRepository;
import com.example.project2.demo.model.Rooms;
import com.example.project2.demo.service.RoomsService;

@ExtendWith(MockitoExtension.class)
public class RoomsServiceCircuitBreakerTest {

    @Mock
    private RoomsRepository roomsRepository;

    @InjectMocks
    private RoomsService roomsService;

    // --- getAllRooms ---

    @Test
    void whenRepositoryFails_getAllRooms_returnsFallbackEmptyList() {
        when(roomsRepository.findAll()).thenThrow(new RuntimeException("DB down"));

        List<Rooms> result = roomsService.getAllRooms();

        assertThat(result).isEmpty();
    }

    // --- getRoomById ---

    @Test
    void whenRepositoryFails_getRoomById_returnsFallbackNull() {
        when(roomsRepository.findById(1L)).thenThrow(new RuntimeException("DB down"));

        Rooms result = roomsService.getRoomById(1L);

        assertThat(result).isNull();
    }

    // --- createRoom ---

    @Test
    void whenRepositoryFails_createRoom_returnsFallbackNull() {
        Rooms room = new Rooms();
        when(roomsRepository.save(room)).thenThrow(new RuntimeException("DB down"));

        Rooms result = roomsService.createRoom(room);

        assertThat(result).isNull();
    }

    // --- updateRoom ---

    @Test
    void whenRepositoryFails_updateRoom_returnsFallbackNull() {
        Rooms room = new Rooms();
        when(roomsRepository.save(room)).thenThrow(new RuntimeException("DB down"));

        Rooms result = roomsService.updateRoom(room);

        assertThat(result).isNull();
    }

    // --- deleteRoom ---

    @Test
    void whenRepositoryFails_deleteRoom_doesNotThrow() {
        doThrow(new RuntimeException("DB down")).when(roomsRepository).deleteById(1L);

        // Should silently absorb the failure via fallback, not throw
        roomsService.deleteRoom(1L);
    }
}
