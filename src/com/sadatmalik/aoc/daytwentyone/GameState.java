package com.sadatmalik.aoc.daytwentyone;

import java.util.Arrays;
import java.util.Objects;

public class GameState {
    int player;
    int[] scores;
    int[] boardSpaces;

    public GameState(int player, int[] scores, int[] boardSpaces) {
        this.player = player;
        this.scores = scores;
        this.boardSpaces = boardSpaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return player == gameState.player && Arrays.equals(scores, gameState.scores)
                && Arrays.equals(boardSpaces, gameState.boardSpaces);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(player);
        result = 31 * result + Arrays.hashCode(scores);
        result = 31 * result + Arrays.hashCode(boardSpaces);
        return result;
    }
}
