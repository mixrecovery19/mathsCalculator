package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.model.MatrixForm;
import com.totalbeginner.mathsCalculator.service.MatrixResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MatrixController {

    private final MatrixResultService matrixResultService;

        @GetMapping("/matrixResult")
        public String matricesPage(Model model) {

                int defaultSize = 2;
                MatrixForm matrixForm = new MatrixForm();
                matrixForm.setSize(defaultSize);

                model.addAttribute("matrixForm", matrixForm);
                model.addAttribute("size", defaultSize);
                model.addAttribute("matrixA", new double[defaultSize][defaultSize]);
                model.addAttribute("matrixB", new double[defaultSize][defaultSize]);

                return "matrixResult";
                }

        @PostMapping("/matrices/result")
        public String handleMatrixAction(
                @RequestParam int size,
                @RequestParam Map<String, String> params,
                @RequestParam(required = false) String action,
                MatrixForm form,           // Spring will bind the booleans here
                Model model) {

                // Manually set fields that Spring doesn't auto-bind well
                form.setSize(size);
                form.setParams(params);
                form.setAction(action);

                matrixResultService.process(form, model);
                model.addAttribute("matrixForm", form);
                return "matrixResult";
        }
}