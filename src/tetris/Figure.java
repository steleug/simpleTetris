package tetris;

public class Figure implements FigureImpl {
    private int globalX;
    private int globalY;
    private int width;
    private int height;
    private Cell[][] cells;
    

    public Figure(int globalX, int globalY){
        this.globalX = globalX;
        this.globalY = globalY;
    }
    
    public void setCells(Cell[][] cells) {
        this.cells = cells;
        this.width = cells.length;  // bibb
        this.height = cells[0].length;  // bibb
    }
    
    public Cell getCell(int localX, int localY) {
        return this.cells[localX][localY];
    }
    
    public void setGlobalX(int globalX) {
        this.globalX = globalX;
    }
    
    public int getGlobalX() {
        return this.globalX;
    }
            
    public void setGlobalY(int globalY) {
        this.globalY = globalY;
    }
    
    public int getGlobalY() {
        return this.globalY;
    }
    
    public int getWidh() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
    @Override
    public void moveDown() {
        this.globalY++;
    }

    @Override
    public void moveLeft() {
        this.globalX--;
    }
    
    @Override
    public void moveRight() {
        this.globalX++;
    }
    
}
