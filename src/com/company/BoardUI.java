package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnie on 12/4/2016.
 */
class BoardUI extends JFrame{

    private BoardPanel boardPanel;

    BoardUI(){
        prepareGUI();
    }

    private void prepareGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(200,400));
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
//        Point newLocation = new Point(middle.x - (this.getWidth() / 2),
//                middle.y - (this.getHeight() / 2));
//        this.setLocation(newLocation);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("Go");
        this.setVisible(true);
    }

    void drawGo(int[][] goState){
        BoardPanel boardPanel = new BoardPanel();
        boardPanel.prepareBoard(goState.length);
        this.setSize(boardPanel.getCanvasSize()+50, boardPanel.getCanvasSize()+80);
        this.add(boardPanel);
        boardPanel.setBounds(20, 20, boardPanel.getCanvasSize(), boardPanel.getCanvasSize());
        boardPanel.setVisible(true);
        boardPanel.drawGo(goState);
        boardPanel.revalidate();
        this.setLocationRelativeTo(null);
    }

}
