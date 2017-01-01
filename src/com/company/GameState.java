package com.company;

/**
 * Created by agnie on 12/18/2016.
 */
public class GameState {

    private int[][] boardState;
    private int deadBlackStones;
    private int deadWhiteStones;
    private int blackStonesInAtari;
    private int whiteStonesInAtari;

    GameState(){
        boardState = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    }

    GameState(int[][] boardState, int deadBlackStones, int deadWhiteStones, int blackStonesInAtari, int whiteStonesInAtari){
        this.boardState=boardState;
        this.deadBlackStones=deadBlackStones;
        this.deadWhiteStones=deadWhiteStones;
        this.blackStonesInAtari=blackStonesInAtari;
        this.whiteStonesInAtari=whiteStonesInAtari;
    }

    GameState(GameState otherState){
        this.boardState=otherState.boardState;
        this.deadBlackStones=otherState.deadBlackStones;
        this.deadWhiteStones=otherState.deadWhiteStones;
        this.blackStonesInAtari=otherState.blackStonesInAtari;
        this.whiteStonesInAtari=otherState.whiteStonesInAtari;
    }

    int[][] getBoardState(){
        return boardState;
    }

    int getDeadBlackStones(){
        return deadBlackStones;
    }

    void setDeadBlackStones(int number){
        deadBlackStones = number;
    }

    int getDeadWhiteStones(){
        return deadWhiteStones;
    }

    void setDeadWhiteStones(int number){
        deadWhiteStones = number;
    }

}
