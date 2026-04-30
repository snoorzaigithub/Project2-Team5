package com.example.reservation_manager.messaging;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitOperations;

class ReservationEventPublisherTest {
    @Test
    void publishesReservationCreatedEvent() {
        RabbitOperations rabbitTemplate = org.mockito.Mockito.mock(RabbitOperations.class);
        ReservationEventPublisher publisher = new ReservationEventPublisher(rabbitTemplate);

        publisher.publishReservationCreated(12L, 34L, 56L, "PENDING");

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConfig.RESERVATION_EXCHANGE,
                ReservationEventTopics.CREATED,
                "12,34,56,PENDING");
    }
}
