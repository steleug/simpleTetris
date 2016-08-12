package tetris;

public class PlayingArea {

    private final int height;
    private final int width;
    private Cell[][] cells;

    public PlayingArea(int width, int height) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[this.width][this.height];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                cells[x][y] = new Cell();
            }
        }
    }

    public PlayingArea() {
        this(10, 20);
    }

    public void addFigure(Figure figure) {
        if (canAddFigure(figure)) {
            int globalX;
            int globalY;
            for (int x = 0; x < figure.getWidh(); x++) {
                for (int y = 0; y < figure.getHeight(); y++) {
                    globalX = figure.getGlobalX() + x;
                    globalY = figure.getGlobalY() + y;
                    this.cells[globalX][globalY] = figure.getCell(x, y);
                }
            }
        }
    }

    private boolean canAddFigure(Figure figure) {
        if (isEnterInArea(figure) && isNoCollisions(figure)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canMoveDown(Figure figure) {
        if ((figure.getGlobalY() + figure.getHeight()) >= this.height) {
            return false;
        }
        int globalX = figure.getGlobalX();
        int globalY = figure.getGlobalY();
        int width = figure.getWidh();
        int height = figure.getHeight();
        for (int x = globalX + width - 1; x >= globalX; x--) {
            for (int y = globalY + height - 1; y >= globalY; y--) {
                if (cells[x][y].getCondition().equals(Condition.FREE)) {
                    break;
                }
                if (cells[x][y + 1].getCondition().equals(Condition.BUSY)) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void moveDown(Figure figure) {
        if (canMoveDown(figure)) {
            cutFigure(figure);
            figure.moveDown();
            addFigure(figure);
        }
    }

    private void cutFigure(Figure figure) {
        int globalX = figure.getGlobalX();
        int globalY = figure.getGlobalY();
        int width = figure.getWidh();
        int height = figure.getHeight();
        for (int x = globalX; x < globalX + width; x++) {
            for (int y = globalY; y < globalY + height; y++) {
                cells[x][y] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    private boolean isEnterInArea(Figure figure) {
        boolean isOutOfRangeLeft = (figure.getGlobalX() < 0);
        boolean isOutOfRangeRight = ((figure.getGlobalX() + figure.getWidh()) > this.width);
        boolean isOutOfRangeTop = (figure.getGlobalY() < 0);
        boolean isOutOfRangeBottom = ((figure.getGlobalY() + figure.getHeight()) > this.height);

        if (isOutOfRangeLeft || isOutOfRangeRight || isOutOfRangeTop || isOutOfRangeBottom) {
            return false;
        }
        return true;
    }

    private boolean isNoCollisions(Figure figure) {
        int globalX;
        int globalY;
        for (int x = 0; x < figure.getWidh(); x++) {
            for (int y = 0; y < figure.getHeight(); y++) {
                globalX = figure.getGlobalX() + x;
                globalY = figure.getGlobalY() + y;
                if ((cells[globalX][globalY].getCondition() == Condition.BUSY)
                        && (figure.getCell(x, y).getCondition() == Condition.BUSY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void moveLeft(Figure figure) {
        if (canMoveLeft(figure)) {
            cutFigure(figure);
            figure.moveLeft();
            addFigure(figure);
        }
    }
    
    public void moveRight(Figure figure) {
        if (canMoveRight(figure)) {
            cutFigure(figure);
            figure.moveRight();
            addFigure(figure);
        }
    }
    
    private boolean canMoveRight(Figure figure) {
        if ((figure.getGlobalX() + figure.getWidh()) >= this.width) {
            return false;
        }
        int globalX = figure.getGlobalX();
        int globalY = figure.getGlobalY();
        int width = figure.getWidh();
        int height = figure.getHeight();        
        for (int y = globalY; y < globalY + height; y++) {
            for (int x = globalX + width - 1; x >= globalX - width; x--) {
                if (cells[x][y].getCondition().equals(Condition.FREE)) {
                    break;
                }
                if (cells[x+1][y].getCondition().equals(Condition.BUSY)) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

    private boolean canMoveLeft(Figure figure) {
        if (figure.getGlobalX() <= 0) {
            return false;
        }
        int globalX = figure.getGlobalX();
        int globalY = figure.getGlobalY();
        int width = figure.getWidh();
        int height = figure.getHeight();        
        for (int y = globalY; y < globalY + height; y++) {
            for (int x = globalX; x < globalX + width; x++) {
                if (cells[x][y].getCondition().equals(Condition.FREE)) {
                    break;
                }
                if (cells[x-1][y].getCondition().equals(Condition.BUSY)) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

}
