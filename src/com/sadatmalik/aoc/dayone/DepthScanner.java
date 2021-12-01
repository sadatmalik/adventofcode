package com.sadatmalik.aoc.dayone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DepthScanner {

    private static int[] testData = new int[] {199, 200, 208, 210, 200, 207, 240, 269, 260, 263};

    public static void main(String[] args) {
        int increments = calculateIncrements(testData);
        System.out.println("Test Data: The depth increased " + increments + " times");

        increments = calculateIncrements(getTestDataFromFile("file.txt"));
        System.out.println("Test Data from File: The depth increased " + increments + " times");

        increments = calculateIncrements(getTestDataFromFile("puzzledata.txt"));
        System.out.println("Puzzle Data from File: The depth increased " + increments + " times");

        increments = secondChallenge("slidingwindowsample.txt");
        System.out.println("Sliding Window Samples from File: The depth increased " + increments + " times");

        increments = secondChallenge("slidingwindowpuzzledata.txt");
        System.out.println("Sliding Window Puzzle Data from File: The depth increased " + increments + " times");

    }

    private static int secondChallenge(String filename) {
        // read file from text
        int[] slidingWindowSample = getTestDataFromFile(filename);

        // calculate sliding window sums
        int[] slidingWindowSums = calculateSlidingWindowSums(slidingWindowSample);
        System.out.println(Arrays.toString(slidingWindowSums));

        // calculate sliding window sum increments
        return calculateIncrements(slidingWindowSums);
    }

    private static int[] calculateSlidingWindowSums(int[] slidingWindowSample) {
        ArrayList<Integer> data = new ArrayList<>();

        int a = slidingWindowSample[0] + slidingWindowSample[1] + slidingWindowSample[2];
        int b = slidingWindowSample[1] + slidingWindowSample[2];
        int c = slidingWindowSample[2];

        for (int i = 3; i < slidingWindowSample.length+1; i++) {
            if (i >= slidingWindowSample.length) {
                if (i % 3 == 0) {
                    data.add(a);
                } else if (i % 3 == 1) {
                    data.add(b);
                } else if (i % 3 == 2) {
                    data.add(c);
                }
                break;
            }

            if (i % 3 == 0) {
                data.add(a);
                a = slidingWindowSample[i];
                b += slidingWindowSample[i];
                c += slidingWindowSample[i];
            } else if (i % 3 == 1) {
                a += slidingWindowSample[i];
                data.add(b);
                b = slidingWindowSample[i];
                c += slidingWindowSample[i];
            } else if (i % 3 == 2) {
                a += slidingWindowSample[i];
                b += slidingWindowSample[i];
                data.add(c);
                c = slidingWindowSample[i];
            }

        }

        return data.stream().mapToInt(i -> i).toArray();
    }

    private static int calculateIncrements(int[] testData) {
        int increments = 0;
        for (int x = 1; x < testData.length; x++) {
            int depthChange = testData[x] - testData[x-1];
            if (depthChange > 0) {
                increments++;
            }
        }
        return increments;
    }

    private static int[] getTestDataFromFile(String filename) {
        ArrayList<Integer> data = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                data.add(Integer.parseInt(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return data.stream().mapToInt(i -> i).toArray();
    }

}
