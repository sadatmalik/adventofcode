package com.sadatmalik.aoc.dayeighteen;

public class TestNumbers {

    public static void main(String[] args) {
        //testNumberPopulation();
        //testReduce();
        testAddition();
    }

    private static void testAddition() {
        add("[1,2]", "[[3,4],5]");
        System.out.println();
        Number result = add("[1,1]", "[2,2]");
        result = add(result, "[3,3]");
        result = add (result, "[4,4]");
        System.out.println();
        add("[[[[1,1],[2,2]],[3,3]],[4,4]]", "[5,5]");
        add("[[[[3,0],[5,3]],[4,4]],[5,5]]", "[6,6]");

        System.out.println();
        Number n = new Number("[1,1]");
        System.out.println(n);
        n.add(new Number("[2,2]"));
        System.out.println(n);
        n.add(new Number("[3,3]"));
        System.out.println(n);
        n.add(new Number("[4,4]"));
        System.out.println(n);
        n.add(new Number("[5,5]"));
        System.out.println(n);
        System.out.println();

        Number bigTest = new Number("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]");
        System.out.println(bigTest);
        bigTest.add(new Number("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]"));
        System.out.println(bigTest);
        bigTest.add(new Number("[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]"));
        System.out.println(bigTest);
        bigTest.add(new Number("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]"));
        System.out.println(bigTest);
        bigTest.add(new Number("[7,[5,[[3,8],[1,4]]]]"));
        System.out.println(bigTest);
        bigTest.add(new Number("[[2,[2,2]],[8,[8,1]]]"));
        System.out.println(bigTest);
        bigTest.add(new Number("[2,9]"));
        System.out.println(bigTest);
        bigTest.add(new Number("[1,[[[9,3],9],[[9,0],[0,7]]]]"));
        System.out.println(bigTest);
        bigTest.add(new Number("[[[5,[7,4]],7],1]"));
        System.out.println(bigTest);
        bigTest.add(new Number("[[[[4,2],2],6],[8,7]]"));
        System.out.println(bigTest);
    }

    private static Number add(String one, String two) {
        Number a = new Number(one);
        System.out.print(a + " + ");
        Number b = new Number(two);
        Number ab = a.add(b);
        System.out.print(b + " = ");
        System.out.println(ab);
        return ab;
    }

    private static Number add(Number a, String two) {
        System.out.print(a + " + ");
        Number b = new Number(two);
        Number ab = a.add(b);
        System.out.print(b + " = ");
        System.out.println(ab);
        return ab;
    }


    private static void testReduce() {
        //explode
        reduce("[[[[[9,8],1],2],3],4]");
        reduce("[7,[6,[5,[4,[3,2]]]]]");
        reduce("[[6,[5,[4,[3,2]]]],1]");
        reduce("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");

        //split
        reduce("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]");
    }

    private static void reduce(String number) {
        Number n = new Number(number);
        System.out.println("Input   = " + n);
        n.reduce();
        System.out.println("Reduced = " + n);
        System.out.println();
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
