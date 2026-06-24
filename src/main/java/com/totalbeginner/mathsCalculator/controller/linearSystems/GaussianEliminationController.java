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

            @RequestParam(required = false) String action,

            Model model) {

        GaussianEliminationResult result = new GaussianEliminationResult();

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

    double eliminationFactor =
            gaussianEliminationService
                    .calculateSectionTwoEliminationFactor(
                            augmentedMatrix);

    double scaledA =
            gaussianEliminationService
                    .calculateSectionTwoScaledA(
                            augmentedMatrix,
                            eliminationFactor);

    double scaledB =
            gaussianEliminationService
                    .calculateSectionTwoScaledB(
                            augmentedMatrix,
                            eliminationFactor);

    double scaledConstant =
            gaussianEliminationService
                    .calculateSectionTwoScaledConstant(
                            augmentedMatrix,
                            eliminationFactor);

    double newRowA =
            gaussianEliminationService
                    .calculateSectionTwoNewRowA(
                            augmentedMatrix,
                            scaledA);

    double newRowB =
            gaussianEliminationService
                    .calculateSectionTwoNewRowB(
                            augmentedMatrix,
                            scaledB);

    double newRowConstant =
            gaussianEliminationService
                    .calculateSectionTwoNewRowConstant(
                            augmentedMatrix,
                            scaledConstant);

                        result.setEliminationFactor(eliminationFactor);

        result.setScaledA(scaledA);
        result.setScaledB(scaledB);
        result.setScaledConstant(scaledConstant);

        result.setNewRowA(newRowA);
        result.setNewRowB(newRowB);
        result.setNewRowConstant(newRowConstant);

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
        result.setCurrentGaussianSection(1);

        result.setCurrentGaussianSectionTwoStep(0);
    }

    if ("back-to-gaussian-section-one".equals(action)) {

        result.setHasAugmentedMatrix(true);
        result.setCurrentGaussianSection(1);

        result.setCurrentGaussianSectionTwoStep(0);
    }
    result.setCurrentGaussianSectionTwoStep(
            Math.max(0, Math.min(result.getCurrentGaussianSectionTwoStep(), 4))
    );
        model.addAttribute("result", result);

        return "gaussianEliminationMethod";
    }
}