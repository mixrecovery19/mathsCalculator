document.addEventListener("DOMContentLoaded", function () {
        const page = document.getElementById("matrixSarrusPage");         
        if (!page) {return;}        

        const currentStep = parseInt(page.dataset.currentStep || 0);           
        sarrusStepColours(currentStep);            
    });

function sarrusStepColours(currentStep) {
    clearColours();   
    if (currentStep >= 1) {
        colourCells(
            ["cell-0-0", "cell-1-1", "cell-2-2", "positiveStep1"],
            "#fff4a8"
        );
    }    
    if (currentStep >= 2) {
    clearColours();
        colourCells(
            ["cell-0-1", "cell-1-2", "cell-2-3", "positiveStep2"],            
            "#cfe8ff"
        );
    }   
    if (currentStep >= 3) {
    clearColours();
        colourCells(
            ["cell-0-2", "cell-1-3", "cell-2-4", "positiveStep3"],           
            "#d5f5d5"
        );
    }    
    if (currentStep >= 4) {
    clearColours();
        colourCells(
            ["cell-2-0", "cell-1-1", "cell-0-2", "negativeStep1"],            
            "#f9d5d5"
        );
    }    
    if (currentStep >= 5) {
    clearColours();
        colourCells(
            ["cell-2-1", "cell-1-2", "cell-0-3", "negativeStep2"],            
            "#ffe6bf"
        );
    }   
    if (currentStep >= 6) {
    clearColours();
        colourCells(
            ["cell-2-2", "cell-1-3", "cell-0-4", "negativeStep3"],            
            "#ead7ff"
        );
    }    
    if (currentStep >= 7) {
    clearColours();
        colourCells(
            ["positiveStep1", "positiveStep2", "positiveStep3", "positiveTotal"],            
            "#b8f2e6"
        );
    }    
    if (currentStep >= 8) {
    clearColours();
        colourCells(
            ["negativeStep1", "negativeStep2", "negativeStep3", "negativeTotal"],            
            "#ffb3b3"
        );
    }    
    if (currentStep >= 9) {
    clearColours();
        colourCells(
            ["negativeTotal", "positiveTotal", "determinant"],            
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

function clearColours() {
    document.querySelectorAll(".matrix-input")
        .forEach(cell => {
            cell.style.backgroundColor = "";
            cell.style.borderColor = "";
        });
    }