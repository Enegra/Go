package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by agnie on 12/5/2016.
 */
class BoardPanel extends JPanel{

    private int goSize;
    private int cellSize = 40;
    private int canvasSize;

    private Color blankCellColor = new Color(255,153,51);
    private Color cellColor = new Color(224,224,224);
    private Font numberFont = new Font("Monospaced", Font.BOLD, 10);

    private JButton[][] cells;

    private GameController controller;

    BoardPanel(GameController controller){
        this.controller = controller;
        this.setVisible(false);
    }

    void prepareBoard(int goSize){
        this.goSize=goSize;
        setLayout(new GridLayout(goSize,goSize));
        canvasSize = cellSize*goSize;
        setSize(new Dimension(canvasSize,canvasSize));
    }

    void drawGo(int[][] goState){
        this.removeAll();
        cells = new JButton[goSize][goSize];
        for(int i=0; i<goSize; i++){
            for (int j=0; j<goSize; j++){
                cells[i][j] = new JButton();
                this.add(cells[i][j]);
                if (goState[i][j] == 0){
                    cells[i][j].setBackground(blankCellColor);
                    cells[i][j].setText("");
                }
                else {
                    cells[i][j].setBackground(cellColor);
                    cells[i][j].setText(String.valueOf(goState[i][j]));
                }
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(numberFont);
                final int a = i;
                final int b = j;
                cells[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (allowed(a,b)){
                            putStone(a,b);
                        }
                    }
                });
                cells[i][j].setVisible(true);
                cells[i][j].setSize(new Dimension(cellSize,cellSize));
            }
        }
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    int getCanvasSize(){
        return canvasSize;
    }

    private void putStone(int i, int j){
        cells[i][j].setBackground(cellColor);
        cells[i][j].setText(String.valueOf(controller.getCurrentPlayer()));
        controller.switchTurn();
        revalidate();
        repaint();
    }

    private void takeStone(int i, int j){
        cells[i][j].setBackground(blankCellColor);
        cells[i][j].setText("");
        revalidate();
        repaint();
    }

    private boolean allowed(int i, int j){
        return cells[i][j].getText()=="";
    }

}
