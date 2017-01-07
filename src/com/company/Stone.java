package com.company;

/**
 * Created by agnie on 1/2/2017.
 */
public class Stone {

    private int color;
    private Stone top,left,right,bottom;
    private int liberties=4; //by default a stone has 4 liberties

    Stone(int color){
        this.color = color;
    }

    Stone(int color, int liberties){
        this.color=color;
        this.liberties=liberties;
    }

    int getColor() {
        return color;
    }

    void setColor(int color) {
        this.color = color;
    }

    int getLiberties(){
        return liberties;
    }

    void setLiberties(int liberties){
        this.liberties=liberties;
    }

}
