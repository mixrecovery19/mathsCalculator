document.addEventListener(
    "DOMContentLoaded",
    function () {

        const page =
            document.getElementById(
                "matrixTwoByTwoPage"
            );

        if (!page) {
            return;
        }

        const currentStep =
            parseInt(
                page.dataset.currentStep || 0
            );

        const inverseCurrentStep =
            parseInt(
                page.dataset.inverseCurrentStep || 0
            );

        twoByTwoStepColours(
            currentStep,
            inverseCurrentStep
        );
    }
);

function twoByTwoStepColours(currentStep, inverseCurrentStep) {
    clearColours();
    // STEP 1
    if (currentStep >= 1) {
        colourCells(
            [
                "cell-0-0",
                "cell-1-1",
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
                "cell-1-0",
                "negativeStep1"
            ],
            "#f9d5d5"
        );
    }

    // STEP 3
    if (currentStep >= 3) {
        colourCells(
            [
                "determinant",
                "finalDeterminant"
            ],
            "#ffff99"
        );
    }

    // INVERSE STEP 1
    if (inverseCurrentStep >= 1) {
        colourCells(
            [
                "inverse-cell-0-0",
                "inverse-cell-1-1"
            ],
            "#fff4a8"
        );
    }

    // INVERSE STEP 2
    if (inverseCurrentStep >= 2) {
        colourCells(
            [
                "inverse-cell-0-1",
                "inverse-cell-1-0"
            ],
            "#f9d5d5"
        );
    }

    // INVERSE STEP 3
    if (inverseCurrentStep >= 3) {

        colourCells(
            [
                "inverseDeterminant"
            ],
            "#cfe8ff"
        );
    }

    // FINAL INVERSE
    if (inverseCurrentStep >= 4) {
        colourElementsByClass(
            "final-inverse-cell",
            "#d5f5d5"
        );
    }
}

function colourCells(cellIds, colour) {
    cellIds.forEach(id => {
        const cell = document.getElementById(id);
        if (cell) {
            cell.style.backgroundColor = colour;
        }
    });
}

function colourElementsByClass(className, colour) {
    document.querySelectorAll("." + className)
        .forEach(element => {
            element.style.backgroundColor = colour;
        });
}

function clearColours() {
    document
        .querySelectorAll(".matrix-input")
        .forEach(cell => {
            cell.style.backgroundColor = "";
            cell.style.borderColor = "";
    });
}