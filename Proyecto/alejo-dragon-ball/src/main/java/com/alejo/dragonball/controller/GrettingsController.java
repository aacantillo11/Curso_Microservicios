package com.alejo.dragonball.controller;

import com.alejo.dragonball.config.DragonBallConfig;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/dragonball/greetings")
public class GrettingsController {

    private static final Logger log = LoggerFactory.getLogger(GrettingsController.class);
    @Autowired
    private DragonBallConfig dragonBallConfig;

    @Autowired
    private MeterRegistry registry;  //Para metricas

    @GetMapping
    @Timed("alejo.dragonball.greeting")
    public ResponseEntity<String> getGreeting(){
        log.info("Estamos solicitando el nombre de la app");
        int value = new Random().nextInt(5);
        registry.counter("alejo.dragonball.name","level",(value < 3) ? "jr":"sr").increment();//Primero va el nombre de la metrica  y después los tags que deben ir en par de claves.
        if(value < 3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(dragonBallConfig.getApplicationName());
    }
}
