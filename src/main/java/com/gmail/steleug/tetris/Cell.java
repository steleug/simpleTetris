package com.gmail.steleug.tetris;

public class Cell {
    private Condition condition;
    private MyColor color;
    
    public void setMyColor(MyColor color) {
        this.color = color;
    }
    
    public MyColor getMyColor() {
        return this.color;
    }
    
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    public Condition getCondition() {
        return this.condition;
    }
    
    public Cell(Condition condition, MyColor color) {
        this.condition = condition;
        this.color = color;
    }
    
    public Cell(){
        this(Condition.FREE, MyColor.DEFAULT);
    }
    
}
