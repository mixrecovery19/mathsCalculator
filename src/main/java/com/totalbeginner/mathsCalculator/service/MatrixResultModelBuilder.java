package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MatrixResultModelBuilder {

    private final MatrixService matrixService;

    public MatrixResultModelBuilder(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    public void addMatrixAResults(
            Model model,
            double[][] matrixA,
            boolean showDeterminant,
            boolean showTranspose,
            boolean showInverse,
            boolean showIdentity,
            boolean showScalarA,
            boolean showSquareA,
            double scalarValue
    ) {

        model.addAttribute("matrixService", matrixService);

        if (showDeterminant) {
            double determinant = matrixService.determinant(matrixA);
            model.addAttribute("determinant", determinant);
            model.addAttribute("formattedDeterminant", matrixService.formatDeterminant(determinant));
        }

        if (showTranspose) {
            model.addAttribute("transpose", matrixService.transpose(matrixA));
        }

        if (showInverse) {
            double[][] inverse = matrixService.inverse(matrixA);
            model.addAttribute("inverse", inverse);
            model.addAttribute("inverseDeterminant", matrixService.determinant(matrixA));

            if (inverse == null) {
                model.addAttribute("inverseError", "This matrix has no inverse because its determinant is 0.");
            }
        }

        if (showIdentity) {
            model.addAttribute("identity", matrixService.identity(matrixA));
        }

        if (showSquareA) {
            model.addAttribute("squareA", matrixService.squareMatrix(matrixA));
        }

        if (showScalarA) {
            model.addAttribute("scalarA", matrixService.scalarMultiply(matrixA, scalarValue));
        }
    }

    public void addMatrixBResults(
            Model model,
            double[][] matrixB,
            boolean showDeterminantB,
            boolean showTransposeB,
            boolean showInverseB,
            boolean showIdentityB,
            boolean showScalarB,
            boolean showSquareB,
            double scalarValue
    ) {

        model.addAttribute("matrixService", matrixService);

        if (showDeterminantB) {
            double determinant = matrixService.determinant(matrixB);
            model.addAttribute("determinantB", determinant);
            model.addAttribute("formattedDeterminantB", matrixService.formatDeterminant(determinant));
        }

        if (showTransposeB) {
            model.addAttribute("transposeB", matrixService.transpose(matrixB));
        }

        if (showInverseB) {
            double[][] inverse = matrixService.inverse(matrixB);
            model.addAttribute("inverseB", inverse);
            model.addAttribute("inverseDeterminantB", matrixService.determinant(matrixB));

            if (inverse == null) {
                model.addAttribute("inverseErrorB", "This matrix has no inverse because its determinant is 0.");
            }
        }

        if (showIdentityB) {
            model.addAttribute("identityB", matrixService.identity(matrixB));
        }

        if (showSquareB) {
            model.addAttribute("squareB", matrixService.squareMatrix(matrixB));
        }

        if (showScalarB) {
            model.addAttribute("scalarB", matrixService.scalarMultiply(matrixB, scalarValue));
        }
    }

    public void addCombinedResults(
            Model model,
            double[][] matrixA,
            double[][] matrixB,
            boolean showAddition,
            boolean showSubtraction,
            boolean showMultiplication
    ) {

        if (showAddition) {
            model.addAttribute("additionResult", matrixService.addMatrices(matrixA, matrixB));
        }

        if (showSubtraction) {
            model.addAttribute("subtractionResult", matrixService.subtractMatrices(matrixA, matrixB));
        }

        if (showMultiplication) {
            model.addAttribute("multiplicationResult", matrixService.multiplyMatrices(matrixA, matrixB));
        }
    }
}