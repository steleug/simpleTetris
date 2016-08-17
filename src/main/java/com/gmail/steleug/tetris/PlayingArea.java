package com.gmail.steleug.tetris;

import java.util.HashSet;
import java.util.ArrayList;

public class PlayingArea {

    private final int height;
    private final int width;
    private Cell[][] cells;
    public final int ELEMENT_WIDTH_PX = 20;
    private boolean isMoveOver;
    private boolean isGameOver;
    private ArrayList<Integer> listFullRowsNumber;

    public PlayingArea(int width, int height) {
        isMoveOver = false;
        isGameOver = false;
        this.height = height;
        this.width = width;
        this.cells = new Cell[this.width][this.height];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                cells[x][y] = new Cell();
            }
        }
        listFullRowsNumber = new ArrayList<Integer>();
    }

    public PlayingArea() {
        this(10, 20);
    }

    public boolean getIsGameOver() {
        return this.isGameOver;
    }

    public boolean getIsMoveOver() {
        return this.isMoveOver;
    }

    public void addFigure(Figure figure) {
        if (canAddFigure(figure)) {
            int globalX;
            int globalY;
            for (int x = 0; x < figure.getWidh(); x++) {
                for (int y = 0; y < figure.getHeight(); y++) {
                    globalX = figure.getGlobalX() + x;
                    globalY = figure.getGlobalY() + y;
                    if (figure.getCell(x, y).getCondition().
                            equals(Condition.BUSY)) {
                        this.cells[globalX][globalY] = figure.getCell(x, y);
                    }
                }
            }
        }
    }

    private boolean canAddFigure(Figure figure) {
        if (isEnterInArea(figure) && isNoCollisions(figure)) {
            return true;
        }
        isGameOver = true;
        return false;
    }

    private boolean canMoveDown(Figure figure) {
        if ((figure.getGlobalY() + figure.getHeight()) >= this.height) {
            isMoveOver = true;
            return false;
        }
        int globalX = figure.getGlobalX();
        int globalY = figure.getGlobalY();
        int figureWidth = figure.getWidh();
        int figureHeight = figure.getHeight();
        for (int x = figureWidth - 1; x >= 0; x--) {
            for (int y = figureHeight - 1; y >= 0; y--) {
                if (figure.getCell(x, y).getCondition().equals(Condition.FREE)) {
                    continue;
                } else if (cells[globalX + x][globalY + y + 1].getCondition().
                        equals(Condition.BUSY)) {
                    isMoveOver = true;
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
        int figureWidth = figure.getWidh();
        int figureHeight = figure.getHeight();
        for (int y = 0; y < figureHeight; y++) {
            for (int x = 0; x < figureWidth; x++) {
                if (figure.getCell(x, y).getCondition().equals(Condition.FREE)) {
                    continue;
                } else if (cells[globalX + x - 1][globalY + y].getCondition().
                        equals(Condition.BUSY)) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

    private boolean canMoveRight(Figure figure) {
        if ((figure.getGlobalX() + figure.getWidh()) >= this.width) {
            return false;
        }
        int globalX = figure.getGlobalX();
        int globalY = figure.getGlobalY();
        int figureWidth = figure.getWidh();
        int figureHeight = figure.getHeight();
        for (int y = 0; y < figureHeight; y++) {
            for (int x = figureWidth - 1; x >= 0; x--) {
                if (figure.getCell(x, y).getCondition().equals(Condition.FREE)) {
                    continue;
                } else if (cells[globalX + x + 1][globalY + y].getCondition().
                        equals(Condition.BUSY)) {
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
        int figureWidth = figure.getWidh();
        int figureHeight = figure.getHeight();
        for (int x = globalX; x < globalX + figureWidth; x++) {
            for (int y = globalY; y < globalY + figureHeight; y++) {
                if (figure.getCell(x - globalX, y - globalY).getCondition().
                        equals(Condition.BUSY)) {
                    cells[x][y] = new Cell();
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    private boolean isEnterInArea(Figure figure) {
        boolean isOutOfRangeLeft = (figure.getGlobalX() < 0);
        boolean isOutOfRangeRight = ((figure.getGlobalX() + figure.getWidh())
                > this.width);
        boolean isOutOfRangeTop = (figure.getGlobalY() < 0);
        boolean isOutOfRangeBottom = ((figure.getGlobalY() + figure.getHeight())
                > this.height);
        boolean isOutOfRange = isOutOfRangeLeft || isOutOfRangeRight
                || isOutOfRangeTop || isOutOfRangeBottom;

        if (isOutOfRange) {
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
                if ((cells[globalX][globalY].getCondition().
                        equals(Condition.BUSY))
                        && (figure.getCell(x, y).getCondition().
                        equals(Condition.BUSY))) {
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

    public void rotateAround(Figure figure) {
        cutFigure(figure);
        if (canRotateAround(figure)) {
            figure.rotateAround();
        }
        addFigure(figure);
    }

    private Figure getRotatedFigureFromFigure(Figure figure) {
        Figure rotatedFigure = new Figure(figure.getGlobalX(),
                figure.getGlobalY());
        Cell[][] cells = new Cell[figure.getHeight()][figure.getWidh()];
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                cells[x][y] = new Cell();
            }
        }
        rotatedFigure.setCells(cells);

        for (int x = 0; x < figure.getWidh(); x++) {
            for (int y = 0; y < figure.getHeight(); y++) {
                rotatedFigure.setCell(figure.getHeight() - 1 - y,
                        x,
                        figure.getCell(x, y));
            }
        }
        return rotatedFigure;
    }

    private boolean canRotateAround(Figure figure) {
        Figure rotatedFigure = getRotatedFigureFromFigure(figure);
        if (isEnterInArea(rotatedFigure) && isNoCollisions(rotatedFigure)) {
            return true;
        }
        return false;
    }

    void setIsMoveOver(boolean isMoveOver) {
        this.isMoveOver = isMoveOver;
    }

    private boolean isExistFullRows() {
        for (int y = cells[0].length - 1; y >= 0; y--) {
            for (int x = 0; x < cells.length; x++) {
                if (cells[x][y].getCondition().equals(Condition.FREE)) {
                    break;
                }
                if (x == (cells.length - 1)) {
                    listFullRowsNumber.add(y);
                }
            }
        }
        if (listFullRowsNumber.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteFullRows() {
        if (!isExistFullRows()) {
            return;
        }
        int moveDownCounter;
        for (int rowIdx : listFullRowsNumber) {
            removeRow(rowIdx);
        }
        for (int y = cells[0].length - 1; y >= 0; y--) {
            moveDownCounter = 0;
            for (int rowIdx : listFullRowsNumber) {
                if (y < rowIdx) {
                    moveDownCounter++;
                }
            }
            moveDownRowByCount(y, moveDownCounter);
        }
        listFullRowsNumber.clear();
    }

    private void removeRow(int rowIdx) {
        for (int x = 0; x < cells.length; x++) {
            cells[x][rowIdx] = new Cell();
        }
    }

    private void moveDownRowByCount(int rowIdx, int moveDownCounter) {
        if (moveDownCounter == 0) {
            return;
        }
        for (int x = 0; x < cells.length; x++) {
            cells[x][rowIdx + moveDownCounter] = cells[x][rowIdx];
        }
        removeRow(rowIdx);
    }

}
