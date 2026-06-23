package com.totalbeginner.mathsCalculator.dto.linearSystems;

public class GaussianEliminationResult {

    // Input
    private double[][] augmentedMatrix;

    // Walkthrough state
    private boolean hasAugmentedMatrix;
    private int currentGaussianSection;
    private int currentGaussianStep;

    // Display
    private String displayMode;

    // Final solution
    private double xAnswer;
    private double yAnswer;

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
    public int getCurrentGaussianStep() {
        return currentGaussianStep;
    }
    public void setCurrentGaussianStep(int currentGaussianStep) {
        this.currentGaussianStep = currentGaussianStep;
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
}