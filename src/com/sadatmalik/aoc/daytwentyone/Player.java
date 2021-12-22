package com.sadatmalik.aoc.daytwentyone;

public class Player {
    String name;
    int score;
    int position;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void addToScore(int score) {
        position = GameBoard.move(position, score);
        this.score += position;
    }

    public int getScore() {
        return this.score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
