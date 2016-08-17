package com.gmail.steleug.tetris;

import java.util.Random;

public enum MyFigures {
    figureO,
    figureS,
    figureZ,
    figureJ,
    figureL,
    figureI,
    figureT;
    
    private static final Random random = new Random();
    
    public static MyFigures getRandomFigures() {
        return MyFigures.values()[random.nextInt(MyFigures.values().length)];
    }
    
}
