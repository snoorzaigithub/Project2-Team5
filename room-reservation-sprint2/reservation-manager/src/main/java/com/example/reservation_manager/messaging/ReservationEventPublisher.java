package com.example.reservation_manager.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ReservationEventPublisher {
    private final  RabbitTemplate rabbitTemplate;

    public ReservationEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    //public void publishReservationCreated(Reservation reservation){
      //  ReservationCreatedEvent event = new ReserverationCreatedEvent(reservation.getId(), reservation.getStatus()){

        //}
    //}
}
