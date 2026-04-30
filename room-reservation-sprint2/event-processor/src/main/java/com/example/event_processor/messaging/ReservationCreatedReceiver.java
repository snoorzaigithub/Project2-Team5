package com.example.event_processor.messaging;

import java.time.Instant;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.stereotype.Component;

@Component
public class ReservationCreatedReceiver {
    private final RabbitOperations rabbitTemplate;

    public ReservationCreatedReceiver(RabbitOperations rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.RESERVATION_CREATED_QUEUE)
    public void receiveMessage(String message) {
        System.out.println("Reservation created: " + message);
        String[] parts = message.split(",");
        String reservationId = parts[0];
        String roomId = parts[1];
        String userId = parts[2];

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.RESERVATION_EXCHANGE,
                RabbitMQConfig.CONFIRMATION_LOGGED_KEY,
                reservationId + "," + roomId + "," + userId + "," + Instant.now());
    }
}
