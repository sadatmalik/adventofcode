package com.sadatmalik.aoc.daytwenty;

import com.sadatmalik.aoc.FileReader;

import java.util.List;

public class TrenchMap {

    private static String imageAlgo;
    private static String[][] input;

    public static void main(String[] args) {
        //loadInputs("data/daytwenty/testdata.txt");
        loadInputs("data/daytwenty/puzzledata.txt");

        enhanceImage(50);
    }

    private static void enhanceImage(int count) {
        for (int i = 0; i < count; i++) {
            input = ImageEnhancer.enhance(input, imageAlgo);
        }
        int lit = getLitCount(input);
        System.out.println("Lit pixels = " + lit);
    }

    private static int getLitCount(String[][] out) {
        int out_x = out.length;
        int out_y = out[0].length;

        int count = 0;
        for (int y = 0; y < out_y; y++) {
            for (int x = 0; x < out_x; x++) {
                if (out[x][y].equals("#")) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void loadInputs(String filename) {
        List<String> data = FileReader.getDataFromFile(filename);
        System.out.println(data);

        imageAlgo = data.get(0);

        int input_x = data.get(2).length();
        int input_y = data.size()-2;

        input = new String[input_x][input_y];

        //add each line to grid
        for (int i = 2; i < data.size(); i++) {
            String line = data.get(i);
            for (int x = 0; x < input_x; x++) {
                input[x][i-2] = String.valueOf(line.charAt(x));
            }
        }

    }

}
