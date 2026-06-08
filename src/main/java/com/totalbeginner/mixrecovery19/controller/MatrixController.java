package com.totalbeginner.mixrecovery19.controller;

import com.totalbeginner.mixrecovery19.service.MatrixService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MatrixController {

    private final MatrixService matrixService;

    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @GetMapping("/matrixResult")
    public String matricesPage(Model model) {
        int defaultSize = 2;

        model.addAttribute("size", defaultSize);
        model.addAttribute("matrixA", new double[defaultSize][defaultSize]);
        model.addAttribute("matrixB", new double[defaultSize][defaultSize]);
        return "matrixResult";
    }

    /*@PostMapping("/matrices/create")
    public String createMatrix(@RequestParam("size") int size, Model model) {
        model.addAttribute("size", size);
        return "matrixInput";
    }*/

    /**
     * SINGLE endpoint for ALL result-page actions (calculate / close any section).
     * The form sends:
     *   - size + cell_* parameters
     *   - current show* flags (hidden inputs)
     *   - action parameter from the submitted button
     */
        @PostMapping("/matrices/result")
        public String handleMatrixAction(
                @RequestParam int size,
                @RequestParam Map<String, String> params,
                @RequestParam(defaultValue = "false") boolean showDeterminant,
                @RequestParam(defaultValue = "false") boolean showTranspose,
                @RequestParam(defaultValue = "false") boolean showInverse,
                @RequestParam(defaultValue = "false") boolean showIdentity,
                @RequestParam(defaultValue = "false") boolean showDeterminantB,
                @RequestParam(defaultValue = "false") boolean showTransposeB,
                @RequestParam(defaultValue = "false") boolean showInverseB,
                @RequestParam(defaultValue = "false") boolean showIdentityB,

                @RequestParam(required = false) String action,

                Model model) {

        if ("change-size".equals(action)) {
                // Fresh empty matrix for the new size
                model.addAttribute("matrixA", new double[size][size]);
                model.addAttribute("matrixB", new double[size][size]);

                model.addAttribute("size", size);
                // Force all sections closed on size change
                model.addAttribute("determinant", null);
                model.addAttribute("transpose", null);
                model.addAttribute("inverse", null);
                model.addAttribute("identity", null);

                return "matrixResult";
        }

    // Normal actions (update-matrix, calculate-*, close-*, etc.)
    double[][] matrixA = buildMatrixA(size, params);
    double[][] matrixB = buildMatrixB(size, params);

        boolean newShowDet  = false;
        boolean newShowTrans = false;
        boolean newShowInv   = false;
        boolean newShowId    = false;
        boolean newShowDetB = false;
        boolean newShowTransB = false;
        boolean newShowInvB = false;
        boolean newShowIdB = false;

    if (action != null && !action.isEmpty()) {
        if ("update-matrix".equals(action)) {
            // Keep whatever sections were already open
            newShowDet  = showDeterminant;
            newShowTrans = showTranspose;
            newShowInv   = showInverse;
            newShowId    = showIdentity;
            newShowDetB = showDeterminantB;
            newShowTransB = showTransposeB;
            newShowInvB = showInverseB;
            newShowIdB = showIdentityB;

        } else {
            // Toggle logic for calculate/close buttons
                newShowDet  = switchAction(action, "determinant", showDeterminant);
                newShowTrans = switchAction(action, "transpose", showTranspose);
                newShowInv   = switchAction(action, "inverse", showInverse);
                newShowId    = switchAction(action, "identity", showIdentity);
                newShowDetB  = switchAction(action, "determinant-b", showDeterminantB);
                newShowTransB = switchAction(action, "transpose-b", showTransposeB);
                newShowInvB   = switchAction(action, "inverse-b", showInverseB);
                newShowIdB    = switchAction(action, "identity-b", showIdentityB);
        }
    }

        model.addAttribute("matrixA", matrixA);
        model.addAttribute("matrixB", matrixB);
        model.addAttribute("size", size);

    restoreOpenSections(model, matrixA, newShowDet, newShowTrans, newShowInv, newShowId);
    restoreOpenSectionsB(model, matrixB, newShowDetB, newShowTransB, newShowInvB, newShowIdB);

    return "matrixResult";
}

    private boolean switchAction(String action, String section, boolean currentValue) {
        String calculate = "calculate-" + section;
        String close = "close-" + section;

        if (calculate.equals(action)) return true;
        if (close.equals(action)) return false;
        return currentValue; // no change for this section
    }
    // =================================================================
    // Helper methods (unchanged except minor cleanup)
    // =================================================================

    private double[][] buildMatrixA(int size, Map<String, String> params) {
        double[][] matrix = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String key = "cell_" + row + "_" + col;
                String value = params.get(key);
                matrix[row][col] = (value == null || value.isBlank()) ? 0 : Double.parseDouble(value);                
            }
        }
        return matrix;
    }

    private double[][] buildMatrixB(int size, Map<String, String> params) {
        double[][] matrix = new double[size][size];

                for (int row = 0; row < size; row++) {
                        for (int col = 0; col < size; col++) {
                        String key = "cellB_" + row + "_" + col;
                        String value = params.get(key);
                        matrix[row][col] = (value == null || value.isBlank()) ? 0 : Double.parseDouble(value);
                        }
                }
                return matrix;
        }

    private void restoreOpenSections(
            Model model,
            double[][] matrixA,
            boolean showDeterminant,
            boolean showTranspose,
            boolean showInverse,
            boolean showIdentity) {

        model.addAttribute("matrixService", matrixService); // kept only because original template used it

        if (showDeterminant) {
            double determinant = matrixService.determinant(matrixA);
            model.addAttribute("determinant", determinant);
            model.addAttribute("formattedDeterminant", matrixService.formatDeterminant(determinant));
        }

        if (showTranspose) {
            model.addAttribute("transpose", matrixService.transpose(matrixA));
        }

        if (showInverse) {
            double[][] inverse = matrixService.inverse(matrixA);
            model.addAttribute("inverse", inverse);
            model.addAttribute("inverseDeterminant", matrixService.determinant(matrixA));

            if (inverse == null) {
                model.addAttribute("inverseError", "This matrix has no inverse because its determinant is 0.");
            }
        }

        if (showIdentity) {
            model.addAttribute("identity", matrixService.identity(matrixA));
        }
    }

    private void restoreOpenSectionsB(
            Model model,
            double[][] matrixB,
            boolean showDeterminantB,
            boolean showTransposeB,
            boolean showInverseB,
            boolean showIdentityB) {

        model.addAttribute("matrixService", matrixService);

        if (showDeterminantB) {
            double determinant = matrixService.determinant(matrixB);
            model.addAttribute("determinantB", determinant);
            model.addAttribute("formattedDeterminantB", matrixService.formatDeterminant(determinant));
        }

        if (showTransposeB) {
            model.addAttribute("transposeB", matrixService.transpose(matrixB));
        }

        if (showInverseB) {
            double[][] inverse = matrixService.inverse(matrixB);
            model.addAttribute("inverseB", inverse);
            model.addAttribute("inverseDeterminantB", matrixService.determinant(matrixB));

            if (inverse == null) {
                model.addAttribute("inverseErrorB", "This matrix has no inverse because its determinant is 0.");
            }
        }

        if (showIdentityB) {
            model.addAttribute("identityB", matrixService.identity(matrixB));
        }
    }

}