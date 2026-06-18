document.addEventListener(
    "DOMContentLoaded",
    function () {

        const page =
            document.getElementById(
                "matrixInverse3x3Page"
            );

        if (!page) {
            return;
        }

        const inverse3x3CurrentStep =
            parseInt(
                page.dataset.inverseCurrentStep || 0
            );

        inverse3x3StepColours(
            inverse3x3CurrentStep
        );
    }
);

function inverse3x3StepColours(
    inverse3x3CurrentStep
) {

    clearColours();    
    if (inverse3x3CurrentStep >= 5) {

        // Step 5
        colourCells([
            "inverse-cell-0-0",
            "inverse-cell-1-1",
            "positiveDiagonal"
        ], "#fff4a8");
            }

    
    if (inverse3x3CurrentStep >= 6) {
        // Step 6
        colourCells([
            "inverse-cell-0-1",
            "inverse-cell-1-0",
            "negativeDiagonal"
        ], "#f9d5d5");            
        }

    // STEP 7
    if (inverse3x3CurrentStep >= 7) {
        colourCells(
            [                
                "positiveDiagonal",
                "negativeDiagonal",
                "diagonalResult",
                "diagonalResultValue"
            ],
            "#d5f5d5"
        );
    }

    // STEP 7
    if (inverse3x3CurrentStep >= 8) {

        colourCells(
            [
                "cell-2-0",
                "cell-1-1",
                "cell-0-2",
                "negativeStep1"
            ],
            "#f9d5d5"
        );
    }

    // STEP 5
    if (inverse3x3CurrentStep >= 9) {

        colourCells(
            [
                "cell-2-1",
                "cell-1-2",
                "cell-0-3",
                "negativeStep2"
            ],
            "#ffe6bf"
        );
    }
    // STEP 6
    if (inverse3x3CurrentStep >= 10) {

        colourCells(
            [
                "cell-2-2",
                "cell-1-3",
                "cell-0-4",
                "negativeStep3"
            ],
            "#ead7ff"
        );
    }
    // STEP 7
    if (inverse3x3CurrentStep >= 11) {
        colourCells(
            [
                "positiveStep1",
                "positiveStep2",
                "positiveStep3",
                "positiveTotal"
            ],
            "#b8f2e6"
        );
    }
    // STEP 8
    if (inverse3x3CurrentStep >= 12) {
        colourCells(
            [
                "negativeStep1",
                "negativeStep2",
                "negativeStep3",
                "negativeTotal"
            ],
            "#ffb3b3"
        );
    }

    // STEP 9
    if (inverse3x3CurrentStep >= 13) {

        colourCells(
            [
                "determinant"
            ],
            "#ffff99"
        );
    }
}

function colourCells(
    cellIds,
    colour
) {

    cellIds.forEach(id => {

        const cell =
            document.getElementById(id);

        if (cell) {

            cell.style.backgroundColor =
                colour;
        }
    });
}

function clearColours() {

    document
        .querySelectorAll(
            ".matrix-input"
        )
        .forEach(cell => {

            cell.style.backgroundColor =
                "";

            cell.style.borderColor =
                "";
        });
}