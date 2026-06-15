package com.totalbeginner.mathsCalculator.model;

import lombok.Data;

@Data
public class MatrixTwoByTwoForm {

    private String action;

    private int currentStep = 0;
    private int inverseCurrentStep = 0;
}