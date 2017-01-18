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
    private Player ai = new Player();
    private int selectedHeuristic = 0;

    GameController() {
        newGame();
        displayBoard();

    }

    GameController(GameState gameState) {
        gameFlow = new ArrayList<>();
        this.gameState = new GameState(gameState);
        gameFlow.add(new GameState(gameState));
    }

    private void newGame() {
        gameFlow = new ArrayList<>();
        gameState = new GameState();
        gameFlow.add(new GameState(gameState));
    }

    void setTurn(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

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
        boardUI.getBoardPanel().revalidate();
        boardUI.getBoardPanel().repaint();
        boardUI.getControlPanel().getTurnLabel().setText("Current turn: " + currentPlayer);
        gameFlow.add(new GameState(gameState));
        if (currentPlayer == 1) {
            aiTurn();
        }
    }

    void aiTurn() {
        Move move = new Move(0, 0, 0);
        if (boardUI.getControlPanel().getHeuristicBox().getSelectedIndex() == 0) {
            move = ai.getMove(currentPlayer, this, 0);
        } else {
            move = ai.getMove(currentPlayer, this, 1);
        }
        putStone(move.getX(), move.getY());
    }

    boolean moveAllowed(int i, int j) {
        boolean[][] checked = new boolean[19][19];
        int liberties = 4;

        if (checkLiberty(checked, i, j - 1, currentPlayer)) {
            liberties--;
        }
        if (checkLiberty(checked, i, j + 1, currentPlayer)) {
            liberties--;
        }
        if (checkLiberty(checked, i + 1, j, currentPlayer)) {
            liberties--;
        }
        if (checkLiberty(checked, i - 1, j, currentPlayer)) {
            liberties--;
        }
        return (liberties > 0) && gameState.getBoardState()[i][j] == null && superko(i, j);
    }

    private boolean superko(int i, int j) {
        for (GameState state : gameFlow) {
            if (state.getBoardState()[i][j] != null && state.getBoardState()[i][j].getColor() == currentPlayer) {
                return false;
            }
        }
        return true;
    }

    void putStone(int i, int j) {
        if (moveAllowed(i, j)) {
            Stone stone = new Stone(currentPlayer);
            gameState.getBoardState()[i][j] = stone;
            killCaptured(i, j);
            switchTurn();
        }
    }

    void takeStone(int i, int j) {
        if (gameState.getBoardState()[i][j].getColor() == 0) {
            gameState.setDeadBlackStones(gameState.getDeadBlackStones() + 1);
        } else if (gameState.getBoardState()[i][j].getColor() == 1) {
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
        boolean[][] checked = new boolean[19][19];
        checkCapturing(i + 1, j, opposingPlayer, checked);
        checkCapturing(i - 1, j, opposingPlayer, checked);
        checkCapturing(i, j + 1, opposingPlayer, checked);
        checkCapturing(i, j - 1, opposingPlayer, checked);
        takeStoneGroup(capturedCoordinates);
        capturedCoordinates.clear();
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

    public void checkCapturing(int x, int y, int color, boolean[][] checked) {
        // is there a stone possible to capture?
        if ((x >= 0 && x <= gameState.getBoardState().length) && (y >= 0 && y <= gameState.getBoardState().length)) {
            if (gameState.getBoardState()[x][y] != null && gameState.getBoardState()[x][y].getColor() == color) {
                // if it has zero liberties capture it
                if (checkLiberty(checked, x, y, color)) {
                    ArrayList<Integer> coordinates = new ArrayList<>();
                    coordinates.add(x);
                    coordinates.add(y);
                    System.out.println("adding " + x + " " + y);
                    capturedCoordinates.add(coordinates);

                    if (!containsCoordinates(x + 1, y)) {
                        checkCapturing(x + 1, y, color, checked);
                    }
                    if (!containsCoordinates(x - 1, y)) {
                        checkCapturing(x - 1, y, color, checked);
                    }
                    if (!containsCoordinates(x, y + 1)) {
                        checkCapturing(x, y + 1, color, checked);
                    }
                    if (!containsCoordinates(x, y - 1)) {
                        checkCapturing(x, y - 1, color, checked);
                    }
                }
            }
        }
    }

    private boolean containsCoordinates(int x, int y) {
        for (ArrayList<Integer> capturedCoordinate : capturedCoordinates) {
            if ((capturedCoordinate.get(0) == x) && (capturedCoordinate.get(1) == y)) {
                return true;
            }
        }
        return false;
    }
}
