package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.dto.MatrixInverse3x3Result;
import com.totalbeginner.mathsCalculator.service.MatrixInverse3x3Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MatrixInverse3x3Controller {

    private final MatrixInverse3x3Service matrixInverse3x3Service;

    public MatrixInverse3x3Controller(
            MatrixInverse3x3Service matrixInverse3x3Service) {

        this.matrixInverse3x3Service = matrixInverse3x3Service;
    }

    public void loadInverseSection(
            Model model,
            double[][] matrix,
            int inverseCurrentStep
    ) {

        MatrixInverse3x3Result result =
                matrixInverse3x3Service.buildInverseResult(
                        matrix,
                        inverseCurrentStep
                );

        model.addAttribute(
                "inverseResult",
                result
        );
    }    
}