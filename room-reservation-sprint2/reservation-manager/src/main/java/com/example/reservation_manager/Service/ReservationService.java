package com.example.reservation_manager.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reservation_manager.Client.OverlapEngineClient;
import com.example.reservation_manager.Client.ReservationAccessClient;
import com.example.reservation_manager.messaging.ReservationEventPublisher;
import com.example.reservation_manager.model.OverlapCheckRequest;
import com.example.reservation_manager.model.OverlapCheckResponse;
import com.example.reservation_manager.model.Reservation;
import com.example.reservation_manager.model.ReservationRequest;
import com.example.reservation_manager.model.ReservationWindow;

@Service
public class ReservationService {
    private final OverlapEngineClient overlapEngineClient;
    private final ReservationAccessClient reservationAccessClient;
    private final ReservationEventPublisher eventPublisher;

    public ReservationService(
            OverlapEngineClient overlapEngineClient,
            ReservationAccessClient reservationAccessClient,
            ReservationEventPublisher eventPublisher) {
        this.overlapEngineClient = overlapEngineClient;
        this.reservationAccessClient = reservationAccessClient;
        this.eventPublisher = eventPublisher;
    }

    public Reservation reserve(ReservationRequest request) {
        List<Reservation> existing = reservationAccessClient.findByRoomId(request.roomId());
        OverlapCheckResponse response = overlapEngineClient.check(new OverlapCheckRequest(
                request.roomId(),
                request.requestedStart(),
                request.requestedEnd(),
                existing.stream()
                        .map(r -> new ReservationWindow(r.id(), r.roomId(), r.start(), r.end(), r.status()))
                        .toList()));

        if (!"CLEAR".equals(response.result())) {
            throw new RuntimeException("Reservation conflicts with " + response.conflictingReservationIds());
        }

        Reservation saved = reservationAccessClient.create(new Reservation(
                null,
                request.roomId(),
                request.userId(),
                request.requestedStart(),
                request.requestedEnd(),
                "PENDING"));
        eventPublisher.publishReservationCreated(saved.id(), saved.roomId(), saved.userId(), saved.status());
        return saved;
    }

    public Reservation confirm(Long id) {
        Reservation reservation = reservationAccessClient.updateStatus(id, "CONFIRMED");
        eventPublisher.publishReservationConfirmed(reservation.id(), reservation.roomId(), reservation.userId());
        return reservation;
    }
}
