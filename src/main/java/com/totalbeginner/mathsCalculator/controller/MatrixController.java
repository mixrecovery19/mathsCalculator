package com.totalbeginner.mathsCalculator.controller;

import com.totalbeginner.mathsCalculator.service.MatrixService;
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
                @RequestParam(defaultValue = "false")
                boolean showSquareA,
                @RequestParam(defaultValue = "false")
                boolean showSquareB,
                @RequestParam(defaultValue = "false")
                boolean showScalarA,
                @RequestParam(defaultValue = "false")
                boolean showScalarB,
                @RequestParam(defaultValue = "1") double scalarValue,

                @RequestParam(required = false) String action, Model model) {    

        if ("change-size".equals(action)) {
                model.addAttribute("matrixA", new double[size][size]);   
                model.addAttribute("matrixB", new double[size][size]);   
                model.addAttribute("size", size);
                model.addAttribute("showAddition", false);
                model.addAttribute("showSubtraction", false);
                model.addAttribute("showMultiplication", false);
                model.addAttribute("scalarValue", 1);    

        return "matrixResult";
        }   

        double[][] matrixA = buildMatrixA(size, params);

        double[][] matrixB = buildMatrixB(size, params);    

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
        boolean newShowSquareA = false;
        boolean newShowSquareB = false;
        boolean newShowScalarA = false;
        boolean newShowScalarB = false;   

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
                newShowSquareA = showSquareA;
                newShowSquareB = showSquareB;
                newShowScalarA = showScalarA;
                newShowScalarB = showScalarB;

                } else {

                // Matrix A
                newShowDet = switchAction(action, "determinant", showDeterminant);                   
                newShowTrans = switchAction(action, "transpose", showTranspose);                       
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

                newShowSquareA =
                                switchAction(
                                        action,
                                        "square-a",
                                        showSquareA
                                );

                newShowSquareB =
                                switchAction(
                                        action,
                                        "square-b",
                                        showSquareB
                                );

                newShowScalarA =
                        switchAction(
                                action,
                                "scalar-a",
                                showScalarA
                        );
                newShowScalarB =
                        switchAction(
                                action,
                                "scalar-b",
                                showScalarB
                        );
                }
        }

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

        model.addAttribute(
                "showSquareA",
                newShowSquareA
        );
        model.addAttribute(
                "showSquareB",
                newShowSquareB
        );    
        model.addAttribute(
                "showScalarA",
                newShowScalarA
        );
        model.addAttribute(
                "showScalarB",
                newShowScalarB
        );

        restoreOpenSections(
                model,
                matrixA,
                newShowDet,
                newShowTrans,
                newShowInv,
                newShowId,
                newShowScalarA,
                newShowSquareA,
                scalarValue

        );
  
        restoreOpenSectionsB(
                model,
                matrixB,
                newShowDetB,
                newShowTransB,
                newShowInvB,
                newShowIdB,
                newShowScalarB,
                newShowSquareB,
                scalarValue
        );
                        
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
                        boolean showIdentity,
                        boolean showScalarA,
                        boolean showSquareA,
                        double scalarValue
                ) {

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
        if (showSquareA) {
            model.addAttribute("squareA", matrixService.squareMatrix(matrixA));
        }
        if (showScalarA) {
                        model.addAttribute("scalarA", matrixService.scalarMultiply(matrixA, scalarValue));
        }
        
    }

        private void restoreOpenSectionsB(
                Model model,
                double[][] matrixB,
                boolean showDeterminantB,
                boolean showTransposeB,
                boolean showInverseB,
                boolean showIdentityB,
                boolean showScalarB,
                boolean showSquareB,
                double scalarValue
                ) {

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
        if (showSquareB) {
            model.addAttribute("squareB", matrixService.squareMatrix(matrixB));
        }
        if (showScalarB) {
                        model.addAttribute("scalarB", matrixService.scalarMultiply(matrixB, scalarValue));
        }
        
    }

}