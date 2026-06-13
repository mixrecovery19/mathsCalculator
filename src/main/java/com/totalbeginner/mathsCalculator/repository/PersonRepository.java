package com.totalbeginner.mathsCalculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.totalbeginner.mathsCalculator.model.*;

public interface PersonRepository
        extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);
        }

    
