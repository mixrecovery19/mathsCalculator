package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.dto.MatrixSarrusResult;
import com.totalbeginner.mathsCalculator.service.MatrixSarrusService;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
        @Controller
        public class MatrixSarrusController {
        private final MatrixSarrusService matrixSarrusService;
        private final MatrixInverse3x3Controller matrixInverse3x3Controller;
        public MatrixSarrusController(MatrixSarrusService matrixSarrusService, MatrixInverse3x3Controller matrixInverse3x3Controller) {        
                this.matrixSarrusService = matrixSarrusService;
                this.matrixInverse3x3Controller = matrixInverse3x3Controller;
        }

        @GetMapping("/matrix-sarrus")
                public String matrixSarrusPage(Model model) {

                        MatrixSarrusResult result = new MatrixSarrusResult();

                        result.setCurrentSarrusSection(0);
                        result.setCurrentStepSarrusSectionTwo(0);
                        result.setDisplayMode("decimal");

                        model.addAttribute("size", 3);
                        model.addAttribute("matrixSarrus", new double[3][3]);
                        model.addAttribute("hasMatrixValues", false);
                        model.addAttribute("result", result);

                        return "matrixSarrus";
                }

        @PostMapping("/matrices-sarrus")
        public String handleSarrusAction(
                @RequestParam int size,
                @RequestParam Map<String, String> params,
                @RequestParam(required = false) String action,
                @RequestParam(defaultValue = "0")       
                int currentSarrusSection,
                @RequestParam(defaultValue = "0")
                int currentStepSarrusSectionTwo,
                @RequestParam(defaultValue = "0")
                int currentStepSarrusSectionThree,
                @RequestParam(defaultValue = "decimal")
                String displayMode,                
                    Model model
        ) {
        MatrixSarrusResult result = new MatrixSarrusResult();

        double[][] matrix = matrixSarrusService.buildMatrix(size, params);

        result.setOriginalMatrix(matrix);
        result.setHasMatrixValues(
                matrixSarrusService.hasMatrixValues(matrix)
        );
        // Section logic
        int newSarrusSection = currentSarrusSection;
        int newStep = currentStepSarrusSectionTwo;
        if ("restart-3x3-inverse".equals(action)) {
        newSarrusSection = 3;
        currentStepSarrusSectionThree = 0;
        }

        if ("display-decimal".equals(action)) {
        displayMode = "decimal";
        }

        if ("display-fraction".equals(action)) {
        displayMode = "fraction";
        }

        if ("update-matrix-sarrus".equals(action)) {
        newSarrusSection = 1;
        }

        if ("generate-sarrus".equals(action)) {
        newSarrusSection = 2;
        newStep = 0;
        }
        if ("close-section-two-sarrus".equals(action)) {
        newSarrusSection = 1;
        }
        if ("close-section-three-sarrus".equals(action)) {
        newSarrusSection = 2;
        currentStepSarrusSectionThree = 0;
        }

        if ("continue-to-inverse".equals(action)) {
        newSarrusSection = 3;
        currentStepSarrusSectionThree = 0;
        }

        // Section 2 Next / Previous
        if ("generate-sarrus".equals(action)) {
        newStep = 0;
        } else if ("next-sarrus-step".equals(action)) {
        newStep++;
        } else if ("previous-sarrus-step".equals(action)) {
        newStep--;
        }
        
        if ("restart-sarrus-walkthrough".equals(action)) {
        return "redirect:/matrix-sarrus";
        }
        if ("next-3x3-inverse-step".equals(action)) {
        currentStepSarrusSectionThree++;
        } else if ("previous-3x3-inverse-step".equals(action)) {
        currentStepSarrusSectionThree--;
        }

        currentStepSarrusSectionThree =
                Math.max(0, Math.min(currentStepSarrusSectionThree, 32));

        // Clamp Sarrus steps
        newStep = Math.max(0, Math.min(newStep, 9));

        if (newSarrusSection >= 2) {
        result = matrixSarrusService.buildSarrusResult(matrix, newStep);       
        }
        if (newSarrusSection >= 3) {
        matrixInverse3x3Controller.loadInverseSection(model, matrix, currentStepSarrusSectionThree);        
        }
        boolean hasMatrixValues = matrixSarrusService.hasMatrixValues(matrix);
                
        model.addAttribute("hasMatrixValues", hasMatrixValues);        
        model.addAttribute("currentSarrusSectionTwoStep", newStep);          
        model.addAttribute("size", size);        
        model.addAttribute("matrixSarrus", matrix);
        
        result.setCurrentSarrusSection(newSarrusSection);
        result.setCurrentStepSarrusSectionTwo(newStep);
        result.setCurrentStepSarrusSectionThree(currentStepSarrusSectionThree);
        result.setDisplayMode(displayMode);

        model.addAttribute("result", result);

        return "matrixSarrus";                
        }
}