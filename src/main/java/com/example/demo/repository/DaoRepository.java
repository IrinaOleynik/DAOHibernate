package com.example.demo.repository;

import com.example.demo.entities.Person;
import com.example.demo.entities.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DaoRepository extends JpaRepository<Person, PersonId> {

    List<Person> findByCityOfLiving(String city);

    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    Optional<Person> findByNameAndSurname(String name, String surname);
}

