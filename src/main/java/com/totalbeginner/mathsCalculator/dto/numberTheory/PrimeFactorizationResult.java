package com.totalbeginner.mathsCalculator.dto.numberTheory;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorizationResult {

    private int originalNumber;
    private int currentNumber;

    private int currentStep;
    private int currentFactor;

    private String factorsDisplay;
    private String finalFactorizationDisplay;

    private String feedbackMessage;

    private boolean successfulDivision;
    private int quotient;
    private int remainder;

    private boolean completed;

    private List<Integer> factors = new ArrayList<>();

    public PrimeFactorizationResult() {
    }

    public int getOriginalNumber() {
        return originalNumber;
    }

    public void setOriginalNumber(int originalNumber) {
        this.originalNumber = originalNumber;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public int getCurrentFactor() {
        return currentFactor;
    }

    public void setCurrentFactor(int currentFactor) {
        this.currentFactor = currentFactor;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public List<Integer> getFactors() {
        return factors;
    }
    public void setFactors(List<Integer> factors) {
        this.factors = factors;
    }
    public String getFactorsDisplay() {
        return factorsDisplay;
    }
    public void setFactorsDisplay(String factorsDisplay) {
        this.factorsDisplay = factorsDisplay;
    }
    public String getFinalFactorizationDisplay() {
        return finalFactorizationDisplay;
    }
    public void setFinalFactorizationDisplay(String finalFactorizationDisplay) {
        this.finalFactorizationDisplay = finalFactorizationDisplay;
    }
    public String getFeedbackMessage() {
        return feedbackMessage;
    }
    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }
    public boolean isSuccessfulDivision() {
        return successfulDivision;
    }
    public void setSuccessfulDivision(boolean successfulDivision) {
        this.successfulDivision = successfulDivision;
    }
    public int getQuotient() {
        return quotient;
    }
    public void setQuotient(int quotient) {
        this.quotient = quotient;
    }
    public int getRemainder() {
        return remainder;
    }
    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }
}