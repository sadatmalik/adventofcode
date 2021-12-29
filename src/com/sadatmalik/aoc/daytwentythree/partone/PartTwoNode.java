package com.sadatmalik.aoc.daytwentythree.partone;

import java.util.*;

public class PartTwoNode extends GameNode {

    private int[] upperB;
    private int[] lowerA;

    public PartTwoNode(int[] hallway, int[] upper, int[] upperB,
                    int[] lowerA, int[] lower) {
        super(hallway, upper, lower);

        this.upperB = upperB;
        this.lowerA = lowerA;
    }

    public int[] getUpperB() {
        return upperB;
    }

    public int[] getLowerA() {
        return lowerA;
    }

    @Override
    public List<GameNode> getAllPossibleMoves() {
        List<GameNode> moves = new ArrayList<>();
        // moves from hallway
        for (int a = 0; a < hallway.length; a++) { // select a hallway space
            PartTwoNode movedToRoom = moveFromHallway(a);
            if (movedToRoom != null) {
                moves.add(movedToRoom);
            }
        }
        // moves from upper rooms
        for (int b = 0; b < upper.length; b++) { // select an upper room space
            for (int c = 0; c < hallway.length; c++) { // select a hallway space
                PartTwoNode movedFromRoom = moveFromUpper(b, c); // see if a move is possible
                if (movedFromRoom != null) {
                    moves.add(movedFromRoom);
                }
            }
        }
        // moves from upperB rooms
        for (int b = 0; b < upper.length; b++) { // select an upper room space
            for (int c = 0; c < hallway.length; c++) { // select a hallway space
                PartTwoNode movedFromRoom = moveFromUpperB(b, c); // see if a move is possible
                if (movedFromRoom != null) {
                    moves.add(movedFromRoom);
                }
            }
        }
        // moves from lowerA rooms
        for (int b = 0; b < upper.length; b++) { // select an upper room space
            for (int c = 0; c < hallway.length; c++) { // select a hallway space
                PartTwoNode movedFromRoom = moveFromLowerA(b, c); // see if a move is possible
                if (movedFromRoom != null) {
                    moves.add(movedFromRoom);
                }
            }
        }
        // moves from lower rooms
        for (int d = 0; d < lower.length; d++) { // select a lower room space
            for (int e = 0; e < hallway.length; e++) { // select a hallway space
                PartTwoNode movedFromRoom = moveFromLower(d, e);
                if (movedFromRoom != null) {
                    moves.add(movedFromRoom);
                }
            }
        }
        return moves;
    }

    private PartTwoNode moveFromHallway(int start) {
        int amphipod = hallway[start];
        if (amphipod == 0) {
            return null;
        }

        // calculate move with score
        PartTwoNode movedToRoom = moveToRoom(start);
        return movedToRoom;
    }

    private PartTwoNode moveToRoom(int start) {
        int amphipod = hallway[start];
        int room = roomsByAmphipod.get(amphipod);
        int end = getHallwayRoomPosition(room);
        int score = 0;

        // check no other piece is in the way
        if (!isHallwayClear(start, end))
            return null;

        PartTwoNode newNode = copyGameNode();

        if (upper[room] + upperB[room] + lowerA[room] + lower[room] == 0) { // all empty
            //move to lower room
            newNode.hallway[start] = 0;
            newNode.lower[room] = amphipod;

            // calculate scores
            score = getHallwayMoveScore(start, end, amphipod);
            score += 4 * amphipod; // moved to lower room

        } else if (lower[room] == amphipod
                && upper[room] + upperB[room] + lowerA[room] == 0) {
            // move to lowerA room
            newNode.hallway[start] = 0;
            newNode.lowerA[room] = amphipod;

            // calculate scores
            score = getHallwayMoveScore(start, end, amphipod);
            score += 3 * amphipod; // moved to lowerA

        } else if (lower[room] == amphipod && lowerA[room] == amphipod
                && upper[room] + upperB[room] == 0) {
            // move to upperB room
            newNode.hallway[start] = 0;
            newNode.upperB[room] = amphipod;

            // calculate scores
            score = getHallwayMoveScore(start, end, amphipod);
            score += 2 * amphipod; // moved to upperB

        } else if (lower[room] == amphipod && lowerA[room] == amphipod && upperB[room] == amphipod
                && upper[room] == 0) {
            // move to upper room
            newNode.hallway[start] = 0;
            newNode.upper[room] = amphipod;

            // calculate scores
            score = getHallwayMoveScore(start, end, amphipod);
            score += 1 * amphipod; // moved to upper room
        } else {
            return null; // either no open rooms or one of lowers has the wrong amphipod
        }

        // todo - heuristic function - currently Djikstra
        updateScores(score, newNode);
        return newNode;
    }

