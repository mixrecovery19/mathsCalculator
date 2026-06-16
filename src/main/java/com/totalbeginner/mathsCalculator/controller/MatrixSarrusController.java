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
        "previous-sarrus-step".equals(action)) {

    MatrixSarrusResult result =
            matrixSarrusService
                    .buildSarrusResult(
                            matrix,
                            newStep
                    );

                model.addAttribute(
                        "result",
                        result
                );
        }

        return "matrixSarrus";
                
        }
}