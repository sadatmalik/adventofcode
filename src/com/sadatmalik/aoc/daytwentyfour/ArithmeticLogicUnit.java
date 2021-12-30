package com.sadatmalik.aoc.daytwentyfour;

import com.sadatmalik.aoc.FileReader;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticLogicUnit {

    static List<String> data;
    static List<Batch> batches;
    static int pointer = 0;

    public static void main(String[] args) {
        //readInstructions("data/daytwentyfour/testdata.txt");
        readInstructions("data/daytwentyfour/puzzledata.txt");
        prepareBatchJobs();
        process(batches.get(0), 9);
        printModelNumber();
    }

    private static void process(Batch batch, int wMax) {

        for (int i = wMax; i > 0; i--) {
            try {
                execute(batch, i);
                batch.setW(i);
                if (batch.batchNum < 14) {
                    Batch next = batches.get(batch.batchNum+1);
                    next.setX(batch.getX());
                    next.setY(batch.getY());
                    next.setZ(batch.getZ());
                    process(next, 9);
                }
                return;

            } catch (BatchArithmeticException bae) {
                if (i > 1) {
                    continue;
                } else {
                  if (batch.batchNum > 0) {

                      System.out.println(bae.msg);
                      System.out.println("Batch number = " + batch.batchNum);
                      System.out.println("Batch integers = " + batch.getW() + ", " + batch.getX() + ", "
                              + batch.getY() + ", " + batch.getZ() + ", ");


                      Batch previous = batches.get(batch.batchNum-1);
                      if (batch.batchNum > 1) {
                          Batch previousPrevious = batches.get(batch.batchNum - 1);
                          previous.setX(previousPrevious.getX());
                          previous.setY(previousPrevious.getY());
                          previous.setZ(previousPrevious.getZ());
                      } else {
                          previous.setX(0);
                          previous.setY(0);
                          previous.setZ(0);
                      }
                      System.out.println("Execute previous batch number = " + previous.batchNum);
                      System.out.println("Previous batch integers = " + previous.getW() + ", " + previous.getX() + ", "
                              + previous.getY() + ", " + previous.getZ() + ", ");
                      System.out.println();

                      process(previous, previous.getW()-1);
                  }
                  return;
                }
            }
        }
    }

    private static void execute(Batch batch, int w) throws BatchArithmeticException {
        List<String> instructions = batch.getInstructions();
        // todo - save initial values of w, x, ,y, z
        for (int i = 1; i < instructions.size(); i++) {
            try {
                batch.setW(w);
                batch.runInstruction(i);
            } catch (BatchArithmeticException bae) {
                // todo - reset w, x, y, z if exception thrown
                throw bae;
            }
        }
    }

    private static void prepareBatchJobs() {
        int batchNum = 0;
        batches = new ArrayList<>();
        while(pointer < data.size()) {
            List<String> batch = new ArrayList<>();
            batch.add(data.get(pointer++)); // inp w

            while (pointer < data.size() && !data.get(pointer).startsWith("inp")) {
                batch.add(data.get(pointer++));
            }

            Batch b = new Batch(batch, 0, 0, 0);
            b.batchNum = batchNum++;
            batches.add(b);
        }
    }

    private static void readInstructions(String filename) {
        data = FileReader.getDataFromFile(filename);
    }

    private static void printModelNumber() {
        System.out.println("Model Number = ");
        for (int i = 0; i < batches.size(); i++) {
            System.out.print(batches.get(i).getW());
        }
        System.out.println();
    }
}
