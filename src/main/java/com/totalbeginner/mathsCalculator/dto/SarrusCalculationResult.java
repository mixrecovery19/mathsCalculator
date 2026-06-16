package com.totalbeginner.mathsCalculator.dto;

public class SarrusCalculationResult {

    private double[][] sarrusMatrix;
    private double determinant;

    private Double positiveStep1;
    private Double positiveStep2;
    private Double positiveStep3;
    private Double positiveTotal;

    private Double negativeStep1;
    private Double negativeStep2;
    private Double negativeStep3;
    private Double negativeTotal;

    private Double finalDeterminant;
    private int currentStep;

    // Getters and Setters
    public double[][] getSarrusMatrix() { return sarrusMatrix; }
    public void setSarrusMatrix(double[][] sarrusMatrix) { this.sarrusMatrix = sarrusMatrix; }

    public double getDeterminant() { return determinant; }
    public void setDeterminant(double determinant) { this.determinant = determinant; }

    public Double getPositiveStep1() { return positiveStep1; }
    public void setPositiveStep1(Double positiveStep1) { this.positiveStep1 = positiveStep1; }

    public Double getPositiveStep2() { return positiveStep2; }
    public void setPositiveStep2(Double positiveStep2) { this.positiveStep2 = positiveStep2; }

    public Double getPositiveStep3() { return positiveStep3; }
    public void setPositiveStep3(Double positiveStep3) { this.positiveStep3 = positiveStep3; }

    public Double getPositiveTotal() { return positiveTotal; }
    public void setPositiveTotal(Double positiveTotal) { this.positiveTotal = positiveTotal; }

    public Double getNegativeStep1() { return negativeStep1; }
    public void setNegativeStep1(Double negativeStep1) { this.negativeStep1 = negativeStep1; }

    public Double getNegativeStep2() { return negativeStep2; }
    public void setNegativeStep2(Double negativeStep2) { this.negativeStep2 = negativeStep2; }

    public Double getNegativeStep3() { return negativeStep3; }
    public void setNegativeStep3(Double negativeStep3) { this.negativeStep3 = negativeStep3; }

    public Double getNegativeTotal() { return negativeTotal; }
    public void setNegativeTotal(Double negativeTotal) { this.negativeTotal = negativeTotal; }

    public Double getFinalDeterminant() { return finalDeterminant; }
    public void setFinalDeterminant(Double finalDeterminant) { this.finalDeterminant = finalDeterminant; }

    public int getCurrentStep() { return currentStep; }
    public void setCurrentStep(int currentStep) { this.currentStep = currentStep; }
}