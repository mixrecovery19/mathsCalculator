package com.totalbeginner.mathsCalculator.dto.numberTheory;

public class EuclideanAlgorithmStep {

    private int dividend;
    private int divisor;
    private int quotient;
    private int remainder;

    public EuclideanAlgorithmStep(int dividend, int divisor, int quotient, int remainder) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public int getDividend() {
        return dividend;
    }

    public void setDividend(int dividend) {
        this.dividend = dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
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
