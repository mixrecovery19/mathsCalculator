package com.totalbeginner.mathsCalculator.controller.linearSystems;

import com.totalbeginner.mathsCalculator.dto.linearSystems.GaussianEliminationResult;
import com.totalbeginner.mathsCalculator.service.linearSystems.GaussianEliminationService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GaussianEliminationController {

    private final GaussianEliminationService gaussianEliminationService;

    public GaussianEliminationController(GaussianEliminationService gaussianEliminationService) {
        this.gaussianEliminationService = gaussianEliminationService;
    }

    @GetMapping("/gaussian-elimination")
    public String showGaussianEliminationPage(Model model) {
        GaussianEliminationResult result = new GaussianEliminationResult();

        result.setAugmentedMatrix(new double[2][3]);
        result.setHasAugmentedMatrix(false);
        result.setDisplayMode("decimal");
        result.setCurrentGaussianSection(0);

        model.addAttribute("result", result);

        return "gaussianEliminationMethod";
    }

    @PostMapping("/gaussian-elimination")
    public String createGaussianElimination(

            @RequestParam(required = false) Double a_0_0,
            @RequestParam(required = false) Double a_0_1,
            @RequestParam(required = false) Double b_0,

            @RequestParam(required = false) Double a_1_0,
            @RequestParam(required = false) Double a_1_1,
            @RequestParam(required = false) Double b_1,
            @RequestParam(defaultValue = "0")  
            int currentGaussianSectionTwoStep,    
            @RequestParam(defaultValue = "0")
            int currentGaussianSectionThreeStep,         

            @RequestParam(required = false) String action,

            Model model) {

        GaussianEliminationResult result = new GaussianEliminationResult();

        result.setCurrentGaussianSectionTwoStep(currentGaussianSectionTwoStep);
        result.setCurrentGaussianSectionThreeStep(currentGaussianSectionThreeStep);
        // User hasn't entered all six values
        if (a_0_0 == null || a_0_1 == null || b_0 == null
                || a_1_0 == null || a_1_1 == null || b_1 == null) {

            result.setAugmentedMatrix(new double[2][3]);
            result.setHasAugmentedMatrix(false);
            result.setDisplayMode("decimal");
            result.setCurrentGaussianSection(0);

            model.addAttribute("result", result);
            model.addAttribute("inputError",
                    "Please enter all six values before continuing.");

            return "gaussianEliminationMethod";
        }        

    double[][] augmentedMatrix = gaussianEliminationService.buildAugmentedMatrix(
            a_0_0, a_0_1, b_0,
            a_1_0, a_1_1, b_1
        );

        result.setAugmentedMatrix(augmentedMatrix); 
        result.setDisplayMode("decimal");

    if ("create-augmented-matrix".equals(action)) {
        result.setHasAugmentedMatrix(true);
        result.setCurrentGaussianSection(1);
        }

    if ("proceed-to-gaussian-section-two".equals(action)) {
        result.setHasAugmentedMatrix(true);
        result.setCurrentGaussianSection(2);
        result.setCurrentGaussianSectionTwoStep(0);
    }

    if ("next-gaussian-section-two-system-step".equals(action)) {
        result.setHasAugmentedMatrix(true);
        result.setCurrentGaussianSection(2);
        result.setCurrentGaussianSectionTwoStep(
                result.getCurrentGaussianSectionTwoStep() + 1
        );
    }

    if ("previous-gaussian-section-two-system-step".equals(action)) {
        result.setHasAugmentedMatrix(true);
        result.setCurrentGaussianSection(2);
        result.setCurrentGaussianSectionTwoStep(
                result.getCurrentGaussianSectionTwoStep() - 1
        );
    }
    if ("back-to-gaussian-section-one".equals(action)) {
        result.setHasAugmentedMatrix(true);
        result.setCurrentGaussianSection(0);
        result.setCurrentGaussianSectionTwoStep(0);
    }
    
    if ("next-gaussian-section-three-system-step".equals(action)) {
    result.setHasAugmentedMatrix(true);
    result.setCurrentGaussianSection(3);
    result.setCurrentGaussianSectionThreeStep(
            result.getCurrentGaussianSectionThreeStep() + 1);
}

if ("previous-gaussian-section-three-system-step".equals(action)) {
    result.setHasAugmentedMatrix(true);
    result.setCurrentGaussianSection(3);
    result.setCurrentGaussianSectionThreeStep(
            result.getCurrentGaussianSectionThreeStep() - 1);
}

if ("back-to-gaussian-section-two".equals(action)) {
    result.setHasAugmentedMatrix(true);
    result.setCurrentGaussianSection(2);
    result.setCurrentGaussianSectionThreeStep(0);
}
 if ("proceed-to-gaussian-section-three".equals(action)) {
        result.setHasAugmentedMatrix(true);
        result.setCurrentGaussianSection(3);
        result.setCurrentGaussianSectionThreeStep(0);
    }

    result.setCurrentGaussianSectionTwoStep(
        Math.max(0, Math.min(result.getCurrentGaussianSectionTwoStep(), 8))
);

result.setCurrentGaussianSectionThreeStep(
        Math.max(0, Math.min(result.getCurrentGaussianSectionThreeStep(), 1))
);
     
        if (result.getCurrentGaussianSectionTwoStep() >= 1) {

        double eliminationFactor =
                gaussianEliminationService
                        .calculateSectionTwoEliminationFactor(
                                augmentedMatrix);

        result.setEliminationFactor(eliminationFactor);
        }
        System.out.println("Section Two Step = " + result.getCurrentGaussianSectionTwoStep());
        

        if (result.getCurrentGaussianSectionTwoStep() >= 2) {

        double scaledA =
                gaussianEliminationService
                        .calculateSectionTwoScaledA(
                                augmentedMatrix,
                                result.getEliminationFactor());

        result.setScaledA(scaledA);
        }
        if (result.getCurrentGaussianSectionTwoStep() >= 3) {
                double scaledB =
                gaussianEliminationService
                        .calculateSectionTwoScaledB(
                                augmentedMatrix,
                                result.getEliminationFactor());

        result.setScaledB(scaledB);
        }
        if (result.getCurrentGaussianSectionTwoStep() >= 4) {
                double scaledConstant =
                gaussianEliminationService
                        .calculateSectionTwoScaledConstant(
                                augmentedMatrix,
                                result.getEliminationFactor());

        result.setScaledConstant(scaledConstant);
        }
        if (result.getCurrentGaussianSectionTwoStep() >= 5) {

        double newRowA =
                gaussianEliminationService
                        .calculateSectionTwoNewRowA(
                                augmentedMatrix,
                                result.getScaledA());

        result.setNewRowA(newRowA);
        }
        if (result.getCurrentGaussianSectionTwoStep() >= 6) {

        double newRowB =
                gaussianEliminationService
                        .calculateSectionTwoNewRowB(
                                augmentedMatrix,
                                result.getScaledB());

        result.setNewRowB(newRowB);
        }

        if (result.getCurrentGaussianSectionTwoStep() >= 7) {

        double newRowConstant =
                gaussianEliminationService
                        .calculateSectionTwoNewRowConstant(
                                augmentedMatrix,
                                result.getScaledConstant());

        result.setNewRowConstant(newRowConstant);
        }
       
    
    if (result.getCurrentGaussianSectionThreeStep() >= 1) {

        double yAnswer =
                gaussianEliminationService
                        .calculateSectionThreeY(
                                result.getNewRowB(),
                                result.getNewRowConstant());

        result.setYAnswer(yAnswer);
        }

                                
        model.addAttribute("result", result);

        return "gaussianEliminationMethod";
    }
}