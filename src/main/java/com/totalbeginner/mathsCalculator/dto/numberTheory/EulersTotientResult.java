package com.totalbeginner.mathsCalculator.dto.numberTheory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EulersTotientResult {
   
    private int number;    
    private boolean hasResult;   
   
    private int currentStep;
    private int eulersSection;
    private int eulersSectionTwoCurrentStep;
    private int currentValue;
    private int previousValue;
    private int activeFactorIndex;

    private Map<Integer, Integer> primeFactors = new LinkedHashMap<>();

   
    private List<EulerTotientFactor> factors;   
    private int totient;

    public EulersTotientResult() {
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public boolean isHasResult() {
        return hasResult;
    }
    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }
    public int getCurrentStep() {
        return currentStep;
    }
    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }
    public Map<Integer, Integer> getPrimeFactors() {
        return primeFactors;
    }
    public void setPrimeFactors(Map<Integer, Integer> primeFactors) {
        this.primeFactors = primeFactors;
    }
    public List<EulerTotientFactor> getFactors() {
    return factors;
    }
    public void setFactors(List<EulerTotientFactor> factors) {
        this.factors = factors;
    }
    public int getTotient() {
        return totient;
    }
    public void setTotient(int totient) {
        this.totient = totient;
    }
    public int getEulersSection() {
        return eulersSection;
    }
    public void setEulersSection(int eulersSection) {
        this.eulersSection = eulersSection;
    }
    public int getEulersSectionTwoCurrentStep() {
        return eulersSectionTwoCurrentStep;
    }
    public void setEulersSectionTwoCurrentStep(int eulersSectionTwoCurrentStep) {
        this.eulersSectionTwoCurrentStep = eulersSectionTwoCurrentStep;
    }
    public int getCurrentValue() {
        return currentValue;
    }
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
    public int getActiveFactorIndex() {
        return activeFactorIndex;
    }
    public void setActiveFactorIndex(int activeFactorIndex) {
        this.activeFactorIndex = activeFactorIndex;
    }
    public int getPreviousValue() {
        return previousValue;
    }
    public void setPreviousValue(int previousValue) {
        this.previousValue = previousValue;
    }
}