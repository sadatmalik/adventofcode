package com.sadatmalik.aoc.daytwentyfour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Batch {

    int batchNum;

    private List<String> instructions;
    private int[] wxyz = new int[4];

    private static Map<String, Integer> letterToIndex;

    static {
        letterToIndex = new HashMap<>();
        letterToIndex.put("w", 0);
        letterToIndex.put("x", 1);
        letterToIndex.put("y", 2);
        letterToIndex.put("z", 3);
    }

    public Batch(List<String> instructions, int x, int y, int z) {
        this.instructions = instructions;
        wxyz[1] = x;
        wxyz[2] = y;
        wxyz[3] = z;
    }

    public void runInstruction(int i) throws BatchArithmeticException {
        String instruction = instructions.get(i);
        String[] split = instruction.split(" ");
        String operator = split[0];
        String op1 = split[1];
        String op2 = null;
        if (split.length > 2) {
            op2 = split[2];
        }
        switch (operator) {
            case "inp":
                break;

            case "add":
                add(op1, op2);
                break;

            case "mul":
                multiply(op1, op2);
                break;

            case "div":
                divide(op1, op2);
                break;

            case "mod":
                modulo(op1, op2);
                break;

            case "eql":
                equals(op1, op2);
                break;
        }
    }

    private void add(String op1, String op2) {
        wxyz[letterToIndex.get(op1)] =
                wxyz[letterToIndex.get(op1)] + getOperand2(op2);
    }

    private void multiply(String op1, String op2) {
        wxyz[letterToIndex.get(op1)] =
                wxyz[letterToIndex.get(op1)] * getOperand2(op2);
    }

    private void divide(String op1, String op2) throws BatchArithmeticException {
        if (getOperand2(op2) == 0)
            throw new BatchArithmeticException("Division by zero: div " + op1 + " " + op2);

        wxyz[letterToIndex.get(op1)] =
                wxyz[letterToIndex.get(op1)] / getOperand2(op2);
    }

    private void modulo(String op1, String op2) throws BatchArithmeticException{
        if (wxyz[letterToIndex.get(op1)] < 0)
            throw new BatchArithmeticException("Modulo op1 less than zero: mod " + wxyz[letterToIndex.get(op1)] + " " + op2);

        if (getOperand2(op2) < 0)
            throw new BatchArithmeticException("Modulo op2 less than zero: mod " + wxyz[letterToIndex.get(op1)] + " " + op2);

        if (getOperand2(op2) == 0)
            throw new BatchArithmeticException("Modulo division by zero: mod  " + wxyz[letterToIndex.get(op1)] + " " + op2);

        wxyz[letterToIndex.get(op1)] =
                wxyz[letterToIndex.get(op1)] % getOperand2(op2);
    }

    private void equals(String op1, String op2) {
        wxyz[letterToIndex.get(op1)] =
                wxyz[letterToIndex.get(op1)] == getOperand2(op2) ? 1 : 0;
    }

    private int getOperand2(String op2) {
        if (op2.equals("w") || op2.equals("x") || op2.equals("y") || op2.equals("z")) {
            return wxyz[letterToIndex.get(op2)];
        } else {
            return Integer.parseInt(op2);
        }
    }

    public void setW(int w) {
        wxyz[0] = w;
    }

    public void setX(int x) {
        wxyz[1] = x;
    }

    public void setY(int y) {
        wxyz[2] = y;
    }

    public void setZ(int z) {
        wxyz[3] = z;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public int getW() {
        return wxyz[0];
    }

    public int getX() {
        return wxyz[1];
    }

    public int getY() {
        return wxyz[2];
    }

    public int getZ() {
        return wxyz[3];
    }
}
