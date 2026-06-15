package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;

@Service
public class MatrixTwoByTwoService {

    public double[][] buildTwoByTwoMatrix(double[][] matrix) {

        if (matrix.length != 2 || matrix[0].length != 2) {

            throw new IllegalArgumentException(
                    "2 × 2 determinant requires a 2 × 2 matrix."
            );
        }

        double[][] twoByTwo = new double[2][2];

        for (int row = 0; row < 2; row++) {

            for (int col = 0; col < 2; col++) {

                twoByTwo[row][col] = matrix[row][col];
            }
        }

        return twoByTwo;
    }

    /**
     * Step 1
     * Main diagonal (green)
     * a × d
     */
    public double calculatePositiveDiagonal(
            double[][] matrix
    ) {

        return
                matrix[0][0]
                *
                matrix[1][1];
    }

    /**
     * Step 2
     * Secondary diagonal (red)
     * b × c
     */
    public double calculateNegativeDiagonal(
            double[][] matrix
    ) {

        return
                matrix[0][1]
                *
                matrix[1][0];
    }

    /**
     * Final determinant
     * (a × d) - (b × c)
     */
    public double calculateDeterminant(
            double positiveDiagonal,
            double negativeDiagonal
    ) {

        return
                positiveDiagonal
                -
                negativeDiagonal;
    }

    /**
     * Convenience method
     */
    public double determinantTwoByTwo(
            double[][] matrix
    ) {

        double positiveDiagonal =
                calculatePositiveDiagonal(matrix);

        double negativeDiagonal =
                calculateNegativeDiagonal(matrix);

        return calculateDeterminant(
                positiveDiagonal,
                negativeDiagonal
        );
    }
    public double[][] buildInverseWalkthroughMatrix(
        double[][] matrix,
        int inverseCurrentStep
) {

    double a = matrix[0][0];
    double b = matrix[0][1];
    double c = matrix[1][0];
    double d = matrix[1][1];

    double[][] walkthrough =
            new double[2][2];

    walkthrough[0][0] = a;
    walkthrough[0][1] = b;
    walkthrough[1][0] = c;
    walkthrough[1][1] = d;

    if (inverseCurrentStep >= 1) {

        walkthrough[0][0] = d;
        walkthrough[0][1] = -b;
        walkthrough[1][0] = -c;
        walkthrough[1][1] = a;
    }

    return walkthrough;
}
   public double[][] calculateInverseTwoByTwo(
        double[][] matrix
) {

    double determinant =
            determinantTwoByTwo(matrix);

    if (determinant == 0) {
        return new double[2][2];
    }

    double[][] inverse =
            new double[2][2];

    inverse[0][0] =
            matrix[1][1] / determinant;

    inverse[0][1] =
            -matrix[0][1] / determinant;

    inverse[1][0] =
            -matrix[1][0] / determinant;

    inverse[1][1] =
            matrix[0][0] / determinant;

    return inverse;
}
    
}