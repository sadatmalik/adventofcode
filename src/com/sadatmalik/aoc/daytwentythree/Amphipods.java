package com.sadatmalik.aoc.daytwentythree;

import java.util.*;

public class Amphipods {

    static int[] hallway = new int[12]; //intialises to 0
    static int[] upper = new int[5]; // initialize to puzzle inputs
    static int[] lower = new int[5];

    static int[] scores = new int[] {0, 0, 0, 0};

    static List<Integer> finalScores = new ArrayList<>();

    static Map<Integer, String> amphipods = new HashMap<>();
    static Map<Integer, Integer> rooms = new HashMap<>();

    static {
        amphipods.put(1, "A");
        amphipods.put(10, "B");
        amphipods.put(100, "C");
        amphipods.put(1000, "D");
        amphipods.put(0, ".");

        rooms.put(1, 1);
        rooms.put(10, 2);
        rooms.put(100, 3);
        rooms.put(1000, 4);
    }

    public static void main(String[] args) {
        setupTestGame();
        //setupPuzzleGame();
        //printGame(hallway, upper, lower);
        move(hallway, upper, lower, scores);

        Collections.sort(finalScores);
        System.out.println("Least possible energy score = " + finalScores.get(finalScores.size()));
    }

    private static void move(int[] hallway, int[] upper, int[] lower, int[] scores) {

        printGame(hallway, upper, lower);

        if (allAmphipodsHome(hallway, upper, lower)) {
            saveFinalScore(scores);
            return;
        }

        for (int a = 0; a < hallway.length; a++) { // select a hallway space
            boolean hallwayMove = moveFromHallway(a, hallway, upper, lower, scores); // see if a move is possible

            for (int b = 0; b < upper.length; b++) { // select an upper room space
                for (int c = 1; c < hallway.length; c++) { // select a hallway space
                    boolean upperMove = moveFromUpper(b, c, hallway, upper, scores); // see if a move is possible

                    for (int d = 0; d < lower.length; d++) { // select a lower room space
                        for (int e = 1; e < hallway.length; e++) { // select a hallway space
                            boolean lowerMove = moveFromLower(d, e, hallway, upper, lower, scores);

                            if (hallwayMove || upperMove || lowerMove) { // if a piece has moved
                                move(Arrays.copyOf(hallway, hallway.length),
                                        Arrays.copyOf(upper, upper.length),
                                        Arrays.copyOf(lower, lower.length),
                                        Arrays.copyOf(scores, scores.length)); // recursively call move()
                            } // no move was possible
                        }
                    }
                }
            }
        }
    }

    private static void saveFinalScore(int[] scores) {
        int totalScore = 0;
        for (int i = 0; i < scores.length; i++) {
            totalScore += scores[i];
        }
        finalScores.add(totalScore);
    }

    private static boolean allAmphipodsHome(int[] hallway, int[] upper, int[] lower) {
        for (int i = 0; i < hallway.length; i++) {
            if (hallway[i] != 0)
                return false;
        }
        for (int i = 1; i < upper.length; i++) {
            if (upper[i] != (int) Math.pow(10, i-1))
                return false;
        }
        for (int i = 1; i < lower.length; i++) {
            if (lower[i] != (int) Math.pow(10, i-1))
                return false;
        }
        return true;
    }

    private static boolean moveFromLower(int room, int end, int[] hallway, int[] upper, int[] lower, int[] scores) {

        if (room == 0) // intentional to simulate no move
            return false;

        int amphipod = lower[room];
        if (amphipod == 0) // check that there's an amphipod to move -- i.e. not '0'
            return false;

        int start = getHallwayRoomPosition(room);

        if (upper[room] != 0)
            return false;

        if (!isHallwayClear(hallway, start, end))
            return false;

        // calculates and updates score
        moveToHallway(amphipod, start, end, false);

        // move
        lower[room] = 0;
        hallway[end] = amphipod;

        return true;
    }

