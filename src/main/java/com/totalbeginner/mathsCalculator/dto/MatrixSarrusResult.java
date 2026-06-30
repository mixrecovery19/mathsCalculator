package com.totalbeginner.mathsCalculator.dto;

public class MatrixSarrusResult {

    private double[][] sarrusMatrix;    

    private int currentSarrusSection;
    private int currentStepSarrusSectionTwo;
    private int currentStepSarrusSectionThree;

    private double determinant;
    private double positiveStep1;
    private double positiveStep2;
    private double positiveStep3;
    private double positiveTotal;

    private double negativeStep1;
    private double negativeStep2;
    private double negativeStep3;
    private double negativeTotal;
    
    

    private double[][] originalMatrix;
    private boolean hasMatrixValues;
    private boolean sarrusGenerated;
    private boolean showInverseSection;
    private String displayMode;
    
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

    public int getCurrentStepSarrusSectionTwo() { return currentStepSarrusSectionTwo; }
    public void setCurrentStepSarrusSectionTwo(int currentStepSarrusSectionTwo) { this.currentStepSarrusSectionTwo = currentStepSarrusSectionTwo; }

    public int getCurrentStepSarrusSectionThree() { return currentStepSarrusSectionThree; }
    public void setCurrentStepSarrusSectionThree(int currentStepSarrusSectionThree) { this.currentStepSarrusSectionThree = currentStepSarrusSectionThree; }

    public double[][] getOriginalMatrix() { return originalMatrix; }
    public void setOriginalMatrix(double[][] originalMatrix) { this.originalMatrix = originalMatrix; }

    public boolean isHasMatrixValues() { return hasMatrixValues; }
    public void setHasMatrixValues(boolean hasMatrixValues) { this.hasMatrixValues = hasMatrixValues; }
    
    public boolean isSarrusGenerated() { return sarrusGenerated; }
    public void setSarrusGenerated(boolean sarrusGenerated) { this.sarrusGenerated = sarrusGenerated; }

    public boolean isShowInverseSection() { return showInverseSection; }
    public void setShowInverseSection(boolean showInverseSection) { this.showInverseSection = showInverseSection; }

    public int getCurrentSarrusSection() { return currentSarrusSection; }
    public void setCurrentSarrusSection(int currentSarrusSection) { this.currentSarrusSection = currentSarrusSection; } 
    public String getDisplayMode() { return displayMode; }
    public void setDisplayMode(String displayMode) { this.displayMode = displayMode; }
}