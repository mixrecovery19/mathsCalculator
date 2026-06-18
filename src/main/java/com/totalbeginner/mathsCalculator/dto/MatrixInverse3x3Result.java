package com.totalbeginner.mathsCalculator.dto;

public class MatrixInverse3x3Result {

    private double[][] originalMatrix;
    private int currentStep;
    private boolean inverseStarted;

    private String stepTitle;
    private String stepDescription;

    private double[][] transposedMatrix;
    private double[][] convertTo5x5Matrix;
    private double[][] helper4x4Matrix;

    private Double diagonalPositiveValue;
    private Double diagonalNegativeValue;
    private Double diagonalResultValue;

    private Double[][] inverseFinalAnswerMatrix;

    public double[][] getOriginalMatrix() {
        return originalMatrix;
    }

    public void setOriginalMatrix(double[][] originalMatrix) {
        this.originalMatrix = originalMatrix;
    }

    public int getCurrentStep() {
        return currentStep;
    }
    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }
    public boolean isInverseStarted() {
        return inverseStarted;
    }
    public void setInverseStarted(boolean inverseStarted) {
        this.inverseStarted = inverseStarted;
    }
    public String getStepDescription() {
        return stepDescription;
    }
    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
    public String getStepTitle() {
        return stepTitle;
    }
    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle; 
    }
    public double[][] getTransposedMatrix() {
        return transposedMatrix;
    }
    public void setTransposedMatrix(double[][] transposedMatrix) {
        this.transposedMatrix = transposedMatrix;
    }
    public double[][] getConvertTo5x5Matrix() {
        return convertTo5x5Matrix;
    }
    public void setConvertTo5x5Matrix(double[][] convertTo5x5Matrix) {
        this.convertTo5x5Matrix = convertTo5x5Matrix;
    }
    public double[][] getHelper4x4Matrix() {
        return helper4x4Matrix;
    }
    public void setHelper4x4Matrix(double[][] helper4x4Matrix) {
        this.helper4x4Matrix = helper4x4Matrix;
    }
    public Double[][] getInverseFinalAnswerMatrix() {
        return inverseFinalAnswerMatrix;
    }
    public void setInverseFinalAnswerMatrix(Double[][] inverseFinalAnswerMatrix) {
        this.inverseFinalAnswerMatrix = inverseFinalAnswerMatrix;
    }
    public Double getDiagonalPositiveValue() {
        return diagonalPositiveValue;
    }
    public void setDiagonalPositiveValue(Double diagonalPositiveValue) {
        this.diagonalPositiveValue = diagonalPositiveValue;
    }
    public Double getDiagonalNegativeValue() {
        return diagonalNegativeValue;
    }
    public void setDiagonalNegativeValue(Double diagonalNegativeValue) {
        this.diagonalNegativeValue = diagonalNegativeValue;
    }
    public Double getDiagonalResultValue() {
        return diagonalResultValue;
    }
    public void setDiagonalResultValue(Double diagonalResultValue) {
        this.diagonalResultValue = diagonalResultValue;
    }
}