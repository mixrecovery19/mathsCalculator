package com.totalbeginner.mixrecovery19.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;
import java.util.List;

import com.totalbeginner.mixrecovery19.model.*;
import com.totalbeginner.mixrecovery19.repository.*;

@Service
public class PersonService {
    private final PersonRepository personRepository;
        private final PasswordEncoder passwordEncoder;
// now we use it inside the constructor. to "set the tone" of the PersonService class.
        public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
                this.personRepository = personRepository;
                this.passwordEncoder = passwordEncoder;
        }

        public Person login(String username, String password) {

        System.out.println("TEMP HASH FOR 1: " + passwordEncoder.encode("1"));        

        debugUsers();

        System.out.println("========== LOGIN ATTEMPT ==========");
        System.out.println("USERNAME ENTERED: " + username);
        System.out.println("PASSWORD ENTERED: " + password);        

    Person person = personRepository
                    .findByUsername(username)
                    .orElse(null);

    if (person == null) {
// self explanatory, if person is null, then user was not found. Handle gracefully.
        System.out.println("USER NOT FOUND");        

        return null;
    }
//this returns the user that was found, again, allowing for a terminal visuall demonstration of what is going on for testing and for demonstration purposes.
    System.out.println("FOUND USER: " + person.getUsername());    
    System.out.println("DATABASE HASH: " + person.getPassword());

    boolean matches = passwordEncoder.matches(password, person.getPassword());            

    System.out.println("PASSWORD MATCHES: " + matches);  
    System.out.println("===================================");    

        if (matches) {
                return person;
        }

        return null;
        }

    // 🔹 Save New User
    public void savePerson(Person person) {
        // hash password
        person.setPassword(passwordEncoder.encode(person.getPassword()));        
        // save user
        personRepository.save(person);
        
    }
        public Person getPersonById(@NonNull Long id) {
        return personRepository
                .findById(id)
                .orElse(null);
        }
                public void debugUsers() {

                System.out.println("===== USERS IN DATABASE =====");                

                personRepository
                        .findAll()
                        .forEach(person -> System.out.println("USERNAME: " + person.getUsername()));                             
                      
                System.out.println("=============================");
                
                }

                public List<Person> findAll() {
                return personRepository.findAll();
                }

                public Person findById(@NonNull Long id) {
                return personRepository.findById(id)
                        .orElse(null);
                }

               public void deleteById(@NonNull Long id)
                {

        System.out.println("========== SERVICE DELETE ==========");  
        System.out.println("LOOKING FOR USER: " + id);    

        Person person = personRepository
                        .findById(id)
                        .orElse(null);

        if (person == null) {

                System.out.println("USER NOT FOUND");        

                return;
        }

    System.out.println("FOUND USER: " + person.getUsername());    
    
                try {

                        personRepository.delete(person);             
                        System.out.println("DELETE SUCCESSFUL");

                } catch (Exception e) {
                        System.out.println("DELETE FAILED");             
                        e.printStackTrace();
                }
        }

                public void saveExistingUser(Person updatedPerson){
                        if (updatedPerson == null) return;

                        // Find existing person by username to avoid relying on getId()
                        Person existingPerson = personRepository
                                        .findByUsername(updatedPerson.getUsername())
                                        .orElse(null);

                if (existingPerson != null) {

                        // prevent password being lost
                        if (
                        updatedPerson.getPassword()== null || updatedPerson .getPassword() .isBlank()) {
                        updatedPerson.setPassword(existingPerson .getPassword());
                        }
                        personRepository.save(updatedPerson);
                }
               
        }
        
        }
