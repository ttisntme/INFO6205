package edu.neu.coe.info6205.union_find;

public class UnionFindClient {
    public static int count(int n) {
        int number = n;
        UF_HWQUPC con = new UF_HWQUPC(number, true);
        int unionNum = 0;
        int loopNum = 0;
        while(con.components() > 1) {
            loopNum++;
            int[] pair = generatePair(number);
            if(!con.connected(pair[0], pair[1])) {
                unionNum++;
                con.union(pair[0], pair[1]);
            }
        }
        return unionNum;
//        return loopNum;
    }

    private static int[] generatePair(int num) {
        int[] pair = new int[2];
        pair[0] = (int)Math.floor(Math.random() * num);
        pair[1] = (int)Math.floor(Math.random() * num);
        while(pair[0] == pair[1]) {
            pair[1] = (int)Math.floor(Math.random() * num);
        }
        return pair;
    }

    public static void main(String[] args) {
//        UF_HWQUPC con2 = new UF_HWQUPC(10, true);
//        System.out.println(con2.find(1));
//        Connections con1 = new UF_HWQUPC(10, true);
//        System.out.println(con1.isConnected(1, 2));

//        int n = 1000;
//        System.out.println("count(" + n + "): " + count(n));
        for(int i = 0; i <= 10000; i += 100) {
            System.out.println("count(" + i + ")= " + count(i));
        }


    }
}
