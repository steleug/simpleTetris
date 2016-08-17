package com.gmail.steleug.tetris;

import java.util.Random;

public enum MyColor {
    BLUE,    
    GREEN,
    RED,
    DEFAULT;  // must be last
    
    private static final Random random = new Random();
    
    public static MyColor getRandomColor() {
        return MyColor.values()[random.nextInt(MyColor.values().length-1)];
    }
}
