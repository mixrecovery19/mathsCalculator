package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;

import com.totalbeginner.mathsCalculator.dto.SolveLinearSystemsResult;
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
        double positive = matrixTwoByTwoService.calculatePositiveDiagonal(coefficientMatrix);

        double negative = matrixTwoByTwoService.calculateNegativeDiagonal(coefficientMatrix);

        double determinant = matrixTwoByTwoService.calculateDeterminant(positive, negative);

                result.setPositiveStep1(positive);
                result.setNegativeStep1(negative);
                result.setDeterminant(determinant);

                return result;
        }
        public void calculateFinalSolutionSteps(SolveLinearSystemsResult result, double[][] inverseMatrix) {

                double[] constants = result.getConstantsVector();

                double xStep1 = inverseMatrix[0][0] * constants[0];
                double xStep2 = inverseMatrix[0][1] * constants[1];

                double yStep1 = inverseMatrix[1][0] * constants[0];
                double yStep2 = inverseMatrix[1][1] * constants[1];

                result.setXStep1(xStep1);
                result.setXStep2(xStep2);
                result.setYStep1(yStep1);
                result.setYStep2(yStep2);

                result.setXAnswer(xStep1 + xStep2);
                result.setYAnswer(yStep1 + yStep2);
        }
}