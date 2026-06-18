package com.totalbeginner.mathsCalculator.dto;

public class LinearEquationResult {

    private double x;
    private double y;

    private String stepOne;
    private String stepTwo;
    private String stepThree;

    private int currentStep;

    // Constructors
    public LinearEquationResult() {
    }

    public LinearEquationResult(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getStepOne() {
        return stepOne;
    }

    public void setStepOne(String stepOne) {
        this.stepOne = stepOne;
    }

    public String getStepTwo() {
        return stepTwo;
    }

    public void setStepTwo(String stepTwo) {
        this.stepTwo = stepTwo;
    }

    public String getStepThree() {
        return stepThree;
    }

    public void setStepThree(String stepThree) {
        this.stepThree = stepThree;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }
}