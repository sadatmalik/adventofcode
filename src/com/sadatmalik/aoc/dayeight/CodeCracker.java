package com.sadatmalik.aoc.dayeight;

import java.util.*;

public class CodeCracker {

    Character[] cipher = new Character[7];

    String zero = null;
    String one = null;
    String two = null;
    String three = null;
    String four = null;
    String five = null;
    String six = null;
    String seven = null;
    String eight = null;
    String nine = null;

    ArrayList<String> fiveLengths = new ArrayList<>();
    ArrayList<String> sixLengths = new ArrayList<>();

    // Common letters in 7 and 4
    Set<Character> sevensAndFours = new HashSet();

    Set<Character> oneLetters = new HashSet();
    Set<Character> fourLetters = new HashSet();
    Set<Character> sevenLetters = new HashSet();
    Set<Character> nineLetters = new HashSet();

    Map<String, Integer> mappings = new HashMap<>();

    @Override
    public String toString() {
        return "CodeCracker{" +
                "zero='" + zero + '\'' +
                ", one='" + one + '\'' +
                ", two='" + two + '\'' +
                ", three='" + three + '\'' +
                ", four='" + four + '\'' +
                ", five='" + five + '\'' +
                ", six='" + six + '\'' +
                ", seven='" + seven + '\'' +
                ", eight='" + eight + '\'' +
                ", nine='" + nine + '\'' +
                '}';
    }

    public CodeCracker(String[] input) {
        parseInput(input);
        System.out.println("After CodeCracker() parse input:");
        System.out.println("One = " + one);
        System.out.println("Four = " + four);
        System.out.println("Seven = " + seven);
        System.out.println("Eight = " + eight);
        System.out.println("Five lengths = " + fiveLengths);
        System.out.println("Six lengths = " + sixLengths + "\n");

        decodeCipherSystem();

        generateMappings();
    }

    private String getOrderedKey(String unorderedKey) {
        char[] chars = unorderedKey.toCharArray();
        Arrays.sort(chars);
        String orderedKey = new String(chars);
        return orderedKey;
    }

    private void generateMappings() {
        mappings.put(getOrderedKey(one), 1);
        mappings.put(getOrderedKey(two), 2);
        mappings.put(getOrderedKey(three), 3);
        mappings.put(getOrderedKey(four), 4);
        mappings.put(getOrderedKey(five), 5);
        mappings.put(getOrderedKey(six), 6);
        mappings.put(getOrderedKey(seven), 7);
        mappings.put(getOrderedKey(eight), 8);
        mappings.put(getOrderedKey(nine), 9);
        mappings.put(getOrderedKey(zero), 0);
    }

    private void decodeCipherSystem() {
        // detect 9 from sixLengths
        this.nine = detectNine();
        System.out.println("Nine = " + nine + "\n");

        // set letter e in cipher
        System.out.println("Detecting 'e'");
        addToSet(nineLetters, nine);
        for (Character c : this.eight.toCharArray()) {
            if (!nineLetters.contains(c)) {
                cipher[4] = c;
            }
        }
        System.out.println("e = " + cipher[4] + "\n");

        // determine two
        this.two = detectTwo();
        System.out.println("Two = " + two + "\n");

        // determine c (two has one character in common with one)
        System.out.println("Detecting c + d:");
        addToSet(oneLetters, one);
        addToSet(fourLetters, four);
        for (Character c : this.two.toCharArray()) {
            if (oneLetters.contains(c)) {
                cipher[2] = c; //c
            } else if (fourLetters.contains(c)) {
                cipher[3] = c; //d
            }
        }
        System.out.println("Two = " + two);
        System.out.println("One = " + one);
        System.out.println("Four = " + four);
        System.out.println("c = " + cipher[2]);
        System.out.println("d = " + cipher[3] + "\n");

        // determine three
        this.three = detectThree();
        System.out.println("Three = " + three);

        // deduce five as remaining five length item
        this.five = fiveLengths.get(0);
        fiveLengths.remove(0);
        System.out.println("Five = " + five);

        // determine g (three has all seven's 3 letters plus a d - remaining is a g)
        System.out.println("Detecting 'g':");
        for (Character c : this.three.toCharArray()) {
            if (!sevenLetters.contains(c) && !(c == cipher[3])) {
                cipher[6] = c;
            }
        }
        System.out.println("g = " + cipher[6]);

        // determine six
        this.six = detectSix();
        System.out.println("Six = " + six);

        // deduce zero
        this.zero = sixLengths.get(0);
        sixLengths.remove(0);
        System.out.println("Zero = " + zero + "\n");

    }

