
/*As I am learning, A Bachelor of I.T. Student, which is how this project developed, to "sink in" some of the math and programming concepts 
I learned in the first semester I decided to leave some of the code as examples. They may help you too.*/

package com.totalbeginner.mathsCalculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.totalbeginner.mathsCalculator.model.*;

public interface PersonRepository
        extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);
        }

    
