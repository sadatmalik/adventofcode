package com.sadatmalik.aoc.daytwentyone;

import java.util.HashMap;
import java.util.Map;

public class DiracDice {

    static Die die;
    static Player[] players;

    static Map<GameState, Long[]> dpCache;

    public static void main(String[] args) {
//        playDeterministicDice(new Die());
//        partOne();

        dpCache = new HashMap<>();
        playDiracDice();
    }

    private static void partOne() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getScore() < 1000) {
                System.out.println("\n" + players[i].getScore() + " * " + die.getNumRolls() + " = " +
                        players[i].getScore() * die.getNumRolls());
            }
        }
    }

    // part two
    private static void playDiracDice() {
        //test board
        //long wins[] = playDirac(0, new int[] {0,0}, new int[] {4,8});
        //puzzle board
        long wins[] = playDirac(0, new int[] {0,0}, new int[] {6,7});
        System.out.println("Player 1 winning universes: " + wins[0]);
        System.out.println("Player 2 winning universes: " + wins[1]);
    }

    private static long[] playDirac(int player, int[] scores, int[] boardSpaces) {

        if (scores[0] >= 21) {
            return new long[] {1,0}; //p1 wins
        }
        if (scores[1] >= 21) {
            return new long[] {0,1}; //p2 wins
        }

        // check for prior result - dynamic programming - mind bend!
        GameState state = new GameState(player, scores, boardSpaces);
        if (dpCache.containsKey(state)) {
            Long[] result = dpCache.get(state);
            return new long[] {result[0], result[1]}; // prior recorded wins for this game setup
        }

        // recursively play dice
        long[] wins = new long[] {0L,0L};

        // simulate 27 die roll possibilities
        for (int i = 1; i <=3; i++) {
            for (int j = 1; j <=3; j++) {
                for (int k = 1; k <= 3; k++) {
                    int dieScore = i + j + k;

                    // update player position
                    int[] newBoardSpaces = new int[] {boardSpaces[0], boardSpaces[1]};
                    newBoardSpaces[player] = GameBoard.move(boardSpaces[player], dieScore);

                    // update player score
                    int[] newScores = new int[] {scores[0], scores[1]};
                    newScores[player] = newScores[player] + newBoardSpaces[player];

                    // recursive call
                    long[] result = playDirac((player + 1) % 2, newScores, newBoardSpaces);

                    // update wins
                    wins[0] = wins[0] + result[0];
                    wins[1] = wins[1] + result[1];
                }
            }
        }
        // dynamic programming - store result for this configuration of player, scores, spaces
        GameState storingState = new GameState(player, scores, boardSpaces);
        dpCache.put(storingState, new Long[] {wins[0], wins[1]});
        return wins;
    }


    // part one
    private static void playDeterministicDice(Die gameDie) {
        die = gameDie;
        // sample - p1 start at 4, p2 starts at 8; result - 745 * 993 = 739785
        // puzzle - p1 starts at 6, p2 starts at 7 - 917 * 1005 = 921585
        Player p1 = new Player("Player 1");
        p1.setPosition(6);
        System.out.println(p1.name + " starting position " + p1.getPosition());

        Player p2 = new Player("Player 2");
        p2.setPosition(7);
        System.out.println(p2.name + " starting position " + p2.getPosition());

        players = new Player[] {p1, p2};

        int turn = 0;

        while(!foundWinner()) {
            Player player = players[turn % 2];

            int roll1 = die.roll();
            int roll2 = die.roll();
            int roll3 = die.roll();

            player.addToScore(roll1 + roll2 + roll3);

            System.out.println(player.name + " rolls " + roll1 + "+" +
                    roll2 + "+" + roll3 + " and moves to space " + player.getPosition() +
                    " for a total score of " + player.score);

            turn++;
        }
    }

    private static boolean foundWinner() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getScore() >= 1000) {
                return true;
            }
        }
        return false;
    }
}
