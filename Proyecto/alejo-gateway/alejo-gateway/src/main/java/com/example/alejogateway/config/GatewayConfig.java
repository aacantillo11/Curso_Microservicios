package com.example.alejogateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {

        return builder.routes()
                .route( r ->r.path("/api/v1/dragonball")
                        //.uri("lb://dragon-ball-failover"))
                        .uri("lb://alejo-dragon-ball"))
                .route( r ->r.path("/api/v1/gameofthrones")
                        .uri("lb://game-of-thrones"))
                . build();
    }
}
