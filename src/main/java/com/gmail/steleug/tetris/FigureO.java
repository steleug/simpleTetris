package com.gmail.steleug.tetris;

public class FigureO extends Figure {
        
    public FigureO(int globalX, int globalY, MyColor color) {
        super(globalX, globalY);
        Cell[][] cells = new Cell[2][2];
        cells[0][0] = new Cell(Condition.BUSY, color);
        cells[1][0] = new Cell(Condition.BUSY, color);
        cells[0][1] = new Cell(Condition.BUSY, color);
        cells[1][1] = new Cell(Condition.BUSY, color);
        super.setCells(cells);        
    }
    
    public FigureO(int globalX, int globalY) {
        this(globalX, globalY, MyColor.BLUE);
    }
    
    public FigureO() {
        this(0, 0);
    }
}
