package tetris;

public class Cell {
    private Condition condition;
    private Color color;
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    public Condition getCondition() {
        return this.condition;
    }
    
    public Cell(Condition condition, Color color) {
        this.condition = condition;
        this.color = color;
    }
    
    public Cell(){
        this(Condition.FREE, Color.WHITE);
    }
    
}
