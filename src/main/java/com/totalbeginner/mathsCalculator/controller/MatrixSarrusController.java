package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.service.MatrixSarrusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
        @Controller
        public class MatrixSarrusController {

        private final MatrixSarrusService matrixSarrusService;

        public MatrixSarrusController(MatrixSarrusService matrixSarrusService) {        
                this.matrixSarrusService = matrixSarrusService;
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

        return "matrixSarrus";
        }

        @PostMapping("/matrices-sarrus")
        public String handleSarrusAction(
                @RequestParam int size,
                @RequestParam Map<String, String> params,
                @RequestParam(required = false) String action,
                @RequestParam(defaultValue = "0")
                int currentStep,
                Model model
        ) {

        double[][] matrix = buildMatrix(size, params);
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
        // Prevent going too far
        newStep = Math.max(0, Math.min(newStep, 9));    
        boolean hasMatrixValues = false;

        for (double[] row : matrix) {
        for (double value : row) {
                if (value != 0) {
                hasMatrixValues = true;
                break;
                }
        }
        }               
                
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
                "previous-sarrus-step"
                        .equals(action)) {

        double[][] sarrusMatrix =
                matrixSarrusService
                        .buildSarrusMatrix(
                                matrix
                        );

        double determinant =
                matrixSarrusService
                        .determinantSarrus(
                                sarrusMatrix
                        );
        Double positiveStep1 = null;
        Double positiveStep2 = null;
        Double positiveStep3 = null;
        Double positiveTotal = null;
        Double negativeStep1 = null;
        Double negativeStep2 = null;
        Double negativeStep3 = null;
        Double negativeTotal = null;
        Double finalDeterminant = null;

        // Step 1
        if (newStep >= 1) {
                positiveStep1 =
                        matrixSarrusService
                                .calculatePositiveStep1(
                                        sarrusMatrix
                                );
                }

        // Step 2
        if (newStep >= 2) {

        positiveStep2 =
                matrixSarrusService
                        .calculatePositiveStep2(
                                sarrusMatrix
                        );
        }
        // Step 3
        if (newStep >= 3) {
                positiveStep3 =
                        matrixSarrusService
                                .calculatePositiveStep3(
                                        sarrusMatrix
                                );
        }
        if (newStep >= 4) {
                negativeStep1 =
                        matrixSarrusService
                                .calculateNegativeStep1(
                                        sarrusMatrix
                                );
        }
        if (newStep >= 5) {
                negativeStep2 =
                        matrixSarrusService
                                .calculateNegativeStep2(
                                        sarrusMatrix
                                );
        }
        if (newStep >= 6) {
                negativeStep3 =
                        matrixSarrusService
                                .calculateNegativeStep3(
                                        sarrusMatrix
                                );
        }
        if (newStep >= 7 && positiveStep1 != null && positiveStep2 != null && positiveStep3 != null) {
                positiveTotal =
                        matrixSarrusService
                                .calculatePositiveTotal(
                                        positiveStep1,
                                        positiveStep2,
                                        positiveStep3
                                );
        }
        if (newStep >= 8 && negativeStep1 != null && negativeStep2 != null && negativeStep3 != null) {
                negativeTotal =
                        matrixSarrusService                                
                                .calculateNegativeTotal(
                                        negativeStep1,
                                        negativeStep2,
                                        negativeStep3
                                );
                        }
        if (newStep >= 9 && positiveTotal != null && negativeTotal != null) {
                finalDeterminant =
                        matrixSarrusService                                
                        .calculateDeterminant(
                                        positiveTotal,
                                        negativeTotal
                                );
                        }
        model.addAttribute(
                "sarrusMatrix",
                sarrusMatrix
        );
        model.addAttribute(
                "determinant",
                determinant
        );
        model.addAttribute(
                "positiveStep1",
                positiveStep1
        );
        model.addAttribute(
                "positiveStep2",
                positiveStep2
        );
        model.addAttribute(
                "positiveStep3",
                positiveStep3
        );
        model.addAttribute("positiveTotal", positiveTotal);
        model.addAttribute("negativeStep1", negativeStep1);
        model.addAttribute("negativeStep2", negativeStep2);
        model.addAttribute("negativeStep3", negativeStep3);
        model.addAttribute("negativeTotal", negativeTotal);
        model.addAttribute("determinant", finalDeterminant);
    }
    return "matrixSarrus";
}

    private double[][] buildMatrix(int size, Map<String, String> params) {    

        double[][] matrix = new double[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                String key = "cell_" + row + "_" + col;
                String value = params.get(key);

                matrix[row][col] = (value == null || value.isBlank())                        
                        ? 0
                        : Double.parseDouble(value);                        
            }
        }
        return matrix;
    }
}