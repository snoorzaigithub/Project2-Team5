package com.example.overlap_engine.Service;

import com.example.overlap_engine.model.OverlapCheckRequest;
import com.example.overlap_engine.model.OverlapCheckResponse;
import com.example.overlap_engine.model.ReservationWindow;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrictOverlapStrategyTest {
    private final OverlapEngine overlapEngine = new OverlapEngine(new StrictOverlapStrategy());

    @Test
    void returnsConflictForOverlappingReservationInSameRoom() {
        OverlapCheckRequest request = new OverlapCheckRequest(
                10L,
                LocalDateTime.of(2026, 4, 23, 10, 0),
                LocalDateTime.of(2026, 4, 23, 11, 0),
                List.of(
                        new ReservationWindow(1L, 10L,
                                LocalDateTime.of(2026, 4, 23, 10, 30),
                                LocalDateTime.of(2026, 4, 23, 11, 30),
                                "CONFIRMED")
                )
        );

        OverlapCheckResponse response = overlapEngine.checkOverlap(request);

        assertEquals("CONFLICT", response.result());
        assertEquals(List.of(1L), response.conflictingReservationIds());
    }

    @Test
    void ignoresDifferentRoomsAndCancelledReservations() {
        OverlapCheckRequest request = new OverlapCheckRequest(
                10L,
                LocalDateTime.of(2026, 4, 23, 10, 0),
                LocalDateTime.of(2026, 4, 23, 11, 0),
                List.of(
                        new ReservationWindow(1L, 11L,
                                LocalDateTime.of(2026, 4, 23, 10, 15),
                                LocalDateTime.of(2026, 4, 23, 10, 45),
                                "CONFIRMED"),
                        new ReservationWindow(2L, 10L,
                                LocalDateTime.of(2026, 4, 23, 10, 15),
                                LocalDateTime.of(2026, 4, 23, 10, 45),
                                "CANCELLED")
                )
        );

        OverlapCheckResponse response = overlapEngine.checkOverlap(request);

        assertEquals("CLEAR", response.result());
        assertEquals(List.of(), response.conflictingReservationIds());
    }

    @Test
    void touchingWindowsAreNotAnOverlap() {
        OverlapCheckRequest request = new OverlapCheckRequest(
                10L,
                LocalDateTime.of(2026, 4, 23, 11, 0),
                LocalDateTime.of(2026, 4, 23, 12, 0),
                List.of(
                        new ReservationWindow(1L, 10L,
                                LocalDateTime.of(2026, 4, 23, 10, 0),
                                LocalDateTime.of(2026, 4, 23, 11, 0),
                                "CONFIRMED")
                )
        );

        OverlapCheckResponse response = overlapEngine.checkOverlap(request);

        assertEquals("CLEAR", response.result());
        assertEquals(List.of(), response.conflictingReservationIds());
    }
}
