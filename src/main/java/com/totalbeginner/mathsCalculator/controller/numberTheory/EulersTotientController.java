package com.totalbeginner.mathsCalculator.controller.numberTheory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totalbeginner.mathsCalculator.dto.numberTheory.EulersTotientResult;
import com.totalbeginner.mathsCalculator.service.numberTheory.EulersTotientService;

@Controller
public class EulersTotientController {   

    private EulersTotientService eulersTotientService;

    public EulersTotientController(EulersTotientService eulersTotientService) {
        this.eulersTotientService = eulersTotientService;
    }

    @GetMapping("/eulers-totient")
    public String showEulersTotientPage(Model model) {

        model.addAttribute("number", null);
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

    result.setFactors(
        eulersTotientService.buildEulerFactors(number));

    String redirect = handleSectionTwoActions(action, result);
    if (redirect != null) {
        return redirect;
    }

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
    private String handleSectionTwoActions(
        String action,
        EulersTotientResult result) {

    if (action == null) {
        return null;
    }

    int maximumStep = result.getFactors().size() + 1;

    switch (action) {
        case "proceed-to-eulers-totient-section-two":
            result.setEulersSection(2);
            result.setEulersSectionTwoCurrentStep(0);
            break;

        case "next-section-two-step":
            if (result.getEulersSectionTwoCurrentStep() < maximumStep) {
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
        case "restart-eulers-totient":
            return "redirect:/eulers-totient";
           
        }
        return null;
    }
}