package com.gmail.steleug.tetris;

public class FigureI extends Figure {
        
    public FigureI(int globalX, int globalY, MyColor color) {
        super(globalX, globalY);
        Cell[][] cells = new Cell[1][4];
        cells[0][0] = new Cell(Condition.BUSY, color);
        cells[0][1] = new Cell(Condition.BUSY, color);
        cells[0][2] = new Cell(Condition.BUSY, color);
        cells[0][3] = new Cell(Condition.BUSY, color);
        super.setCells(cells);        
    }
    
    public FigureI(int globalX, int globalY) {
        this(globalX, globalY, MyColor.BLUE);
    }
    
    public FigureI() {
        this(0, 0);
    }
}
