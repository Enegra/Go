package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by agnie on 1/12/2017.
 */
public class Player {

    int size = 19;
    int maxDepth = 2;


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

    public int[] getBest(int player, int depth, GameController controller) {
        ArrayList<Move> possibleMoves = controller.getGameState().getStones(player);

        Move bestMove = new Move(-1, -1, 0);

        int bestScore = (player == 1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;

        int deadWhites = controller.getGameState().getDeadWhiteStones();
        int deadBlacks = controller.getGameState().getDeadBlackStones();

        if ((depth > maxDepth) || possibleMoves.isEmpty()) {
            bestScore = (findScoreForMove(controller, bestMove, player, deadWhites, deadBlacks));
        } else {
            depth++;
            for (Move possibleMove : possibleMoves) {
                controller.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = new Stone(player);
                if (player == 1) {
                    currentScore = getBest(depth, player - 1, controller)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = possibleMove.getX();
                        bestCol = possibleMove.getY();
                    }
                } else {
                    currentScore = getBest(depth, player, controller)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = possibleMove.getX();
                        bestCol = possibleMove.getY();
                    }
                }
                controller.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = null;
            }
        }
        return new int[]{bestRow, bestCol, bestScore};
    }

    public int[] getBestAlphaBeta(int player, int depth, GameController controller, int alpha, int beta) {
        ArrayList<Move> possibleMoves = controller.getGameState().getStones(player);

        Move bestMove = new Move(-1, -1, 0);

        int currentScore;
        int bestRow = -1;
        int bestCol = -1;

        int deadWhites = controller.getGameState().getDeadWhiteStones();
        int deadBlacks = controller.getGameState().getDeadBlackStones();

        if ((depth > maxDepth) || possibleMoves.isEmpty()) {
            currentScore = (findScoreForMove(controller, bestMove, player, deadWhites, deadBlacks));
            return new int[]{bestRow, bestCol, currentScore};

        } else {

            for (Move possibleMove : possibleMoves) {
                depth++;
                GameController newControl = new GameController(controller.getGameState());
                newControl.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = new Stone(player);
                if (player == 1) {
                    currentScore = getBestAlphaBeta(depth, player - 1, newControl, alpha, beta)[0];
                    if (currentScore > alpha) {
                        alpha = currentScore;
                        bestRow = possibleMove.getX();
                        bestCol = possibleMove.getY();
                    }
                } else {
                    currentScore = getBestAlphaBeta(depth, player, newControl, alpha, beta)[0];
                    if (currentScore < beta) {
                        beta = currentScore;
                        bestRow = possibleMove.getX();
                        bestCol = possibleMove.getY();
                    }
                }
                //  controller.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = null;
                if (alpha >= beta) break;
            }
        }
        return new int[]{bestRow, bestCol, (player == 1) ? alpha : beta};
    }

    public int[] getBestNegascout(int player, int depth, GameController controller, int alpha, int beta) {
        ArrayList<Move> possibleMoves = controller.getGameState().getStones(player);
        int currentScore;
        Move bestMove = new Move(-1, -1, 0);

        int bestRow = -1;
        int bestCol = -1;

        int deadWhites = controller.getGameState().getDeadWhiteStones();
        int deadBlacks = controller.getGameState().getDeadBlackStones();

        if (possibleMoves.isEmpty()) {
            currentScore = (findScoreForMove(controller, bestMove, player, deadWhites, deadBlacks));
            return new int[]{bestRow, bestCol, currentScore};
        }

        if (depth > maxDepth) {
            int score = Quiesce(player, depth, controller, alpha, beta);
            return new int[]{bestRow, bestCol, score};
        }

        for (Move possibleMove : possibleMoves) {
            depth++;
            GameController newControl = new GameController(controller.getGameState());

            currentScore = Integer.MIN_VALUE;
            if (player == 1) {
                newControl.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = new Stone(player);
                currentScore = getBestNegascout(depth, player - 1, newControl, alpha + 1, alpha)[0];
                if (currentScore > alpha && currentScore < beta) {
                    currentScore = getBestNegascout(depth, player - 1, newControl, beta, currentScore)[0];
                    alpha = currentScore;
                    bestRow = possibleMove.getX();
                    bestCol = possibleMove.getY();
                    newControl.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = null;
                }
            } else {
                newControl.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = new Stone(player);
                currentScore = getBestNegascout(depth, player - 1, newControl, beta, alpha)[0];
                newControl.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = null;
            }
            //  controller.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = null;
            if (currentScore >= beta) {
                return new int[]{bestRow, bestCol, currentScore};
            }
            if (currentScore > alpha) {
                alpha = currentScore;
            }
        }
        return new int[]{bestRow, bestCol, alpha};
    }


    private int findScoreForMove(GameController controller, Move possibleMove, int player, int deadWhiteStones, int deadBlackStones) {
        if (player == 0) {
            if (controller.getGameState().getDeadBlackStones() > deadBlackStones) {
                possibleMove.setRank(1000);
            } else if (controller.getGameState().getDeadBlackStones() == deadBlackStones) {
                possibleMove.setRank(10);
            } else {
                possibleMove.setRank(0);
            }
        } else {
            if (controller.getGameState().getDeadWhiteStones() > deadWhiteStones) {
                possibleMove.setRank(100);
            } else if (controller.getGameState().getDeadWhiteStones() == deadWhiteStones) {
                possibleMove.setRank(10);
            } else {
                possibleMove.setRank(0);
            }
        }
        return possibleMove.getRank();
    }

    public Move getMove(int player, GameController controller) {
        //int[] bestMove = (getBest(player, 0, controller));
        //int[] bestMove = (getBestAlphaBeta(player, 0, controller, Integer.MIN_VALUE, Integer.MAX_VALUE));
        int[] bestMove = (getBestNegascout(player, 0, controller, Integer.MIN_VALUE, Integer.MAX_VALUE));
        Move move = new Move(bestMove[0], bestMove[1], bestMove[2]);
        return move;
    }

    public int Quiesce(int player, int depth, GameController controller, int alpha, int beta) {
        ArrayList<Move> possibleMoves = controller.getGameState().getStones(player);
        int currentScore;
        Move bestMove = new Move(-1, -1, 0);
        int deadWhites = controller.getGameState().getDeadWhiteStones();
        int deadBlacks = controller.getGameState().getDeadBlackStones();
        int score = (findScoreForMove(controller, bestMove, player, deadWhites, deadBlacks));

        if (score >= beta) {
            return beta;
        }
        if (alpha < score) {
            alpha = score;
        }
        if (possibleMoves.isEmpty()) {
            currentScore = (findScoreForMove(controller, bestMove, player, deadWhites, deadBlacks));
            return currentScore;
        }
        for (Move possibleMove : possibleMoves) {
            depth--;
            GameController newControl = new GameController(controller.getGameState());
            newControl.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = new Stone(player);
            int tempScore = Quiesce(player - 1, depth, newControl, beta, alpha);
            newControl.getGameState().getBoardState()[possibleMove.getX()][possibleMove.getY()] = null;

            if (tempScore >= beta) {
                return beta;
            }
            if (tempScore > alpha) {
                alpha = tempScore;
            }
        }
        return alpha;
    }
}


