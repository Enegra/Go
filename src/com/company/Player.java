package com.company;

import java.util.ArrayList;

/**
 * Created by agnie on 1/12/2017.
 */
public class Player {

    int size = 19;
    int maxDepth = 3;


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

        for (ArrayList<Integer> possibleMove : possibleMoves) {
            GameController newController = new GameController(controller.getGameState());
            ArrayList<Integer> newSpot = possibleMove;

            controller.putStone(newSpot.get(0), newSpot.get(1));

            //If Player is player 1
            if (controller.getCurrentPlayer() == 0) {
                findScoreWhite(controller, newController, newSpot, player, bestMove);
                bestMove.addAll(getBestMoveMinMax(newController.getGameState(), player, depth++));
            }
            //Player is player 2
            else {
                findScoreBlack(controller, newController, newSpot, player, bestMove);
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

        for (ArrayList<Integer> possibleMove : possibleMoves) {
            GameController newController = new GameController(controller.getGameState());
            ArrayList<Integer> newSpot = possibleMove;

            controller.putStone(newSpot.get(0), newSpot.get(1));
            ArrayList<Move> bestMoveClone = new ArrayList<>(bestMove);
            //If Player is player 1
            if (controller.getCurrentPlayer() == 0) {
                findScoreWhite(controller, newController, newSpot, player, bestMove);
                if (!bestMove.equals(bestMoveClone)) {
                    bestMove.addAll(getBestMoveMinMax(newController.getGameState(), player, depth++));
                } else {
                    return bestMove;
                }
            }
            //If Player is player 2
            else {
                if (controller.getCurrentPlayer() == 0) {
                    findScoreBlack(controller, newController, newSpot, player, bestMove);
                    if (!bestMove.equals(bestMoveClone)) {
                        bestMove.addAll(getBestMoveMinMax(newController.getGameState(), player, depth++));
                    } else {
                        return bestMove;
                    }
                }
            }
        }
        return bestMove;
    }

    private void findScoreWhite(GameController controller, GameController newController, ArrayList<Integer> newSpot, int player, ArrayList<Move> bestMove) {
        Move move = new Move(newSpot.get(0), newSpot.get(1), 0);
        if (controller.getCurrentPlayer() == player) {
            if (controller.getGameState().getDeadBlackStones() <= newController.getGameState().getDeadBlackStones()) {
                if (controller.getGameState().getDeadBlackStones() < newController.getGameState().getDeadBlackStones()) {
                    move.setRank(100);
                } else {
                    move.setRank(10);
                }
                bestMove.add(move);
            }
        } else {
            //Min if it is opponents turn
            if (controller.getGameState().getDeadBlackStones() >= newController.getGameState().getDeadBlackStones()) {
                if (controller.getGameState().getDeadBlackStones() > newController.getGameState().getDeadBlackStones()) {
                    move.setRank(-100);
                } else {
                    move.setRank(-10);
                }
                bestMove.add(move);
            }
        }
    }

    private void findScoreBlack(GameController controller, GameController newController, ArrayList<Integer> newSpot, int player, ArrayList<Move> bestMove) {
        Move move = new Move(newSpot.get(0), newSpot.get(1), 0);
        if (controller.getCurrentPlayer() == player) {
            if (controller.getGameState().getDeadWhiteStones() <= newController.getGameState().getDeadWhiteStones()) {
                if (controller.getGameState().getDeadWhiteStones() < newController.getGameState().getDeadWhiteStones()) {
                    move.setRank(100);
                } else {
                    move.setRank(10);
                }
                bestMove.add(move);
            }
        } else {
            //Min if it is opponents turn
            if (controller.getGameState().getDeadWhiteStones() >= newController.getGameState().getDeadWhiteStones()) {
                if (controller.getGameState().getDeadWhiteStones() > newController.getGameState().getDeadWhiteStones()) {
                    move.setRank(-100);
                } else {
                    move.setRank(-10);
                }
                bestMove.add(move);
            }
        }
    }
}