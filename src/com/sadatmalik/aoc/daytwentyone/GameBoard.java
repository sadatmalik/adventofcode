package com.sadatmalik.aoc.daytwentyone;

public class GameBoard {

    public static int move(int start, int numSpaces) {
        int boardSpace = (start+numSpaces) % 10;
        return boardSpace != 0 ? boardSpace : 10;
    }

}
