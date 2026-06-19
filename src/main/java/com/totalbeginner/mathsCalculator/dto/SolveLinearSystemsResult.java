package com.totalbeginner.mathsCalculator.dto;

public class SolveLinearSystemsResult {

    private double[][] coefficientMatrix;
    private double[] constantsVector;
    private boolean hasMatrixValues;

    private double positiveStep1;
    private double negativeStep1;
    private double determinant;

    

    private int currentStep;

    // Constructors
    public SolveLinearSystemsResult() {
    }

    public SolveLinearSystemsResult(double[][] coefficientMatrix, double[] constantsVector, boolean hasMatrixValues) {
        this.coefficientMatrix = coefficientMatrix;
        this.constantsVector = constantsVector;
        this.hasMatrixValues = hasMatrixValues;
    }

    // Getters and Setters
    public double[][] getCoefficientMatrix() {
        return coefficientMatrix;
    }

    public void setCoefficientMatrix(double[][] coefficientMatrix) {
        this.coefficientMatrix = coefficientMatrix;
    }

    public double[] getConstantsVector() {
        return constantsVector;
    }

    public void setConstantsVector(double[] constantsVector) {
        this.constantsVector = constantsVector;
    }

    public boolean hasMatrixValues() {
        return hasMatrixValues;
    }

    public void setHasMatrixValues(boolean hasMatrixValues) {
        this.hasMatrixValues = hasMatrixValues;
    }    

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }
    public double getPositiveStep1() {
        return positiveStep1;
    }
    public void setPositiveStep1(double positiveStep1) {
        this.positiveStep1 = positiveStep1;
    }

    public double getNegativeStep1() {
        return negativeStep1;
    }
    public void setNegativeStep1(double negativeStep1) {
        this.negativeStep1 = negativeStep1;
    }

    public double getDeterminant() {
        return determinant;
    }
    public void setDeterminant(double determinant) {
        this.determinant = determinant;
    }
}