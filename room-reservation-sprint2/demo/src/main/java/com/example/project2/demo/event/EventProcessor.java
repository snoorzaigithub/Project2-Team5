package com.example.project2.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventProcessor {

    @EventListener
    public void handleReservationConfirmed(ReservationConfirmedEvent event) {
        System.out.println("EVENT RECEIVED: ReservationConfirmed");

        System.out.println("Logging confirmation for reservation: " 
                + event.getReservationId());
    }
}