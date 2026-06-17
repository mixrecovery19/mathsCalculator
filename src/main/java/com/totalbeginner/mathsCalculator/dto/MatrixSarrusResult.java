package com.totalbeginner.mathsCalculator.dto;

public class MatrixSarrusResult {

    private double[][] sarrusMatrix;
    private Double determinant;

    private Double positiveStep1;
    private Double positiveStep2;
    private Double positiveStep3;
    private Double positiveTotal;

    private Double negativeStep1;
    private Double negativeStep2;
    private Double negativeStep3;
    private Double negativeTotal;
    
    private int currentStep;

    private double[][] originalMatrix;
    private boolean hasMatrixValues;
    private boolean sarrusGenerated;
    private boolean showInverseSection;

    // Getters and Setters
    public double[][] getSarrusMatrix() { return sarrusMatrix; }
    public void setSarrusMatrix(double[][] sarrusMatrix) { this.sarrusMatrix = sarrusMatrix; }

    public Double getDeterminant() { return determinant; }
    public void setDeterminant(Double determinant) { this.determinant = determinant; }

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

    public int getCurrentStep() { return currentStep; }
    public void setCurrentStep(int currentStep) { this.currentStep = currentStep; }

    public double[][] getOriginalMatrix() { return originalMatrix; }
    public void setOriginalMatrix(double[][] originalMatrix) { this.originalMatrix = originalMatrix; }

    public boolean isHasMatrixValues() { return hasMatrixValues; }
    public void setHasMatrixValues(boolean hasMatrixValues) { this.hasMatrixValues = hasMatrixValues; }
    
    public boolean isSarrusGenerated() { return sarrusGenerated; }
    public void setSarrusGenerated(boolean sarrusGenerated) { this.sarrusGenerated = sarrusGenerated; }

    public boolean isShowInverseSection() { return showInverseSection; }
    public void setShowInverseSection(boolean showInverseSection) { this.showInverseSection = showInverseSection; }
}