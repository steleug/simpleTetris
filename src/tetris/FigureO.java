package tetris;

public class FigureO extends Figure {
        
    public FigureO(int globalX, int globalY, Color color) {
        super(globalX, globalY);
        Cell[][] cells = new Cell[2][2];
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                cells[x][y] = new Cell(Condition.BUSY, color);
            }
        }
        super.setCells(cells);        
    }
    
    public FigureO(int globalX, int globalY) {
        this(globalX, globalY, Color.BLUE);
    }
    
    public FigureO() {
        this(0, 0);
    }
}
