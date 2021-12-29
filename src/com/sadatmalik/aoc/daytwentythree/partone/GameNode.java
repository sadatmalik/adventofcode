package com.sadatmalik.aoc.daytwentythree.partone;

import java.util.*;

public class GameNode {

    int[] hallway;
    int[] upper;
    int[] lower;

    int fCost;
    int gCost;
    int hCost;

    static Map<Integer, String> amphipods = new HashMap<>();
    static Map<Integer, Integer> roomsByAmphipod = new HashMap<>();

    static {
        amphipods.put(1, "A");
        amphipods.put(10, "B");
        amphipods.put(100, "C");
        amphipods.put(1000, "D");
        amphipods.put(0, ".");

        roomsByAmphipod.put(1, 0);
        roomsByAmphipod.put(10, 1);
        roomsByAmphipod.put(100, 2);
        roomsByAmphipod.put(1000, 3);
    }

    public GameNode() {
        hallway = new int[11]; //intialises to 0
        upper = new int[4]; // initialise with puzzle inputs
        lower = new int[4];
    }

    public GameNode(int[] hallway, int[] upper, int[] lower) {
        this.hallway = hallway;
        this.upper = upper;
        this.lower = lower;
    }

    int finalCost() {
        return fCost;
    }

    List<GameNode> getAllPossibleMoves() {
        List<GameNode> moves = new ArrayList<>();
        // moves from hallway
        for (int a = 0; a < hallway.length; a++) { // select a hallway space
            GameNode movedToRoom = moveFromHallway(a);
            if (movedToRoom != null) {
                moves.add(movedToRoom);
            }
        }
        // moves from upper rooms
        for (int b = 0; b < upper.length; b++) { // select an upper room space
            for (int c = 1; c < hallway.length; c++) { // select a hallway space
                GameNode movedFromRoom = moveFromUpper(b, c); // see if a move is possible
                if (movedFromRoom != null) {
                    moves.add(movedFromRoom);
                }
            }
        }
        // moves from lower rooms
        for (int d = 0; d < lower.length; d++) { // select a lower room space
            for (int e = 1; e < hallway.length; e++) { // select a hallway space
                GameNode movedFromRoom = moveFromLower(d, e);
                if (movedFromRoom != null) {
                    moves.add(movedFromRoom);
                }
            }
        }
        return moves;
    }

    private GameNode moveFromHallway(int start) {

        int amphipod = hallway[start];
        if (amphipod == 0) {
            return null;
        }

        // calculate move with score
        GameNode movedToRoom = moveToRoom(start);
        return movedToRoom;
    }

    private GameNode moveToRoom(int start) {
        int amphipod = hallway[start];
        int room = roomsByAmphipod.get(amphipod);
        int end = getHallwayRoomPosition(room);
        int score = 0;

        // check no other piece is in the way
        if (!isHallwayClear(start, end))
            return null;

        GameNode newNode = copyGameNode();

        if (upper[room] + lower[room] == 0) { // both empty
            //move to lower room
            newNode.hallway[start] = 0;
            newNode.lower[room] = amphipod;

            // calculate scores
            score = getHallwayMoveScore(start, end, amphipod);
            score += 2 * amphipod; // moved to lower room

        } else if (lower[room] == amphipod && upper[room] == 0) {
            // move to upper room
            newNode.hallway[start] = 0;
            newNode.upper[room] = amphipod;

            // calculate scores
            score = getHallwayMoveScore(start, end, amphipod);
            score += 1 * amphipod; // moved to upper room
        } else {
            return null; // either no open rooms or lower has the wrong amphipod
        }

        // todo - heuristic function - currently Djikstra
        updateScores(score, newNode);
        return newNode;
    }

    int getHallwayRoomPosition(int room) {
        return (room*2) + 2;
    }

    boolean isHallwayClear(int start, int end) {
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

    static int getHallwayMoveScore(int start, int end, int amphipod) {
        int moves = Math.abs(end - start);
        return moves * amphipod;
    }

    private GameNode moveFromUpper(int room, int end) {

        if (endingIsOutsideRoomEntrance(end))
            return null;

        int amphipod = upper[room];
        if (amphipod == 0) // check that there's an amphipod to move -- i.e. not '0'
            return null;

        // don't move a piece out of a room it's meant to be in
        if (pieceInCorrectRoom(amphipod, room) && lower[room] == amphipod)
            return null;

        int start = getHallwayRoomPosition(room);

        if (!isHallwayClear(start, end))
            return null;

        // calculates and updates score
        GameNode movedFromRoom = moveToHallway(amphipod, room, start, end, true);
        return movedFromRoom;
    }

    private GameNode moveFromLower(int room, int end) {

        // todo - move these common lower/upper validations to a shared method

        // don't move to spaces immediately outside a room
        if (endingIsOutsideRoomEntrance(end))
            return null;

        int amphipod = lower[room];
        if (amphipod == 0) // check that there's an amphipod to move -- i.e. not '0'
            return null;

        if (pieceInCorrectRoom(amphipod, room)) // don't move a belonging piece out of a lower room
            return null;

        int start = getHallwayRoomPosition(room);

        if (upper[room] != 0) // if there's a piece in the way in the upper room
            return null;

        if (!isHallwayClear(start, end))
            return null;

        // calculates and updates score
        GameNode movedFromRoom = moveToHallway(amphipod, room, start, end, false);
        return movedFromRoom;
    }

    static boolean endingIsOutsideRoomEntrance(int end) {
        if (end == 2 || end == 4 || end == 6 || end == 8)
            return true;
        return false;
    }

    static boolean pieceInCorrectRoom(int amphipod, int room) {
        if (roomsByAmphipod.get(amphipod) == room)
            return true;
        return false;
    }

    private GameNode moveToHallway(int amphipod, int room, int start, int end, boolean fromUpper) {
        int score = Math.abs(start - end) * amphipod;
        GameNode newNode = copyGameNode();
        if (fromUpper) {
            score += 1 * amphipod; //moving from upper room
            newNode.upper[room] = 0;
            newNode.hallway[end] = amphipod;
        } else {
            score += 2 * amphipod; //moving from lower room
            newNode.lower[room] = 0;
            newNode.hallway[end] = amphipod;
        }

        // todo - heuristic function - currently Djikstra
        updateScores(score, newNode);
        return newNode;
    }

    private GameNode copyGameNode() {
        int[] newHallway = Arrays.copyOf(hallway, hallway.length);
        int[] newUpper = Arrays.copyOf(upper, upper.length);
        int[] newLower = Arrays.copyOf(lower, lower.length);

        return new GameNode(newHallway, newUpper, newLower);
    }

    void updateScores(int score, GameNode newNode) {
        newNode.gCost = gCost + score;
        newNode.fCost = newNode.gCost + newNode.hCost;
    }

    int[] getHallway() {
        return hallway;
    }

    int[] getUpper() {
        return upper;
    }

    int[] getLower() {
        return lower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameNode gameNode = (GameNode) o;
        return Arrays.equals(hallway, gameNode.hallway) &&
                Arrays.equals(upper, gameNode.upper) &&
                Arrays.equals(lower, gameNode.lower);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(hallway);
        result = 31 * result + Arrays.hashCode(upper);
        result = 31 * result + Arrays.hashCode(lower);
        return result;
    }
}
