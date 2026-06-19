package com.totalbeginner.mathsCalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.totalbeginner.mathsCalculator.dto.SolveLinearSystemsResult;
import com.totalbeginner.mathsCalculator.service.SolveLinearSystemsService;

    @Controller
    public class SolveLinearSystemsController {
    private final SolveLinearSystemsService solveLinearSystemsService;

    public SolveLinearSystemsController(
            SolveLinearSystemsService solveLinearSystemsService) {

        this.solveLinearSystemsService = solveLinearSystemsService;
    }

        @GetMapping("/linear-systems")
        public String showLinearSystemsPage(Model model) {

            SolveLinearSystemsResult result = new SolveLinearSystemsResult();

            result.setCoefficientMatrix(new double[2][2]);
            result.setConstantsVector(new double[2]);
            result.setHasMatrixValues(false);

            model.addAttribute("result", result);

            return "linearSystems";
        }
    @PostMapping("/linear-systems")
    public String createLinearSystem(
            @RequestParam double a_0_0,
            @RequestParam double a_0_1,
            @RequestParam double b_0,

            @RequestParam double a_1_0,
            @RequestParam double a_1_1,
            @RequestParam double b_1,

            @RequestParam(required = false) String action,
            @Deprecated @RequestParam(defaultValue = "0") int currentStep,

            Model model) {
            SolveLinearSystemsResult result =
                    solveLinearSystemsService.solveByElimination(
                            a_0_0,
                            a_0_1,
                            b_0,

                            a_1_0,
                            a_1_1,
                            b_1);

                    int newStep = currentStep;

if ("create-linear-system".equals(action)) {
    newStep = 0;
}
else if ("proceed-to-step-two".equals(action)) {
    newStep = 1;
}
else if ("next-linear-system-step".equals(action)) {
    newStep++;
}
else if ("previous-linear-system-step".equals(action)) {
    newStep--;
}

// Keep the walkthrough within its valid range
newStep = Math.max(0, Math.min(newStep, 4));

result.setCurrentStep(newStep);

        model.addAttribute("result", result);

        return "linearSystems";
    }
}