package com.totalbeginner.mathsCalculator.controller.numberTheory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totalbeginner.mathsCalculator.dto.numberTheory.PrimeFactorizationResult;
import com.totalbeginner.mathsCalculator.service.numberTheory.PrimeFactorizationService;

@Controller
public class PrimeFactorizationController {

    private final PrimeFactorizationService primeFactorizationService;

    public PrimeFactorizationController(PrimeFactorizationService primeFactorizationService) {
        this.primeFactorizationService = primeFactorizationService;
    }

    @GetMapping("/prime-factorization")
    public String showPrimeFactorizationPage(Model model) {

        model.addAttribute("number", 0);

        return "numberTheory/primeFactorization";
    }

    @PostMapping("/prime-factorization")
    public String handlePrimeFactorization(
            @RequestParam(defaultValue = "0") int number,
            @RequestParam(required = false) String action,
            Model model) {

        PrimeFactorizationResult result = primeFactorizationService.buildPrimeFactorizationResult(number);

        switch (action == null ? "" : action) {

            case "update-number":
                break;

            case "generate-factorization":
                // TODO: Call PrimeFactorizationService
                break;

            case "next-step":
                // TODO
                break;

            case "previous-step":
                // TODO
                break;

            case "reset":
                number = 0;
                break;

            default:
                break;
        }

        model.addAttribute("number", number);
        model.addAttribute("result", result);
       
        return "numberTheory/primeFactorization";
    }
}