package com.totalbeginner.mathsCalculator.controller.numberTheory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totalbeginner.mathsCalculator.dto.numberTheory.EulersTotientResult;
import com.totalbeginner.mathsCalculator.dto.numberTheory.EulerTotientFactor;
import com.totalbeginner.mathsCalculator.service.numberTheory.EulersTotientService;

@Controller
public class EulersTotientController {   

    private EulersTotientService eulersTotientService;

    public EulersTotientController(EulersTotientService eulersTotientService) {
        this.eulersTotientService = eulersTotientService;
    }

    @GetMapping("/eulers-totient")
    public String showEulersTotientPage(Model model) {

        model.addAttribute("number", 0);
        model.addAttribute("result", new EulersTotientResult());

        return "numberTheory/eulersTotient";
    }

  @PostMapping("/eulers-totient")
public String calculateTotient(
        @RequestParam(required = false) String action,
        @RequestParam int number,
        @RequestParam(defaultValue = "0") int eulersSectionTwoCurrentStep,
        @RequestParam(defaultValue = "0") int eulersSection,
        
        Model model) {

    EulersTotientResult result = new EulersTotientResult();

   result.setNumber(number);
result.setHasResult(true);
result.setEulersSection(eulersSection);
result.setEulersSectionTwoCurrentStep(eulersSectionTwoCurrentStep);

handleSectionTwoActions(action, result);

result.setFactors(
        eulersTotientService.buildEulerFactors(number));

if (result.getEulersSectionTwoCurrentStep() >= 1) {
    eulersTotientService.simplifyEulerFactors(result.getFactors());
}

if (result.getEulersSectionTwoCurrentStep() >= 2) {
    eulersTotientService.buildSectionTwoWalkthroughState(result);
}

model.addAttribute("number", number);
model.addAttribute("result", result);

    return "numberTheory/eulersTotient";
}
    private void handleSectionTwoActions(
        String action,
        EulersTotientResult result) {

            if (action == null) {
                return;
            }

    switch (action) {

        case "proceed-to-eulers-totient-section-two":
            result.setEulersSection(2);
            result.setEulersSectionTwoCurrentStep(0);
            break;

        case "next-section-two-step":
            if (result.getEulersSectionTwoCurrentStep() < 6) {
                result.setEulersSectionTwoCurrentStep(
                        result.getEulersSectionTwoCurrentStep() + 1);
            }
            break;

        case "previous-section-two-step":
            if (result.getEulersSectionTwoCurrentStep() > 0) {
                result.setEulersSectionTwoCurrentStep(
                        result.getEulersSectionTwoCurrentStep() - 1);
            }
            break;
    }
}
}