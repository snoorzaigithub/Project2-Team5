package com.example.edge_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

class EdgeServiceRouteConfigTest {

    @Test
    void applicationConfigRoutesRoomsAndReservations() {
        Properties properties = loadYaml(new ClassPathResource("application.yml"));

        assertEdgeRoutes(properties);
    }

    @Test
    void configRepoRoutesRoomsAndReservations() {
        Properties properties = loadYaml(new FileSystemResource("../config-repo/edge-service.yml"));

        assertEdgeRoutes(properties);
    }

    private static Properties loadYaml(Resource resource) {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(resource);
        return yaml.getObject();
    }

    private static void assertEdgeRoutes(Properties properties) {
        assertEquals("room-manager", properties.getProperty("spring.cloud.gateway.routes[0].id"));
        assertEquals("http://room-manager:8080", properties.getProperty("spring.cloud.gateway.routes[0].uri"));
        assertEquals("Path=/rooms/**", properties.getProperty("spring.cloud.gateway.routes[0].predicates[0]"));

        assertEquals("reservation-manager", properties.getProperty("spring.cloud.gateway.routes[1].id"));
        assertEquals("http://reservation-manager:8080", properties.getProperty("spring.cloud.gateway.routes[1].uri"));
        assertEquals("Path=/reservations/**", properties.getProperty("spring.cloud.gateway.routes[1].predicates[0]"));
    }
}
