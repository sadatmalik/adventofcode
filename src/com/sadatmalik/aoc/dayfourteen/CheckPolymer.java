package com.sadatmalik.aoc.dayfourteen;

import java.util.HashMap;
import java.util.Map;

//calculate result value
public class CheckPolymer {

    static Map<Character, Long> charCount;

    // part 1
    // parse the polymer string counting the occurence of each character
    public static long process(String polymer) {
        charCount = new HashMap<>();

        char[] chars = polymer.toCharArray();
        for (char c : chars) {
            if (!charCount.containsKey(c)) {
                charCount.put(c, 1L);
            } else {
                charCount.put(c, (charCount.get(c) + 1));
            }
        }
        return calculateResult();
    }

    // return the difference of the max number of occurrences of a character and the
    // minimum number of occurrences of a character
    private static long calculateResult() {
        long mostCommon = 0;
        long leastCommon = Long.MAX_VALUE;

        for (Map.Entry entry : charCount.entrySet()) {
            if ((long)entry.getValue() > mostCommon) {
                mostCommon = (long)entry.getValue();
            }
            if ((long)entry.getValue() < leastCommon) {
                leastCommon = (long)entry.getValue();
            }
        }
        long result = mostCommon - leastCommon;
        return result;
    }

    // part 2
    // tallies the total count of a given character in the passed-in map argument
    // incrementing the last character by 1 before calculting the result
    public static long process(String lastPair, Map<String, Long> pairsCountMap) {
        Map<Character, Long> finalCount = new HashMap<>();
        for (String pair : pairsCountMap.keySet()) {
            Character firstLetter = pair.charAt(0);
            if (finalCount.containsKey(firstLetter)) {
                finalCount.put(firstLetter, finalCount.get(firstLetter) + pairsCountMap.get(pair));
            } else {
                finalCount.put(firstLetter, pairsCountMap.get(pair));
            }
        }
        Character lastChar = lastPair.charAt(1);
        finalCount.put(lastChar, finalCount.get(lastChar) + 1);

        charCount = finalCount;

        return calculateResult();
    }
}
