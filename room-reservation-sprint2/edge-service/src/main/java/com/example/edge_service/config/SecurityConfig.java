package com.example.edge_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http,
            ReactiveClientRegistrationRepository clientRegistrationRepository) {

        //RP-Initiated Logout handler — redirects to Keycloak logout endpoint
        OidcClientInitiatedServerLogoutSuccessHandler logoutHandler =
            new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);
        logoutHandler.setPostLogoutRedirectUri("{baseUrl}");

        return http
            //HTTP 401 for unauthenticated AJAX instead of redirect to login
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(
                    new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .authorizeExchange(exchanges -> exchanges
                // Kubernetes health probes must be public
                .pathMatchers("/actuator/health", "/actuator/info").permitAll()
                .anyExchange().authenticated()
            )
            //OIDC login
            .oauth2Login(Customizer.withDefaults())
            //RP-Initiated Logout with {baseUrl}
            .logout(logout -> logout
                .logoutSuccessHandler(logoutHandler)
            )
            //Cookie-based CSRF with WebFilter subscription
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
                .csrfTokenRequestHandler(new ServerCsrfTokenRequestAttributeHandler())
            )
            //SaveSession filter — persists session before forwarding
            .requestCache(Customizer.withDefaults())
            .build();
    }
}
