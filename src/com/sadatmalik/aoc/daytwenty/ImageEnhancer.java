package com.sadatmalik.aoc.daytwenty;

public class ImageEnhancer {

    static String[][] input;
    static String algo;
    static String defaultPixel = ".";

    public static String[][] enhance(String[][] in, String imageAlgo) {
        input = in;
        algo = imageAlgo;

        String[][] out = render();
        Print.printImage(out);

        return out;
    }

    private static String[][] render() {

        int out_x = input.length+2;
        int out_y = input[0].length+2;

        String[][] out = new String[out_x][out_y];

        // fill in each pixel in out
        for (int y = -1; y < input[0].length+1; y++) {
            for (int x = -1; x < input.length+1; x++) {
                out[x+1][y+1] =  getMappedPixel(x,y);
            }
        }

        // toggle default if necessary to prevent image flashing
        if (defaultPixel == "." && algo.charAt(0) == '#') {
            defaultPixel = "#";
        } else if (defaultPixel == "#" && algo.charAt(algo.length()-1) == '.') {
            defaultPixel = ".";
        }

        return out;
    }

    private static String getMappedPixel(int x, int y) {
        StringBuffer binary = new StringBuffer();
        for (int i = y-1; i <= y+1; i++) {
            for (int j = x-1; j <= x+1; j++) {
                binary.append(getPixel(j,i).equals(".") ? "0" : "1");
            }
        }
        int dec = Integer.parseInt(binary.toString(), 2);
        String mappedPixel = String.valueOf(algo.charAt(dec));

        // System.out.println("Binary = " + binary + ", (" + dec + "), mapped = " + mappedPixel);

        return mappedPixel;
    }

    private static String getPixel(int x, int y) {
        try {
            return input[x][y];
        } catch (ArrayIndexOutOfBoundsException out) {
            return defaultPixel;
        }
    }
}
