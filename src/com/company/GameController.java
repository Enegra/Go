package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 12/18/2016.
 */
public class GameController {

    private int currentPlayer;
    private int opposingPlayer;
    private BoardUI boardUI;
    private ArrayList<GameState> gameFlow;
    private GameState gameState;
    private ArrayList<ArrayList<Integer>> capturedCoordinates = new ArrayList<>();
    private int[] coordinatesX = new int[19];
    private int[] coordinatesY = new int[19];
    private int k = 0;
    private boolean[][] testing = new boolean[19][19];

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
        killCaptured(i, j);
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

    private void killCaptured(int i, int j) {
        checkCapturing(i + 1, j, opposingPlayer);
        checkCapturing(i - 1, j, opposingPlayer);
        checkCapturing(i, j + 1, opposingPlayer);
        checkCapturing(i, j - 1, opposingPlayer);
        takeStoneGroup(capturedCoordinates);
        testing = new boolean[19][19];
    }

    private void takeStoneGroup(ArrayList<ArrayList<Integer>> capturedCoordinates) {
        for (ArrayList<Integer> capturedCoordinate : capturedCoordinates) {
            takeStone(capturedCoordinate.get(0), capturedCoordinate.get(1));
            System.out.println("stone removed: " + capturedCoordinate.get(0) + " " + capturedCoordinate.get(1));
        }
    }

    private boolean checkLiberty(boolean[][] testing, int x, int y, int color) {
        // out of the board there aren't liberties
        if (x < 0 || x >= gameState.getBoardState().length || y < 0 || y >= gameState.getBoardState().length)
            return true;
        // however empty field means liberty
        if (gameState.getBoardState()[x][y] == null) return false;
        // already tested field or stone of enemy isn't giving us a liberty.
        if (testing[x][y] || gameState.getBoardState()[x][y].getColor() != color) {
            return true;
        }
        // set this field as tested
        testing[x][y] = true;

        // in this case we are checking our stone, if we get 4 trues, it has no liberty
        return checkLiberty(testing, x, y - 1, color) &&
                checkLiberty(testing, x, y + 1, color) &&
                checkLiberty(testing, x - 1, y, color) &&
                checkLiberty(testing, x + 1, y, color);
    }

    private void checkCapturing(int x, int y, int color) {

        // is there a stone possible to capture?
        if (gameState.getBoardState()[x][y] != null && gameState.getBoardState()[x][y].getColor() == color) {
            // create testing map

            // if it has zero liberties capture it
            if (checkLiberty(testing, x, y, color)) {
                ArrayList<Integer> coordinates = new ArrayList<>();
                coordinates.add(x);
                coordinates.add(y);
                System.out.println("adding " + x + " " + y);
                capturedCoordinates.add(coordinates);

                //Dirty Lithfix right here
                coordinatesX[k] = x;
                coordinatesY[k] = y;
                k++;

                if (!containsCoordinates(x+1, y)) {
                    checkCapturing(x + 1, y, color);
                }
                if (!containsCoordinates(x-1, y)) {
                    checkCapturing(x - 1, y, color);
                }
                if (!containsCoordinates(x, y+1)) {
                    checkCapturing(x, y + 1, color);
                }
                if (!containsCoordinates(x, y-1)) {
                    checkCapturing(x, y - 1, color);
                }
            }
        }
    }

    private boolean containsCoordinates(int x, int y){
        for (int i = 0; i < coordinatesX.length ; i++) {
            if((coordinatesX[i] == x) && (coordinatesY[i] == y)){
                return true;
            }
        }
        return false;
    }
}
