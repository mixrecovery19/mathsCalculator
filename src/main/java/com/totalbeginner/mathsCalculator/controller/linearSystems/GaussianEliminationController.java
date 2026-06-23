package com.totalbeginner.mathsCalculator.controller.linearSystems;

import com.totalbeginner.mathsCalculator.dto.linearSystems.GaussianEliminationResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GaussianEliminationController {

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

        double[][] augmentedMatrix = {
                {a_0_0, a_0_1, b_0},
                {a_1_0, a_1_1, b_1}
        };

        result.setAugmentedMatrix(augmentedMatrix);
        result.setDisplayMode("decimal");

        if ("create-augmented-matrix".equals(action)) {
            result.setHasAugmentedMatrix(true);
            result.setCurrentGaussianSection(1);

        } else {
            result.setHasAugmentedMatrix(false);
            result.setCurrentGaussianSection(0);
        }
         if ("proceed-to-gaussian-step-two".equals(action)) {                    
                    result.setCurrentGaussianSection(2);
                }
                else if ("next-gaussian-system-step".equals(action)) {
                    result.setCurrentGaussianStep(result.getCurrentGaussianStep() + 1);
                    result.setCurrentGaussianSection(2);
                }
                else if ("previous-gaussian-system-step".equals(action)) {
                    result.setCurrentGaussianStep(result.getCurrentGaussianStep() - 1);
                    result.setCurrentGaussianSection(2);
                }     

        model.addAttribute("result", result);

        return "gaussianEliminationMethod";
    }
}