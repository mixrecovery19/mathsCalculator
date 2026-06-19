package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;

import com.totalbeginner.mathsCalculator.dto.SolveLinearSystemsResult;
import com.totalbeginner.mathsCalculator.service.MatrixTwoByTwoService;

import java.util.Map;

@Service
public class SolveLinearSystemsService {

    private final MatrixTwoByTwoService matrixTwoByTwoService;

    public SolveLinearSystemsService(MatrixTwoByTwoService matrixTwoByTwoService) {
        this.matrixTwoByTwoService = matrixTwoByTwoService;
    }

    public SolveLinearSystemsResult solveByElimination(

            double a,
            double b,
            double c,
            double d,
            double e,
            double f) {

        SolveLinearSystemsResult result = new SolveLinearSystemsResult();

        double[][] coefficientMatrix = {
                {a, b},
                {d, e}
        };

        double[] constantsVector = {
                c,
                f
        };

        result.setCoefficientMatrix(coefficientMatrix);
        result.setConstantsVector(constantsVector);
        result.setHasMatrixValues(true);

        // Reuse the existing 2 × 2 determinant service

        double positive =
                matrixTwoByTwoService.calculatePositiveDiagonal(coefficientMatrix);

        double negative =
                matrixTwoByTwoService.calculateNegativeDiagonal(coefficientMatrix);

        double determinant =
                matrixTwoByTwoService.calculateDeterminant(
                        positive,
                        negative);

        result.setPositiveStep1(positive);
        result.setNegativeStep1(negative);
        result.setDeterminant(determinant);

        return result;
    }
}