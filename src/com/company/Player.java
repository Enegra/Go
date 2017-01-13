package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 1/12/2017.
 */
public class Player {

    int size = 19;

    private int[] currentTopMove = new int[2];
    private int currentTopScore;

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

    private ArrayList<Integer> GetBestMove(GameState gamestate, Player p) {
        ArrayList<Integer> bestMove = new ArrayList<>();

        GameController controller = new GameController(gamestate);

        int[][] possibleMoves = controller.getGameState().getFreeSpots();

        for (int i = 0; i < possibleMoves.length; i++) {
            for (int j = 0; j < possibleMoves.length; j++) {

                GameController newController = new GameController(controller.getGameState());
                int[] newSpot = new int[2];
                newSpot[0] = possibleMoves[i][0];
                newSpot[1] = possibleMoves[0][j];

                controller.putStone(newSpot[0], newSpot[1]);

                if(controller.getCurrentPlayer() == 0){
                    if(controller.getGameState().getDeadBlackStones() < newController.getGameState().getDeadBlackStones()){
                        currentTopScore = newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones();
                        currentTopMove[0] = newSpot[0];
                        currentTopMove[1] = newSpot[1];
                    }
                } else {
                    if(controller.getGameState().getDeadWhiteStones() < newController.getGameState().getDeadWhiteStones()) {
                        currentTopScore = newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones();
                        currentTopMove[0] = newSpot[0];
                        currentTopMove[1] = newSpot[1];
                    }
                }
                bestMove.add(currentTopMove[0]);
                bestMove.add(currentTopMove[1]);
            }
        }
        return bestMove;
    }

}