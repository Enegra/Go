package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by agnie on 12/5/2016.
 */
class BoardPanel extends JPanel {

    private int goSize;
    private int cellSize = 40;
    private int canvasSize;

    private Color backgroundColor = new Color(255, 153, 51);
    private Color cellColor = new Color(224, 224, 224);
    private Font numberFont = new Font("Monospaced", Font.BOLD, 10);

    private JButton[][] cells;

    private GameController controller;

    BoardPanel(GameController controller) {
        this.controller = controller;
        prepareBoard();
        this.setVisible(true);
    }

    private void prepareBoard() {
        goSize = controller.getGameState().getBoardState().length;
        setLayout(new GridLayout(goSize, goSize));
        canvasSize = cellSize * (goSize + 1);
        setBackground(backgroundColor);
        setSize(new Dimension(canvasSize, canvasSize));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int positionX = (int) (e.getX() - cellSize) / cellSize;
                int positionY = (int) (e.getY() - cellSize) / cellSize;
                int gridPositionX = positionX * cellSize + cellSize;
                int gridPositionY = positionY * cellSize + cellSize;
                System.out.println(positionX + " " + gridPositionX);

                if (Math.abs(gridPositionX - e.getX()) < cellSize/2){
                    if (Math.abs(gridPositionY - e.getY()) < cellSize/2){
                        if (allowed(positionX, positionY)) {
                            putStone(positionX, positionY);
                        }
                    } else if (Math.abs((gridPositionY+cellSize)-e.getY()) < cellSize/2){
                        if (allowed(positionX, positionY+1)) {
                            putStone(positionX, positionY+1);
                        }
                    }
                }
                else if (Math.abs((gridPositionX+cellSize))-e.getX() < cellSize/2){
                    if (Math.abs(gridPositionY - e.getY()) < cellSize/2){
                        if (allowed(positionX+1, positionY)) {
                            putStone(positionX+1, positionY);
                        }
                    } else if (Math.abs((gridPositionY+cellSize)-e.getY()) < cellSize/2){
                        if (allowed(positionX+1, positionY+1)) {
                            putStone(positionX+1, positionY+1);
                        }
                    }
                }
                System.out.println(positionX + " " + gridPositionX);
                System.out.println(positionY + " " + gridPositionY);
            }
        });
    }

    private void drawGrid(Graphics2D graphics2D) {
        graphics2D.setColor(Color.black);
        for (int i = 0; i < goSize; i++) {
            graphics2D.drawLine(i * cellSize + cellSize, cellSize, i * cellSize + cellSize, goSize * cellSize);
            graphics2D.drawLine(cellSize, i * cellSize + cellSize, goSize * cellSize, i * cellSize + cellSize);
        }
    }

    private void drawStone(Graphics2D graphics2D, int i, int j, int color) {
        if (color == 0) {
            graphics2D.setColor(Color.black);
        } else {
            graphics2D.setColor(Color.white);
        }
        graphics2D.fillOval(i * cellSize + cellSize / 2, j * cellSize + cellSize / 2, cellSize, cellSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(graphics2D);
        Stone[][] boardState = controller.getGameState().getBoardState();
        for (int i = 0; i < goSize; i++) {
            for (int j = 0; j < goSize; j++) {
                if (boardState[i][j] != null) {
                    drawStone(graphics2D, i, j, boardState[i][j].getColor());
                }
            }
        }
    }

    int getCanvasSize() {
        return canvasSize;
    }

    private void putStone(int i, int j) {
        controller.putStone(i, j);
        controller.switchTurn();
        this.revalidate();
        this.repaint();
    }

    private void takeStone(int i, int j) {
        cells[i][j].setBackground(backgroundColor);
        cells[i][j].setText("");
        revalidate();
        repaint();
    }

    private boolean allowed(int i, int j) {
        return controller.getGameState().getBoardState()[i][j] == null;
    }

}
