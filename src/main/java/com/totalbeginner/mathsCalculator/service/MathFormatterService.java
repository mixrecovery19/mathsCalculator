package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;

@Service("mathFormatterService")
public class MathFormatterService {

    public static final String DECIMAL = "decimal";
    public static final String FRACTION = "fraction";

    // ==========================================================
    // Default formatter (Exact Fractions)
    // ==========================================================

    public String format(double value, String displayMode) {

        if (FRACTION.equals(displayMode)) {
            return convertToExactFraction(value);
        }

        return formatDecimal(value);
    }

    // ==========================================================
    // Nice formatter (Simple Fractions)
    // ==========================================================

    public String formatNice(double value, String displayMode) {

        if (FRACTION.equals(displayMode)) {
            return convertToNiceFraction(value);
        }

        return formatDecimal(value);
    }

    // ==========================================================
    // Decimal Formatting
    // ==========================================================

    public String formatDecimal(double value) {

        if (value == Math.rint(value)) {
            return String.format("%.1f", value);
        }

        return String.format("%.3f", value)
                .replaceAll("0+$", "")
                .replaceAll("\\.$", "");
    }

    // ==========================================================
    // Exact Fraction Conversion
    // ==========================================================

    private String convertToExactFraction(double value) {

        if (Math.abs(value) < 1e-10) {
            return "0";
        }

        boolean negative = value < 0;
        value = Math.abs(value);

        if (Math.abs(value - Math.round(value)) < 1e-10) {

            long whole = Math.round(value);

            return (negative ? "-" : "") + whole;
        }

        // Convert exactly what is displayed
        value = Double.parseDouble(formatDecimal(value));

        int denominator = 1000;
        int numerator = (int) Math.round(value * denominator);

        int gcd = greatestCommonDivisor(numerator, denominator);

        numerator /= gcd;
        denominator /= gcd;

        return (negative ? "-" : "") + numerator + "/" + denominator;
    }

    // ==========================================================
    // Nice Fraction Conversion
    // ==========================================================

    private String convertToNiceFraction(double value) {

        if (Math.abs(value) < 1e-10) {
            return "0";
        }

        boolean negative = value < 0;
        value = Math.abs(value);

        if (Math.abs(value - Math.round(value)) < 1e-10) {

            long whole = Math.round(value);

            return (negative ? "-" : "") + whole;
        }

        int bestNumerator = 0;
        int bestDenominator = 1;

        double smallestError = Double.MAX_VALUE;

        final int MAX_DENOMINATOR = 100;

        for (int denominator = 1; denominator <= MAX_DENOMINATOR; denominator++) {

            int numerator = (int) Math.round(value * denominator);

            double approximation =
                    (double) numerator / denominator;

            double error =
                    Math.abs(value - approximation);

            if (error < smallestError) {

                smallestError = error;
                bestNumerator = numerator;
                bestDenominator = denominator;
            }
        }

        // If there isn't a genuinely nice fraction,
        // fall back to the exact formatter.
        if (smallestError > 0.0001) {
            return convertToExactFraction(negative ? -value : value);
        }

        int gcd =
                greatestCommonDivisor(bestNumerator, bestDenominator);

        bestNumerator /= gcd;
        bestDenominator /= gcd;

        return (negative ? "-" : "")
                + bestNumerator
                + "/"
                + bestDenominator;
    }

    // ==========================================================
    // Greatest Common Divisor
    // ==========================================================

    private int greatestCommonDivisor(int a, int b) {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;
        }

        return Math.abs(a);
    }
}