package com.totalbeginner.mathsCalculator.dto.numberTheory;

public class EulerTotientFactor {

    private int prime;

    private String displayValue;
    private boolean highlighted;
    private int numerator;
    private int denominator;

    public EulerTotientFactor() {
    }
    public EulerTotientFactor(int prime) {
        this.prime = prime;
        this.displayValue = "(1-1/" + prime + ")";
    }
    public int getPrime() {
        return prime;
    }
    public void setPrime(int prime) {
        this.prime = prime;
    }
    public String getDisplayValue() {
        return displayValue;
    }
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
    public boolean isHighlighted() {
        return highlighted;
    }
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
    public int getNumerator() {
        return numerator;
    }
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }
    public int getDenominator() {
        return denominator;
    }
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }
}