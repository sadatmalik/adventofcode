package com.sadatmalik.aoc.dayseventeen;

public class Velocity {
    int x;
    int y;

    public Velocity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void xTowardZero() {
        if (x > 0) {
            x--;
        }
        if (x < 0) {
            x++;
        }
    }

    public void decreaseY(){
        this.y--;
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
