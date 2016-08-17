package com.gmail.steleug.tetris;

public class FigureZ extends Figure {
        
    public FigureZ(int globalX, int globalY, MyColor color) {
        super(globalX, globalY);
        Cell[][] cells = new Cell[3][2];
        cells[0][0] = new Cell(Condition.BUSY, color);
        cells[1][0] = new Cell(Condition.BUSY, color);
        cells[2][0] = new Cell();
        cells[0][1] = new Cell();
        cells[1][1] = new Cell(Condition.BUSY, color);
        cells[2][1] = new Cell(Condition.BUSY, color);
        super.setCells(cells);        
    }
    
    public FigureZ(int globalX, int globalY) {
        this(globalX, globalY, MyColor.BLUE);
    }
    
    public FigureZ() {
        this(0, 0);
    }
}
