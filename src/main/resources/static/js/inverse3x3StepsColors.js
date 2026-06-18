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
        const inverse3x3CurrentStep = parseInt(page.dataset.inverseCurrentStep || 0 );
        inverse3x3StepColours(inverse3x3CurrentStep);
    }
);

function inverse3x3StepColours(inverse3x3CurrentStep) {
    clearColours();   
    if (inverse3x3CurrentStep <= 7) { 
        if (inverse3x3CurrentStep >= 5) {
            colourCells([
            "inverse-cell-0-0",
            "inverse-cell-1-1",
            "positiveDiagonal"
        ], "#b8f2e6");
        }        
        if (inverse3x3CurrentStep >= 6) {
            colourCells([
                "inverse-cell-0-1",
                "inverse-cell-1-0",
                "negativeDiagonal"
            ], "#ffb3b3");            
        }      
        if (inverse3x3CurrentStep >= 7) {
            colourCells(["positiveDiagonal", "negativeDiagonal", "diagonalResult"],"#ffff99");            
        }
        } else if (inverse3x3CurrentStep <= 10) {
            if (inverse3x3CurrentStep >= 8) {    
                colourCells(["inverse-cell-0-1", "inverse-cell-1-2", "positiveDiagonal"],"#b8f2e6");
            }        
        if (inverse3x3CurrentStep >= 9) {
            colourCells(["inverse-cell-0-2", "inverse-cell-1-1", "negativeDiagonal"],"#ffb3b3");
        }        
        if (inverse3x3CurrentStep >= 10) {
            colourCells(
                [                
                    "positiveDiagonal",
                    "negativeDiagonal",
                    "diagonalResult",
                    "diagonalResultValue"
                ],
                "#ffff99"
            );
        }
        } else if (inverse3x3CurrentStep <= 13) {
            if (inverse3x3CurrentStep >= 11) {
                colourCells(["inverse-cell-0-2", "inverse-cell-1-3", "positiveDiagonal"], "#b8f2e6");
            }            
            if (inverse3x3CurrentStep >= 12) {
                colourCells(
                    ["inverse-cell-0-3", "inverse-cell-1-2", "negativeDiagonal"],                
                    "#ffb3b3"
                );
            }            
            if (inverse3x3CurrentStep >= 13) {
                colourCells(
                    [                
                        "positiveDiagonal",
                        "negativeDiagonal",
                        "diagonalResult",
                        "diagonalResultValue"
                    ],
                    "#ffff99"
                );
            }
        } else if (inverse3x3CurrentStep <= 16) {
            if (inverse3x3CurrentStep >= 14) {
                colourCells(["inverse-cell-1-0", "inverse-cell-2-1", "positiveDiagonal"],"#b8f2e6");
                }               
            if (inverse3x3CurrentStep >= 15) {
                colourCells(
                    [
                        "inverse-cell-1-1", 
                        "inverse-cell-2-0", 
                        "negativeDiagonal"],                
                    "#ffb3b3"
                );
            }            
            if (inverse3x3CurrentStep >= 16) {
                colourCells(
                    [                
                        "positiveDiagonal",
                        "negativeDiagonal",
                        "diagonalResult",
                        "diagonalResultValue"
                    ],
                    "#ffff99"
                );
            }
        } else if (inverse3x3CurrentStep <= 19) {
            if (inverse3x3CurrentStep >= 17) {
                colourCells(
                    [
                    "inverse-cell-1-1",
                    "inverse-cell-2-2",
                    "positiveDiagonal"
                    ],
                    "#b8f2e6"
                );
            }        
            if (inverse3x3CurrentStep >= 18) {
                colourCells(
                    [
                        "inverse-cell-1-2", 
                        "inverse-cell-2-1", 
                        "negativeDiagonal"],                
                    "#ffb3b3"
                );
            }        
            if (inverse3x3CurrentStep >= 19) {
                colourCells(
                    [                
                        "positiveDiagonal",
                        "negativeDiagonal",
                        "diagonalResult",
                        "diagonalResultValue"
                    ],
                    "#ffff99"
                );
            }
        } else if (inverse3x3CurrentStep <= 22) {
            if (inverse3x3CurrentStep >= 20) {
                colourCells(
                    [
                    "inverse-cell-1-2",
                    "inverse-cell-2-3",
                    "positiveDiagonal"
                    ],
                    "#b8f2e6"
                );
            }            
            if (inverse3x3CurrentStep >= 21) {
                colourCells(
                    [
                        "inverse-cell-1-3", 
                        "inverse-cell-2-2", 
                        "negativeDiagonal"],                
                    "#ffb3b3"
                );
            }            
            if (inverse3x3CurrentStep >= 22) {
                colourCells(
                    [                
                        "positiveDiagonal",
                        "negativeDiagonal",
                        "diagonalResult",
                        "diagonalResultValue"
                    ],
                    "#ffff99"
                );
            }
        } else if (inverse3x3CurrentStep <= 25) {
            if (inverse3x3CurrentStep >= 23) {
                colourCells(["inverse-cell-2-0", "inverse-cell-3-1", "positiveDiagonal"], "#b8f2e6");                    
                }                
            if (inverse3x3CurrentStep >= 24) {
                    colourCells(["inverse-cell-2-1", "inverse-cell-3-0", "negativeDiagonal"], "#ffb3b3");   
                }                
                if (inverse3x3CurrentStep >= 25) {
                    colourCells(
                        [                
                            "positiveDiagonal",
                            "negativeDiagonal",
                            "diagonalResult",
                            "diagonalResultValue"
                        ],
                        "#ffff99"
                    );
                }
        } else if (inverse3x3CurrentStep <= 28) {
            if (inverse3x3CurrentStep >= 26) {
                    colourCells(
                        [
                        "inverse-cell-2-1",
                        "inverse-cell-3-2",
                        "positiveDiagonal"
                        ],
                        "#b8f2e6"
                    );
                }            
            if (inverse3x3CurrentStep >= 27) {
                    colourCells(
                        [
                            "inverse-cell-2-2", 
                            "inverse-cell-3-1", 
                            "negativeDiagonal"],                
                        "#ffb3b3"
                    );
                }                
            if (inverse3x3CurrentStep >= 28) {
                    colourCells(
                        [                
                            "positiveDiagonal",
                            "negativeDiagonal",
                            "diagonalResult",
                            "diagonalResultValue"
                        ],
                        "#ffff99"
                    );
                }
        } else if (inverse3x3CurrentStep <= 31) {
            if (inverse3x3CurrentStep >= 29) {
                    colourCells(
                        [
                        "inverse-cell-2-2",
                        "inverse-cell-3-3",
                        "positiveDiagonal"
                        ],
                        "#b8f2e6"
                    );
                }               
            if (inverse3x3CurrentStep >= 30) {
                    colourCells(
                        [
                            "inverse-cell-2-3", 
                            "inverse-cell-3-2", 
                            "negativeDiagonal"],                
                        "#ffb3b3"
                    );
                }
            
            if (inverse3x3CurrentStep >= 31) {
                    colourCells(
                        [                
                            "positiveDiagonal",
                            "negativeDiagonal",
                            "diagonalResult",
                            "diagonalResultValue"
                        ],
                        "#ffff99"
                    );
                }
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
    document
        .querySelectorAll(".matrix-input")
        .forEach(cell => {
            cell.style.backgroundColor = "";
            cell.style.borderColor = "";
    });
}    
                    
                