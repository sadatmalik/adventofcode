package com.sadatmalik.aoc.dayten;

import com.sadatmalik.aoc.FileReader;

import java.util.*;

public class SyntaxScoring {

    static Set<Character> openingChars = new HashSet<Character>(Set.of('(', '[', '{', '<'));
    static Map<Character, Character> pairs = new HashMap<>();
    static Map<Character, Integer> scores = new HashMap<>();

    static {
        pairs.put('(', ')');
        pairs.put('[', ']');
        pairs.put('{', '}');
        pairs.put('<', '>');

        scores.put(')', 3);
        scores.put(']', 57);
        scores.put('}', 1197);
        scores.put('>', 25137);
    }

    public static void main(String[] args) {
        // 1. with test data
        cleanCorruptedLines("data/dayten/testdata.txt");

        // 1. with puzzle data
        cleanCorruptedLines("data/dayten/puzzledata1.txt");

    }

    private static void cleanCorruptedLines(String filename) {
        ArrayList<String> lines = FileReader.getDataFromFile(filename);
        ArrayList<String> cleanedData = parseLines(lines);
        System.out.println("\nCleaned data:");
        printLines(cleanedData);
    }

    private static ArrayList<String> parseLines(ArrayList<String> lines) {
        ArrayList<String> corruptLines = new ArrayList<>();
        ArrayList<String> incompleteLines = new ArrayList<>();

        int score = 0;

        for (String line : lines) {
            char[] charTokens = line.toCharArray();
            Stack stack = new Stack();
            boolean isCorrupted = false;
            for (char c : charTokens) {
                if (isOpeningChar(c)) {
                    stack.push(c);
                } else {
                    char previous = stack.pop();
                    if (!isMatchingPair(previous, c, line)) {
                        isCorrupted = true;
                        corruptLines.add(line);
                        score += scores.get(c);
                    }
                }
            }
            if (!isCorrupted) {
                incompleteLines.add(line);
            }
        }
        System.out.println("Score = " + score);
        return incompleteLines;
    }

    private static boolean isMatchingPair(char previous, char c, String line) {
        char expectedClose = pairs.get(previous);
        if (!(expectedClose == c)) {
            System.out.println(line + " - Expected " + expectedClose + ", but found " + c + " instead.");
            return false;
        }
        return true;
    }

    private static boolean isOpeningChar(char c) {
        return openingChars.contains(c);
    }

    private static void printLines(ArrayList<String> cleanedData) {
        for (String line: cleanedData) {
            System.out.println(line);
        }
    }

}
