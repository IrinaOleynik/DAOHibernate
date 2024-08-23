package com.example.demo.controller;

import com.example.demo.entities.Person;
import com.example.demo.repository.DaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DaoController {
    private final DaoRepository daoRepository;

    public DaoController(DaoRepository daoRepository) {
        this.daoRepository = daoRepository;
    }
    @GetMapping("/persons/by-city")
    public ResponseEntity<List<Person>> getByCity(@RequestParam String city) {
        List<Person> persons = daoRepository.getPersonsByCity(city);
        return ResponseEntity.ok(persons);
    }
}
