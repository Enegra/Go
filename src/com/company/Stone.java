package com.company;

/**
 * Created by agnie on 1/2/2017.
 */
public class Stone {

    private int color;
    private Stone top,left,right,bottom;

    Stone(int color){
        this.color = color;
    }

    Stone(Stone top, Stone left, Stone right, Stone bottom){
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Stone getTop() {
        return top;
    }

    public void setTop(Stone top) {
        this.top = top;
    }

    public Stone getLeft() {
        return left;
    }

    public void setLeft(Stone left) {
        this.left = left;
    }

    public Stone getRight() {
        return right;
    }

    public void setRight(Stone right) {
        this.right = right;
    }

    public Stone getBottom() {
        return bottom;
    }

    public void setBottom(Stone bottom) {
        this.bottom = bottom;
    }

}
