function colourCells(cellIds,colour) {
    cellIds.forEach(id => {
        const cell = document.getElementById(id);

        if (cell) {
            cell.style.backgroundColor = colour;}
    });
}

function colourElementsByClass(className, colour) {

    document.querySelectorAll("." + className)        
        .forEach(element => {element.style.backgroundColor = colour;});
}

function clearColours() {
    document.querySelectorAll(".matrix-input")
            .forEach(cell => {cell.style.backgroundColor = ""; cell.style.borderColor = "";});        
}