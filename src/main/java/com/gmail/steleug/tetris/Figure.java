package com.gmail.steleug.tetris;

public class Figure implements FigureImpl {
    private int globalX;
    private int globalY;
    protected int width;
    protected int height;
    protected Cell[][] cells;
    

    public Figure(int globalX, int globalY){
        this.globalX = globalX;
        this.globalY = globalY;
    }
    
    public void setCells(Cell[][] cells) {
        this.cells = cells;
        this.width = cells.length;  // bibb
        this.height = cells[0].length;  // bibb
    }
    
    public void setCell(int x, int y, Cell cell) {
        this.cells[x][y] = cell;
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
    
    @Override
    public void rotateAround() {
        // initial new figure rotated matrix
        Cell[][] rotatedCells = new Cell[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                rotatedCells[x][y] = new Cell();
            }
        }
        
        // rotate current matrix
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rotatedCells[height - 1 - y][x] =
                        cells[x][y];                
            }
        }
        cells = rotatedCells;
        
        // revert width and height to new rotated matrix size
        int tmp = this.height;
        this.height = this.width;
        this.width = tmp;        
    }
    
}
