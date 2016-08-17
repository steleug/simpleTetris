package com.gmail.steleug.tetris;

import java.util.Random;

class Game {
    private PlayingArea playingArea;
    private Figure figure;
    private final int centerX;
    private boolean isMoveOver;
    private boolean isGameOver;
    
    private void restart() {
        playingArea = new PlayingArea();
        isMoveOver = false;
        isGameOver = false;
        genereteNewFigure();
    }
    
    
    public Game(){
        playingArea = new PlayingArea();
        isMoveOver = false;
        isGameOver = false;
        centerX = playingArea.getWidth() / 2 - 1;
        genereteNewFigure();
    }
    
    public PlayingArea getPlayingArea(){
        return playingArea;
    }
    
    public void moveDown(){
        playingArea.moveDown(figure);
        this.isMoveOver = playingArea.getIsMoveOver();
    }
    
    public void addFigure(Figure figure) {
        playingArea.addFigure(figure);
        this.isGameOver = playingArea.getIsGameOver();
    }

    void moveLeft() {
        playingArea.moveLeft(figure);
    }

    void moveRight() {
        playingArea.moveRight(figure);
    }
    
    void nextStep() {
        moveDown();
        if (isMoveOver) {
            playingArea.deleteFullRows();
            genereteNewFigure();
            if (isGameOver) {
                restart();
                return;
            }
        }
    }
            
    
    void rotateAround() {
        playingArea.rotateAround(figure);
    }

    private Figure GenerateFigure() {
        MyFigures randomFigures = MyFigures.getRandomFigures();
        switch (randomFigures){
            case figureZ:
                return new FigureZ(centerX, 0, MyColor.getRandomColor());
            case figureS:
                return new FigureS(centerX, 0, MyColor.getRandomColor());
            case figureJ:
                return new FigureJ(centerX, 0, MyColor.getRandomColor());
            case figureL:
                return new FigureL(centerX, 0, MyColor.getRandomColor());
            case figureT:
                return new FigureT(centerX, 0, MyColor.getRandomColor());
            case figureI:
                return new FigureI(centerX, 0, MyColor.getRandomColor());
            default:
                return new FigureO(centerX, 0, MyColor.getRandomColor());
        }
    }

    private void genereteNewFigure() {
        playingArea.setIsMoveOver(false);
        Figure generatedFigure = GenerateFigure();
        // generate random figure orientations
        Random random = new Random();
        for (int i = 0; i < random.nextInt(4); i++) {
            generatedFigure.rotateAround();
        }        
        figure = generatedFigure;
        addFigure(generatedFigure);
    }
    
}
