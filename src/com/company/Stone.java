package com.company;

/**
 * Created by agnie on 1/2/2017.
 */
class Stone {

    private int color;

    Stone(int color){
        this.color = color;
    }

    Stone(Stone another){
        this.color = another.color;
    }

    int getColor() {
        return color;
    }

    void setColor(int color) {
        this.color = color;
    }


}
