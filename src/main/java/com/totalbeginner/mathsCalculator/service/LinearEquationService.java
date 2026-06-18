package com.totalbeginner.mathsCalculator.service;

import com.totalbeginner.mathsCalculator.dto.LinearEquationResult;

public class LinearEquationService {
    public LinearEquationResult solveByElimination(
        double a,
        double b,
        double c,
        double d,
        double e,
        double f) {
        LinearEquationResult result = new LinearEquationResult();
        // Step 1: Eliminate y
        double stepOne = a * d - b * c; // Coefficient of x after elimination
        double stepTwo = a * e - b * f; // Result of elimination for x
        double stepThree = c * e - d * f; // Result of elimination for y

        result.setStepOne(String.format("Eliminate y: (%.2f * %.2f) - (%.2f * %.2f) = %.2f", a, d, b, c, stepOne));
        result.setStepTwo(String.format("Eliminate y: (%.2f * %.2f) - (%.2f * %.2f) = %.2f", a, e, b, f, stepTwo));
        result.setStepThree(String.format("Eliminate y: (%.2f * %.2f) - (%.2f * %.2f) = %.2f", c, e, d, f, stepThree));
        return result;
    }

}

