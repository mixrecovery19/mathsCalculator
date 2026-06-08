/* this is a login controller, spring boot uses controllers to handle https requests and map the urls
 */
package com.totalbeginner.mixrecovery19.controller;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.core.io.Resource;
import java.util.Arrays;
import java.util.List;


import com.totalbeginner.mixrecovery19.model.*;
import com.totalbeginner.mixrecovery19.service.*;
import com.totalbeginner.mixrecovery19.service.*;

@Controller
public class LoginController {

    private final PersonService personService;
  

    public LoginController(PersonService personService)
    {
        this.personService = personService;
    }
       
        @GetMapping("/")
                public String home(Model model, HttpSession session)
                throws IOException {
        
        Person user = 
                (Person) session.getAttribute("user");                       

                model.addAttribute("user", user);      
                model.addAttribute("isAdmin", user != null && Boolean.TRUE.equals(user.getIsAdmin()));            
        
        // loads all images so the home page can display and javascript function can access them
        Resource[] files = new PathMatchingResourcePatternResolver()
                                .getResources("classpath:/static/images/*");                     

        List<String> images =
                Arrays.stream(files)
                        .map(file -> "/images/" + file.getFilename())                        
                        .toList();

                model.addAttribute("images", images);               
                     
                return "index";
        }

        // 🔹 Login Page
        @GetMapping("/login")
        public String showLogin(Model model) {            
                model.addAttribute("person", new Person());                

                return "login";
        }

        // 🔹 Login Action
        @PostMapping("/login")
        public String handleLogin(Person formUser, Model model, HttpSession session)
        {
        Person loggedInUser =
                personService.login(
                        formUser.getUsername(),
                        formUser.getPassword()
                );

        if (loggedInUser != null) {

            session.setAttribute("user", loggedInUser);           
            session.setAttribute("isAdmin", loggedInUser.getIsAdmin());           

            return "redirect:/";
        }
                model.addAttribute("error", "Invalid username or password");       

                return "login";
        }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();

        return "redirect:/";
    }
}