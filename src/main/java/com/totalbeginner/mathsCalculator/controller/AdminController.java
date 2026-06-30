/*As I am learning, A Bachelor of I.T. Student, which is how this project developed, to "sink in" some of the math and programming concepts 
I learned in the first semester I decided to leave some of the code as examples. They may help you too.*/
package com.totalbeginner.mathsCalculator.controller;
import com.totalbeginner.mathsCalculator.model.Person;
import com.totalbeginner.mathsCalculator.service.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//class to handle admin... controller "directs traffic".
        @Controller
        public class AdminController {

        private final PersonService personService;

        public AdminController(PersonService personService) {
                this.personService = personService;
        }

        private boolean isAdmin(HttpSession session) {
                Person user = (Person) session.getAttribute("user");

                return user != null && "1".equals(user.getUsername());
        }

        @GetMapping("/admin")
        public String adminDashboard(Model model, HttpSession session)
                {
                        if (!isAdmin(session)) {
                        return "redirect:/login";
                        }

                        Person user = (Person) session.getAttribute("user");
                        model.addAttribute("user", user);       
                        model.addAttribute("users", personService.findAll());        

                        return "adminDashboard";
                }

        @GetMapping("/admin/users")
        public String manageUsers(Model model, HttpSession session)
                {
                        if (!isAdmin(session)) {
                        return "redirect:/login";
                        }

                        Person user = (Person) session.getAttribute("user");

                        model.addAttribute("user", user);           
                        model.addAttribute("users", personService.findAll());        

                        return "manageUsers";
                }

        @GetMapping("/admin/users/edit/{id}")
        public String editUserForm(@PathVariable @NonNull Long id, Model model, HttpSession session)
                {
                        if (!isAdmin(session)) {
                        return "redirect:/login";
                        }
                        Person user = (Person) session.getAttribute("user");

                        model.addAttribute("user", user);       
                        model.addAttribute("person", personService.findById(id));        

                        return "editUser";
                }

        @PostMapping("/admin/users/update")
        public String updateUser(@ModelAttribute Person person, HttpSession session)
                {
                        if (!isAdmin(session)) {
                        return "redirect:/login";
                        }

                        personService.saveExistingUser(person);

                        return "redirect:/admin/users";
                }

        @GetMapping("/admin/users/delete/{id}")
        public String deleteUser(@PathVariable @NonNull Long id)
                {
                        System.out.println("=================================");   
                        System.out.println("DELETE BUTTON REACHED CONTROLLER");   
                        System.out.println("USER ID TO DELETE: " + id);    

                        personService.deleteById(id);

                        System.out.println("CONTROLLER FINISHED");
                        System.out.println("=================================");    

                        return "redirect:/admin/users";
                }
}
