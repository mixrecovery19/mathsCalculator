package com.totalbeginner.mathsCalculator.service.linearSystems;

import org.springframework.stereotype.Service;

@Service
public class GaussianEliminationService {

    /**
     * Section 1
     * Construct the augmented matrix.
     */
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

    /**
     * Section 2 - Step 1
     * Calculate the elimination factor.
     */
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

        return matrix[1][0] - scaledA;
    }
   
    public double calculateSectionTwoNewRowB(
            double[][] matrix,
            double scaledB) {

        return matrix[1][1] - scaledB;
    }

    /**
     * Section 2 - Step 5
     * Calculate the new constant.
     */
    public double calculateSectionTwoNewRowConstant(
            double[][] matrix,
            double scaledConstant) {

        return matrix[1][2] - scaledConstant;
    }

}