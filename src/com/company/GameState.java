package com.company;

/**
 * Created by agnie on 12/18/2016.
 */
public class GameState {

    private Stone[][] boardState;
    private int deadBlackStones;
    private int deadWhiteStones;
    private int blackStonesInAtari;
    private int whiteStonesInAtari;

    GameState(){
       boardState = new Stone[19][19];
    }

    GameState(Stone[][] boardState, int deadBlackStones, int deadWhiteStones, int blackStonesInAtari, int whiteStonesInAtari){
        this.boardState=boardState;
        this.deadBlackStones=deadBlackStones;
        this.deadWhiteStones=deadWhiteStones;
        this.blackStonesInAtari=blackStonesInAtari;
        this.whiteStonesInAtari=whiteStonesInAtari;
    }

    GameState(GameState otherState){
        boardState = new Stone[19][19];
            for (int i=0; i<boardState.length; i++){
                for (int j=0; j<boardState[i].length; j++){
                    if (otherState.boardState[i][j]!=null){
                        boardState[i][j] = new Stone(otherState.boardState[i][j]);
                    }
                }
            }
//        this.boardState=otherState.boardState;
        this.deadBlackStones=otherState.deadBlackStones;
        this.deadWhiteStones=otherState.deadWhiteStones;
        this.blackStonesInAtari=otherState.blackStonesInAtari;
        this.whiteStonesInAtari=otherState.whiteStonesInAtari;
    }

    Stone[][] getBoardState(){
        return boardState;
    }

    int[][] getFreeSpots() {
        int k = 0;
        int[][] emptySpots = new int[19][19];
        for (int i = 0; i < getBoardState().length ; i++) {
            for (int j = 0; j < getBoardState().length ; j++) {
                if(getBoardState()[i][j] == null) {
                    emptySpots[k][0] = i;
                    emptySpots[1][k] = j;
                    k++;
                }
            }
        }
        return emptySpots;
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
