document.addEventListener(
    "DOMContentLoaded",
        function () {
            const page = document.getElementById("linearSystemsPage");

            if (!page) {
                return;
            }

            const determinantStep = parseInt(page.dataset.determinantStep || 0);    
            console.log("determinantStep =", determinantStep);         
            const inverseCurrentStep = parseInt(page.dataset.inverseCurrentStep || 0);                
            const solveLinearStep = parseInt(page.dataset.solveLinearStep || 0);

            twoByTwoStepColours(determinantStep);
            linearSystemsInverseStepColours(inverseCurrentStep);
            solveLinearSystemsStepsColours(solveLinearStep);
        }
    );

function twoByTwoStepColours(determinantStep) {
    console.log("determinantStep =", determinantStep);
    clearColours();  
    if (determinantStep >= 1) {
        colourCells(
            [
                "cell-0-0",
                "cell-1-1",
                "positiveStep1"
            ],
            "#b8f2e6"
        );
    }
    // STEP 2
    if (determinantStep >= 2) {
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
    if (determinantStep >= 3) {
        colourCells(
            [
                "determinant",
                "finalDeterminant"
            ],
            "#ffff99"
        );
    }
}
    function linearSystemsInverseStepColours(inverseCurrentStep) {
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

// SOLVE LINEAR SYSTEM STEP 1
    function solveLinearSystemsStepsColours(solveLinearStep) {
        if (solveLinearStep >= 1) {
            colourCells(
                [
                    "inverse-final-0-0",
                    "constant-0",
                    "xMultiplyOne"
                ],
                "#cfe8ff"
            );
        }
        if (solveLinearStep >= 2) {
            clearColours();
            colourCells(
                [
                    "inverse-final-0-1",
                    "constant-1",
                    "xMultiplyTwo"
                ],
                "#b8f2e6"
                );
            }
        if (solveLinearStep >= 3) {
            clearColours();
            colourCells(
                ["xMultiplyOne", "xMultiplyTwo", "xAnswer", "solution-x"], "#ffff99");    
        }
        if (solveLinearStep >= 4) {
        clearColours();
            colourCells(
                [
                    "inverse-final-1-0",
                    "constant-0",
                    "yMultiplyOne"
                ],
                "#d5f5d5"
            );
        }
        if (solveLinearStep >= 5) {
            clearColours();
                colourCells(
                    [ "inverse-final-1-1", "constant-1", "yMultiplyTwo"],
                    "#f9d5d5"
                );
            }
        if (solveLinearStep >= 6) {
            clearColours();
                colourCells(
                    ["yMultiplyOne", "yMultiplyTwo", "yAnswer", "solution-y"],            
                    "#ffff99"
                );
            }
            if (solveLinearStep >= 7) {
                clearColours();
                colourCells(
                    ["solution-x", "solution-y"],                
                    "#ffff99"
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

function colourElementsByClass(
    className,
    colour
) {

    document
        .querySelectorAll(
            "." + className
        )
        .forEach(element => {

            element.style.backgroundColor =
                colour;
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
       
    
                
               