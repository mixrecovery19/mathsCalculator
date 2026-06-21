package com.totalbeginner.mathsCalculator.service;

import org.springframework.stereotype.Service;

@Service("mathFormatterService")
public class MathFormatterService {

    public static final String DECIMAL = "decimal";
    public static final String FRACTION = "fraction";

    public String format(double value, String displayMode) {

        if (FRACTION.equals(displayMode)) {
            return formatFraction(value);
        }

        return formatDecimal(value);
    }

    public String formatDecimal(double value) {
        return String.format("%.3f", value);
    }

    public String formatFraction(double value) {
        return convertToFraction(value);
    }

    private String convertToFraction(double value) {

    if (value == 0) {
        return "0";
    }

    boolean negative = value < 0;
    value = Math.abs(value);

    // Round to exactly what the user sees
    value = Double.parseDouble(formatDecimal(value));

    int denominator = 1000;
    int numerator = (int) Math.round(value * denominator);

    int gcd = greatestCommonDivisor(numerator, denominator);

    numerator /= gcd;
    denominator /= gcd;

    return (negative ? "-" : "") + numerator + "/" + denominator;
}

    private int greatestCommonDivisor(int a, int b) {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;
        }

        return Math.abs(a);
    }
    
}