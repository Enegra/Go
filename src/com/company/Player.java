package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 1/12/2017.
 */
public class Player {

    int size = 19;

    Player(){

    }

    private ArrayList<ArrayList<Integer>> movePool = new ArrayList<>();

    private void addInitialMoves(){
        setCoords(3,3);
        setCoords(3, size-2);
        setCoords(size-2,3);
        setCoords(size-2,size-2);
    }

    private void setCoords(int i, int j){
        ArrayList<Integer> coordinate = new ArrayList<>();
        coordinate.add(i);
        coordinate.add(j);
        movePool.add(coordinate);
    }
}
