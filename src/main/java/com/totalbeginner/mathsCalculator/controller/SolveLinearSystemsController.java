package com.totalbeginner.mathsCalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totalbeginner.mathsCalculator.dto.SolveLinearSystemsResult;
import com.totalbeginner.mathsCalculator.service.SolveLinearSystemsService;
import com.totalbeginner.mathsCalculator.service.MatrixTwoByTwoService;

    @Controller
    public class SolveLinearSystemsController {
    private final SolveLinearSystemsService solveLinearSystemsService;
    private final MatrixTwoByTwoService matrixTwoByTwoService;

    public SolveLinearSystemsController(
            SolveLinearSystemsService solveLinearSystemsService, MatrixTwoByTwoService matrixTwoByTwoService) {

        this.solveLinearSystemsService = solveLinearSystemsService;
        this.matrixTwoByTwoService = matrixTwoByTwoService;
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

            @RequestParam(defaultValue = "0") int currentStep,
            @RequestParam(defaultValue = "0") int inverseCurrentStep,
            @RequestParam(defaultValue = "0") int solveLinearStep,

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
                boolean hasInverseWalkthrough = false;

                if ("start-inverse-walkthrough".equals(action)) {
                    newStep = 4;
                    inverseCurrentStep = 0;
                    hasInverseWalkthrough = true;
                }

                if ("next-linear-inverse-step".equals(action)) {
                    newStep = 4;
                    inverseCurrentStep++;
                    hasInverseWalkthrough = true;
                }

                if ("previous-linear-inverse-step".equals(action)) {
                    newStep = 4;
                    inverseCurrentStep--;
                    hasInverseWalkthrough = true;
                }
                boolean hasFinalLinearSystemWalkthrough = false;
                if ("start-final-linear-system-walkthrough".equals(action)) {
                    newStep = 4;
                    inverseCurrentStep = 4;
                    solveLinearStep = 0;
                    hasInverseWalkthrough = true;
                    hasFinalLinearSystemWalkthrough = true;
                }
                if ("next-final-linear-system-step".equals(action)) {
                    newStep = 4;
                    inverseCurrentStep = 4;
                    solveLinearStep++;
                    hasInverseWalkthrough = true;
                    hasFinalLinearSystemWalkthrough = true;
                }
                if ("previous-final-linear-system-step".equals(action)) {
                    newStep = 4;
                    inverseCurrentStep = 4;
                    solveLinearStep--;
                    hasInverseWalkthrough = true;
                    hasFinalLinearSystemWalkthrough = true;
                }

        inverseCurrentStep = Math.max(0, Math.min(inverseCurrentStep, 4));
        solveLinearStep = Math.max(0, Math.min(solveLinearStep, 4));

        // Keep the walkthrough within its valid range
        newStep = Math.max(0, Math.min(newStep, 4));

        result.setCurrentStep(newStep);
        result.setInverseCurrentStep(inverseCurrentStep);
        result.setSolveLinearStep(solveLinearStep);

        double[][] coefficientMatrix = result.getCoefficientMatrix();

        double[][] inverseWalkthroughMatrix =
                matrixTwoByTwoService.buildInverseWalkthroughMatrix(
                        coefficientMatrix,
                        inverseCurrentStep
                );

        double[][] finalInverseMatrix =
                matrixTwoByTwoService.calculateInverseTwoByTwo(
                        coefficientMatrix
                );

        model.addAttribute("hasInverseWalkthrough", hasInverseWalkthrough);
        model.addAttribute("inverseWalkthroughMatrix", inverseWalkthroughMatrix);

        model.addAttribute(
                "inverseStep1",
                inverseCurrentStep >= 1 ? "Swap" : ""
        );

        model.addAttribute(
                "inverseStep2",
                inverseCurrentStep >= 2 ? "Negate" : ""
        );

        model.addAttribute(
                "inverseDeterminant",
                inverseCurrentStep >= 3 ? result.getDeterminant() : ""
        );

        model.addAttribute(
                "finalInverseMatrix",
                inverseCurrentStep >= 4
                        ? finalInverseMatrix
                        : new double[2][2]
        );
        model.addAttribute(
            "hasFinalLinearSystemWalkthrough",
            hasFinalLinearSystemWalkthrough
        );

        model.addAttribute("result", result);

        return "linearSystems";
    }
}