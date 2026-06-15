package com.totalbeginner.mathsCalculator.model;

import lombok.Data;
import java.util.Map;

@Data
public class MatrixForm {

    private int size;
    private Map<String, String> params;           // raw form params
    private String action;

    // Display flags
    private boolean showDeterminant = false;
    private boolean showTranspose = false;
    private boolean showInverse = false;
    private boolean showIdentity = false;

    private boolean showDeterminantB = false;
    private boolean showTransposeB = false;
    private boolean showInverseB = false;
    private boolean showIdentityB = false;

    private boolean showAddition = false;
    private boolean showSubtraction = false;
    private boolean showMultiplication = false;

    private boolean showSquareA = false;
    private boolean showSquareB = false;
    private boolean showScalarA = false;
    private boolean showScalarB = false;

    private double scalarValue = 1.0;
}