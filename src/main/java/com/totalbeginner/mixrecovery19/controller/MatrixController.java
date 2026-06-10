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

   @PostMapping("/matrices/result")
public String handleMatrixAction(

        @RequestParam int size,
        @RequestParam Map<String, String> params,

        @RequestParam(defaultValue = "false")
        boolean showDeterminant,

        @RequestParam(defaultValue = "false")
        boolean showTranspose,

        @RequestParam(defaultValue = "false")
        boolean showInverse,

        @RequestParam(defaultValue = "false")
        boolean showIdentity,

        @RequestParam(defaultValue = "false")
        boolean showDeterminantB,

        @RequestParam(defaultValue = "false")
        boolean showTransposeB,

        @RequestParam(defaultValue = "false")
        boolean showInverseB,

        @RequestParam(defaultValue = "false")
        boolean showIdentityB,

        @RequestParam(defaultValue = "false")
        boolean showAddition,

        @RequestParam(defaultValue = "false")
        boolean showSubtraction,

        @RequestParam(defaultValue = "false")
        boolean showMultiplication,

        @RequestParam(required = false)
        String action,

        Model model
    ) {

    if ("change-size".equals(action)) {

    model.addAttribute(
            "matrixA",
            new double[size][size]
    );

    model.addAttribute(
            "matrixB",
            new double[size][size]
    );

    model.addAttribute("size", size);

    model.addAttribute("showAddition", false);
    model.addAttribute("showSubtraction", false);
    model.addAttribute("showMultiplication", false);

    return "matrixResult";
}

    // ==========================================
    // BUILD MATRICES
    // ==========================================

    double[][] matrixA =
            buildMatrixA(size, params);

    double[][] matrixB =
            buildMatrixB(size, params);

    // ==========================================
    // UI STATE FLAGS
    // ==========================================

    boolean newShowDet = false;
    boolean newShowTrans = false;
    boolean newShowInv = false;
    boolean newShowId = false;

    boolean newShowDetB = false;
    boolean newShowTransB = false;
    boolean newShowInvB = false;
    boolean newShowIdB = false;

    boolean newShowAddition = false;
    boolean newShowSubtraction = false;
    boolean newShowMultiplication = false;

    // ==========================================
    // ACTION HANDLING
    // ==========================================

    if (action != null && !action.isEmpty()) {

        // Matrix update buttons
        if ("update-matrix-a".equals(action)
                || "update-matrix-b".equals(action)) {

            // Preserve Matrix A sections
            newShowDet = showDeterminant;
            newShowTrans = showTranspose;
            newShowInv = showInverse;
            newShowId = showIdentity;

            // Preserve Matrix B sections
            newShowDetB = showDeterminantB;
            newShowTransB = showTransposeB;
            newShowInvB = showInverseB;
            newShowIdB = showIdentityB;

            // Preserve result operations
            newShowAddition = showAddition;
            newShowSubtraction = showSubtraction;
            newShowMultiplication = showMultiplication;

        } else {

            // Matrix A
            newShowDet =
                    switchAction(
                            action,
                            "determinant",
                            showDeterminant
                    );

            newShowTrans =
                    switchAction(
                            action,
                            "transpose",
                            showTranspose
                    );

            newShowInv =
                    switchAction(
                            action,
                            "inverse",
                            showInverse
                    );

            newShowId =
                    switchAction(
                            action,
                            "identity",
                            showIdentity
                    );

            // Matrix B
            newShowDetB =
                    switchAction(
                            action,
                            "determinant-b",
                            showDeterminantB
                    );

            newShowTransB =
                    switchAction(
                            action,
                            "transpose-b",
                            showTransposeB
                    );

            newShowInvB =
                    switchAction(
                            action,
                            "inverse-b",
                            showInverseB
                    );

            newShowIdB =
                    switchAction(
                            action,
                            "identity-b",
                            showIdentityB
                    );

            // Result operations
            newShowAddition =
                    switchAction(
                            action,
                            "addition",
                            showAddition
                    );

            newShowSubtraction =
                    switchAction(
                            action,
                            "subtraction",
                            showSubtraction
                    );

            newShowMultiplication =
                    switchAction(
                            action,
                            "multiplication",
                            showMultiplication
                    );
        }
    }

    // ==========================================
    // SEND MATRICES TO VIEW
    // ==========================================

    model.addAttribute("matrixA", matrixA);
    model.addAttribute("matrixB", matrixB);
    model.addAttribute("size", size);

    // Persist operation state
    model.addAttribute(
            "showAddition",
            newShowAddition
    );

    model.addAttribute(
            "showSubtraction",
            newShowSubtraction
    );

    model.addAttribute(
            "showMultiplication",
            newShowMultiplication
    );

    // ==========================================
    // MATRIX A SECTIONS
    // ==========================================

    restoreOpenSections(
            model,
            matrixA,
            newShowDet,
            newShowTrans,
            newShowInv,
            newShowId
    );

    // ==========================================
    // MATRIX B SECTIONS
    // ==========================================

    restoreOpenSectionsB(
            model,
            matrixB,
            newShowDetB,
            newShowTransB,
            newShowInvB,
            newShowIdB
    );

    // ==========================================
    // MATRIX OPERATIONS
    // ==========================================

    if (newShowAddition) {

        model.addAttribute(
                "additionResult",
                matrixService.addMatrices(
                        matrixA,
                        matrixB
                )
        );
    }

    if (newShowSubtraction) {

        model.addAttribute(
                "subtractionResult",
                matrixService.subtractMatrices(
                        matrixA,
                        matrixB
                )
        );
    }

    if (newShowMultiplication) {

        model.addAttribute(
                "multiplicationResult",
                matrixService.multiplyMatrices(
                        matrixA,
                        matrixB
                )
        );
    }

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