    private static boolean moveFromUpper(int room, int end, int[] hallway, int[] upper, int[] scores) {

        if (room == 0) // intentional to simulate no move
            return false;

        int amphipod = upper[room];
        if (amphipod == 0) // check that there's an amphipod to move -- i.e. not '0'
            return false;

        int start = getHallwayRoomPosition(room);

        if (!isHallwayClear(hallway, start, end))
            return false;

        // calculates and updates score
        moveToHallway(amphipod, start, end, true);

        // move
        upper[room] = 0;
        hallway[end] = amphipod;

        return true;
    }

    private static int moveToHallway(int amphipod, int start, int end, boolean fromUpper) {
        int score = Math.abs(start - end) * amphipod;
        if (fromUpper) {
            score += 1; //moving from upper room
        } else {
            score += 2; //moving from lower room
        }
        System.out.println("Amphipod = " + amphipod);
        scores[rooms.get(amphipod)-1] = scores[rooms.get(amphipod)-1] + score;
        return score;
    }

    private static boolean moveFromHallway(int start, int[] hallway, int[] upper, int[] lower, int[] scores) {

        int amphipod = hallway[start];
        if (amphipod == 0) {
            return false;
        }

        // move and update score
        int score = moveToRoom(amphipod, start, hallway, upper, lower);
        scores[rooms.get(amphipod)-1] = scores[rooms.get(amphipod)-1] + score;

        return score == 0 ? false : true;
    }

    private static int moveToRoom(int amphipod, int start, int[] hallway, int[] upper, int[] lower) {
        int room = rooms.get(amphipod);
        int score = 0;

        // check no other piece is in the way
        if (!isHallwayClear(hallway, start, room))
            return score;

        if (upper[room] + lower[room] == 0) { // both empty
            //move to lower room
            hallway[start] = 0;
            lower[room] = amphipod;

            // calculate scores
            int end = getHallwayRoomPosition(room);
            score = getHallwayMoveScore(start, end, amphipod);
            score += 2; // moved to lower room

        } else if (lower[room] == amphipod && upper[room] == 0) {
            // move to upper
            hallway[start] = 0;
            upper[room] = amphipod;

            // calculate scores
            int end = getHallwayRoomPosition(room);
            score = getHallwayMoveScore(start, end, amphipod);
            score += 1; // moved to upper room
        }
        return score; // no move possible
    }

    private static int getHallwayMoveScore(int start, int end, int amphipod) {
        int moves = Math.abs(end - start);
        return moves * amphipod;
    }

    private static int getHallwayRoomPosition(int room) {
        return (room*2) +1;
    }

    private static boolean isHallwayClear(int[] hallway, int start, int end) {
        if (start < end) {
            for (int i = start+1; i <= end; i++) {
                if (!(hallway[i] == 0)) {
                    return false; // found a block
                }
            }
        } else {
            for (int i = start-1; i >= end; i--) {
                if (!(hallway[i] == 0)) {
                    return false; // found a block
                }
            }
        }
        return true; // all clear
    }

    private static void printGame(int[] hallway, int[] upper, int[] lower) {
        System.out.println("#############");
        System.out.print("#");
        for (int i = 1; i < hallway.length; i++) {
            System.out.print(amphipods.get(hallway[i]));
        }
        System.out.println("#");
        System.out.print("###");
        for (int i = 1; i < upper.length; i++) {
            System.out.print(amphipods.get(upper[i]) + "#");
        }
        System.out.println("##");
        System.out.print("  #");
        for (int i = 1; i < lower.length; i++) {
            System.out.print(amphipods.get(lower[i]) + "#");
        }
        System.out.println("\n  #########");
    }

    private static void setupTestGame() {
        upper[1] = 10; //B
        upper[2] = 100; //C
        upper[3] = 10; //B
        upper[4] = 1000; //D

        lower[1] = 1; //A
        lower[2] = 1000; //D
        lower[3] = 100; //C
        lower[4] = 1; //A
    }


}
