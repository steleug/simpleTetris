package com.gmail.steleug.tetris;

public class FigureJ extends Figure {
        
    public FigureJ(int globalX, int globalY, MyColor color) {
        super(globalX, globalY);
        Cell[][] cells = new Cell[2][3];
        cells[0][0] = new Cell();
        cells[1][0] = new Cell(Condition.BUSY, color);
        cells[0][1] = new Cell();
        cells[1][1] = new Cell(Condition.BUSY, color);
        cells[0][2] = new Cell(Condition.BUSY, color);
        cells[1][2] = new Cell(Condition.BUSY, color);
        super.setCells(cells);        
    }
    
    public FigureJ(int globalX, int globalY) {
        this(globalX, globalY, MyColor.BLUE);
    }
    
    public FigureJ() {
        this(0, 0);
    }
}
