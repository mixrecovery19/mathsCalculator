package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.model.MatrixTwoByTwoForm;
import com.totalbeginner.mathsCalculator.service.MatrixRequestParser;
import com.totalbeginner.mathsCalculator.service.MatrixTwoByTwoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

        @Controller
        public class MatrixTwoByTwoController {
        private final MatrixTwoByTwoService matrixTwoByTwoService;
        private final MatrixRequestParser matrixRequestParser;

        public MatrixTwoByTwoController(
                MatrixTwoByTwoService matrixTwoByTwoService,
                MatrixRequestParser matrixRequestParser
        ) {
                this.matrixTwoByTwoService = matrixTwoByTwoService;
                this.matrixRequestParser = matrixRequestParser;
        }

        @GetMapping("/matrix-two-by-two")
        public String matrixTwoByTwoPage(Model model) {

                model.addAttribute("size", 2);
                model.addAttribute("matrixTwoByTwo", new double[2][2]);
                model.addAttribute("hasMatrixValues", false);
                model.addAttribute("hasWalkthrough", false);
                model.addAttribute("hasInverseWalkthrough", false);
                model.addAttribute("currentStep", 0);
                model.addAttribute("inverseCurrentStep", 0);

                return "matrixTwoByTwo";
        }

        @PostMapping("/matrix-two-by-two")
        public String handleTwoByTwo(@RequestParam Map<String, String> params, MatrixTwoByTwoForm form, Model model) {    

                double[][] matrixTwoByTwo =
                        matrixRequestParser.buildMatrix(
                                2,
                                params,
                                "cell_"
                        );

        String action = form.getAction();
        int currentStep = form.getCurrentStep();
        int inverseCurrentStep = form.getInverseCurrentStep();

        boolean hasWalkthrough = false;
        boolean hasInverseWalkthrough = false;

        double positiveDiagonal =
        matrixTwoByTwoService
                .calculatePositiveDiagonal(
                        matrixTwoByTwo
                );

        double negativeDiagonal =
                matrixTwoByTwoService
                        .calculateNegativeDiagonal(
                                matrixTwoByTwo
                        );

        double determinant =
                matrixTwoByTwoService
                        .determinantTwoByTwo(
                                matrixTwoByTwo
                        );       

        double[][] inverseWalkthroughMatrix =
        matrixTwoByTwoService
                .buildInverseWalkthroughMatrix(
                        matrixTwoByTwo,
                        inverseCurrentStep
                );

        double[][] finalInverseMatrix =
        matrixTwoByTwoService
                .calculateInverseTwoByTwo(
                        matrixTwoByTwo
                );      

        if ("update-matrix-two-by-two".equals(action)) {
            currentStep = 0;
            inverseCurrentStep = 0;
            hasWalkthrough = false;
            hasInverseWalkthrough = false;
        }

        if ("generate-two-by-two".equals(action)) {
            currentStep = 0;
            hasWalkthrough = true;
        }

        if ("next-two-by-two-step".equals(action)) {
            currentStep = Math.min(currentStep + 1, 3);
            hasWalkthrough = true;
        }

        if ("previous-two-by-two-step".equals(action)) {
            currentStep = Math.max(currentStep - 1, 0);
            hasWalkthrough = true;
        }

        if ("start-inverse-walkthrough".equals(action)) {
            currentStep = 3;
            inverseCurrentStep = 0;
            hasWalkthrough = true;
            hasInverseWalkthrough = true;
        }

        if ("next-inverse-step".equals(action)) {
            inverseCurrentStep = Math.min(inverseCurrentStep + 1, 4);
            currentStep = 3;
            hasWalkthrough = true;
            hasInverseWalkthrough = true;
        }

        if ("previous-inverse-step".equals(action)) {
            inverseCurrentStep = Math.max(inverseCurrentStep - 1, 0);
            currentStep = 3;
            hasWalkthrough = true;
            hasInverseWalkthrough = true;
        }

        model.addAttribute("size", 2);
        model.addAttribute("matrixTwoByTwo", matrixTwoByTwo);

        model.addAttribute(
                "walkthroughTwoByTwoMatrix",
                matrixTwoByTwoService.buildTwoByTwoMatrix(matrixTwoByTwo)
        );

        model.addAttribute("hasWalkthrough", hasWalkthrough);
        model.addAttribute("currentStep", currentStep);

        model.addAttribute(
                "positiveStep1",
                currentStep >= 1 ? positiveDiagonal : ""
        );

        model.addAttribute(
                "negativeStep1",
                currentStep >= 2 ? negativeDiagonal : ""
        );

        model.addAttribute(
                "determinant",
                currentStep >= 3 ? determinant : ""
        );

        model.addAttribute("hasInverseWalkthrough", hasInverseWalkthrough);
        model.addAttribute("inverseCurrentStep", inverseCurrentStep);
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
                inverseCurrentStep >= 3 ? determinant : ""
        );

        model.addAttribute(
        "finalInverseMatrix",
        inverseCurrentStep >= 4
                ? finalInverseMatrix
                : new double[2][2]
        );

        return "matrixTwoByTwo";
    }
}