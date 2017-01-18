package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by agnie on 12/20/2016.
 */
class ControlPanel extends JPanel{

    private JLabel turnLabel;
    private JLabel scoreLabel;
    private JLabel deadWhiteStonesLabel;
    private JLabel deadBlackStonesLabel;
    private JComboBox gameTypeChoiceComboBox, heuristicChoiceComboBox;
    private JButton skipGameButton;
    private BoardUI boardUI;

    ControlPanel(BoardUI boardUI){
        setupPanel();
        this.boardUI = boardUI;
    }

    private void setupPanel(){
        this.setLayout(new FlowLayout());
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setPreferredSize(new Dimension(220, 600));
        setupGameTypeChoiceLabel();
        setupGameTypeChoiceComboBox();
        setupHeuristicChoiceLabel();
        setupHeuristicChoiceComboBox();
        setupStartGameButton();
        setupPauseGameButton();
        setupSkipGameButton();
        setupTurnLabel();
        setupScoreLabels();
    }

    private void setupGameTypeChoiceLabel(){
        JLabel gameTypeChoiceLabel = new JLabel("Choose the game type");
        gameTypeChoiceLabel.setPreferredSize(new Dimension(200,30));
        this.add(gameTypeChoiceLabel);
        gameTypeChoiceLabel.setVisible(true);
    }

    private void setupHeuristicChoiceLabel(){
        JLabel heuristicChoiceLabel = new JLabel("Choose heuristic");
        heuristicChoiceLabel.setPreferredSize(new Dimension(200,30));
        this.add(heuristicChoiceLabel);
        heuristicChoiceLabel.setVisible(true);
    }

    private void setupGameTypeChoiceComboBox(){
        String[] gameTypes = {"Human vs AI", "AI vs AI"};
        gameTypeChoiceComboBox = new JComboBox(gameTypes);
        gameTypeChoiceComboBox.setPreferredSize(new Dimension(200,30));
        this.add(gameTypeChoiceComboBox);
        gameTypeChoiceComboBox.setVisible(true);
    }

    private void setupHeuristicChoiceComboBox(){
        String[] heuristics = {"None","NegaScout", "Node search"};
        heuristicChoiceComboBox = new JComboBox(heuristics);
        heuristicChoiceComboBox.setPreferredSize(new Dimension(200,30));
        heuristicChoiceComboBox.setSelectedIndex(0);
        this.add(heuristicChoiceComboBox);
        heuristicChoiceComboBox.setVisible(true);
    }

    private void setupStartGameButton(){
        JButton startGameButton = new JButton("Start game");
        startGameButton.setPreferredSize(new Dimension(200,30));
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardUI.getBoardPanel().getController().setSelectedHeuristic(heuristicChoiceComboBox.getSelectedIndex());
                boardUI.getBoardPanel().getController().setGameStarted(true);
                scoreLabel.setVisible(true);
                deadBlackStonesLabel.setVisible(true);
                deadWhiteStonesLabel.setVisible(true);
            }
        });
        this.add(startGameButton);
        startGameButton.setVisible(true);
    }

    private void setupPauseGameButton(){
        JButton pauseGameButton = new JButton("Pause game");
        pauseGameButton.setPreferredSize(new Dimension(200,30));
        pauseGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardUI.getBoardPanel().getController().setGameStarted(false);
            }
        });
        this.add(pauseGameButton);
        pauseGameButton.setVisible(true);
    }

    private void setupSkipGameButton(){
        skipGameButton = new JButton("Skip to the end of game");
        skipGameButton.setPreferredSize(new Dimension(200,30));
        this.add(skipGameButton);
        skipGameButton.setVisible(true);
    }

    private void setupTurnLabel(){
        turnLabel = new JLabel("Current turn: ");
        turnLabel.setPreferredSize(new Dimension(200,60));
        this.add(turnLabel);
        turnLabel.setVisible(false);
    }

    private void setupScoreLabels(){
        scoreLabel = new JLabel("Current score: ");
        scoreLabel.setPreferredSize(new Dimension(200,60));
        deadBlackStonesLabel = new JLabel("Dead black stones: ");
        deadBlackStonesLabel.setPreferredSize(new Dimension(200,60));
        deadWhiteStonesLabel = new JLabel("Dead white stones: ");
        deadWhiteStonesLabel.setPreferredSize(new Dimension(200,60));
        this.add(scoreLabel);
        this.add(deadBlackStonesLabel);
        this.add(deadWhiteStonesLabel);
        scoreLabel.setVisible(false);
        deadBlackStonesLabel.setVisible(false);
        deadWhiteStonesLabel.setVisible(false);
    }

    JLabel getTurnLabel(){
        return turnLabel;
    }

    void setScoreLabels(int blackStones, int whiteStones){
        deadBlackStonesLabel.setText("Dead black stones: " + blackStones);
        deadWhiteStonesLabel.setText("Dead white stones: " + whiteStones);
    }


}
