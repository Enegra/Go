package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 12/18/2016.
 */
public class GameState {

    private Stone[][] boardState;
    private int deadBlackStones;
    private int deadWhiteStones;
    private int blackStonesInAtari;
    private int whiteStonesInAtari;

    GameState() {
        boardState = new Stone[19][19];
    }

    GameState(Stone[][] boardState, int deadBlackStones, int deadWhiteStones, int blackStonesInAtari, int whiteStonesInAtari) {
        this.boardState = boardState;
        this.deadBlackStones = deadBlackStones;
        this.deadWhiteStones = deadWhiteStones;
        this.blackStonesInAtari = blackStonesInAtari;
        this.whiteStonesInAtari = whiteStonesInAtari;
    }

    GameState(GameState otherState) {
        boardState = new Stone[19][19];
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (otherState.boardState[i][j] != null) {
                    boardState[i][j] = new Stone(otherState.boardState[i][j]);
                }
            }
        }
//        this.boardState=otherState.boardState;
        this.deadBlackStones = otherState.deadBlackStones;
        this.deadWhiteStones = otherState.deadWhiteStones;
        this.blackStonesInAtari = otherState.blackStonesInAtari;
        this.whiteStonesInAtari = otherState.whiteStonesInAtari;
    }

    Stone[][] getBoardState() {
        return boardState;
    }

    ArrayList<Move> getStones(int player, GameController controller) {
        ArrayList<Move> stoneLocation = new ArrayList<>();
        for (int i = 0; i < getBoardState().length; i++) {
            for (int j = 0; j < getBoardState().length; j++) {
                if (getBoardState()[i][j] != null && getBoardState()[i][j].getColor() != player) {
                    if((i+1 < getBoardState().length) && getBoardState()[i+1][j] == null){
                        if (controller.moveAllowed(i+1,j)){
                            Move move = new Move(i+1, j, 10);
                            stoneLocation.add(move);
                        }
                    }
                    if((i-1 > 0) &&getBoardState()[i-1][j] == null){
                        if (controller.moveAllowed(i-1, j)) {
                            Move move = new Move(i-1, j, 10);
                            stoneLocation.add(move);
                        }
                    }
                    if((j+1 < getBoardState().length) && getBoardState()[i][j+1] == null){
                        if (controller.moveAllowed(i, j+1)){
                            Move move = new Move(i, j+1, 10);
                            stoneLocation.add(move);
                        }
                    }
                    if((j-1 > 0) && getBoardState()[i][j-1] == null){
                        if (controller.moveAllowed(i, j-1)){
                            Move move = new Move(i, j-1, 10);
                            stoneLocation.add(move);
                        }
                    }
                }
            }
        }
        return stoneLocation;
    }


    int getDeadBlackStones() {
        return deadBlackStones;
    }

    void setDeadBlackStones(int number) {
        deadBlackStones = number;
    }

    int getDeadWhiteStones() {
        return deadWhiteStones;
    }

    void setDeadWhiteStones(int number) {
        deadWhiteStones = number;
    }

}
