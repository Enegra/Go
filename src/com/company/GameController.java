package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 12/18/2016.
 */
public class GameController {

    private int currentPlayer;
    private BoardUI boardUI;
    private ArrayList<GameState> gameFlow;
    private GameState gameState;

    GameController(){
        newGame();
        displayBoard();
    }

    private void newGame(){
        gameFlow = new ArrayList<GameState>();
        gameState = new GameState();
        gameFlow.add(new GameState(gameState));
    }

    void setTurn(int currentPlayer){
        this.currentPlayer = currentPlayer;
    };

    int getCurrentPlayer(){
        return currentPlayer;
    }

    private void displayBoard(){
        setTurn(0);
        boardUI = new BoardUI();
        boardUI.getControlPanel().getTurnLabel().setVisible(true);
        boardUI.getControlPanel().getTurnLabel().setText("Current turn: " + currentPlayer);
        boardUI.drawGo(gameState.getBoardState(), this);
    }

    void switchTurn(){
        if (currentPlayer==0){
            currentPlayer = 1;
        }
        else {
            currentPlayer = 0;
        }
        boardUI.getControlPanel().getTurnLabel().setText("Current turn: " + currentPlayer);
        gameFlow.add(new GameState(gameState));
    }

    boolean moveAllowed(int i, int j){
        //// TODO: 1/1/2017
        return false;
    }

    void putStone(int i, int j){
        Stone stone = new Stone(currentPlayer);

        gameState.getBoardState()[i][j]=stone;
    }

    void takeStone(int i, int j){
        if (gameState.getBoardState()[i][j].getColor()==1){
            gameState.setDeadBlackStones(gameState.getDeadBlackStones()+1);
        }
        else if (gameState.getBoardState()[i][j].getColor()==2){
            gameState.setDeadWhiteStones(gameState.getDeadWhiteStones()+1);
        }
        gameState.getBoardState()[i][j]=null;
    }

}
