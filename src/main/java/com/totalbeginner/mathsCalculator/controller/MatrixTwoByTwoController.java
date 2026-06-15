package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.service.MatrixTwoByTwoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MatrixTwoByTwoController {

    private final MatrixTwoByTwoService matrixTwoByTwoService;

    public MatrixTwoByTwoController(
            MatrixTwoByTwoService matrixTwoByTwoService
    ) {

        this.matrixTwoByTwoService =
                matrixTwoByTwoService;
    }

    @GetMapping("/matrix-two-by-two")
    public String matrixTwoByTwoPage(
            Model model
    ) {

        model.addAttribute("size", 2);
        model.addAttribute(
                "matrixTwoByTwo",
                new double[2][2]
        );

        model.addAttribute(
                "hasMatrixValues",
                false
        );

        model.addAttribute(
                "hasWalkthrough",
                false
        );

        model.addAttribute(
                "hasInverseWalkthrough",
                false
        );

        return "matrixTwoByTwo";
    }

    @PostMapping("/matrix-two-by-two")
    public String handleTwoByTwo(

            @RequestParam
            Map<String, String> params,

            Model model
    ) {

        // ==========================
        // BUILD MATRIX
        // ==========================

        double[][] matrixTwoByTwo =
                new double[2][2];

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                String key =
                        "cell_"
                        + row
                        + "_"
                        + col;

                String value =
                        params.get(key);

                matrixTwoByTwo[row][col] =
                        value == null
                        || value.isBlank()
                        ? 0
                        : Double.parseDouble(value);
            }
        }

        // ==========================
        // REQUEST PARAMS
        // ==========================

        String action =
                params.getOrDefault(
                        "action",
                        ""
                );

        int currentStep =
                params.get("currentStep") == null
                || params.get("currentStep").isBlank()
                ? 0
                : Integer.parseInt(
                        params.get("currentStep")
                );

        int inverseCurrentStep =
                params.get(
                        "inverseCurrentStep"
                ) == null
                || params.get(
                        "inverseCurrentStep"
                ).isBlank()
                ? 0
                : Integer.parseInt(
                        params.get(
                                "inverseCurrentStep"
                        )
                );

        boolean hasWalkthrough =
                false;

        boolean hasInverseWalkthrough =
                false;

        // ==========================
        // DETERMINANT CALCULATIONS
        // ==========================

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
                        .calculateDeterminant(
                                positiveDiagonal,
                                negativeDiagonal
                        );

        // ==========================
        // MATRIX VALUES
        // ==========================

        double a =
                matrixTwoByTwo[0][0];

        double b =
                matrixTwoByTwo[0][1];

        double c =
                matrixTwoByTwo[1][0];

        double d =
                matrixTwoByTwo[1][1];

        // ==========================
        // INVERSE WALKTHROUGH MATRIX
        // ==========================

        double[][] inverseWalkthroughMatrix =
                new double[2][2];

        // Step 0
        inverseWalkthroughMatrix[0][0] = a;
        inverseWalkthroughMatrix[0][1] = b;
        inverseWalkthroughMatrix[1][0] = c;
        inverseWalkthroughMatrix[1][1] = d;

        // Step 1
        // Swap diagonal values
        if (inverseCurrentStep >= 1) {
                inverseWalkthroughMatrix[0][1] = -b;
            inverseWalkthroughMatrix[1][0] = -c;
            inverseWalkthroughMatrix[0][0] = d;
            inverseWalkthroughMatrix[1][1] = a;
        }

        // Step 2
        // Negate off diagonal values
        if (inverseCurrentStep >= 2) {

            inverseWalkthroughMatrix[0][1] = -b;
            inverseWalkthroughMatrix[1][0] = -c;
        }

        // ==========================
        // FINAL INVERSE MATRIX
        // ==========================

        double[][] finalInverseMatrix =
                new double[2][2];

        if (determinant != 0) {

            double invDet =
                    1.0 / determinant;

            finalInverseMatrix[0][0] =
                    d * invDet;

            finalInverseMatrix[0][1] =
                    -b * invDet;

            finalInverseMatrix[1][0] =
                    -c * invDet;

            finalInverseMatrix[1][1] =
                    a * invDet;
        }

        // ==========================
        // ACTIONS
        // ==========================

        if (action.equals(
                "update-matrix-two-by-two"
        )) {

            currentStep = 0;
            inverseCurrentStep = 0;

            hasWalkthrough = false;
            hasInverseWalkthrough = false;
        }

        if (action.equals(
                "generate-two-by-two"
        )) {

            currentStep = 0;

            hasWalkthrough = true;
        }

        // ==========================
        // DETERMINANT WALKTHROUGH
        // ==========================

        if (action.equals(
                "next-two-by-two-step"
        )) {

            currentStep =
                    Math.min(
                            currentStep + 1,
                            3
                    );

            hasWalkthrough = true;
        }

        if (action.equals(
                "previous-two-by-two-step"
        )) {

            currentStep =
                    Math.max(
                            currentStep - 1,
                            0
                    );

            hasWalkthrough = true;
        }

        // ==========================
        // START INVERSE
        // ==========================

        if (action.equals(
                "start-inverse-walkthrough"
        )) {

            currentStep = 3;
            inverseCurrentStep = 0;

            hasWalkthrough = true;
            hasInverseWalkthrough = true;
        }

        // ==========================
        // INVERSE WALKTHROUGH
        // ==========================

        if (action.equals(
                "next-inverse-step"
        )) {

            inverseCurrentStep =
                    Math.min(
                            inverseCurrentStep + 1,
                            4
                    );

            currentStep = 3;

            hasWalkthrough = true;
            hasInverseWalkthrough = true;
        }

        if (action.equals(
                "previous-inverse-step"
        )) {

            inverseCurrentStep =
                    Math.max(
                            inverseCurrentStep - 1,
                            0
                    );

            currentStep = 3;

            hasWalkthrough = true;
            hasInverseWalkthrough = true;
        }

        // ==========================
        // MODEL
        // ==========================

        model.addAttribute(
                "size",
                2
        );

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

        model.addAttribute(
                "hasWalkthrough",
                hasWalkthrough
        );

        model.addAttribute(
                "currentStep",
                currentStep
        );

        // Determinant steps

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

        // Inverse walkthrough

        model.addAttribute(
                "hasInverseWalkthrough",
                hasInverseWalkthrough
        );

        model.addAttribute(
                "inverseCurrentStep",
                inverseCurrentStep
        );

        model.addAttribute(
                "inverseWalkthroughMatrix",
                inverseWalkthroughMatrix
        );

        model.addAttribute(
                "inverseStep1",
                inverseCurrentStep >= 1
                ? "Swap diagonal values"
                : ""
        );

        model.addAttribute(
                "inverseStep2",
                inverseCurrentStep >= 2
                ? "Negate off-diagonal values"
                : ""
        );

        model.addAttribute(
                "inverseDeterminant",
                inverseCurrentStep >= 3
                ? determinant
                : ""
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