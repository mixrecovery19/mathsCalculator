package com.totalbeginner.mathsCalculator.service.linearSystems;

import org.springframework.stereotype.Service;

import com.totalbeginner.mathsCalculator.dto.linearSystems.GaussianEliminationResult;

@Service
public class GaussianEliminationService {
    public GaussianEliminationResult createGaussianElimination(
            double a,
            double b,
            double c,

            double d,
            double e,
            double f) {

        GaussianEliminationResult result = new GaussianEliminationResult();

        double[][] augmentedMatrix = {
                {a, b, c},
                {d, e, f}
        };

        result.setAugmentedMatrix(augmentedMatrix);
        result.setHasAugmentedMatrix(true);
       
        result.setCurrentGaussianSection(1);
        result.setCurrentGaussianStep(0);
        result.setDisplayMode("decimal");

        return result;
    }
}