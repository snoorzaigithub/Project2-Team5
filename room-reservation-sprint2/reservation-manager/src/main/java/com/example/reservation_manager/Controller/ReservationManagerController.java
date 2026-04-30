package com.example.reservation_manager.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation_manager.messaging.ReservationEventPublisher;


@RestController
@RequestMapping("/reservations")
public class ReservationManagerController {
    private final ReservationEventPublisher eventPublisher;

    public ReservationManagerController(ReservationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/{id}/events/created")
    public void publishCreated(
            @PathVariable Long id,
            @RequestParam Long roomId,
            @RequestParam Long userId) {
        eventPublisher.publishReservationCreated(id, roomId, userId, "PENDING");
    }
}
