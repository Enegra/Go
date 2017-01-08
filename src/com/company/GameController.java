package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agnie on 12/18/2016.
 */
public class GameController {

    private int currentPlayer;
    private int opposingPlayer;
    private BoardUI boardUI;
    private ArrayList<GameState> gameFlow;
    private GameState gameState;

    GameController() {
        newGame();
        displayBoard();
    }

    private void newGame() {
        gameFlow = new ArrayList<GameState>();
        gameState = new GameState();
        gameFlow.add(new GameState(gameState));
    }

    void setTurn(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    ;

    int getCurrentPlayer() {
        return currentPlayer;
    }

    private void displayBoard() {
        setTurn(0);
        boardUI = new BoardUI();
        boardUI.getControlPanel().getTurnLabel().setVisible(true);
        boardUI.getControlPanel().getTurnLabel().setText("Current turn: " + currentPlayer);
        boardUI.drawGo(this);
    }

    void switchTurn() {
        if (currentPlayer == 0) {
            currentPlayer = 1;
            opposingPlayer = 0;
        } else {
            currentPlayer = 0;
            opposingPlayer = 1;
        }
        boardUI.getControlPanel().getTurnLabel().setText("Current turn: " + currentPlayer);
        gameFlow.add(new GameState(gameState));
    }

    boolean moveAllowed(int i, int j) {
        //// TODO: 1/1/2017
        return false;
    }

    void putStone(int i, int j) {
        Stone stone = new Stone(currentPlayer);

        gameState.getBoardState()[i][j] = stone;

        ArrayList<Stone> captured = new ArrayList<>();
        checkCapturing(gameState.getBoardState(), i+1, j, opposingPlayer);
        checkCapturing(gameState.getBoardState(), i-1, j, opposingPlayer);
        checkCapturing(gameState.getBoardState(), i, j+1, opposingPlayer);
        checkCapturing(gameState.getBoardState(), i, j-1, opposingPlayer);

//        checkCapturing(gameState.getBoardState(), i, j, currentPlayer);
    }

    void takeStone(int i, int j) {
        if (gameState.getBoardState()[i][j].getColor() == 1) {
            gameState.setDeadBlackStones(gameState.getDeadBlackStones() + 1);
        } else if (gameState.getBoardState()[i][j].getColor() == 2) {
            gameState.setDeadWhiteStones(gameState.getDeadWhiteStones() + 1);
        }
        gameState.getBoardState()[i][j] = null;
        boardUI.getBoardPanel().revalidate();
        boardUI.getBoardPanel().repaint();
    }

    GameState getGameState() {
        return gameState;
    }

    void takeStoneGroup(ArrayList<ArrayList<Integer>> capturedCoordinates){
        for (int i = 0; i < capturedCoordinates.size() ; i++) {
            takeStone(capturedCoordinates.get(i).get(0), capturedCoordinates.get(i).get(1));
            System.out.println("stone removed: " + capturedCoordinates.get(i).get(0) + " " + capturedCoordinates.get(i).get(1));
        }
    }

    boolean checkLiberty(Stone[][] position, boolean[][] testing, int x, int y, int color) {
        // out of the board there aren't liberties
        if (x < 0 || x >= position.length || y < 0 || y >= position.length) return true;
        // however empty field means liberty
        if (position[x][y] == null) return false;
        // already tested field or stone of enemy isn't giving us a liberty.
        if (testing[x][y] == true || position[x][y].getColor() != color) {
            return true;
        }
        // set this field as tested
        testing[x][y] = true;

        // in this case we are checking our stone, if we get 4 trues, it has no liberty
        return  checkLiberty(position, testing, x, y - 1, color) &&
                checkLiberty(position, testing, x, y + 1, color) &&
                checkLiberty(position, testing, x - 1, y, color) &&
                checkLiberty(position, testing, x + 1, y, color);
    }

    void checkCapturing(Stone[][] position, int x, int y, int color) {
       ArrayList<ArrayList<Integer>> capturedCoordinates = new ArrayList<>();
        // is there a stone possible to capture?
            if (position[x][y] != null  && position[x][y].getColor() == color) {
                // create testing map
                boolean[][] testing = new boolean[19][19];
                // if it has zero liberties capture it
                if (checkLiberty(position, testing, x, y, color)) {
                    ArrayList<Integer> coordinates = new ArrayList<>();
                    coordinates.add(x);
                    coordinates.add(y);
                    capturedCoordinates.add(coordinates);
                    // capture stones from game
                    //takeStone(x, y);
                }
                takeStoneGroup(capturedCoordinates);
            }

    }
}
