package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.service.MatrixTwoByTwoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
        @Controller
        public class MatrixTwoByTwoController {
        private final MatrixTwoByTwoService matrixTwoByTwoService;

        public MatrixTwoByTwoController(MatrixTwoByTwoService matrixTwoByTwoService) {        
                this.matrixTwoByTwoService = matrixTwoByTwoService;
        }

       @GetMapping("/matrix-two-by-two")
        public String matrixTwoByTwoPage(Model model) {

        model.addAttribute(
                "size",
                2
        );

        model.addAttribute(
                "matrixTwoByTwo",
                new double[2][2]
        );

        model.addAttribute(
                "hasMatrixValues",
                false
        );

        return "matrixTwoByTwo";
        }

        @PostMapping("/matrix-two-by-two")
        public String handleTwoByTwo(
                @RequestParam Map<String, String> params,
                Model model
        ) {

        double[][] matrixTwoByTwo = new double[2][2];

        for (int row = 0; row < 2; row++) {
                for (int col = 0; col < 2; col++) {

                String key = "cell_" + row + "_" + col;
                String value = params.get(key);

                matrixTwoByTwo[row][col] =
                        value == null || value.isBlank()
                                ? 0
                                : Double.parseDouble(value);
                }
        }

        String action = params.getOrDefault("action", "");

        String currentStepParam = params.get("currentStep");

        int currentStep =
                currentStepParam == null || currentStepParam.isBlank()
                        ? 0
                        : Integer.parseInt(currentStepParam);
        
        boolean hasWalkthrough = false;

        double positiveDiagonal =
                matrixTwoByTwoService.calculatePositiveDiagonal(matrixTwoByTwo);

        double negativeDiagonal =
                matrixTwoByTwoService.calculateNegativeDiagonal(matrixTwoByTwo);

        double determinant =
                matrixTwoByTwoService.calculateDeterminant(
                        positiveDiagonal,
                        negativeDiagonal
                );

        if (action.equals("update-matrix-two-by-two")) {
                currentStep = 0;
                hasWalkthrough = false;
        }

       if (action.equals("generate-two-by-two")) {

        currentStep = 0;
        hasWalkthrough = true;

        model.addAttribute("positiveStep1", "");
        model.addAttribute("negativeStep1", "");
        model.addAttribute("determinant", "");
}

        if (action.equals("next-two-by-two-step")) {
                currentStep = Math.min(currentStep + 1, 3);
                hasWalkthrough = true;
        }

        if (action.equals("previous-two-by-two-step")) {
                currentStep = Math.max(currentStep - 1, 0);
                hasWalkthrough = true;
        }

        model.addAttribute("size", 2);
        model.addAttribute(
        "matrixTwoByTwo",
        matrixTwoByTwo
);

model.addAttribute(
        "walkthroughTwoByTwoMatrix",
        matrixTwoByTwoService
                .buildTwoByTwoMatrix(
                        matrixTwoByTwo
                )
);
        
        model.addAttribute("hasWalkthrough", hasWalkthrough);
        model.addAttribute("currentStep", currentStep);

        model.addAttribute(
        "positiveStep1",
        currentStep >= 1
                ? positiveDiagonal
                : ""
        );

model.addAttribute(
        "negativeStep1",
        currentStep >= 2
                ? negativeDiagonal
                : ""
);

model.addAttribute(
        "determinant",
        currentStep >= 3
                ? determinant
                : ""
);

        return "matrixTwoByTwo";
        }
}