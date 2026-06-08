package com.totalbeginner.mixrecovery19.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.totalbeginner.mixrecovery19.model.*;

public interface PersonRepository
        extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);
        }

    
