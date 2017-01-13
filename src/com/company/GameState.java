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

    ArrayList<ArrayList<Integer>> getEmptyFields() {
        ArrayList<ArrayList<Integer>> emptyFields = new ArrayList<>();
        for (int i = 0; i < getBoardState().length; i++) {
            for (int j = 0; j < getBoardState().length ; j++) {
                if(getBoardState()[i][j] == null) {
                    ArrayList<Integer> coordinates = new ArrayList<>();
                    coordinates.add(i);
                    coordinates.add(j);
                    emptyFields.add(coordinates);
                }
            }
        }
        return emptyFields;
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
