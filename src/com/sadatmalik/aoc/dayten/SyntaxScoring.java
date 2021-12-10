package com.sadatmalik.aoc.dayten;

import com.sadatmalik.aoc.FileReader;

import java.util.*;

public class SyntaxScoring {

    static Set<Character> openingChars = new HashSet<>(Set.of('(', '[', '{', '<'));
    static Map<Character, Character> pairs = new HashMap<>();
    static Map<Character, Integer> scores = new HashMap<>();
    static Map<Character, Integer> completionScores = new HashMap<>();

    static {
        pairs.put('(', ')');
        pairs.put('[', ']');
        pairs.put('{', '}');
        pairs.put('<', '>');

        scores.put(')', 3);
        scores.put(']', 57);
        scores.put('}', 1197);
        scores.put('>', 25137);

        completionScores.put(')', 1);
        completionScores.put(']', 2);
        completionScores.put('}', 3);
        completionScores.put('>', 4);
    }

    public static void main(String[] args) {
        // part 1. with test data
        ArrayList<String> incompleteLines = cleanCorruptedLines("data/dayten/testdata.txt");
        // part 2.
        completeLines(incompleteLines);

        // part 1. with puzzle data
        incompleteLines = cleanCorruptedLines("data/dayten/puzzledata1.txt");
        // part 2.
        completeLines(incompleteLines);
    }

    private static void completeLines(ArrayList<String> incompleteLines) {
        System.out.println("\nMissing tokens:");
        long[] scores = new long[incompleteLines.size()];
        int scoreIndex = 0;

        for (String line : incompleteLines) {
            ArrayList<Character> chars = new ArrayList<>();
            for (char c : line.toCharArray()) {
                chars.add(c);
            }
            StringBuffer missingTokens = findMissingTokens(chars, new StringBuffer());
            long score = getScore(missingTokens);
            scores[scoreIndex] = score;
            scoreIndex++;
            System.out.println(line + " - Complete by adding " + missingTokens +
                    " - " + score + " total points");
        }
        Arrays.sort(scores);
        System.out.println("\nMiddle score = " + scores[scores.length / 2]);

    }

    private static long getScore(StringBuffer missingTokens) {
        char[] chars = missingTokens.toString().toCharArray();
        long score = 0L;
        for (char c : chars) {
            score *= 5;
            score += completionScores.get(c);
        }
        return score;
    }

    // recursive
    private static StringBuffer findMissingTokens(ArrayList<Character> tokens, StringBuffer missingTokens) {
        if (tokens.size() == 0) {
            return missingTokens;
        }

        // found unpaired open
        char token = tokens.get(tokens.size()-1);
        if (isOpeningChar(token)) {
            missingTokens.append(pairs.get(token));
            tokens.remove(tokens.size()-1);
            return findMissingTokens(tokens, missingTokens);
        }

        // found matching pair
        for (int i = tokens.size()-1; i >= 0; i--) {
            if (isOpeningChar(tokens.get(i))) {
                tokens.remove(i+1);
                tokens.remove(i);

                return findMissingTokens(tokens, missingTokens);
            }
        }

        return null;
    }

    private static ArrayList<String> cleanCorruptedLines(String filename) {
        ArrayList<String> lines = FileReader.getDataFromFile(filename);
        ArrayList<String> cleanedData = parseLines(lines);
        System.out.println("\nCleaned data:");
        printLines(cleanedData);
        return cleanedData;
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
            if (line != null) {
                System.out.println(line + " - Expected " + expectedClose + ", but found " + c + " instead.");
            }
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