    private PartTwoNode moveFromUpper(int room, int end) {

        if (endingIsOutsideRoomEntrance(end))
            return null;

        int amphipod = upper[room];
        if (amphipod == 0) // check that there's an amphipod to move -- i.e. not '0'
            return null;

        // don't move a piece out of a room it's meant to be in
        if (pieceInCorrectRoom(amphipod, room)
                && upperB[room] == amphipod
                && lowerA[room] == amphipod
                && lower[room] == amphipod)
            return null;

        int start = getHallwayRoomPosition(room);

        if (!isHallwayClear(start, end))
            return null;

        // calculates and updates score
        PartTwoNode movedFromRoom = moveToHallway(amphipod, room, start, end, 0);
        return movedFromRoom;
    }

    private PartTwoNode moveFromUpperB(int room, int end) {

        if (endingIsOutsideRoomEntrance(end))
            return null;

        int amphipod = upperB[room];
        if (amphipod == 0) // check that there's an amphipod to move -- i.e. not '0'
            return null;

        // don't move a piece out of a room it's meant to be in
        if (pieceInCorrectRoom(amphipod, room)
                && lowerA[room] == amphipod
                && lower[room] == amphipod)
            return null;

        int start = getHallwayRoomPosition(room);

        if (upper[room] != 0) // if there's a piece in the way in the upper room
            return null;

        if (!isHallwayClear(start, end))
            return null;

        // calculates and updates score
        PartTwoNode movedFromRoom = moveToHallway(amphipod, room, start, end, 1);
        return movedFromRoom;
    }

    private PartTwoNode moveFromLowerA(int room, int end) {

        if (endingIsOutsideRoomEntrance(end))
            return null;

        int amphipod = lowerA[room];
        if (amphipod == 0) // check that there's an amphipod to move -- i.e. not '0'
            return null;

        // don't move a piece out of a room it's meant to be in
        if (pieceInCorrectRoom(amphipod, room)
                && lower[room] == amphipod)
            return null;

        int start = getHallwayRoomPosition(room);

        if (upperB[room] != 0 || upper[room] != 0) // if there's a piece in the way in the upper room
            return null;

        if (!isHallwayClear(start, end))
            return null;

        // calculates and updates score
        PartTwoNode movedFromRoom = moveToHallway(amphipod, room, start, end, 2);
        return movedFromRoom;
    }

    private PartTwoNode moveFromLower(int room, int end) {

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

        if (lowerA[room] != 0 || upperB[room] != 0 ||
                upper[room] != 0) // if there's a piece in the way in the upper room
            return null;

        if (!isHallwayClear(start, end))
            return null;

        // calculates and updates score
        PartTwoNode movedFromRoom = moveToHallway(amphipod, room, start, end, 3);
        return movedFromRoom;
    }

    private PartTwoNode moveToHallway(int amphipod, int room, int start, int end, int floor) {
        int score = Math.abs(start - end) * amphipod;
        PartTwoNode newNode = copyGameNode();

        switch (floor) {
            case 0:
                score += 1 * amphipod; //moving from upper room
                newNode.upper[room] = 0;
                newNode.hallway[end] = amphipod;
                break;

            case 1:
                score += 2 * amphipod; //moving from upperB room
                newNode.upperB[room] = 0;
                newNode.hallway[end] = amphipod;
                break;

            case 2:
                score += 3 * amphipod; //moving from lowerA room
                newNode.lowerA[room] = 0;
                newNode.hallway[end] = amphipod;
                break;

            case 3:
                score += 4 * amphipod; //moving from lower room
                newNode.lower[room] = 0;
                newNode.hallway[end] = amphipod;
                break;
        }

        // todo - heuristic function - currently Djikstra
        updateScores(score, newNode);
        return newNode;
    }

    private PartTwoNode copyGameNode() {
        int[] newHallway = Arrays.copyOf(hallway, hallway.length);
        int[] newUpper = Arrays.copyOf(upper, upper.length);
        int[] newUpperB = Arrays.copyOf(upperB, upperB.length);
        int[] newLowerA = Arrays.copyOf(lowerA, lowerA.length);
        int[] newLower = Arrays.copyOf(lower, lower.length);

        return new PartTwoNode(newHallway, newUpper, newUpperB,
                newLowerA, newLower);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartTwoNode gameNode = (PartTwoNode) o;
        return Arrays.equals(hallway, gameNode.hallway) &&
                Arrays.equals(upper, gameNode.upper) &&
                Arrays.equals(upperB, gameNode.upperB) &&
                Arrays.equals(lowerA, gameNode.lowerA) &&
                Arrays.equals(lower, gameNode.lower);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(hallway);
        result = 31 * result + Arrays.hashCode(upper);
        result = 31 * result + Arrays.hashCode(upperB);
        result = 31 * result + Arrays.hashCode(lowerA);
        result = 31 * result + Arrays.hashCode(lower);
        return result;
    }
}
