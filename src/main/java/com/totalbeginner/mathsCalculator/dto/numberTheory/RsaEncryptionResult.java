package com.totalbeginner.mathsCalculator.dto.numberTheory;

public class RsaEncryptionResult {  

    private int p1;
    private int p2;
    private int e;
    private int d;
    private int n;
    private int phi;

    private Integer message;
    private Integer ciphertext;
    private Integer decryptedMessage;

    private boolean hasValues;
    private boolean validPrimes;
    private boolean validE;

    private String errorMessage;

    private int currentRsaSection = 1;

    public int getP1() {
        return p1;
    }
    public void setP1(int p1) {
        this.p1 = p1;
    }
    public int getP2() {
        return p2;
    }
    public void setP2(int p2) {
        this.p2 = p2;
    }
    public int getE() {
        return e;
    }
    public void setE(int e) {
        this.e = e;
    }
    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getPhi() {
        return phi;
    }

    public void setPhi(int phi) {
        this.phi = phi;
    }
    public Integer getMessage() {
        return message;
    }
    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCiphertext() {
        return ciphertext;
    }
    public void setCiphertext(Integer ciphertext) {
        this.ciphertext = ciphertext;
    }
    public Integer getDecryptedMessage() {
        return decryptedMessage;
    }
    public void setDecryptedMessage(Integer decryptedMessage) {
        this.decryptedMessage = decryptedMessage;
    }
    public boolean isHasValues() {
        return hasValues;
    }
    public void setHasValues(boolean hasValues) {
        this.hasValues = hasValues;
    }
    public boolean isValidPrimes() {
        return validPrimes;
    }
    public void setValidPrimes(boolean validPrimes) {
        this.validPrimes = validPrimes;
    }
    public boolean isValidE() {
        return validE;
    }
    public void setValidE(boolean validE) {
        this.validE = validE;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public int getCurrentRsaSection() {
        return currentRsaSection;
    }
    public void setCurrentRsaSection(int currentRsaSection) {
        this.currentRsaSection = currentRsaSection;
    }
}