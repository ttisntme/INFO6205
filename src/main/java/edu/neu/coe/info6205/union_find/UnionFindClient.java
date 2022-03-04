package edu.neu.coe.info6205.union_find;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UnionFindClient {
    public static int count(int n) {
        int number = n;
        UF_HWQUPC con = new UF_HWQUPC(number, true);
        Set<Pair> set = new HashSet<>();
        int loopNum = 0;  // Or the Pair number generated to union n objects.
        int unionNum = 0;  // inorder to make component number equal to 1, the union number need to be n - 1.
        while(con.components() > 1) {
            loopNum++;
            Pair pair = generatePair(set, number);
            if(!con.connected(pair.getFirst(), pair.getSecond())) {
                unionNum++;
                con.union(pair.getFirst(), pair.getSecond());
            }
        }
//        return con.components();
//        return unionNum;
        return loopNum;
    }

    private static Pair generatePair(Set<Pair> set, int num) {
        int number1;
        int number2;
        number1 = (int)Math.floor(Math.random() * num);
        number2 = (int)Math.floor(Math.random() * num);
        while(number1 == number2) {
            number2 = (int)Math.floor(Math.random() * num);
        }
        Pair cur;
        if(number1 < number2) {
            cur = new Pair(number1, number2);
        }else {
            cur = new Pair(number2, number1);
        }
        // generate not repeating Pair

        while(set.contains(cur)) {
//            System.out.println("repeat: " + "pair = (" + cur.getFirst() + ", " + cur.getSecond() + ")");
            number1 = (int)Math.floor(Math.random() * num);
            number2 = (int)Math.floor(Math.random() * num);
            while(number1 == number2) {
                number2 = (int)Math.floor(Math.random() * num);
            }
            cur.setFirst(Math.min(number1, number2));
            cur.setSecond(Math.max(number1, number2));
        }


        set.add(cur);
//        System.out.println("pair = (" + cur.getFirst() + ", " + cur.getSecond() + ")");
        return cur;
    }

    public static void main(String[] args) {
//        UF_HWQUPC con2 = new UF_HWQUPC(10, true);
//        System.out.println(con2.find(1));
//        Connections con1 = new UF_HWQUPC(10, true);
//        System.out.println(con1.isConnected(1, 2));

//        int n = 1000000;
//        System.out.println("count(" + n + "): " + count(n));
//        int[] test = new int[]{1, 2, 5, 10, 20, 40, 50, 100, 160, 200, 400, 800, 1000, 1600, 2000, 3000, 4000, 5000, 8000, 10000};
        int[] test = new int[]{50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000};
        int average = 50;
        System.out.println("Get average pair number from " + average + " tests:");
        for(int i = 0; i < test.length; i++) {
            int cur = 0;
            for(int k = 0; k < average; k++) {
                cur += count(test[i]);
            }
            System.out.println("n = " + test[i] + ", " + "count(" + test[i] + ") = " + cur / average);
        }


    }
}




// package private class
class Pair {
    private int first;
    private int second;
    public Pair(int a, int b) {
        first = a;
        second = b;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}