package com.totalbeginner.mathsCalculator.service;

import com.totalbeginner.mathsCalculator.dto.MatrixInverse3x3Result;
import com.totalbeginner.mathsCalculator.controller.MatrixInverse3x3Controller;
import org.springframework.stereotype.Service;

@Service
public class MatrixInverse3x3Service {

    private static final int FIRST_ROW = 0;
    private static final int SECOND_ROW = 1;
    private static final int THIRD_ROW = 2;
    public MatrixInverse3x3Result buildInverseResult(double[][] matrix, int currentStep) {    

    MatrixInverse3x3Result result = new MatrixInverse3x3Result();
        result.setOriginalMatrix(matrix);
        result.setCurrentStep(currentStep);
        result.setInverseStarted(true);

        if (currentStep >= 0) {
            result.setStepTitle("Original Matrix");
            result.setStepDescription(
                    "Begin with the original 3 × 3 matrix."
            );
        }

        if (currentStep >= 1) {
            result.setStepTitle("1: Transpose Matrix");
            result.setStepDescription(
                    "Transpose the matrix by swapping rows and columns."
            );
            result.setTransposedMatrix(transposeMatrix(matrix));
        }

        if (currentStep >= 2) {
            result.setStepTitle("2: Create 5 × 5 Helper Matrix");
            result.setStepDescription(
                    "Extend the transposed matrix by repeating values to create the 5 × 5 helper matrix."
            );
            result.setConvertTo5x5Matrix(convertTo5x5Matrix(result.getTransposedMatrix()));
        }

        if (currentStep >= 3) {
            result.setStepTitle("3: Remove Row 1 and Column 1");
            result.setStepDescription(
                    "Remove the first row and first column to prepare the first inverse cell calculation."
            );
        }

        if (currentStep >= 4) {
            result.setStepTitle("4: Now you should have a 4 x 4 matrix");
            result.setStepDescription(
                    "Using this version of your transposed matrix, you can now calculate through the cells to form your inverse matrix."
            );
            result.setHelper4x4Matrix(create4x4Matrix(result.getConvertTo5x5Matrix()));
            result.setInverseFinalAnswerMatrix(createEmptyInverseMatrix());
        }
       
        if (currentStep >= 5) {
            result.setStepTitle("5: Calculate Positive Diagonal");
            result.setStepDescription(
                    "Multiply the downward diagonal elements."
            );
            result.setDiagonalPositiveValue(
                calculateDiagonalPositiveValue(
                    result.getHelper4x4Matrix(),
                    0,
                    0
                )
            );
        }
        if (currentStep >= 6) {
            result.setStepTitle("6: Calculate Negative Diagonal");
            result.setStepDescription(
                    "Multiply the upward diagonal elements."
            );
            result.setDiagonalNegativeValue(
                calculateDiagonalNegativeValue(
                    result.getHelper4x4Matrix(),
                    0,
                    0
                )
            );
        }
        if (currentStep >= 7) {
            result.setStepTitle("7: Calculate Minor To Get First(A1) Of Your Inverse Matrix");
            result.setStepDescription(
                    "Subtract the negative diagonal value from the positive diagonal value to get the minor."
            );
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(
                            result.getDiagonalPositiveValue(),
                            result.getDiagonalNegativeValue()
                    )
            );

            Double[][] answerMatrix = createEmptyInverseMatrix();
            answerMatrix[0][0] = result.getDiagonalResultValue();
            result.setInverseFinalAnswerMatrix(answerMatrix);            
        }
        if (currentStep >= 8) {
            result.setStepTitle("8: Calculate Positive Diagonal for A2 X B3");
            result.setStepDescription(
                    "Multiply the downward diagonal elements"
            );
            result.setDiagonalPositiveValue(
                calculateDiagonalPositiveValue(result.getHelper4x4Matrix(), 0, 1));           
            
        }
        if (currentStep >= 9) {
            result.setStepTitle("9: Calculate Negative Diagonal");
            result.setStepDescription(
                    "Multiply the upward diagonal elements."
            );
            result.setDiagonalNegativeValue(
                calculateDiagonalNegativeValue(
                    result.getHelper4x4Matrix(),
                    0,
                    1
                )
            );
        }
        if (currentStep >= 10) {
            result.setStepTitle("10: Calculate Minor To Get Next (A2) Value Of Your Inverse Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");           

            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setInverseFinalAnswerMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        return result;
    }
    public double[][] transposeMatrix(double[][] matrix) {
        double[][] transposed = new double[3][3];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    transposed[col][row] = matrix[row][col];
                }
            }
            return transposed;
        }

    public double[][] convertTo5x5Matrix(double[][] matrix) {
        double[][] convertTo5x5 = new double[5][5];
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    convertTo5x5[row][col] = matrix[row % 3][col % 3];
                    }
                }
            return convertTo5x5;
        }
    public double[][] create4x4Matrix(double[][] matrix5x5) {
        double[][] matrix4x4 = new double[4][4];
            for (int row = 1; row < 5; row++) {
                for (int col = 1; col < 5; col++) {
                    matrix4x4[row - 1][col - 1] = matrix5x5[row][col];
                }
            }
        return matrix4x4;
    }

    public Double[][] createEmptyInverseMatrix() {
        return new Double[3][3];
    }
    
    public Double calculateDiagonalResultValue(Double diagonalPositiveValue, Double diagonalNegativeValue) {
        return diagonalPositiveValue - diagonalNegativeValue;
    }
    public Double calculateDiagonalPositiveValue(double[][] helper4x4Matrix, int row, int col){
        return helper4x4Matrix[row][col] * helper4x4Matrix[row + 1][col + 1];
    }
    public Double calculateDiagonalNegativeValue(double[][] helper4x4Matrix, int row, int col){
        return helper4x4Matrix[row][col + 1] * helper4x4Matrix[row + 1][col];
    }
    private Double[][] buildAnswerMatrix(MatrixInverse3x3Result result, int currentStep){   
        Double[][] answer = createEmptyInverseMatrix();
            if (currentStep >= 7) {
                answer[0][0] = calculateMinor(result.getHelper4x4Matrix(), 0, 0);                        
            }
            if (currentStep >= 10) {
                answer[0][1] = result.getDiagonalResultValue();
            }
        return answer;
    }

    public Double calculateMinor(double[][] helper4x4Matrix, int row, int col) {   
        double positiveDiagonalValue =
                calculateDiagonalPositiveValue(helper4x4Matrix, row, col);
        double negativeDiagonalValue =
                calculateDiagonalNegativeValue(helper4x4Matrix, row, col);

        return calculateDiagonalResultValue(positiveDiagonalValue, negativeDiagonalValue);
    }
}
