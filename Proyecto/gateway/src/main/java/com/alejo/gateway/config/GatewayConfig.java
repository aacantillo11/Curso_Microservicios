package com.alejo.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfig {

    @Bean
    @Profile("localhost-no-eureka")
    public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
        //return builder.routes().build();

        return builder.routes()
                .route( r ->r.path("/api/v1/dragonball")
                        .uri("http://localhost:8082"))
                .route( r ->r.path("/api/v1/gameofthrones")
                        . uri("http://localhost:8083"))
                . build();
    }

    @Bean
    @Profile("localhost-eureka")
    public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {

        return builder.routes()
                .route( r ->r.path("/api/v1/dragonball")
                        //.uri("lb://dragon-ball-failover"))
                        .uri("lb://alejo-dragon-ball"))
                .route( r ->r.path("/api/v1/gameofthrones")
                        .uri("lb://game-of-thrones"))
                . build();
    }

    @Bean
    @Profile("localhost-eureka-cb")
    public RouteLocator configLocalEurekaCB(RouteLocatorBuilder builder) {

        return builder.routes()
                .route( r ->r.path("/api/v1/dragonball")
                        .filters(f->f.circuitBreaker(c->c.setName("failoverCB")
                                .setFallbackUri("forward:/api/v1/db-failover/dragonball")
                                .setRouteId("dbFailover")))
                        .uri("lb://alejo-dragon-ball"))
                .route( r ->r.path("/api/v1/gameofthrones")
                        .uri("lb://game-of-thrones"))
                .route( r ->r.path("/api/v1/db-failover/dragonball")
                        .uri("lb://dragon-ball-failover"))
                . build();
    }
}
