package com.gmail.steleug.tetris;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;
import javax.swing.*;

public class TetrisGUI {

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new FillFrame();
                frame.setTitle("Tetris");
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class FillFrame extends JFrame implements KeyListener {

    FillComponent fillComponent;
    Game game;
    private Timer timer = null;

    public FillFrame() {
        game = new Game();
        fillComponent = new FillComponent(game.getPlayingArea());
        addKeyListener(this);
        add(fillComponent);
        pack();

        Thread updateThread = new Thread() {
            @Override
            public void run() {
                while (true) {                    
                    game.nextStep();
                    fillComponent.paintPlayingArea(game.getPlayingArea());
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ignore) {}
                }
            }
        };
        updateThread.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            game.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            game.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            game.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            game.rotateAround();
        }
        fillComponent.paintPlayingArea(game.getPlayingArea());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

class FillComponent extends JComponent {

    private PlayingArea playingArea;

    FillComponent(PlayingArea playingArea) {
        paintPlayingArea(playingArea);
    }

    public void paintPlayingArea(PlayingArea playingArea) {
        this.playingArea = playingArea;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int x = 0; x < playingArea.getWidth(); x++) {
            for (int y = 0; y < playingArea.getHeight(); y++) {
                g.setColor(getColor(playingArea.getCell(x, y)));
                g.drawRect(x * playingArea.ELEMENT_WIDTH_PX,
                        y * playingArea.ELEMENT_WIDTH_PX,
                        playingArea.ELEMENT_WIDTH_PX,
                        playingArea.ELEMENT_WIDTH_PX);
                g.fillRect(x * playingArea.ELEMENT_WIDTH_PX,
                        y * playingArea.ELEMENT_WIDTH_PX,
                        playingArea.ELEMENT_WIDTH_PX,
                        playingArea.ELEMENT_WIDTH_PX);
            }
        }
    }

    private java.awt.Color getColor(Cell cell) {
        MyColor myColor = cell.getMyColor();
        switch (myColor) {
            case DEFAULT:
                return java.awt.Color.BLACK;
            case BLUE:
                return java.awt.Color.BLUE;
            case GREEN:
                return java.awt.Color.GREEN;
            case RED:
                return java.awt.Color.RED;
            default:
                return java.awt.Color.GRAY;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int playingAreaWidthPx = playingArea.getWidth()
                * playingArea.ELEMENT_WIDTH_PX;
        int playingAreaHeightPx = playingArea.getHeight()
                * playingArea.ELEMENT_WIDTH_PX;
        return new Dimension(playingAreaWidthPx, playingAreaHeightPx);
    }

}
