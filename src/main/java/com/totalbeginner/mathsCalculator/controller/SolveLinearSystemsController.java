package com.totalbeginner.mathsCalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totalbeginner.mathsCalculator.dto.SolveLinearSystemsResult;
import com.totalbeginner.mathsCalculator.service.SolveLinearSystemsService;
import com.totalbeginner.mathsCalculator.service.MatrixTwoByTwoService;
import com.totalbeginner.mathsCalculator.service.MathFormatterService;


    @Controller
    public class SolveLinearSystemsController {
    private final SolveLinearSystemsService solveLinearSystemsService;
    private final MatrixTwoByTwoService matrixTwoByTwoService;    

    public SolveLinearSystemsController(
            SolveLinearSystemsService solveLinearSystemsService, MatrixTwoByTwoService matrixTwoByTwoService, MathFormatterService mathFormatterService) {

        this.solveLinearSystemsService = solveLinearSystemsService;
        this.matrixTwoByTwoService = matrixTwoByTwoService;        
    }

        @GetMapping("/linear-systems")
        public String showLinearSystemsPage(Model model) {

            SolveLinearSystemsResult result = new SolveLinearSystemsResult();

            result.setCoefficientMatrix(new double[2][2]);
            result.setConstantsVector(new double[2]);
            result.setHasMatrixValues(false);
            result.setDisplayMode("decimal");

            model.addAttribute("result", result);

            return "linearSystems";
        }
    @PostMapping("/linear-systems")
    public String createLinearSystem(
            @RequestParam(required = false) Double a_0_0,
            @RequestParam(required = false) Double a_0_1,
            @RequestParam(required = false) Double b_0,

            @RequestParam(required = false) Double a_1_0,
            @RequestParam(required = false) Double a_1_1,
            @RequestParam(required = false) Double b_1,

            @RequestParam(required = false) String action,
            @RequestParam(defaultValue = "decimal") String displayMode,

            @RequestParam(defaultValue = "0") int currentStep,
            @RequestParam(defaultValue = "0") int inverseCurrentStep,
            @RequestParam(defaultValue = "0") int solveLinearStep,

            Model model) {
                if (a_0_0 == null || a_0_1 == null || b_0 == null || a_1_0 == null || a_1_1 == null || b_1 == null) {

                SolveLinearSystemsResult result = new SolveLinearSystemsResult();

                result.setCoefficientMatrix(new double[2][2]);
                result.setConstantsVector(new double[2]);
                result.setHasMatrixValues(false);
                result.setDisplayMode(displayMode);

                model.addAttribute("result", result);
                model.addAttribute("inputError", "Please enter all six values before continuing.");                

                return "linearSystems";
        }
                
        SolveLinearSystemsResult result =            
                    solveLinearSystemsService.solveByElimination(
                            a_0_0,
                            a_0_1,
                            b_0,

                            a_1_0,
                            a_1_1,
                            b_1);


            int newStep = currentStep;
                result.setDisplayMode(displayMode);
                
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
                if ("back-one-section".equals(action)) {
                    result.setHasInitialValues(true);
                    result.setHasMatrixValues(false);
                    currentStep = 0;
                    inverseCurrentStep = 0;
                    solveLinearStep = 0;
                }
                if ("back-to-section-one".equals(action)) {

                    result.setHasInitialValues(false);
                    result.setHasMatrixValues(true);

                    newStep = 0;
                    inverseCurrentStep = 0;
                    solveLinearStep = 0;

                    hasInverseWalkthrough = false;
                    hasFinalLinearSystemWalkthrough = false;
                }
                if ("back-to-section-two".equals(action)) {
                    result.setHasInitialValues(true);
                    result.setHasMatrixValues(false);

                    newStep = 4;
                    inverseCurrentStep = 0;
                    solveLinearStep = 0;

                    hasInverseWalkthrough = false;
                    hasFinalLinearSystemWalkthrough = false;
                }
                if ("back-to-section-three".equals(action)) {
                    result.setHasInitialValues(true);
                    result.setHasMatrixValues(true);

                    newStep = 4;
                    inverseCurrentStep = 4;
                    solveLinearStep = 0;

                    hasInverseWalkthrough = true;
                    hasFinalLinearSystemWalkthrough = false;
                }
                if ("restart-linear-system-walkthrough".equals(action)) {
                    result.setHasInitialValues(false);
                    result.setHasMatrixValues(false);

                    newStep = 0;
                    inverseCurrentStep = 0;
                    solveLinearStep = 0;
                    hasInverseWalkthrough = false;
                    hasFinalLinearSystemWalkthrough = false;
                }
                              
            newStep = Math.max(0, Math.min(newStep, 4));
            inverseCurrentStep = Math.max(0, Math.min(inverseCurrentStep, 4));
            solveLinearStep = Math.max(0, Math.min(solveLinearStep, 7));

            // Store the final values
            result.setCurrentStep(newStep);
            result.setInverseCurrentStep(inverseCurrentStep);
            result.setSolveLinearStep(solveLinearStep);

        
        double[][] coefficientMatrix = result.getCoefficientMatrix();

        double[][] inverseWalkthroughMatrix =
                matrixTwoByTwoService.buildInverseWalkthroughMatrix(coefficientMatrix, inverseCurrentStep);               

        double[][] finalInverseMatrix =
                matrixTwoByTwoService.calculateInverseTwoByTwo(
                        coefficientMatrix
                );

        solveLinearSystemsService.calculateFinalSolutionSteps(result, finalInverseMatrix);
        //solveLinearStep = Math.max(0, Math.min(solveLinearStep, 7));

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