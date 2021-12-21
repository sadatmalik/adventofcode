package com.sadatmalik.aoc.daytwenty.attemptone;

public class ImageEnhancer {

    static String[][] wrapped;
    static String algo;

    public static String[][] enhance(String[][] input, String imageAlgo) {

        wrapped = wrap(input); // wraps a 2 pixel '.' border
        Print.printImage(wrapped);
        algo = imageAlgo;
        String[][] out = render();
        Print.printImage(out);

        return out;
    }

    private static String[][] render() {

        int out_x = wrapped.length-2;
        int out_y = wrapped[0].length-2;

        String[][] out = blankImage(out_x, out_y); // 1 pixel narrower than wrapped input

        // fill in each pixel in out
        for (int y = 0; y < out_y; y++) {
            for (int x = 0; x < out_x; x++) {
                String square = getInputSquare(x+1, y+1);

                // special handling for blank edge cases
                if ((x <= 1 || x >= out_x-2) || (y <= 1 || y >= out_y-2)) {
                    if (square.equals(".........")) {
                        //System.out.println("Blank edge case - this one should be missed");
                        out[x][y] = "."; // leave unlit
                    } else {
                        out[x][y] = getPixel(square);
                    }
                } else { // normal processing unless edge case
                    out[x][y] = getPixel(square);
                }
            }
        }

        return out;
    }

    private static String getInputSquare(int centre_x, int centre_y) {
        // get 3x3 patch from wrapped
        StringBuffer sb = new StringBuffer();
        for (int y = centre_y-1; y <= centre_y+1; y++) {
            for (int x = centre_x-1; x <= centre_x+1; x++) {
                sb.append(wrapped[x][y]);
            }
        }

        //System.out.println("[" + centre_x + "," + centre_y + "] = " + sb.toString());

        return sb.toString();
    }

    private static String getPixel(String pattern) {
        String binary = pattern.replaceAll("#", "1").replaceAll("\\.", "0");
        int decimal = Integer.parseInt(binary,2);

        //System.out.println("Binary Pattern = " + binary + ", (" + decimal + ")");

        return String.valueOf(algo.charAt(decimal));
    }

    private static String[][] wrap(String[][] input) {

        int wrapped_x = input.length+4;
        int wrapped_y = input[0].length+4;

        String[][] wrapped = blankImage(wrapped_x, wrapped_y);

        for (int y = 2; y < wrapped_y-2; y++) {
            for (int x = 2; x < wrapped_x - 2; x++) {
                wrapped[x][y] = input[x-2][y-2];
            }
        }
        return wrapped;
    }


    private static String[][] blankImage(int blank_x, int blank_y) {
        String[][] blankImg = new String[blank_x][blank_y];

        for (int y = 0; y < blank_y; y++) {
            for (int x = 0; x < blank_x; x++) {
                blankImg[x][y] = ".";
            }
        }
        return blankImg;
    }
}
