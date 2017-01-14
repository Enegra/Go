package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 1/12/2017.
 */
public class Player {

    int size = 19;

    ArrayList<Integer> currentBestMove;
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

        ArrayList<ArrayList<Integer>> possibleMoves = controller.getGameState().getEmptyFields();

        for (int i = 0; i < possibleMoves.size(); i++) {

                GameController newController = new GameController(controller.getGameState());
                ArrayList<Integer> newSpot = possibleMoves.get(i);

                controller.putStone(newSpot.get(0), newSpot.get(1));

                if(controller.getCurrentPlayer() == 0){
                    if((controller.getGameState().getDeadBlackStones() < newController.getGameState().getDeadBlackStones())
                            && (newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones() > currentTopScore) ){
                        currentTopScore = newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones();
                        currentBestMove = newSpot;
                    }
                } else {
                    if((controller.getGameState().getDeadWhiteStones() < newController.getGameState().getDeadWhiteStones())
                            && (newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones() > currentTopScore)) {
                        currentTopScore = newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones();
                        currentBestMove = newSpot;
                    }
                }
                bestMove = currentBestMove;
        }
        return bestMove;
    }

}