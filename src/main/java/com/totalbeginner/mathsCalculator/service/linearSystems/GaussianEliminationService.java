package com.totalbeginner.mathsCalculator.service.linearSystems;

import org.springframework.stereotype.Service;
@Service
public class GaussianEliminationService {    
    
    public double[][] buildAugmentedMatrix(
            double a,
            double b,
            double c,
            double d,
            double e,
            double f) {

        return new double[][]{
                {a, b, c},
                {d, e, f}
        };
    }    
    public double calculateSectionTwoEliminationFactor(double[][] matrix) {
        return matrix[1][0] / matrix[0][0];
    }    
    public double calculateSectionTwoScaledA(
            double[][] matrix,
            double factor) {

        return matrix[0][0] * factor;
    }   
    public double calculateSectionTwoScaledB(
            double[][] matrix,
            double factor) {

        return matrix[0][1] * factor;
    }    
    public double calculateSectionTwoScaledConstant(
            double[][] matrix,
            double factor) {

        return matrix[0][2] * factor;
    }    
    public double calculateSectionTwoNewRowA(
        double[][] matrix,
        double scaledA) {

    // d - ka
    return matrix[1][0] - scaledA;
}

public double calculateSectionTwoNewRowB(
        double[][] matrix,
        double scaledB) {

    // e - kb
    return matrix[1][1] - scaledB;
}

public double calculateSectionTwoNewRowConstant(
        double[][] matrix,
        double scaledConstant) {

    // f - kc
    return matrix[1][2] - scaledConstant;
}

public double calculateSectionThreeY(double newRowB, double newRowConstant) {

    // y = f - kb / e - kb
    return newRowConstant / newRowB;
}

}