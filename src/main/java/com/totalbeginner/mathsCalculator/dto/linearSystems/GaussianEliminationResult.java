package com.totalbeginner.mathsCalculator.dto.linearSystems;

public class GaussianEliminationResult {

    // Input
    private double[][] augmentedMatrix;

    // Walkthrough state
    private boolean hasAugmentedMatrix;
    private int currentGaussianSection;
    private int currentGaussianSectionTwoStep;

    // Display
    private String displayMode;

    // Final solution
    private double xAnswer;
    private double yAnswer;

    //Section Two Walkthrough state
    private double eliminationFactor;

    private double scaledA;
    private double scaledB;
    private double scaledConstant;

    private double newRowA;
    private double newRowB;
    private double newRowConstant;

    // Constructors
    public GaussianEliminationResult() {
    }

    // -----------------------------
    // Getters and Setters
    // -----------------------------

    public double[][] getAugmentedMatrix() {
        return augmentedMatrix;
    }

    public void setAugmentedMatrix(double[][] augmentedMatrix) {
        this.augmentedMatrix = augmentedMatrix;
    }
    public boolean hasAugmentedMatrix() {
        return hasAugmentedMatrix;
    }
    public void setHasAugmentedMatrix(boolean hasAugmentedMatrix) {
        this.hasAugmentedMatrix = hasAugmentedMatrix;
    }
    public int getCurrentGaussianSection() {
        return currentGaussianSection;
    }
    public void setCurrentGaussianSection(int currentGaussianSection) {
        this.currentGaussianSection = currentGaussianSection;
    }
    public int getCurrentGaussianSectionTwoStep() {
        return currentGaussianSectionTwoStep;
    }
    public void setCurrentGaussianSectionTwoStep(int currentGaussianSectionTwoStep) {
        this.currentGaussianSectionTwoStep = currentGaussianSectionTwoStep;
    }
    public String getDisplayMode() {
        return displayMode;
    }
    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
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
    public double getEliminationFactor() {
        return eliminationFactor;
    }
    public void setEliminationFactor(double eliminationFactor) {
        this.eliminationFactor = eliminationFactor;
    }
    public double getScaledA() {
        return scaledA;
    }
    public void setScaledA(double scaledA) {
        this.scaledA = scaledA;
    }
    public double getScaledB() {
        return scaledB;
    }
    public void setScaledB(double scaledB) {
        this.scaledB = scaledB;
    }
    public double getScaledConstant() {
        return scaledConstant;
    }
    public void setScaledConstant(double scaledConstant) {
        this.scaledConstant = scaledConstant;
    }
    public double getNewRowA() {
        return newRowA;
    }
    public void setNewRowA(double newRowA) {
        this.newRowA = newRowA;
    }
    public double getNewRowB() {
        return newRowB;
    }
    public void setNewRowB(double newRowB) {
        this.newRowB = newRowB;
    }
    public double getNewRowConstant() {
        return newRowConstant;
    }
    public void setNewRowConstant(double newRowConstant) {
        this.newRowConstant = newRowConstant;
    }
}