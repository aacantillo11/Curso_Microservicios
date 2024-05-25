package com.alejo.dragonball.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dragonball")
public class DragonBallController {

    List<String> people = Arrays.asList("Goku","Vegeta","Piccolo","Gohan","Krillin","Trunks");

    @GetMapping
    public ResponseEntity<List<String>> getAll(){
        System.out.println("Estamos");
        return ResponseEntity.ok(people);
    }
}
