package com.company;

/**
 * Created by agnie on 1/14/2017.
 */
public class Move {
    private int x;
    private int y;
    private int rank;

    public Move(int x, int y, int rank){
        this.x = x;
        this.y = y;
        this.rank = rank;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
