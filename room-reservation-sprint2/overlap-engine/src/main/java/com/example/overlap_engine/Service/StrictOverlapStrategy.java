package com.example.overlap_engine.Service;

import com.example.overlap_engine.model.ReservationWindow;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class StrictOverlapStrategy implements OverlapStrategy {
    @Override
    public List<Long> findConflicts(
            Long roomId,
            LocalDateTime requestedStart,
            LocalDateTime requestedEnd,
            List<ReservationWindow> existingReservations
    ) {
        List<Long> conflicts = new ArrayList<>();

        if (existingReservations == null) {
            return conflicts;
        }

        for (ReservationWindow reservation : existingReservations) {
            if (reservation == null
                    || reservation.roomId() == null
                    || reservation.start() == null
                    || reservation.end() == null) {
                continue;
            }

            if (!reservation.roomId().equals(roomId)) {
                continue;
            }

            if (!isBlocking(reservation.status())) {
                continue;
            }

            if (requestedStart.isBefore(reservation.end()) && requestedEnd.isAfter(reservation.start())) {
                conflicts.add(reservation.id());
            }
        }

        return conflicts;
    }

    private boolean isBlocking(String status) {
        if (status == null) {
            return true;
        }

        String normalized = status.toUpperCase(Locale.ROOT);
        return !normalized.equals("CANCELLED") && !normalized.equals("REJECTED");
    }
}
