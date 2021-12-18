package com.sadatmalik.aoc.dayeighteen;

public class TestNumbers {

    public static void main(String[] args) {
        //testNumberPopulation();
        //testAddition();
        testReduce();
    }

    private static void testReduce() {
        reduce("[[[[[9,8],1],2],3],4]");
        reduce("[7,[6,[5,[4,[3,2]]]]]");
        reduce("[[6,[5,[4,[3,2]]]],1]");
        reduce("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
    }

    private static void reduce(String number) {
        Number n = new Number(number);
        System.out.println("Input   = " + n);
        n.reduce();
        System.out.println("Reduced = " + n);
        System.out.println();
    }

    private static void testAddition() {
        Number a = new Number("[1,2]");
        Number b = new Number("[[3,4],5]");
        Number ab = a.add(b);
        System.out.println(ab);
    }

    private static void testNumberPopulation() {
        String number = "[1,2]";
        Number sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[1,2],3]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[9,[8,7]]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[1,9],[8,5]]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[[[1,2],[3,4]],[[5,6],[7,8]]],9]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);

        number = "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]";
        sfNumber = new Number(number);
        System.out.println(sfNumber);
    }
}
