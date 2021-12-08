package com.sadatmalik.aoc.dayeight;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;
import java.util.Arrays;

public class DigitCounter {

    static String[] currentInput;
    static String[] currentOutput;

    public static void main(String[] args) {
        // 1. with test data
        int count = calculatePartOne("data/dayeight/testdata.txt");
        System.out.println("Digit count = " + count);

        // 1. with puzzle data
        count = calculatePartOne("data/dayeight/puzzledata1.txt");
        System.out.println("Digit count = " + count);

        // 2. with test data
        ArrayList<String> data = FileReader.getDataFromFile("data/dayeight/testdata.txt");
        processData(data);

        // 2. with puzzle data
        data = FileReader.getDataFromFile("data/dayeight/puzzledata2.txt");
        processData(data);

    }

    private static void processData(ArrayList<String> data) {
        int totalOutputSum = 0;
        // take a line at a time
        for (String line : data) {
            // split into input and output
            String input = line.split(" \\| ")[0];
            String output = line.split(" \\| ")[1];

            currentInput = input.split(" ");
            currentOutput = output.split(" ");

            System.out.println("\nCurrent input = " + Arrays.toString(currentInput));
            System.out.println("Current output = " + Arrays.toString(currentOutput) + "\n");

            // set up a code cracker on current input
            CodeCracker codeCracker = new CodeCracker(currentInput);
            System.out.println(codeCracker);

            // decode current output
            totalOutputSum += codeCracker.crack(currentOutput);
        }
        System.out.println("Total output sum = " + totalOutputSum);
    }

    private static ArrayList<String> getInputs(ArrayList<String> data) {
        // xxx yyy ddd hhh | jjj kkk lll
        //get inputs
        ArrayList<String> inputs = new ArrayList<>();
        for (String line : data ) {
            inputs.add(line.split(" \\| ")[0]);
        }
        return inputs;
    }

    private static int calculatePartOne(String filename) {
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        System.out.println(data);

        //get outputs
        ArrayList<String> outputs = new ArrayList<>();
        for (String line : data ) {
            outputs.add(line.split(" \\| ")[1]);
        }
        System.out.println(outputs);

        //count 1, 4, 7, 8 digits in output
        return getCount(outputs);
    }

    private static int getCount(ArrayList<String> outputs) {
        int count = 0;
        for (String output : outputs) {
            String[] digits = output.split(" ");
            for (String digit : digits) {
                switch (digit.length()) {
                    case 2:
                    case 4:
                    case 3:
                    case 7:
                        count++;
                        break;
                }
            }
        }
        return count;
    }

}
