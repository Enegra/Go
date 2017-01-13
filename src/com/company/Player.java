package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 1/12/2017.
 */
public class Player {

    private ArrayList<ArrayList<Integer>> movePool = new ArrayList<>();

    private void addInitialMoves(){
        ArrayList<Integer> coordinates = new ArrayList<>();
        coordinates.add(4);
        coordinates.add(4);

        coordinates.add(4);
        coordinates.add(16);

        coordinates.add(16);
        coordinates.add(4);

        coordinates.add(16);
        coordinates.add(16);

        movePool.add(coordinates);
    }
}
