package com.alejo.dragonball.controller;

import com.alejo.dragonball.config.DragonBallConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dragonball/greetings")
public class GrettingsController {

    private static final Logger log = LoggerFactory.getLogger(GrettingsController.class);
    @Autowired
    private DragonBallConfig dragonBallConfig;

    @GetMapping
    public ResponseEntity<String> getGreeting(){
        log.info("Estamos solicitando el nombre de la app");
        return ResponseEntity.ok(dragonBallConfig.getApplicationName());
    }
}
