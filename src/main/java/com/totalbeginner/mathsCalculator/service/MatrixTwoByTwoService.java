package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;

@Service
public class MatrixTwoByTwoService {
    public double[][] buildTwoByTwoMatrix(double[][] matrix) {
        if (matrix == null || matrix.length != 2 || matrix[0].length != 2) {
            throw new IllegalArgumentException("2 × 2 determinant requires a 2 × 2 matrix.");
        }
        double[][] result = new double[2][2];
        for (int i = 0; i < 2; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, 2);
        }
        return result;
    }
    public double calculatePositiveDiagonal(double[][] matrix) {
        return matrix[0][0] * matrix[1][1];
    }
    public double calculateNegativeDiagonal(double[][] matrix) {
        return matrix[0][1] * matrix[1][0];
    }
    public double calculateDeterminant(double positiveDiagonal, double negativeDiagonal) {
        return positiveDiagonal - negativeDiagonal;
    }
    public double determinantTwoByTwo(double[][] matrix) {
        return calculateDeterminant(
                calculatePositiveDiagonal(matrix),
                calculateNegativeDiagonal(matrix)
        );
    }    
    public double[][] buildInverseWalkthroughMatrix(double[][] matrix, int step) {
        double a = matrix[0][0];
        double b = matrix[0][1];
        double c = matrix[1][0];
        double d = matrix[1][1];

        if (step >= 1) {
            return new double[][]{
                    {d, -b},
                    {-c, a}
            };
        }

        return new double[][]{
                {a, b},
                {c, d}
        };
    }
    public double[][] calculateInverseTwoByTwo(double[][] matrix) {
        double det = determinantTwoByTwo(matrix);

        if (det == 0) {
            return new double[2][2];
        }

        return new double[][]{
                {matrix[1][1] / det, -matrix[0][1] / det},
                {-matrix[1][0] / det, matrix[0][0] / det}
        };
    }
}