package com.example.demo.controller;

import com.example.demo.entities.Person;
import com.example.demo.repository.DaoRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/persons")
    @PreAuthorize("#username == authentication.principal.username")
    public String personsHomePage(@RequestParam String username) {
        return "Homepage for " + username;
    }

    @GetMapping("/persons/by-city")
    @Secured("ROLE_READ")
    public ResponseEntity<List<Person>> getByCity(@RequestParam String city) {
        List<Person> persons = daoRepository.findByCityOfLiving(city);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/persons/by-less-than-age")
    @RolesAllowed("ROLE_WRITE")
    public ResponseEntity<List<Person>> getByAge(@RequestParam int age) {
        List<Person> persons = daoRepository.findByAgeLessThanOrderByAgeAsc(age);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/persons/by-name-and-surname")
    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
    public ResponseEntity<Person> getByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        Optional<Person> person = daoRepository.findByNameAndSurname(name, surname);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
