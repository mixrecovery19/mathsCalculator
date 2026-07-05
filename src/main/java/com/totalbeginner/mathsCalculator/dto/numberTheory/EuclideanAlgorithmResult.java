package com.totalbeginner.mathsCalculator.dto.numberTheory;

import java.util.ArrayList;
import java.util.List;

public class EuclideanAlgorithmResult {

    private int firstNumber;
    private int secondNumber;   

    private int greatestCommonDivisor;

    private int currentEuclideanStep;
    private int euclideanSection;
    private int maximumEuclideanStep;

    private boolean hasResult;
    private boolean completed;

    private List<EuclideanAlgorithmStep> steps = new ArrayList<>();

    public EuclideanAlgorithmResult() {
    }
    public int getFirstNumber() {
        return firstNumber;
    }
    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }
    public int getSecondNumber() {
        return secondNumber;
    }
    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }    
    public int getGreatestCommonDivisor() {
        return greatestCommonDivisor;
    }
    public void setGreatestCommonDivisor(int greatestCommonDivisor) {
        this.greatestCommonDivisor = greatestCommonDivisor;
    }
    public int getCurrentEuclideanStep() {
        return currentEuclideanStep;
    }
    public void setCurrentEuclideanStep(int currentEuclideanStep) {
        this.currentEuclideanStep = currentEuclideanStep;
    }
    public int getEuclideanSection() {
        return euclideanSection;
    }
    public void setEuclideanSection(int euclideanSection) {
        this.euclideanSection = euclideanSection;
    }
    public boolean isHasResult() {
        return hasResult;
    }
    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public List<EuclideanAlgorithmStep> getSteps() {
    return steps;
    }
    public void setSteps(List<EuclideanAlgorithmStep> steps) {
        this.steps = steps;
    }
    public int getMaximumEuclideanStep() {
        return maximumEuclideanStep;
    }
    public void setMaximumEuclideanStep(int maximumEuclideanStep) {
        this.maximumEuclideanStep = maximumEuclideanStep;
    }
}