package com.totalbeginner.mathsCalculator.service;

import com.totalbeginner.mathsCalculator.dto.MatrixInverse3x3Result;
import com.totalbeginner.mathsCalculator.controller.MatrixInverse3x3Controller;
import org.springframework.stereotype.Service;

@Service
public class MatrixInverse3x3Service {

    public MatrixInverse3x3Result
    buildInverseResult(
            double[][] matrix,
            int currentStep
    ) {

        MatrixInverse3x3Result result =
                new MatrixInverse3x3Result();

        result.setOriginalMatrix(matrix);
        result.setCurrentStep(currentStep);
        result.setInverseStarted(true);

        return result;
    }
}