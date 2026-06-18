package com.totalbeginner.mathsCalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class LinearEquationController {

        @GetMapping("/linear-equations")
        public String showLinearEquationsPage(Model model) {

        model.addAttribute("a", 0);
        model.addAttribute("b", 0);
        model.addAttribute("c", 0);

        model.addAttribute("d", 0);
        model.addAttribute("e", 0);
        model.addAttribute("f", 0);

        return "linearEquations";
    }
}