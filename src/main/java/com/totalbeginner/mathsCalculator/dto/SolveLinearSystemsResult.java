package com.totalbeginner.mathsCalculator.dto;

public class SolveLinearSystemsResult {

    private double[][] coefficientMatrix;
    private double[] constantsVector;
    private boolean hasMatrixValues;
    private boolean hasInitialValues;
    private boolean hasInverseWalkthrough;
    private boolean hasFinalLinearSystemWalkthrough;
    private int currentSection;
    

    private String displayMode;

    private double positiveStep1;
    private double negativeStep1;
    private double determinant;    

    private int currentStep;
    private int determinantStep;
    private int inverseCurrentStep;
    private int solveLinearStep;


    private double xStep1;
    private double xStep2;

    private double yStep1;
    private double yStep2;

    private double xAnswer;
    private double yAnswer;
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
    public int getDeterminantStep() {
        return determinantStep;
    }
    public void setDeterminantStep(int determinantStep) {
        this.determinantStep = determinantStep;
    }
    public int getInverseCurrentStep() {
        return inverseCurrentStep;
    }
    public void setInverseCurrentStep(int inverseCurrentStep) {
        this.inverseCurrentStep = inverseCurrentStep;
    }
    public double getXStep1() {
        return xStep1;
    }
    public void setXStep1(double xStep1) {
        this.xStep1 = xStep1;
    }
    public double getXStep2() {
        return xStep2;
    }
    public void setXStep2(double xStep2) {
        this.xStep2 = xStep2;
    }
    public double getYStep1() {
        return yStep1;
    }
    public void setYStep1(double yStep1) {
        this.yStep1 = yStep1;
    }
    public double getYStep2() {
        return yStep2;
    }
    public void setYStep2(double yStep2) {
        this.yStep2 = yStep2;
    }
    public int getSolveLinearStep() {
        return solveLinearStep;
    }
    public void setSolveLinearStep(int solveLinearStep) {
        this.solveLinearStep = solveLinearStep;
    }
    public double getXAnswer() {
        return xAnswer;
    }
    public void setXAnswer(double xAnswer) {
        this.xAnswer = xAnswer;
    }
    public double getYAnswer() {
        return yAnswer;
    }
    public void setYAnswer(double yAnswer) {
        this.yAnswer = yAnswer;
    }
    public String getDisplayMode() {
        return displayMode;
    }
    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
    }
    public boolean hasInitialValues() {
        return hasInitialValues;
    }
    public void setHasInitialValues(boolean hasInitialValues) {
        this.hasInitialValues = hasInitialValues;
    }
    public boolean hasInverseWalkthrough() {
        return hasInverseWalkthrough;
    }
    public void setHasInverseWalkthrough(boolean hasInverseWalkthrough) {
        this.hasInverseWalkthrough = hasInverseWalkthrough;
    }
    public boolean hasFinalLinearSystemWalkthrough() {
        return hasFinalLinearSystemWalkthrough;
    }
    public void setHasFinalLinearSystemWalkthrough(boolean hasFinalLinearSystemWalkthrough) {
        this.hasFinalLinearSystemWalkthrough = hasFinalLinearSystemWalkthrough;
    }
    public int getCurrentSection() {
        return currentSection;
    }
    public void setCurrentSection(int currentSection) {
        this.currentSection = currentSection;   
    }

}