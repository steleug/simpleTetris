import tetris.FigureO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.*;

public class FiguresTests {
    
    public FiguresTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initialFigureOTest() {
        FigureO figureO = new FigureO();
        assertEquals(Condition.BUSY, figureO.getCell(0, 0).getCondition());
        assertEquals(Condition.BUSY, figureO.getCell(0, 1).getCondition());
        assertEquals(Condition.BUSY, figureO.getCell(1, 0).getCondition());
        assertEquals(Condition.BUSY, figureO.getCell(1, 1).getCondition());
    }
    
    @Test
    public void initialPlayingAreaTest() {
        PlayingArea playingArea = new PlayingArea();
        for (int x = 0; x < playingArea.getWidth(); x++) {
            for (int y = 0; y < playingArea.getHeight(); y++) {
                assertEquals(Condition.FREE, playingArea.getCell(x, y).getCondition());
            }
        }
    }
    
    @Test
    public void addFigureOToPlayingAreaTest() {
        PlayingArea playingArea = new PlayingArea();
        int centerX = playingArea.getWidth() / 2;
        FigureO figureO = new FigureO(centerX, 0);
        playingArea.addFigure(figureO);
        assertEquals(Condition.BUSY, playingArea.getCell(centerX, 0).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX + 1, 0).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX, 1).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX + 1, 1).getCondition());
    }
    
    @Test
    public void moveDownFigureOTest() {
        PlayingArea playingArea = new PlayingArea();
        int centerX = playingArea.getWidth() / 2;
        FigureO figureO = new FigureO(centerX, 0);
        playingArea.addFigure(figureO);
        playingArea.moveDown(figureO);
        assertEquals(Condition.FREE, playingArea.getCell(centerX, 0).getCondition());
        assertEquals(Condition.FREE, playingArea.getCell(centerX + 1, 0).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX, 1).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX + 1, 1).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX, 2).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX + 1, 2).getCondition());        
    }
    
    @Test
    public void moveLeftFigure0Test() {
        PlayingArea playingArea = new PlayingArea();
        int centerX = playingArea.getWidth() / 2;
        FigureO figureO = new FigureO(centerX, 0);
        playingArea.addFigure(figureO);
        playingArea.moveLeft(figureO);
        assertEquals(Condition.FREE, playingArea.getCell(centerX + 1, 0).getCondition());
        assertEquals(Condition.FREE, playingArea.getCell(centerX + 1, 1).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX, 0).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX, 1).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX - 1, 0).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX - 1, 1).getCondition());        
    }
    
    @Test
    public void moveRightFigureOTest() {
        PlayingArea playingArea = new PlayingArea();
        int centerX = playingArea.getWidth() / 2;
        FigureO figureO = new FigureO(centerX, 0);
        playingArea.addFigure(figureO);
        playingArea.moveRight(figureO);
        assertEquals(Condition.FREE, playingArea.getCell(centerX, 0).getCondition());
        assertEquals(Condition.FREE, playingArea.getCell(centerX, 1).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX + 1, 0).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX + 1, 1).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX + 2, 0).getCondition());
        assertEquals(Condition.BUSY, playingArea.getCell(centerX + 2, 1).getCondition());        
    }
    
}
