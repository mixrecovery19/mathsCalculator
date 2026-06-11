package com.totalbeginner.mixrecovery19.controller;

import com.totalbeginner.mixrecovery19.service.MatrixSarrusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MatrixSarrusController {

    private final MatrixSarrusService matrixSarrusService;

    public MatrixSarrusController(
            MatrixSarrusService matrixSarrusService
    ) {
        this.matrixSarrusService =
                matrixSarrusService;
    }

    @GetMapping("/matrix-sarrus")
    public String matrixSarrusPage(Model model) {

        int defaultSize = 3;

        model.addAttribute(
                "size",
                defaultSize
        );

        model.addAttribute(
                "matrixSarrus",
                new double[3][3]
        );

        return "matrixSarrus";
    }

    @PostMapping("/matrices-sarrus")
    public String handleSarrusAction(

            @RequestParam int size,
            @RequestParam Map<String, String> params,
            @RequestParam(required = false)
            String action,

            Model model
    ) {

        double[][] matrix =
                buildMatrix(size, params);

        model.addAttribute(
                "size",
                size
        );

        model.addAttribute(
                "matrixSarrus",
                matrix
        );

        // THIS is the if condition you need
        if ("generate-sarrus".equals(action)) {

            double[][] sarrusMatrix =
                    matrixSarrusService
                            .buildSarrusMatrix(
                                    matrix
                            );

            double determinant =
                    matrixSarrusService
                            .determinantSarrus(
                                    sarrusMatrix
                            );

            model.addAttribute(
                    "sarrusMatrix",
                    sarrusMatrix
            );

            model.addAttribute(
                    "determinant",
                    determinant
            );
        }

        return "matrixSarrus";
    }

    private double[][] buildMatrix(
            int size,
            Map<String, String> params
    ) {

        double[][] matrix =
                new double[size][size];

        for (int row = 0; row < size; row++) {

            for (int col = 0; col < size; col++) {

                String key =
                        "cell_"
                        + row
                        + "_"
                        + col;

                String value =
                        params.get(key);

                matrix[row][col] =
                        (
                                value == null
                                || value.isBlank()
                        )
                        ? 0
                        : Double.parseDouble(
                                value
                        );
            }
        }

        return matrix;
    }
}