    private String detectSix() {
        for (String num : sixLengths) {
            for (Character c : num.toCharArray()) {
                if (c == cipher[3]) {
                    sixLengths.remove(num);
                    return num;
                }
            }
        }
        return null;
    }

    private String detectThree() {
        System.out.println("Detect 3:");
        // cycle fives
        // a three is a sevenLetter match plus 'd' and 'g'
        addToSet(sevenLetters, seven);
        for (String word : fiveLengths) {
            boolean isThree = true;
            HashSet<Character> numberToTest = new HashSet<>();
            addToSet(numberToTest, word);
            for (Character c : seven.toCharArray()) {
                if (!numberToTest.contains(c)) {
                    isThree = false;
                }
            }
            if (isThree == true) {
                fiveLengths.remove(word);
                return word;
            }
        }
        return null;
    }

    private String detectTwo() {
        System.out.println("Detect 2:");
        // cycle fives - 3 and 5 don't have an 'e' so if it has an 'e' it's a 2
        for (String word : fiveLengths) {
            for (Character c : word.toCharArray()) {
                if (c == cipher[4]) {
                    fiveLengths.remove(word);
                    return word;
                }
            }
        }
        return null;
    }

    private String detectNine() {
        System.out.println("Detect 9:");
        System.out.println("Seven = " + seven);
        System.out.println("Four = " + four);

        // all common characters in 4 + 7
        addToSet(sevensAndFours, seven);
        addToSet(sevensAndFours, four);
        System.out.println("Seven + Four = " + sevensAndFours);

        // discern the nine
        for (String number : sixLengths) {
            boolean isNine = false;
            int nonMatchingLetters = 0;
            for (Character c : number.toCharArray()) {
                if (!sevensAndFours.contains(c)) {
                    nonMatchingLetters++;
                }
            }
            if (nonMatchingLetters == 1) {
                isNine = true;
                sixLengths.remove(number);
                return number;
            }
        }
        return null;
    }

    private void addToSet(Set<Character> set, String chars) {
        for (Character c : chars.toCharArray()) {
            set.add(c);
        }
    }

    private void parseInput(String[] input) {
        // input xxx yyy ddd
        for (String line : input) {
            String[] words = line.split(" ");
            for (String word : words) {
                switch (word.length()) {
                    case 2:
                        this.one = word;
                        break;
                    case 4:
                        this.four = word;
                        break;
                    case 3:
                        this.seven = word;
                        break;
                    case 7:
                        this.eight = word;
                        break;
                    case 5:
                        fiveLengths.add(word);
                        break;
                    case 6:
                        sixLengths.add(word);
                        break;
                }
            }
        }
    }

    public int addFiveLength(String fiver) {
        fiveLengths.add(fiver);
        return fiveLengths.size();
    }

    public int addSixLength(String sixer) {
        sixLengths.add(sixer);
        return sixLengths.size();
    }

    public void setOne(String one) {
        this.one = one;
    }

    public void setfour(String four) {
        this.four = four;
    }

    public void setSeven(String seven) {
        this.seven = seven;
    }

    public void setEight(String eight) {
        this.eight = eight;
    }

    public int crack(String[] data) {
        int total = 0;
        int[] powersOf10 = {1000, 100, 10, 1};
        for (int i = 0; i < data.length; i++) {
            Integer value = mappings.get(getOrderedKey(data[i]));
            total += value * powersOf10[i];
        }
        return total;
    }
}
