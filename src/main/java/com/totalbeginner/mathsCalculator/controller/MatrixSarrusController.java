package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.dto.MatrixSarrusResult;
import com.totalbeginner.mathsCalculator.service.MatrixSarrusService;
import com.totalbeginner.mathsCalculator.service.MatrixInverse3x3Service;
import com.totalbeginner.mathsCalculator.dto.MatrixInverse3x3Result;
import com.totalbeginner.mathsCalculator.controller.MatrixInverse3x3Controller;
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

        model.addAttribute(
                "size",
                3
        );

        model.addAttribute(
                "matrixSarrus",
                new double[3][3]
        );

        model.addAttribute(
                "hasMatrixValues",
                false
        );
        model.addAttribute("result", new MatrixSarrusResult());

        return "matrixSarrus";
        }

        @PostMapping("/matrices-sarrus")
        public String handleSarrusAction(
                @RequestParam int size,
                @RequestParam Map<String, String> params,
                @RequestParam(required = false) String action,
                @RequestParam(defaultValue = "0")
                int currentStep,
                @RequestParam(defaultValue = "0")
                int inverseCurrentStep,
                Model model
        ) {

        double[][] matrix = matrixSarrusService.buildMatrix(size, params);
        int newStep = currentStep;
                if ("generate-sarrus".equals(action)) {
                        newStep = 0; // Reset to first step when generating a new matrix
                } else if ("next-sarrus-step".equals(action)) {
                        newStep++;
                } else if (
                        "previous-sarrus-step"
                                .equals(action)
                ) {
                        newStep--;
                }
                if ("next-3x3-inverse-step".equals(action)) {
                        inverseCurrentStep++;
                } else if ("previous-3x3-inverse-step".equals(action)) {
                        inverseCurrentStep--;
                        }
                        inverseCurrentStep =
                                Math.max(
                                        0,
                                        Math.min(inverseCurrentStep, 20)
                                );
        // Prevent going too far
        newStep = Math.max(0, Math.min(newStep, 9));    
        boolean hasMatrixValues = matrixSarrusService.hasMatrixValues(matrix);
                
        model.addAttribute(
                "hasMatrixValues",
                hasMatrixValues
        );
        model.addAttribute(
                "currentStep",
                newStep
        );   

        model.addAttribute(
                "size",
                size
        );

        model.addAttribute(
        "matrixSarrus",
        matrix
        );

       if ("generate-sarrus".equals(action)
        ||
        "next-sarrus-step".equals(action)
        ||
        "previous-sarrus-step".equals(action)
        ||
        "continue-to-inverse".equals(action)
        ||
        "next-3x3-inverse-step".equals(action)
        ||
        "previous-3x3-inverse-step".equals(action)) {

    MatrixSarrusResult result =
            matrixSarrusService.buildSarrusResult(
                    matrix,
                    newStep
            );

    if ("continue-to-inverse".equals(action)
            ||
            "next-3x3-inverse-step".equals(action)
            ||
            "previous-3x3-inverse-step".equals(action)) {

        result.setShowInverseSection(true);

        matrixInverse3x3Controller.loadInverseSection(
                model,
                matrix,
                inverseCurrentStep
        );
    }

    model.addAttribute(
            "result",
            result
    );
}

        return "matrixSarrus";
                
        }
}