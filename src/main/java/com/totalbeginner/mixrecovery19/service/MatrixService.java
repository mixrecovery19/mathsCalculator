package com.totalbeginner.mixrecovery19.service;

import org.springframework.stereotype.Service;

@Service
public class MatrixService {

   public double determinant(double[][] matrix) {

    int size = matrix.length;

    // Base case (1×1)
    if (size == 1) {

        return matrix[0][0];
    }

    // Base case (2×2)
    if (size == 2) {

        return
                matrix[0][0]
                * matrix[1][1]

                -

                matrix[0][1]
                * matrix[1][0];
    }

    double determinant = 0;

    for (int col = 0; col < size; col++) {

        double[][] minor =
                getMinor(
                        matrix,
                        0,
                        col
                );

        determinant +=
                Math.pow(-1, col)
                *
                matrix[0][col]
                *
                determinant(minor);
    }

    return determinant;
}

    private double[][] getMinor(double[][] matrix, int excludedRow, int excludedCol)
    {
        int size = matrix.length;
        double[][] minor = new double[size - 1] [size - 1];
        int minorRow = 0;

        for (int row = 0; row < size; row++) {

            if (row == excludedRow) {
                continue;
            }

            int minorCol = 0;

            for (int col = 0; col < size; col++) {

                if (col == excludedCol) {
                    continue;
                }
                minor[minorRow][minorCol] = matrix[row][col];
                minorCol++;
            }
            minorRow++;
        }

        return minor;
    }
    public double[][] transpose(double[][] matrix)
    {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transpose = new double[cols][rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                transpose[col][row] = matrix[row][col];
            }
        }
    return transpose;
    }

    public double[][] inverse(double[][] matrix) {

        double determinant = determinant(matrix);

            if (determinant == 0) {
                return null;
            }

        int size = matrix.length;
        double[][] cofactorMatrix = new double[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                double[][] minor = getMinor(matrix, row, col);

                cofactorMatrix[row][col] = Math.pow(-1, row + col) * determinant(minor);
            }
        }

        double[][] adjugate = transpose(cofactorMatrix);
        double[][] inverse = new double[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                inverse[row][col] = adjugate[row][col] / determinant;
            }
        }
        return inverse;
    }
    public String formatNumber(double value)
    {
        // whole number
        if (value == Math.floor(value)) {
            return String.valueOf((int) value);
        }

        // decimal number rounded to 3dp
        return String.format("%.3f", value)
                .replaceAll("0+$", "")
                .replaceAll("\\.$", "");
    }

    public double[][] identity(double[][] matrix)
    {
        double[][] inverse = inverse(matrix);
        if (inverse == null) {
            return null;
        }

        int size = matrix.length;
        double[][] result = new double[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                for (int k = 0; k < size; k++) {

                    result[row][col] += matrix[row][k] * inverse[k][col];
                }
            }
        }
        return result;
    }
    public String formatFraction(double numerator, double denominator)
    {
        // whole number case
        if (numerator % denominator == 0) {

            return String.valueOf(
                    (int) (numerator / denominator)
            );
        }

        return
                formatNumber(numerator)
                +
                "/"
                +
                formatNumber(denominator);
    }
    
    public String formatInverseFraction(double value, double determinant)
    {
        double numerator =
                Math.round(value * determinant);

        return formatNumber(numerator)
                + "/"
                + formatNumber(determinant);
    }
}