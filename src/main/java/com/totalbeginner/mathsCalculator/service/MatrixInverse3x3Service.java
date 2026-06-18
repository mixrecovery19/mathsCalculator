package com.totalbeginner.mathsCalculator.service;

import com.totalbeginner.mathsCalculator.dto.MatrixInverse3x3Result;
import org.springframework.stereotype.Service;

@Service
public class MatrixInverse3x3Service {
    private final MatrixSarrusService matrixSarrusService;

    public MatrixInverse3x3Service(MatrixSarrusService matrixSarrusService) {
        this.matrixSarrusService = matrixSarrusService;
    }

    private static final int FIRST_ROW = 0;
    private static final int SECOND_ROW = 1;
    private static final int THIRD_ROW = 2;
   
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
   

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
                    "Using this version of your transposed matrix, you can now calculate through the cells to form your adjugate matrix leading to the final inverse."
            );
            result.setHelper4x4Matrix(create4x4Matrix(result.getConvertTo5x5Matrix()));
            result.setFinalAdjugateMatrix(createEmptyInverseMatrix());
        }
       
        if (currentStep >= 5) {
            result.setStepTitle("5: Calculate Positive Diagonal");
            result.setStepDescription(
                    "Multiply the downward diagonal elements."
            );
                calculatePositive(result, FIRST_ROW, FIRST_COLUMN);              
        
        }
        if (currentStep >= 6) {
            result.setStepTitle("6: Calculate Negative Diagonal");
            result.setStepDescription(
                    "Multiply the upward diagonal elements."
            );
            calculateNegative(result, FIRST_ROW, FIRST_COLUMN);
        }
        if (currentStep >= 7) {
            result.setStepTitle("7: Calculate Minor To Get First(A1) Of Your Adjugate Matrix");
            result.setStepDescription(
                    "Subtract the negative diagonal value from the positive diagonal value to get the minor."
            );
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(
                            result.getDiagonalPositiveValue(),
                            result.getDiagonalNegativeValue()
                    )
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );            
        }
        if (currentStep >= 8) {
            clearCalculationValues(result);
            result.setStepTitle("8: Calculate Positive Diagonal for A2 X B3");
            result.setStepDescription("Multiply the downward diagonal elements");            
            calculatePositive(result, FIRST_ROW, SECOND_COLUMN);          
            
        }
        if (currentStep >= 9) {
            result.setStepTitle("9: Calculate Negative Diagonal");
            result.setStepDescription("Multiply the upward diagonal elements.");            
            calculateNegative(result, FIRST_ROW, SECOND_COLUMN);
        }           
    
        if (currentStep >= 10) {
            result.setStepTitle("10: Calculate Minor To Get Next (A2) Value Of Your Adjugate Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");           

            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        if (currentStep >= 11) {
            clearCalculationValues(result);
            result.setStepTitle("11: Calculate Positive Diagonal for A3 X B4");
            result.setStepDescription("Multiply the downward diagonal elements");            
            calculatePositive(result, FIRST_ROW, THIRD_COLUMN);          
            
        }
        if (currentStep >= 12) {
            result.setStepTitle("12: Calculate Negative Diagonal A4 x B3");
            result.setStepDescription("Multiply the upward diagonal elements.");            
            calculateNegative(result, FIRST_ROW, THIRD_COLUMN);
        }
        if (currentStep >= 13) {
            result.setStepTitle("13: Calculate Minor To Get Next (A3) Value Of Your Adjugate Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        if (currentStep >= 14) {
            clearCalculationValues(result);
            result.setStepTitle("14: Calculate Positive Diagonal for B1 X C2");
            result.setStepDescription("Multiply the downward diagonal elements");            
            calculatePositive(result, SECOND_ROW, FIRST_COLUMN);          
            
        }
        if (currentStep >= 15) {
            result.setStepTitle("15: Calculate Negative Diagonal B2 x C1");
            result.setStepDescription("Multiply the upward diagonal elements.");            
            calculateNegative(result, SECOND_ROW, FIRST_COLUMN);
        }
        if (currentStep >= 16) {
            result.setStepTitle("16: Calculate Minor To Get Next (B1) Value Of Your Adjugate Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        if (currentStep >= 17) {
            clearCalculationValues(result);
            result.setStepTitle("17: Calculate Positive Diagonal for B2 X C3");
            result.setStepDescription("Multiply the downward diagonal elements");            
            calculatePositive(result, SECOND_ROW, SECOND_COLUMN);          
            
        }
        if (currentStep >= 18) {
            result.setStepTitle("18: Calculate Negative Diagonal B3 x C2");
            result.setStepDescription("Multiply the upward diagonal elements.");            
            calculateNegative(result, SECOND_ROW, SECOND_COLUMN);
        }
        if (currentStep >= 19) {
            result.setStepTitle("19: Calculate Minor To Get Next (B2) Value Of Your Adjugate Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        if (currentStep >= 20) {
            clearCalculationValues(result);
            result.setStepTitle("20: Calculate Positive Diagonal for B3 X C4");
            result.setStepDescription("Multiply the downward diagonal elements");            
            calculatePositive(result, SECOND_ROW, THIRD_COLUMN);    
        }
        if (currentStep >= 21) {
            result.setStepTitle("21: Calculate Negative Diagonal B4 x C3");
            result.setStepDescription("Multiply the upward diagonal elements.");            
            calculateNegative(result, SECOND_ROW, THIRD_COLUMN);
        }
        if (currentStep >= 22) {
            result.setStepTitle("22: Calculate Minor To Get Next (B3) Value Of Your Adjugate Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        if (currentStep >= 23) {
            clearCalculationValues(result);
            result.setStepTitle("23: Calculate Positive Diagonal for C1 X D2");
            result.setStepDescription("Multiply the downward diagonal elements");            
            calculatePositive(result, THIRD_ROW, FIRST_COLUMN); 
        }
        if (currentStep >= 24) {
            result.setStepTitle("24: Calculate Negative Diagonal C2 x D1");
            result.setStepDescription("Multiply the upward diagonal elements.");            
            calculateNegative(result, THIRD_ROW, FIRST_COLUMN);
        }
        if (currentStep >= 25) {
            result.setStepTitle("25: Calculate Minor To Get Next (C1) Value Of YOUR Adjugate Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        if (currentStep >= 26) {
            clearCalculationValues(result);
            result.setStepTitle("26: Calculate Positive Diagonal for C2 X D3");
            result.setStepDescription("Multiply the downward diagonal elements");            
            calculatePositive(result, THIRD_ROW, SECOND_COLUMN); 
        }
        if (currentStep >= 27) {
            result.setStepTitle("27: Calculate Negative Diagonal C3 x D2");
            result.setStepDescription("Multiply the upward diagonal elements.");            
            calculateNegative(result, THIRD_ROW, SECOND_COLUMN);
        }
        if (currentStep >= 28) {
            result.setStepTitle("28: Calculate Minor To Get Next (C2) Value Of YOUR Adjugate Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        if (currentStep >= 29) {
            clearCalculationValues(result);
            result.setStepTitle("29: Calculate Positive Diagonal for C3 X D1");
            result.setStepDescription("Multiply the downward diagonal elements");            
            calculatePositive(result, THIRD_ROW, THIRD_COLUMN); 
        }
        if (currentStep >= 30) {
            result.setStepTitle("30: Calculate Negative Diagonal C1 x D3");
            result.setStepDescription("Multiply the upward diagonal elements.");            
            calculateNegative(result, THIRD_ROW, THIRD_COLUMN);
        }
        if (currentStep >= 31) {
            result.setStepTitle("31: Calculate Minor To Get Next (C3) Value Of YOUR Adjugate Matrix as and Final Value For Your Adjugate Matrix");          
            result.setStepDescription("Subtract the negative diagonal value from the positive diagonal value to get the minor.");
            result.setDiagonalResultValue(
                    calculateDiagonalResultValue(result.getDiagonalPositiveValue(), result.getDiagonalNegativeValue())                    
            );
            result.setFinalAdjugateMatrix(buildAnswerMatrix(result, currentStep)                
            );
        }
        if (currentStep >= 32) {

    result.setStepTitle("32: Your Final 3 × 3 Inverse");

    result.setStepDescription(
            "The final inverse is calculated by dividing every value in the adjugate matrix by the determinant."
    );

    double determinant =
            matrixSarrusService.determinantSarrus(
                    matrixSarrusService.buildSarrusMatrix(matrix)
            );

    Double[][] adjugateMatrix =
            buildAnswerMatrix(result, currentStep);

    result.setFinalAdjugateMatrix(adjugateMatrix);

    result.setFinalInverseMatrix(
            buildFinalInverseMatrix(
                    adjugateMatrix,
                    determinant
            )
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
    private Double[][] buildAnswerMatrix(MatrixInverse3x3Result result, int currentStep) {
        Double[][] answer = createEmptyInverseMatrix();
        if (currentStep >= 7) {
            answer[0][0] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    FIRST_ROW,
                    FIRST_COLUMN
            );
        }
        if (currentStep >= 10) {
            answer[0][1] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    FIRST_ROW,
                    SECOND_COLUMN
            );
        }
        if (currentStep >= 13) {
            answer[0][2] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    FIRST_ROW,
                    THIRD_COLUMN
            );
        }
        if (currentStep >= 16) {
            answer[1][0] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    SECOND_ROW,
                    FIRST_COLUMN
            );
        }
        if (currentStep >= 19) {
            answer[1][1] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    SECOND_ROW,
                    SECOND_COLUMN
            );
        }
        if (currentStep >= 22) {
            answer[1][2] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    SECOND_ROW,
                    THIRD_COLUMN
            );
        }
        if (currentStep >= 25) {
            answer[2][0] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    THIRD_ROW,
                    FIRST_COLUMN
            );
        }
        if (currentStep >= 28) {
            answer[2][1] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    THIRD_ROW,
                    SECOND_COLUMN
            );
        }
        if (currentStep >= 31) {
            answer[2][2] = calculateMinor(
                    result.getHelper4x4Matrix(),
                    THIRD_ROW,
                    THIRD_COLUMN
            );
        }
       
        return answer;
    }
    private void calculatePositive(MatrixInverse3x3Result result, int row, int col) {      
            result.setDiagonalPositiveValue(
                calculateDiagonalPositiveValue(
                    result.getHelper4x4Matrix(),
                    row,
                    col
                )
            );
        }

    private void calculateNegative(MatrixInverse3x3Result result, int row, int col) {
        result.setDiagonalNegativeValue(
            calculateDiagonalNegativeValue(
                result.getHelper4x4Matrix(),
                row,
                col
            )
        );
    }

    public Double calculateMinor(double[][] helper4x4Matrix, int row, int col) {   
        double positiveDiagonalValue =
                calculateDiagonalPositiveValue(helper4x4Matrix, row, col);
        double negativeDiagonalValue =
                calculateDiagonalNegativeValue(helper4x4Matrix, row, col);

        return calculateDiagonalResultValue(positiveDiagonalValue, negativeDiagonalValue);
    }

    private void clearCalculationValues(MatrixInverse3x3Result result) {
        result.setDiagonalPositiveValue(null);
        result.setDiagonalNegativeValue(null);
        result.setDiagonalResultValue(null);
    }

    private String[][] buildFinalInverseMatrix(Double[][] adjugateMatrix, double determinant) {
        String[][] finalInverseMatrix = new String[3][3];

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (adjugateMatrix[row][col] != null) {
                        finalInverseMatrix[row][col] = adjugateMatrix[row][col] + " / " + determinant;
                    }
                }
            }
            return finalInverseMatrix;
        }
}
