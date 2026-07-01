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

    public PrimeFactorizationController(
            PrimeFactorizationService primeFactorizationService) {

        this.primeFactorizationService = primeFactorizationService;
    }

    @GetMapping("/prime-factorization")
    public String showPrimeFactorizationPage(Model model) {

        model.addAttribute("number", 0);
        model.addAttribute("primeButtons",
                primeFactorizationService.buildPrimeButtons());

        return "numberTheory/primeFactorization";
    }

    @PostMapping("/prime-factorization")
    public String handlePrimeFactorization(

            @RequestParam(defaultValue = "0") int number,
            @RequestParam(required = false) String action,
            @RequestParam(defaultValue = "0") int currentNumber,
            @RequestParam(defaultValue = "0") int attemptedPrime,
            @RequestParam(required = false) String factors,

            Model model) {  
        PrimeFactorizationResult result =
                primeFactorizationService.buildPrimeFactorizationResult(number);
        /*
         * Restore the current value only if the walkthrough
         * is not already complete.
         */
        if (!result.isCompleted()) {
            result.setCurrentNumber(
                    currentNumber == 0 ? number : currentNumber);
        }
        switch (action == null ? "" : action) {

            case "generate-factorization":
                result = primeFactorizationService.buildPrimeFactorizationResult(number);
                result.setCurrentNumber(number);
            break;

            case "check-prime-factor":
                result = primeFactorizationService.checkPrimeFactor(
                        result,
                        attemptedPrime,
                        factors);
                break;

            case "reset":
                return "redirect:/prime-factorization";

            default:
                break;
        }

        model.addAttribute("number", number);
        model.addAttribute("result", result);
        model.addAttribute("primeButtons",
                primeFactorizationService.buildPrimeButtons());

        if (result != null) {
            model.addAttribute("factorsDisplay",
                    result.getFactorsDisplay());

            model.addAttribute("finalFactorizationDisplay",
                    result.getFinalFactorizationDisplay());
        }

        return "numberTheory/primeFactorization";
    }
}