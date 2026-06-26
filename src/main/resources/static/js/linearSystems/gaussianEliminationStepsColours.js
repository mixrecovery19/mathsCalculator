document.addEventListener(
    "DOMContentLoaded",
    function () {

        const page = document.getElementById("gaussianEliminationPage");

        if (!page) {
            return;
        }

        const currentSection = parseInt(
            page.dataset.currentGaussianSection || 0
        );

        const gaussianEliminationSectionTwoStep = parseInt(
            page.dataset.gaussianEliminationSectionTwoStep || 0
        );

        const gaussianEliminationSectionThreeStep = parseInt(
            page.dataset.gaussianEliminationSectionThreeStep || 0
        );
        const gaussianEliminationSectionFourStep = parseInt(
            page.dataset.gaussianEliminationSectionFourStep || 0
        );

        if (currentSection === 2) {
            gaussianEliminationSectionTwoStepsColours(
                gaussianEliminationSectionTwoStep
            );
        }

        if (currentSection === 3) {
            gaussianEliminationSectionThreeStepsColours(
                gaussianEliminationSectionThreeStep
            );
        }
        if (currentSection === 4) {
            gaussianEliminationSectionFourStepsColours(
                gaussianEliminationSectionFourStep
            );
        }

    }
);
    function gaussianEliminationSectionTwoStepsColours(step) {    
        clearColours();                    // ← Keep only this one

        if (step >= 1) {                
            colourCells(
                [
                    "originalMatrixCell_0_0",
                    "originalMatrixCell_1_0",
                    "gaussianFactorD",
                    "gaussianFactorA",
                    "gaussianEliminationFactor"
                ],
                "#cfe8ff"
            );
        }

        if (step >= 2) {
            clearColours();                    // ← Keep only this one
            colourCells(
                ["originalMatrixCell_0_0", "gaussianEliminationFactor", "scaledA"],
                "#d8f3dc"
            );
        }

        if (step >= 3) {
            clearColours();                    // ← Keep only this one
            colourCells(
                ["originalMatrixCell_0_1", "gaussianEliminationFactor", "scaledB"],
                "#ffe8a3"
            );
        }

        if (step >= 4) {
            clearColours();                    // ← Keep only this one
            colourCells(
                ["originalMatrixConstant_0", "gaussianEliminationFactor", "scaledConstant"],
                "#cfe8ff"
            );
        }

        if (step >= 5) {
            clearColours();                    // ← Keep only this one
            colourCells(
                ["originalMatrixCell_1_0", "scaledA", "newRowA"],
                "#cfe8ff"
            );
        }

        if (step >= 6) {
            clearColours();                    // ← Keep only this one
            colourCells(
                ["originalMatrixCell_1_1", "scaledB", "newRowB"],
                "#ffe8a3"
            );
        }

        if (step >= 7) {
            clearColours();                    // ← Keep only this one
            colourCells(
                ["originalMatrixConstant_1", "scaledConstant", "newRowC"],
                "#d8f3dc"
            );
        }

        if (step >= 8) {
            clearColours();                    // ← Keep only this one
            colourCells(
                [
                    "resultMatrix00", "resultMatrix01", "resultMatrix02",
                    "resultMatrix10", "resultMatrix11", "resultMatrix12"
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
                    "sectionThreeResultMatrix11",
                    "sectionThreeResultMatrix12",
                    "gaussianYDenominator",
                    "gaussianYNumerator"
                ],
                "#cfe8ff"            
            );        
        }
        if (step >= 2) {
            clearColours();                    // ← Keep only this one
            colourCells(
                ["gaussianYDenominator", "gaussianYNumerator", "gaussianYAnswer"],
                "#d8f3dc"
            );
        }    
    }
    function gaussianEliminationSectionFourStepsColours(step) {     
            if (step >= 1) {                          
                colourCells(
                    [
                        "sectionFourResultMatrix02",
                        "sectionFourResultMatrix03",
                        "gaussianYValue",
                        "gaussianBValue",
                    ],
                    "#cfe8ff"            
                );        
            }
            if (step >= 2) {
                clearColours();                    // ← Keep only this one
                colourCells(
                    [  "gaussianYValue",
                        "gaussianBValue",
                    "gaussianBYProduct"],
                    "#d8f3dc"
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
            