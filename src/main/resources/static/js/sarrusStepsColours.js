document.addEventListener(
    "DOMContentLoaded",
    function () {

        const page =
            document.getElementById(
                "matrixSarrusPage"
            );

        if (!page) {
            return;
        }

        const currentStep =
            parseInt(
                page.dataset.currentStep || 0
            );

        sarrusStepColours(
            currentStep
        );
    }
);

function sarrusStepColours(
    currentStep
) {

    clearColours();

    // STEP 1
    if (currentStep >= 1) {

        colourCells(
            [
                "cell-0-0",
                "cell-1-1",
                "cell-2-2",
                "positiveStep1"
            ],
            "#fff4a8"
        );
    }

    // STEP 2
    if (currentStep >= 2) {

        colourCells(
            [
                "cell-0-1",
                "cell-1-2",
                "cell-2-3",
                "positiveStep2"
            ],
            "#cfe8ff"
        );
    }

    // STEP 3
    if (currentStep >= 3) {

        colourCells(
            [
                "cell-0-2",
                "cell-1-3",
                "cell-2-4",
                "positiveStep3"
            ],
            "#d5f5d5"
        );
    }

    // STEP 4
    if (currentStep >= 4) {

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
    if (currentStep >= 5) {

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
    if (currentStep >= 6) {

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
    if (currentStep >= 7) {

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
    if (currentStep >= 8) {

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
    if (currentStep >= 9) {

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