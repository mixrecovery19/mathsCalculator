package com.totalbeginner.mathsCalculator.controller.numberTheory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totalbeginner.mathsCalculator.dto.numberTheory.EuclideanAlgorithmResult;
import com.totalbeginner.mathsCalculator.service.numberTheory.EuclideanAlgorithmService;

@Controller
public class EuclideanAlgorithmController {

    private final EuclideanAlgorithmService euclideanAlgorithmService;

    public EuclideanAlgorithmController(EuclideanAlgorithmService euclideanAlgorithmService) {
        this.euclideanAlgorithmService = euclideanAlgorithmService;
    }

    @GetMapping("/euclidean-algorithm")
    public String showEuclideanAlgorithmPage(Model model) {

        model.addAttribute("firstNumber", null);
        model.addAttribute("secondNumber", null);
        model.addAttribute("result", new EuclideanAlgorithmResult());

        return "numberTheory/euclideanAlgorithm";
    }

    @PostMapping("/euclidean-algorithm")
    public String calculateEuclideanAlgorithm(
        @RequestParam int firstNumber,
        @RequestParam int secondNumber,
        @RequestParam(required = false) String action,
        @RequestParam(defaultValue = "0")
        int euclideanSection,
        @RequestParam(defaultValue = "0")
        int currentEuclideanStep,
        Model model) {

  EuclideanAlgorithmResult result = new EuclideanAlgorithmResult();

result.setFirstNumber(firstNumber);
result.setSecondNumber(secondNumber);

result.setEuclideanSection(euclideanSection);
result.setCurrentEuclideanStep(currentEuclideanStep);

// Restart first
if ("restart-euclidean-algorithm".equals(action)) {
    return "redirect:/euclidean-algorithm";
}

// Build all calculations
euclideanAlgorithmService.buildEuclideanWalkthrough(result);

// Handle next/previous/etc.
handleEuclideanActions(action, result);

model.addAttribute("firstNumber", firstNumber);
model.addAttribute("secondNumber", secondNumber);
model.addAttribute("result", result);

return "numberTheory/euclideanAlgorithm";
        }

    private String handleEuclideanActions(String action, EuclideanAlgorithmResult result) {
        if (action == null) {
            return null;
        }

    switch (action) {

        case "start-euclidean":
            result.setEuclideanSection(1);
            result.setCurrentEuclideanStep(0);
            break;

        case "next-euclidean-step":
            if (result.getCurrentEuclideanStep() < result.getMaximumEuclideanStep()) {
                result.setCurrentEuclideanStep(
                    result.getCurrentEuclideanStep() + 1);
            }
            break;

        case "previous-euclidean-step":
            if (result.getCurrentEuclideanStep() > 0) {
                result.setCurrentEuclideanStep(
                    result.getCurrentEuclideanStep() - 1);
            }
            break;
        case "restart-euclidean-algorithm":                
            return "redirect:/euclidean-algorithm";           
        }
        return null;
    }
}
