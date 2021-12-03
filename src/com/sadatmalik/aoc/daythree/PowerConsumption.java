package com.sadatmalik.aoc.daythree;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;

public class PowerConsumption {

    static StringBuffer gamma;
    static StringBuffer epsilon;

    public static void main(String[] args) {
        // 1. with test data
        System.out.println("Result = " + getPowerConsumption("data/daythree/testdata1.txt") + "\n");

        // 1. with puzzle data
        System.out.println("Result = " + getPowerConsumption("data/daythree/puzzledata1.txt") + "\n");

        // 2. with test data
        System.out.println("Result = " + getLifeSupportRating("data/daythree/testdata1.txt") + "\n");

        // 2. with test data
        System.out.println("Result = " + getLifeSupportRating("data/daythree/puzzledata2.txt") + "\n");

    }

    private static int getLifeSupportRating(String filename) {
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        System.out.println("Test Data = " + data);
        int ogr = getOxygenGenRating(data);
        int csr = getCarbonScrubRating(data);

        return ogr * csr;
    }

    private static int getCarbonScrubRating(ArrayList<String> data) {
        int bit = 0;

        while (data.size() > 1) {
            // Get the column counts
            ColCount[] colCount = calculateColumnCount(data);

            // get most common value for current bit
            // if more zeroes than ones; keep numbers with ones
            // if more ones than zeroes; keep numbers with zeroes
            // if equal ones and zeroes; keep numbers with zeroes
            ArrayList<String> newData = new ArrayList<>();
            if (colCount[bit].zeros > colCount[bit].ones) {
                for (String item : data) {
                    if (item.charAt(bit) == '1') {
                        newData.add(item);
                    }
                }
            } else {
                for (String item : data) {
                    if (item.charAt(bit) == '0') {
                        newData.add(item);
                    }
                }
            }

            data = newData;
            bit++; // consider bit to the right in next loop
        }

        String carbonScrubRating = data.get(0);
        int decimalValue = getDecimalValue(carbonScrubRating);

        System.out.println("Carbon scrub rating = " + carbonScrubRating +
                ", decimal value = " + decimalValue);

        return decimalValue;
    }

    private static int getOxygenGenRating(ArrayList<String> data) {
        // calculate Oxygen Gen Rating
        int bit = 0;
        while (data.size() > 1) {
            // Get the column counts
            ColCount[] colCount = calculateColumnCount(data);

            // get most common value for current bit
            // if more zeroes than ones; keep numbers with zero
            // if more ones than zeroes; keep numbers with ones
            // if equal ones and zeroes; keep numbers with ones
            ArrayList<String> newData = new ArrayList<>();
            if (colCount[bit].zeros > colCount[bit].ones) {
                for (String item : data) {
                    if (item.charAt(bit) == '0') {
                        newData.add(item);
                    }
                }
            } else {
                for (String item : data) {
                    if (item.charAt(bit) == '1') {
                        newData.add(item);
                    }
                }
            }

            data = newData;
            bit++; // consider bit to the right in next loop
        }

        String oxygenGenRating = data.get(0);
        int decimalValue = getDecimalValue(oxygenGenRating);

        System.out.println("Oxygen gen rating = " + oxygenGenRating +
                ", decimal value = " + decimalValue);

        return decimalValue;
    }

    private static int getPowerConsumption(String filename) {
        ArrayList<String> data = FileReader.getDataFromFile(filename);
        System.out.println("1. Test Data = " + data);
        ColCount[] colCount = calculateColumnCount(data);
        calculateGammaAndEpsilon(colCount);
        return calculateResult();
    }

    private static int getDecimalValue(String binaryString) {
        return Integer.parseInt(binaryString.toString(),2);
    }

    private static int calculateResult() {
        int decimalGamma = Integer.parseInt(gamma.toString(),2);
        int decimalEpsilon = Integer.parseInt(epsilon.toString(),2);

        System.out.println("Gamma = " + decimalGamma + ", Espilon = " + decimalEpsilon);

        return decimalGamma * decimalEpsilon;
    }

    private static void calculateGammaAndEpsilon(ColCount[] colCount) {
        gamma = new StringBuffer();
        epsilon = new StringBuffer();

        for (int i = 0; i < colCount.length; i++) {
            if (colCount[i].zeros > colCount[i].ones) {
                gamma.append('0');
                epsilon.append('1');
            } else {
                gamma.append('1');
                epsilon.append('0');
            }
        }

        System.out.println("gamma = " + gamma + ", epsilon = " + epsilon);
    }

    private static ColCount[] calculateColumnCount(ArrayList<String> data) {

        int numColumns = data.get(0).length();

        ColCount[] count = new ColCount[numColumns];
        for (int i = 0; i < count.length; i++) {
            count[i] = new ColCount();
        }

        for (String item : data) {
            for (int i = 0; i < item.length(); i++) {
                if (item.charAt(i) == '0') {
                    count[i].zeros++;
                } else {
                    count[i].ones++;
                }
            }
        }

        return count;
    }

    private static class ColCount {
        int zeros = 0;
        int ones = 0;
    }


}
