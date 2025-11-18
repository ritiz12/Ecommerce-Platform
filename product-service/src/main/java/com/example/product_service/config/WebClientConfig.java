package com.example.product_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
            .filter((request,next) -> {
                String token = extractKeycloakToken();

                if(token != null) {
                    ClientRequest filteredRequest = ClientRequest.from(request)
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .build();

                    return next.exchange(filteredRequest);
                }
                
                return next.exchange(request);
            });
    }
    private String extractKeycloakToken() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuth) {
                return jwtAuth.getToken().getTokenValue();
            }


            return null;
        } catch (Exception e) {
            System.err.println("⚠️ Could not extract JWT token: " + e.getMessage());
            return null;
        }
    }
}
