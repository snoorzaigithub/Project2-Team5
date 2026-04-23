package com.example.reservation_manager.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class BookingEventPublisher {
    private final  RabbitTemplate rabbitTemplate;

    public BookingEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
