package com.totalbeginner.mixrecovery19.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totalbeginner.mixrecovery19.service.MatrixService;

@Controller
public class MatrixController {

    private final MatrixService matrixService;

    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @GetMapping("/matrices")
    public String matricesPage(Model model) {

        return "matrices";
    }

    @PostMapping("/matrices/create")
    public String createMatrix(@RequestParam("size") int size, Model model) {

        model.addAttribute("size", size);
        // Logic for creating matrix
        return "matrixInput";
    }

    @PostMapping("/matrices/determinant")
    public String determinant(@RequestParam int size, @RequestParam Map<String, String> params, Model model)
    {
        double[][] matrix = new double[size][size];

        for (int row = 0;
            row < size; row++) {

            for (int col = 0; col < size; col++) {

                String key = "cell_" + row + "_" + col;

                matrix[row][col] = Double.parseDouble(params.get(key));
                
            }
        }

        model.addAttribute("matrix", matrix);
        model.addAttribute("size",size);
                

        return "matrixResult";
    }
    @PostMapping("/matrices/calculate-determinant")
    public String calculateDeterminant(@RequestParam int size, @RequestParam Map<String, String> params, Model model)
    {
    double[][] matrix = new double[size][size];

    for (int row = 0; row < size; row++) {
        for (int col = 0; col < size; col++) {

            String key = "cell_" + row + "_" + col;

            matrix[row][col] = Double.parseDouble(params.get(key));               
        }
    }

    double determinant = matrixService.determinant(matrix);
        model.addAttribute("matrix", matrix);   
        model.addAttribute("size", size);
        model.addAttribute("determinant", determinant);    

    return "matrixResult";
    }

    @PostMapping("/matrices/close-determinant")
    public String closeDeterminant(@RequestParam int size, @RequestParam Map<String, String> params, Model model)
    {
        double[][] matrix = buildMatrix(size, params);                

        model.addAttribute("matrix", matrix);        
        model.addAttribute("size", size);
        
        return "matrixResult";
    }

   @PostMapping("/matrices/transpose")
        public String transposeMatrix(
                @RequestParam int size,
                @RequestParam Map<String, String> params,
                @RequestParam(defaultValue = "false")
                boolean showDeterminant,
                @RequestParam(defaultValue = "false")
                boolean showInverse,
                Model model
        )
        {
            double[][] matrix = buildMatrix(size, params);

            double[][] transpose = matrixService.transpose(matrix);

            model.addAttribute("matrix", matrix);           
            model.addAttribute("transpose", transpose);         
            model.addAttribute("size", size); 
            model.addAttribute("matrixService", matrixService);           

            // reopen determinant if it was already open
            if (showDeterminant) {
                model.addAttribute("determinant", matrixService.determinant(matrix));
                
            }
            // reopen inverse if it was already open
            if (showInverse) {
                double[][] inverse = matrixService.inverse(matrix);
                model.addAttribute("inverse", inverse);
                
                if (inverse == null) {

                    model.addAttribute(
                            "inverseError",
                            "This matrix has no inverse because its determinant is 0."
                    );
                }
            }

            return "matrixResult";
        }

    @PostMapping("/matrices/close-transpose")
    public String closeTranspose(@RequestParam int size, @RequestParam Map<String, String> params, Model model)
    {
        double[][] matrix = buildMatrix(size, params);
        model.addAttribute("matrix", matrix);
        model.addAttribute("size", size);

        return "matrixResult";
    }

    private double[][] buildMatrix(int size, Map<String, String> params)
    {
        double[][] matrix = new double[size][size];

        for (int row = 0; row < size; row++) {

            for (int col = 0; col < size; col++) {

                String key = "cell_" + row + "_" + col;

                matrix[row][col] = Double.parseDouble(params.get(key));
            }
        }
        return matrix;
    }
    @PostMapping("/matrices/inverse")
        public String inverseMatrix(
                @RequestParam int size,
                @RequestParam Map<String, String> params,
                @RequestParam(defaultValue = "false")
                boolean showDeterminant,
                @RequestParam(defaultValue = "false")
                boolean showTranspose,
                Model model
        )
        {
            double[][] matrix =
                    buildMatrix(size, params);

            double[][] inverse =
                    matrixService.inverse(matrix);
            double inverseDeterminant = matrixService.determinant(matrix);

            model.addAttribute(
                    "inverseDeterminant",
                    inverseDeterminant
            );
            model.addAttribute(
                    "matrix",
                    matrix
            );
            model.addAttribute("matrixService", matrixService);

            model.addAttribute(
                    "size",
                    size
            );

            model.addAttribute(
                    "inverse",
                    inverse
            );

            // restore determinant if open
            if (showDeterminant) {

                model.addAttribute(
                        "determinant",
                        matrixService.determinant(matrix)
                );
            }

            // restore transpose if open
            if (showTranspose) {

                model.addAttribute(
                        "transpose",
                        matrixService.transpose(matrix)
                );
            }

            // inverse error handling
            if (inverse == null) {

                model.addAttribute(
                        "inverseError",
                        "This matrix has no inverse because its determinant is 0."
                );
            }

            return "matrixResult";
        }

    @PostMapping("/matrices/close-inverse")
    public String closeInverse(@RequestParam int size, @RequestParam Map<String, String> params, Model model)
    {
        double[][] matrix = buildMatrix(size, params);
        model.addAttribute("matrix", matrix);
        model.addAttribute("size", size);

        return "matrixResult";
    }

    @PostMapping("/matrices/identity")
    public String identityMatrix(
            @RequestParam int size,
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "false")
            boolean showDeterminant,
            @RequestParam(defaultValue = "false")
            boolean showTranspose,
            @RequestParam(defaultValue = "false")
            boolean showInverse,
            Model model
    )
    {
        double[][] matrix =
                buildMatrix(size, params);

        double[][] identity =
                matrixService.identity(matrix);

        model.addAttribute(
                "matrix",
                matrix
        );

        model.addAttribute(
                "size",
                size
        );

        model.addAttribute(
                "identity",
                identity
        );

        model.addAttribute(
                "matrixService",
                matrixService
        );

        // restore determinant
        if (showDeterminant) {

            model.addAttribute(
                    "determinant",
                    matrixService.determinant(matrix)
            );
        }

        // restore transpose
        if (showTranspose) {

            model.addAttribute(
                    "transpose",
                    matrixService.transpose(matrix)
            );
        }

        // restore inverse
        if (showInverse) {

            model.addAttribute(
                    "inverse",
                    matrixService.inverse(matrix)
            );
        }

        if (identity == null) {

            model.addAttribute(
                    "inverseError",
                    "This matrix has no inverse because its determinant is 0."
            );
        }

        return "matrixResult";
    }

    @PostMapping("/matrices/close-identity")
    public String closeIdentity(
            @RequestParam int size,
            @RequestParam Map<String, String> params,
            Model model
    )
    {
        double[][] matrix =
                buildMatrix(size, params);

        model.addAttribute(
                "matrix",
                matrix
        );

        model.addAttribute(
                "size",
                size
        );

        return "matrixResult";
    }
}