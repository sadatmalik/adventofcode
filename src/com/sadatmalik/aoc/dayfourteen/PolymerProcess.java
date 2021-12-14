package com.sadatmalik.aoc.dayfourteen;

import java.util.HashMap;
import java.util.Map;

//applies polymerization rules
public class PolymerProcess {

    String template;
    Map<String, String> mappings;
    Map<String, Long> pairsCountMap;

    public PolymerProcess(String template, Map<String, String> mappings) {
        this.template = template;
        this.mappings = mappings;
    }

    // part 1
    // iterates through template string applying insertion rules to each
    // character pair. returns the modified string.
    public String polymerize(int steps) {
        StringBuffer sb = null;

        for (int p = 1; p <= steps; p++) {
            sb = new StringBuffer();

            // cycle one pair at a time
            for (int i = 0; i < template.length() - 1; i++) {
                String pair = template.substring(i, i + 2);
                String insert = mappings.get(pair);
                if (insert != null) {
                    sb.append(template.charAt(i) + insert);
                } else {
                    sb.append(template.charAt(i));
                }
            }
            sb.append(template.charAt(template.length() - 1));
            System.out.println("Step " + p);
            template = sb.toString();
        }
        return sb.toString();
    }

    // part 2
    // creates a mapping of each pair and the number of times it appears in the template
    // applies insertion rules, generating a new map of pairs and the number of times it appears
    // keeps track of the last pair in the sequence - could refactor this as only need the last
    // character which in fact never changes
    public String polymerize2(int steps) {
        // 1. setup initial pairs from base template, keep track of last pair
        String lastPair = setUpPairsCountMap();

        // 2. cycle steps
        for (int i = 1; i <= steps; i++) {
            System.out.println("Step " + i + ".");
            generateNewPairs();
            lastPair = nextLastPair(lastPair);
        }

        return lastPair;
    }

    private String setUpPairsCountMap() {
        pairsCountMap = new HashMap<>();
        for (int i = 0; i < template.length() - 1; i++) {
            String pair = template.substring(i, i + 2);
            if (pairsCountMap.containsKey(pair)) {
                pairsCountMap.put(pair, pairsCountMap.get(pair) + 1);
            } else {
                pairsCountMap.put(pair, 1L);
            }
        }
        return template.substring(template.length()-2);
    }

    private void generateNewPairs() {
        Map<String, Long> newPairs = new HashMap<>();

        for (String pair : pairsCountMap.keySet()) {
            Long pairCount = pairsCountMap.get(pair);
            String insert = mappings.get(pair);
            if (insert != null) {
                String firstNewPair = pair.charAt(0) + insert;
                String secondNewPair = insert + pair.charAt(1);
                if (!newPairs.containsKey(firstNewPair)) {
                    newPairs.put(firstNewPair, pairCount);
                } else {
                    newPairs.put(firstNewPair, newPairs.get(firstNewPair) + pairCount);
                }
                if (!newPairs.containsKey(secondNewPair)) {
                    newPairs.put(secondNewPair, pairCount);
                } else {
                    newPairs.put(secondNewPair, newPairs.get(secondNewPair) + pairCount);
                }
            } else if (!newPairs.containsKey(pair)) {
                newPairs.put(pair, pairCount);
            } else {
                newPairs.put(pair, newPairs.get(pair) + pairCount);
            }
        }
        pairsCountMap = newPairs;
    }

    private String nextLastPair(String lastPair) {
        String insert = mappings.get(lastPair);
        if (insert == null) {
            return lastPair;
        } else {
            return insert + lastPair.charAt(1);
        }
    }

}
