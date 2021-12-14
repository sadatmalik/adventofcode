package com.sadatmalik.aoc.dayfourteen;

import java.util.HashMap;
import java.util.Map;

public class CheckPolymer {

    static Map<Character, Long> charCount;

    // part 1
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
