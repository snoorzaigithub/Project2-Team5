package com.example.reservation_manager.messaging;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String RESERVATION_EXCHANGE = "reservation.exchange";
    public static final String RESERVATION_CREATED_KEY = "reservation.created";

    @Bean
    public TopicExchange bookingExchange(){
        return new TopicExchange(RESERVATION_EXCHANGE);
    }
}
