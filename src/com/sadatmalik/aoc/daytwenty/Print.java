package com.sadatmalik.aoc.daytwenty;

public class Print {

    public static void printImage(String[][] image) {

        int image_x = image.length;
        int image_y = image[0].length;

        for (int y = 0; y < image_y; y++) {
            for (int x = 0; x < image_x; x++) {
                System.out.print(image[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
