package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnie on 12/20/2016.
 */
public class ControlPanel extends JPanel{

    private JLabel gameTypeChoiceLabel, heuristicChoiceLabel;
    private JComboBox gameTypeChoiceComboBox, heuristicChoiceComboBox;
    private JButton startGameButton, pauseGameButton, skipGameButton;

    ControlPanel(){
        setupPanel();
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
    }

    private void setupGameTypeChoiceLabel(){
        gameTypeChoiceLabel = new JLabel("Choose the game type");
        gameTypeChoiceLabel.setPreferredSize(new Dimension(200,30));
        this.add(gameTypeChoiceLabel);
        gameTypeChoiceLabel.setVisible(true);
    }

    private void setupHeuristicChoiceLabel(){
        heuristicChoiceLabel = new JLabel("Choose heuristic");
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
        String[] heuristics = {"NegaScout", "Node search"};
        heuristicChoiceComboBox = new JComboBox(heuristics);
        heuristicChoiceComboBox.setPreferredSize(new Dimension(200,30));
        this.add(heuristicChoiceComboBox);
        heuristicChoiceComboBox.setVisible(true);
    }

    private void setupStartGameButton(){
        startGameButton = new JButton("Start game");
        startGameButton.setPreferredSize(new Dimension(200,30));
        this.add(startGameButton);
        startGameButton.setVisible(true);
    }

    private void setupPauseGameButton(){
        pauseGameButton = new JButton("Pause game");
        pauseGameButton.setPreferredSize(new Dimension(200,30));
        this.add(pauseGameButton);
        pauseGameButton.setVisible(true);
    }

    private void setupSkipGameButton(){
        skipGameButton = new JButton("Skip to the end of game");
        skipGameButton.setPreferredSize(new Dimension(200,30));
        this.add(skipGameButton);
        skipGameButton.setVisible(true);
    }

}
