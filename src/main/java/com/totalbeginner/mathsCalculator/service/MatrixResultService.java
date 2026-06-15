package com.totalbeginner.mathsCalculator.service;

import com.totalbeginner.mathsCalculator.model.MatrixForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class MatrixResultService {

    private final MatrixRequestParser matrixRequestParser;
    private final MatrixResultModelBuilder matrixResultModelBuilder;

    public void process(MatrixForm form, Model model) {

        // 1. Handle special action: change size
        if ("change-size".equals(form.getAction())) {
            handleChangeSize(form, model);
            return;
        }

        // 2. Parse matrices
        double[][] matrixA = matrixRequestParser.buildMatrix(form.getSize(), form.getParams(), "cell_");
        double[][] matrixB = matrixRequestParser.buildMatrix(form.getSize(), form.getParams(), "cellB_");

        applyAction(form);       

        // 5. Add basic attributes
        model.addAttribute("size", form.getSize());
        model.addAttribute("matrixA", matrixA);
        model.addAttribute("matrixB", matrixB);
        model.addAttribute("scalarValue", form.getScalarValue());

        
        matrixResultModelBuilder.addMatrixAResults(
        model,
        matrixA,
        form.isShowDeterminant(),
        form.isShowTranspose(),
        form.isShowInverse(),
        form.isShowIdentity(),
        form.isShowScalarA(),
        form.isShowSquareA(),
        form.getScalarValue()
);

        matrixResultModelBuilder.addMatrixBResults(
        model,
        matrixB,
        form.isShowDeterminantB(),
        form.isShowTransposeB(),
        form.isShowInverseB(),
        form.isShowIdentityB(),
        form.isShowScalarB(),
        form.isShowSquareB(),
        form.getScalarValue()
);

        matrixResultModelBuilder.addCombinedResults(
        model,
        matrixA,
        matrixB,
        form.isShowAddition(),
        form.isShowSubtraction(),
        form.isShowMultiplication()
);
    }

    private void handleChangeSize(MatrixForm form, Model model) {
        model.addAttribute("size", form.getSize());
        model.addAttribute("matrixA", new double[form.getSize()][form.getSize()]);
        model.addAttribute("matrixB", new double[form.getSize()][form.getSize()]);
        model.addAttribute("scalarValue", 1.0);
        // Reset result flags if desired
    }    

  private void applyAction(MatrixForm form) {

    String action = form.getAction();

    if (action == null || action.isEmpty()) {
        return;
    }

    // Matrix A
    form.setShowDeterminant(
            switchAction(
                    action,
                    "determinant",
                    form.isShowDeterminant()
            )
    );

    form.setShowTranspose(
            switchAction(
                    action,
                    "transpose",
                    form.isShowTranspose()
            )
    );

    form.setShowInverse(
            switchAction(
                    action,
                    "inverse",
                    form.isShowInverse()
            )
    );

    form.setShowIdentity(
            switchAction(
                    action,
                    "identity",
                    form.isShowIdentity()
            )
    );

    // Matrix B
    form.setShowDeterminantB(
            switchAction(
                    action,
                    "determinant-b",
                    form.isShowDeterminantB()
            )
    );

    form.setShowTransposeB(
            switchAction(
                    action,
                    "transpose-b",
                    form.isShowTransposeB()
            )
    );

    form.setShowInverseB(
            switchAction(
                    action,
                    "inverse-b",
                    form.isShowInverseB()
            )
    );

    form.setShowIdentityB(
            switchAction(
                    action,
                    "identity-b",
                    form.isShowIdentityB()
            )
    );

    // Combined operations
    form.setShowAddition(
            switchAction(
                    action,
                    "addition",
                    form.isShowAddition()
            )
    );

    form.setShowSubtraction(
            switchAction(
                    action,
                    "subtraction",
                    form.isShowSubtraction()
            )
    );

    form.setShowMultiplication(
            switchAction(
                    action,
                    "multiplication",
                    form.isShowMultiplication()
            )
    );

    form.setShowSquareA(
            switchAction(
                    action,
                    "square-a",
                    form.isShowSquareA()
            )
    );

    form.setShowSquareB(
            switchAction(
                    action,
                    "square-b",
                    form.isShowSquareB()
            )
    );

    form.setShowScalarA(
            switchAction(
                    action,
                    "scalar-a",
                    form.isShowScalarA()
            )
    );

    form.setShowScalarB(
            switchAction(
                    action,
                    "scalar-b",
                    form.isShowScalarB()
            )
    );
}

    private boolean switchAction(String action, String section, boolean currentValue) {
        String calculate = "calculate-" + section;
        String close = "close-" + section;

        if (calculate.equals(action)) return true;
        if (close.equals(action)) return false;
        return currentValue;
    }
}