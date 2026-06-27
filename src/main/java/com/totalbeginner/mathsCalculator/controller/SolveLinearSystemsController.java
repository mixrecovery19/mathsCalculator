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

        @GetMapping("/inverse-matrix-method")
        public String showLinearSystemsPage(Model model) {

            SolveLinearSystemsResult result = new SolveLinearSystemsResult();

            result.setCoefficientMatrix(new double[2][2]);
            result.setConstantsVector(new double[2]);
            result.setHasMatrixValues(false);
            result.setDisplayMode("decimal");
            result.setCurrentSection(0);

            model.addAttribute("result", result);

            return "inverseMatrixMethod";
        }
    @PostMapping("/inverse-matrix-method")
    public String createLinearSystem(
            @RequestParam(defaultValue = "0") int currentSection,
            @RequestParam(required = false) Double a_0_0,
            @RequestParam(required = false) Double a_0_1,
            @RequestParam(required = false) Double b_0,

            @RequestParam(required = false) Double a_1_0,
            @RequestParam(required = false) Double a_1_1,
            @RequestParam(required = false) Double b_1,

            @RequestParam(required = false) String action,
            @RequestParam(defaultValue = "decimal") String displayMode,           

            @RequestParam(defaultValue = "0") int determinantStep,
            @RequestParam(defaultValue = "0") int inverseCurrentStep,
            @RequestParam(defaultValue = "0") int solveLinearStep,

            Model model) {
                if (a_0_0 == null || a_0_1 == null || b_0 == null || a_1_0 == null || a_1_1 == null || b_1 == null) {

                SolveLinearSystemsResult result = new SolveLinearSystemsResult();

                result.setCoefficientMatrix(new double[2][2]);
                result.setConstantsVector(new double[2]);
                result.setHasMatrixValues(false);
                result.setDisplayMode(displayMode);
                result.setCurrentSection(currentSection);

                model.addAttribute("result", result);
                model.addAttribute("inputError", "Please enter all six values before continuing.");               
                return "inverseMatrixMethod";
        }
                
                SolveLinearSystemsResult result =
                solveLinearSystemsService.solveByElimination(
                        a_0_0,
                        a_0_1,
                        b_0,
                        a_1_0,
                        a_1_1,
                        b_1
                );

                int newDeterminantStep = determinantStep;
                result.setDisplayMode(displayMode);
            
                if (action == null || action.isBlank()) {
                    // User clicked Decimal or Fraction (or some other non-navigation button)
                    result.setCurrentSection(currentSection);
                    result.setDeterminantStep(determinantStep);
                    result.setInverseCurrentStep(inverseCurrentStep);
                    result.setSolveLinearStep(solveLinearStep);

                    result.setHasInverseWalkthrough(determinantStep >= 3);
                    result.setHasFinalLinearSystemWalkthrough(
                        determinantStep >= 3 && inverseCurrentStep >= 4 && solveLinearStep >= 0
                    );
                }
                else if ("create-linear-system".equals(action)) {
                    newDeterminantStep = 0;
                    result.setCurrentSection(1);
                }
                else if ("proceed-to-step-two".equals(action)) {
                    newDeterminantStep = 0;
                    result.setCurrentSection(2);
                }
                else if ("next-linear-system-step".equals(action)) {
                    newDeterminantStep++;
                    result.setCurrentSection(2);
                }
                else if ("previous-linear-system-step".equals(action)) {
                    newDeterminantStep--;
                    result.setCurrentSection(2);
                }     
           
                if ("start-inverse-walkthrough".equals(action)) {
                    newDeterminantStep = 3;
                    inverseCurrentStep = 0;
                    result.setHasInverseWalkthrough(true);
                    result.setCurrentSection(3);
                }

                if ("next-linear-inverse-step".equals(action)) {
                    newDeterminantStep = 3;
                    inverseCurrentStep++;
                    result.setHasInverseWalkthrough(true);
                    result.setCurrentSection(3);
                }

                if ("previous-linear-inverse-step".equals(action)) {
                    newDeterminantStep = 3;
                    inverseCurrentStep--;
                    result.setHasInverseWalkthrough(true);
                    result.setCurrentSection(3);
                }           

                if ("start-final-linear-system-walkthrough".equals(action)) {
                    newDeterminantStep = 3;
                    inverseCurrentStep = 4;
                    solveLinearStep = 0;
                    result.setHasInverseWalkthrough(true);
                    result.setHasFinalLinearSystemWalkthrough(true);
                    result.setCurrentSection(4);
                }
                if ("next-final-linear-system-step".equals(action)) {
                    newDeterminantStep = 3;
                    inverseCurrentStep = 4;
                    solveLinearStep++;
                    result.setHasInverseWalkthrough(true);
                    result.setHasFinalLinearSystemWalkthrough(true);
                    result.setCurrentSection(4);
                }
                if ("previous-final-linear-system-step".equals(action)) {
                    newDeterminantStep = 3;
                    inverseCurrentStep = 4;
                    solveLinearStep--;
                    result.setHasInverseWalkthrough(true);
                    result.setHasFinalLinearSystemWalkthrough(true);
                    result.setCurrentSection(4);
                }
                if ("back-to-start".equals(action)) {
                    result.setHasInitialValues(true);
                    result.setHasMatrixValues(false);
                    newDeterminantStep = 0;
                    inverseCurrentStep = 0;
                    solveLinearStep = 0;
                    result.setCurrentSection(0);
                }
                if ("back-to-section-one".equals(action)) {
                    result.setHasInitialValues(true);
                    result.setHasMatrixValues(false);

                    newDeterminantStep = 0;
                    inverseCurrentStep = 0;
                    solveLinearStep = 0;

                    result.setHasInverseWalkthrough(false);
                    result.setHasFinalLinearSystemWalkthrough(false);
                    result.setCurrentSection(1);
                }
                if ("back-to-section-two".equals(action)) {
                    result.setHasInitialValues(false);
                    result.setHasMatrixValues(true);

                    newDeterminantStep = 3;
                    inverseCurrentStep = 0;
                    solveLinearStep = 0;

                    result.setHasInverseWalkthrough(false);
                    result.setHasFinalLinearSystemWalkthrough(false);
                    result.setCurrentSection(2);
                }
                if ("back-to-section-three".equals(action)) {
                    result.setHasInitialValues(false);
                    result.setHasMatrixValues(true);

                    newDeterminantStep = 3;
                    inverseCurrentStep = 4;
                    solveLinearStep = 0;
                    result.setCurrentSection(3);

                    result.setHasInverseWalkthrough(true);
                    result.setHasFinalLinearSystemWalkthrough(false);
                }
                if ("restart-linear-system-walkthrough".equals(action)) {
                    result.setHasInitialValues(false);
                    result.setHasMatrixValues(false);

                    newDeterminantStep = 0;
                    inverseCurrentStep = 0;
                    solveLinearStep = 0;
                    result.setHasInverseWalkthrough(false);
                    result.setHasFinalLinearSystemWalkthrough(false);
                    result.setCurrentSection(0);
                }                
                              
            newDeterminantStep = Math.max(0, Math.min(newDeterminantStep, 3));            
            inverseCurrentStep = Math.max(0, Math.min(inverseCurrentStep, 4));
            solveLinearStep = Math.max(0, Math.min(solveLinearStep, 8));

            // Store the final values
            result.setDeterminantStep(newDeterminantStep);
            result.setInverseCurrentStep(inverseCurrentStep);
            result.setSolveLinearStep(solveLinearStep);
            result.setHasInverseWalkthrough(newDeterminantStep >= 3);
            result.setHasFinalLinearSystemWalkthrough(
                    newDeterminantStep >= 3                    
                    && inverseCurrentStep >= 4
                    && solveLinearStep >= 0
            );
  
        double[][] coefficientMatrix = result.getCoefficientMatrix();

        double[][] inverseWalkthroughMatrix =
                matrixTwoByTwoService.buildInverseWalkthroughMatrix(coefficientMatrix, inverseCurrentStep);               

        double[][] finalInverseMatrix =
                matrixTwoByTwoService.calculateInverseTwoByTwo(
                        coefficientMatrix
                );

        solveLinearSystemsService.calculateFinalSolutionSteps(result, finalInverseMatrix);
        //solveLinearStep = Math.max(0, Math.min(solveLinearStep, 7));
        
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
       
        model.addAttribute("result", result);

        return "inverseMatrixMethod";
    }
}