package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 1/12/2017.
 */
public class Player {

    private GameController controller;

    Player(GameController controller){
        this.controller = controller;
    }

    private ArrayList<ArrayList<Integer>> movePool = new ArrayList<>();

    private void addInitialMoves(){
        setCoords(3,3);
        setCoords(3, controller.getGameState().getBoardState().length-4);
        setCoords(controller.getGameState().getBoardState().length-4,3);
        setCoords(controller.getGameState().getBoardState().length-4,controller.getGameState().getBoardState().length-4);
    }

    private void setCoords(int i, int j){
        ArrayList<Integer> coordinate = new ArrayList<>();
        coordinate.add(i);
        coordinate.add(j);
        movePool.add(coordinate);
    }
}
