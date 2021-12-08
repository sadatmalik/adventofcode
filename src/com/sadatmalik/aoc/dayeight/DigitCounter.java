package com.sadatmalik.aoc.dayeight;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;

public class DigitCounter {

    public static void main(String[] args) {
        // 1. with test data
        ArrayList<String> data = FileReader.getDataFromFile("data/dayeight/testdata.txt");
        System.out.println(data);

        //get outputs
        ArrayList<String> outputs = new ArrayList<String>();
        for (String line : data ) {
            outputs.add(line.split(" \\| ")[1]);
        }
        System.out.println(outputs);

        //count 1, 4, 7, 8 digits in output
        int count = getCount(outputs);
        System.out.println("Digit count = " + count);

        // 1. with puzzle data
        data = FileReader.getDataFromFile("data/dayeight/puzzledata1.txt");
        System.out.println(data);

        //get outputs
        outputs = new ArrayList<String>();
        for (String line : data ) {
            outputs.add(line.split(" \\| ")[1]);
        }
        System.out.println(outputs);

        //count 1, 4, 7, 8 digits in output
        count = getCount(outputs);
        System.out.println("Digit count = " + count);

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
