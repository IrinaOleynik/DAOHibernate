package com.example.demo.controller;

import com.example.demo.entities.Person;
import com.example.demo.repository.DaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DaoController {
    private final DaoRepository daoRepository;

    public DaoController(DaoRepository daoRepository) {
        this.daoRepository = daoRepository;
    }

    @GetMapping("/persons/by-city")
    public ResponseEntity<List<Person>> getByCity(@RequestParam String city) {
        List<Person> persons = daoRepository.findByCityOfLiving(city);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/persons/by-less-than-age")
    public ResponseEntity<List<Person>> getByAge(@RequestParam int age) {
        List<Person> persons = daoRepository.findByAgeLessThanOrderByAgeAsc(age);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/persons/by-name-and-surname")
    public ResponseEntity<Person> getByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        Optional<Person> person = daoRepository.findByNameAndSurname(name, surname);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
