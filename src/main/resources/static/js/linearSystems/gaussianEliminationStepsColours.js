document.addEventListener(
    "DOMContentLoaded",
        function () {
            const page = document.getElementById("gaussianEliminationPage");

            if (!page) {
                return;
            }

            const gaussianEliminationSectionTwoStep = parseInt(page.dataset.gaussianEliminationSectionTwoStep || 0);    
            const gaussianEliminationSectionThreeStep = parseInt(page.dataset.gaussianEliminationSectionThreeStep || 0);                  
            
            gaussianEliminationSectionTwoStepsColours(gaussianEliminationSectionTwoStep);
            gaussianEliminationSectionThreeStepsColours(gaussianEliminationSectionThreeStep);

        }
    );

    function gaussianEliminationSectionTwoStepsColours(step) {
        clearColours();
        if (step >= 1) {

            colourCells(
                [
                    // Original augmented matrix
                    "originalMatrixCell_0_0",      // a (pivot)
                    "originalMatrixCell_1_0",     // d (value beneath pivot)
                    // Elimination factor (d/a)
                    "gaussianFactorD",
                    "gaussianFactorA",
                    "gaussianEliminationFactor"
                ],
                "#cfe8ff"
            );
        }

    if (step >= 2) {
        clearColours();
            colourCells(
                [ "originalMatrixCell_0_0",
                    "gaussianEliminationFactor",
                    "scaledA",                        
                ], "#d8f3dc"
                );
            }

    if (step >= 3) {
        clearColours();
        colourCells(
            [
                "originalMatrixCell_0_1",
                "gaussianEliminationFactor",
                "scaledB",
            ],
            "#ffe8a3"
        );
    }

    if (step >= 4) {
        clearColours();
        colourCells(
            [
                "originalMatrixConstant_0",
                "gaussianEliminationFactor",
                "scaledConstant",
            ],
            "#cfe8ff"
        );
    }  
    if (step >= 5) {
        clearColours();
        colourCells(
            [
                "originalMatrixCell_1_0",    // d (value beneath pivot)
                 "scaledA",
                 "newRowA" 
            ],
            "#cfe8ff"
        );
    }  
    if (step >= 6) {
        clearColours();
        colourCells(
            [
                "originalMatrixCell_1_1",    // e (value beneath pivot)
                 "scaledB",
                 "newRowB" 
            ],
            "#ffe8a3"
        );
    }  
    if (step >= 7) {
        clearColours();
        colourCells(
            [
                "originalMatrixConstant_1",    // f (value beneath pivot)
                 "scaledConstant",
                 "newRowC" 
            ],
            "#d8f3dc"
        );
    }
    if (step >= 8) {
        clearColours();
        colourCells(
            [
                "resultMatrix00",
                "resultMatrix01",
                "resultMatrix02",
                "resultMatrix10",
                "resultMatrix11",
                "resultMatrix12"
            ],
             "#fff4a8"
        );
    }
}
function gaussianEliminationSectionThreeStepsColours(step) {
        clearColours();
        if (step >= 1) {
           colourCells(
                    [
                        "sectionThreeResultMatrix11",      // e'
                        "sectionThreeResultMatrix12",      // f'
                        "gaussianYDenominator",
                        "gaussianYNumerator"
                    ], "#cfe8ff");
        }
        if (step >= 2) {
            clearColours();
                colourCells(
                    [ "gaussianYDenominator",
                        "gaussianYNumerator",
                        "gaussianYAnswer" ],
                    "#d8f3dc"
                );
            }
            if (step >= 3) {
            clearColours();
                
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
    
       
    
                
            