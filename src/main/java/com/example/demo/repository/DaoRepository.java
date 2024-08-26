package com.example.demo.repository;

import com.example.demo.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getPersonsByCity(String city) {
        Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.cityOfLiving = :city", Person.class);
        query.setParameter("city", city);
        return query.getResultList();
    }
}

