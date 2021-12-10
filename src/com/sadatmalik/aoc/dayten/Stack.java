package com.sadatmalik.aoc.dayten;

import java.util.ArrayList;

public class Stack {
    ArrayList<Character> stack = new ArrayList<>();

    void push(Character c) {
        stack.add(c);
    }

    Character pop() {
        return stack.remove(stack.size()-1);
    }

    @Override
    public String toString() {
        return "Stack{" +
                "stack=" + stack +
                '}';
    }
}
