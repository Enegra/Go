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
        boardUI = new BoardUI();
        boardUI.drawGo(gameState.getBoardState(), this);
        setTurn(1);
    }

    void switchTurn(){
        if (currentPlayer==1){
            currentPlayer = 2;
        }
        else {
            currentPlayer = 1;
        }
        gameFlow.add(new GameState(gameState));
    }

    boolean moveAllowed(int i, int j){
        //// TODO: 1/1/2017
        return false;
    }

    void putStone(int i, int j){
        gameState.getBoardState()[i][j]=currentPlayer;
    }

    void takeStone(int i, int j){
        if (gameState.getBoardState()[i][j]==1){
            gameState.setDeadBlackStones(gameState.getDeadBlackStones()+1);
        }
        else if (gameState.getBoardState()[i][j]==2){
            gameState.setDeadWhiteStones(gameState.getDeadWhiteStones()+1);
        }
        gameState.getBoardState()[i][j]=0;
    }

}
