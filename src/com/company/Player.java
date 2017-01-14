package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 1/12/2017.
 */
public class Player {

    int size = 19;
    int maxDepth = 3;

    ArrayList<Integer> currentBestMove;
    private int currentTopScore;

    Player() {

    }

    private ArrayList<ArrayList<Integer>> movePool = new ArrayList<>();

    private void addInitialMoves() {
        setCoords(3, 3);
        setCoords(3, size - 2);
        setCoords(size - 2, 3);
        setCoords(size - 2, size - 2);
    }

    private void setCoords(int i, int j) {
        ArrayList<Integer> coordinate = new ArrayList<>();
        coordinate.add(i);
        coordinate.add(j);
        movePool.add(coordinate);
    }

    private ArrayList<Move> getBestMoveMinMax(GameState gamestate, int player, int depth) {
        ArrayList<Move> bestMove = new ArrayList<>();

        if (depth > maxDepth) {
            return bestMove;
        }

        GameController controller = new GameController(gamestate);

        ArrayList<ArrayList<Integer>> possibleMoves = controller.getGameState().getEmptyFields();

        for (int i = 0; i < possibleMoves.size(); i++) {

            GameController newController = new GameController(controller.getGameState());
            ArrayList<Integer> newSpot = possibleMoves.get(i);

            controller.putStone(newSpot.get(0), newSpot.get(1));

            //If Player is player 1
            if (controller.getCurrentPlayer() == 0) {

                //Maxing if this is our turn
                if (controller.getCurrentPlayer() == player) {
                    if (controller.getGameState().getDeadBlackStones() < newController.getGameState().getDeadBlackStones()) {
                        Move move = new Move(newSpot.get(0), newSpot.get(1), newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones());
                        bestMove.add(move);
                        if ((newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones() > currentTopScore)) {
                            currentTopScore = newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones();
                            currentBestMove = newSpot;
                        }
                    }
                } else {
                    //Min if it is opponents turn
                    if (controller.getGameState().getDeadBlackStones() > newController.getGameState().getDeadBlackStones()) {
                        Move move = new Move(newSpot.get(0), newSpot.get(1), controller.getGameState().getDeadBlackStones() - newController.getGameState().getDeadBlackStones());
                        bestMove.add(move);
                        if ((newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones() < currentTopScore)) {
                            currentTopScore = controller.getGameState().getDeadBlackStones() - newController.getGameState().getDeadBlackStones();
                            currentBestMove = newSpot;
                        }
                    }
                }
                bestMove.addAll(getBestMoveMinMax(newController.getGameState(), player, depth++));
                //If Player is player 2
            } else {

                //Maxing if this is our turn
                if (controller.getCurrentPlayer() == player) {
                    if (controller.getGameState().getDeadWhiteStones() < newController.getGameState().getDeadWhiteStones()) {
                        Move move = new Move(newSpot.get(0), newSpot.get(1), newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones());
                        bestMove.add(move);
                        if ((newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones() > currentTopScore)) {
                            currentTopScore = newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones();
                            currentBestMove = newSpot;
                        }
                    }
                } else {
                    //Min if it is opponents turn
                    if (controller.getGameState().getDeadWhiteStones() > newController.getGameState().getDeadWhiteStones()) {
                        Move move = new Move(newSpot.get(0), newSpot.get(1), controller.getGameState().getDeadWhiteStones() - newController.getGameState().getDeadWhiteStones());
                        bestMove.add(move);
                        if ((newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones() < currentTopScore)) {
                            currentTopScore = controller.getGameState().getDeadWhiteStones() - newController.getGameState().getDeadWhiteStones();
                            currentBestMove = newSpot;
                        }
                    }
                }
                bestMove.addAll(getBestMoveMinMax(newController.getGameState(), player, depth++));
            }
        }
        return bestMove;
    }


    private ArrayList<Move> getBestMoveAlphaBeta(GameState gamestate, int player, int depth) {
        ArrayList<Move> bestMove = new ArrayList<>();

        if (depth > maxDepth) {
            return bestMove;
        }

        GameController controller = new GameController(gamestate);

        ArrayList<ArrayList<Integer>> possibleMoves = controller.getGameState().getEmptyFields();

        for (int i = 0; i < possibleMoves.size(); i++) {

            GameController newController = new GameController(controller.getGameState());
            ArrayList<Integer> newSpot = possibleMoves.get(i);

            controller.putStone(newSpot.get(0), newSpot.get(1));

            //If Player is player 1
            if (controller.getCurrentPlayer() == 0) {

                //Maxing if this is our turn
                if (controller.getCurrentPlayer() == player) {
                    if (controller.getGameState().getDeadBlackStones() < newController.getGameState().getDeadBlackStones()) {
                        Move move = new Move(newSpot.get(0), newSpot.get(1), newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones());
                        bestMove.add(move);
                        if ((newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones() > currentTopScore)) {
                            currentTopScore = newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones();
                            currentBestMove = newSpot;
                        }
                    } else{
                        return bestMove;
                    }
                } else {
                    //Min if it is opponents turn
                    if (controller.getGameState().getDeadBlackStones() > newController.getGameState().getDeadBlackStones()) {
                        Move move = new Move(newSpot.get(0), newSpot.get(1), controller.getGameState().getDeadBlackStones() - newController.getGameState().getDeadBlackStones());
                        bestMove.add(move);
                        if ((newController.getGameState().getDeadBlackStones() - controller.getGameState().getDeadBlackStones() < currentTopScore)) {
                            currentTopScore = controller.getGameState().getDeadBlackStones() - newController.getGameState().getDeadBlackStones();
                            currentBestMove = newSpot;
                        }
                    } else {
                        return bestMove;
                    }
                }
                bestMove.addAll(getBestMoveMinMax(newController.getGameState(), player, depth++));
                //If Player is player 2
            } else {

                //Maxing if this is our turn
                if (controller.getCurrentPlayer() == player) {
                    if (controller.getGameState().getDeadWhiteStones() < newController.getGameState().getDeadWhiteStones()) {
                        Move move = new Move(newSpot.get(0), newSpot.get(1), newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones());
                        bestMove.add(move);
                        if ((newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones() > currentTopScore)) {
                            currentTopScore = newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones();
                            currentBestMove = newSpot;
                        }
                    } else {
                        return bestMove;
                    }
                } else {
                    //Min if it is opponents turn
                    if (controller.getGameState().getDeadWhiteStones() > newController.getGameState().getDeadWhiteStones()) {
                        Move move = new Move(newSpot.get(0), newSpot.get(1), controller.getGameState().getDeadWhiteStones() - newController.getGameState().getDeadWhiteStones());
                        bestMove.add(move);
                        if ((newController.getGameState().getDeadWhiteStones() - controller.getGameState().getDeadWhiteStones() < currentTopScore)) {
                            currentTopScore = controller.getGameState().getDeadWhiteStones() - newController.getGameState().getDeadWhiteStones();
                            currentBestMove = newSpot;
                        }
                    } else{
                        return bestMove;
                    }
                }
                bestMove.addAll(getBestMoveMinMax(newController.getGameState(), player, depth++));
            }
        }
        return bestMove;
    }


}