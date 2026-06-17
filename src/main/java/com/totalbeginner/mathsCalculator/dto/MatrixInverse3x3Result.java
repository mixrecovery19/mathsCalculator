package com.totalbeginner.mathsCalculator.dto;

public class MatrixInverse3x3Result {

    private double[][] originalMatrix;
    private int currentStep;
    private boolean inverseStarted;

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
}