package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;
import com.totalbeginner.mathsCalculator.dto.MatrixSarrusResult;
import java.util.Map;

@Service
public class MatrixSarrusService {
      public MatrixSarrusResult buildSarrusResult(double[][] matrix, int currentStep) {

        MatrixSarrusResult result = new MatrixSarrusResult();

        double[][] sarrusMatrix = buildSarrusMatrix(matrix);

    result.setOriginalMatrix(matrix);
    result.setSarrusMatrix(sarrusMatrix);
    result.setCurrentStep(currentStep);    
    result.setHasMatrixValues(hasMatrixValues(matrix));

    if (currentStep >= 1) {
        result.setPositiveStep1(calculatePositiveStep1(sarrusMatrix));
    }

    if (currentStep >= 2) {
        result.setPositiveStep2(calculatePositiveStep2(sarrusMatrix));
    }

    if (currentStep >= 3) {
        result.setPositiveStep3(calculatePositiveStep3(sarrusMatrix));
    }

    if (currentStep >= 4) {
        result.setNegativeStep1(calculateNegativeStep1(sarrusMatrix));
    }

    if (currentStep >= 5) {
        result.setNegativeStep2(calculateNegativeStep2(sarrusMatrix));
    }

    if (currentStep >= 6) {
        result.setNegativeStep3(calculateNegativeStep3(sarrusMatrix));
    }

    if (currentStep >= 7) {
        result.setPositiveTotal(
                calculatePositiveTotal(
                        result.getPositiveStep1(),
                        result.getPositiveStep2(),
                        result.getPositiveStep3()
                )
        );
    }

    if (currentStep >= 8) {
        result.setNegativeTotal(
                calculateNegativeTotal(
                        result.getNegativeStep1(),
                        result.getNegativeStep2(),
                        result.getNegativeStep3()
                )
        );
    }

    if (currentStep >= 9) {
        result.setDeterminant(
                calculateDeterminant(
                        result.getPositiveTotal(),
                        result.getNegativeTotal()
                )
        );
    }

    return result;
}

        public double determinantSarrus(double[][] matrix) {

                double positiveDiagonalSum =
                        matrix[0][0] * matrix[1][1] * matrix[2][2]
                        +
                        matrix[0][1] * matrix[1][2] * matrix[2][3]
                        +
                        matrix[0][2] * matrix[1][3] * matrix[2][4];

                double negativeDiagonalSum =
                        matrix[0][2] * matrix[1][1] * matrix[2][0]
                        +
                        matrix[0][3] * matrix[1][2] * matrix[2][1]
                        +
                        matrix[0][4] * matrix[1][3] * matrix[2][2];

                return positiveDiagonalSum - negativeDiagonalSum;
        }

        public double calculatePositiveStep1(double[][] matrix) {
                return
                        matrix[0][0]
                        *
                        matrix[1][1]
                        *
                        matrix[2][2];
                }

        public double calculatePositiveStep2(double[][] matrix) {
                return
                        matrix[0][1]
                        *
                        matrix[1][2]
                        *
                        matrix[2][3];
                }
        public double calculatePositiveStep3(double[][] matrix) {        

                return
                        matrix[0][2]
                        *
                        matrix[1][3]
                        *
                        matrix[2][4];
        }

        public double calculateNegativeStep1(double[][] matrix) {        

                return
                        matrix[0][2]
                        *
                        matrix[1][1]
                        *
                        matrix[2][0];
        }

        public double calculateNegativeStep2(
                double[][] matrix
        ) {

                return
                        matrix[0][3]
                        *
                        matrix[1][2]
                        *
                        matrix[2][1];
        }

        public double calculateNegativeStep3(
                double[][] matrix
        ) {

                return
                        matrix[0][4]
                        *
                        matrix[1][3]
                        *
                        matrix[2][2];
        }
        public double calculatePositiveTotal(
                double positiveStep1,
                double positiveStep2,
                double positiveStep3
        ) {
                return positiveStep1 + positiveStep2 + positiveStep3;
        }

        public double calculateNegativeTotal(
                double negativeStep1,
                double negativeStep2,
                double negativeStep3
        ) {
                return negativeStep1 + negativeStep2 + negativeStep3;
        }
        public double calculateDeterminant(
                double positiveTotal,
                double negativeTotal
        ) {
                return positiveTotal - negativeTotal;
        }
        public double[][] buildMatrix(int size, Map<String, String> params) {    

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
        public boolean hasMatrixValues(double[][] matrix) {
                for (double[] row : matrix) {
                        for (double value : row) {
                        if (value != 0) {
                                return true;
                        }
                        }
                }
                return false;
                }
                public double[][] buildSarrusMatrix(double[][] matrix) {

    if (matrix.length != 3 || matrix[0].length != 3) {

        throw new IllegalArgumentException(
                "Sarrus method requires a 3 × 3 matrix."
        );
    }

    double[][] sarrus = new double[3][5];

    // Copy original matrix
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {

            sarrus[row][col] =
                    matrix[row][col];
        }
    }

    // Copy first two columns
    for (int row = 0; row < 3; row++) {

        sarrus[row][3] = matrix[row][0];
        sarrus[row][4] = matrix[row][1];
    }

    return sarrus;
}
}
