package com.alejo.gameofthrones.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gameofthrones")
public class GameOfThronesController {

    List<String> people = Arrays.asList("Ser Beric Dondarrion","Cersei Lannister","Ser Jaime Lannister","Euron Greyjoy","Qyburn","Melisandre");

    @GetMapping
    public ResponseEntity<List<String>> getAll(){
        return ResponseEntity.ok(people);
    }
}
