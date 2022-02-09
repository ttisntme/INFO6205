/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.Timer;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();

        // FIXME
//        for(int i = 1; i < xs.length; i++) {
//            int j = i;
//            while(j >= 1 && helper.swapStableConditional(xs, j)) {
//                j--;
//            }
//        }
        if(from < 1) from = 1;
        if(to > xs.length - 1) to = xs.length - 1;
        for(int i = from; i <= to; i++) {
            int j = i;
            while(j >= 1 && helper.swapStableConditional(xs, j)) {
                j--;
            }
        }

        // END 
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }

    public static void main(String args[]) {
        // test InsertionSort Time
        int nRuns = 1;
//        int warmups = 2;
//        Integer[] xs = new Integer[]{5, 4, 3, 2, 1};
//        Benchmark<Integer[]> bm = new Benchmark_Timer<Integer[]>("InsertionSort", null,
//                b -> {
//                    InsertionSort.sort(xs);
//                },
//                null
//        );
//        double time = bm.run(xs, nRuns);
//        System.out.println("average time: " + time + " ms, in " + nRuns + " repeats.");

        System.out.println("Start Testing: ");
        int[] arrayLength = new int[]{10, 50, 100, 500, 1000, 1500, 2000, 4000, 6000, 10000};
        for(int i = 0; i < arrayLength.length; i++) {
            int curLen = arrayLength[i];
            for(int j = 0; j < 4; j++) {
//                Integer[] cur = new Integer[curLen];
                int repeat = 10;
                if(j == 3) { // ordered
                    double allTime = 0;
                    for(int m = 0; m < repeat; m++) {
                        Integer[] cur = new Integer[curLen];
                        for(int k = 0; k < cur.length; k++) {
                            cur[k] = (Integer)k;
                        }
                        double curTime = new Timer().repeat(nRuns, () -> cur, t -> {
                            InsertionSort.sort(cur);
                            return null;
                        }, null, null);
                        allTime += curTime;
                    }

                    System.out.println("average time: " + allTime / repeat + " ms, " + "type: " + j + ", " + "length: " + curLen +".");
                }else if(j == 0) { // reverse-ordered

                    double allTime = 0;
                    for(int m = 0; m < repeat; m++) {
                        Integer[] cur = new Integer[curLen];
                        int temp = 0;
                        for(int k = cur.length - 1; k >= 0; k--) {
                            cur[k] = (Integer) temp++;
                        }
                        double curTime = new Timer().repeat(nRuns, () -> cur, t -> {
                            InsertionSort.sort(cur);
                            return null;
                        }, null, null);
                        allTime += curTime;
                    }

                    System.out.println("average time: " + allTime / repeat + " ms, " + "type: " + j + ", " + "length: " + curLen +".");
                }else if(j == 1) { // random-ordered

                    double allTime = 0;
                    for(int m = 0; m < repeat; m++) {
                        Integer[] cur = new Integer[curLen];
                        for(int k = 0; k < cur.length; k++) {
                            cur[k] = (Integer)(int)(Math.random() * curLen * 1000);
                        }
                        double curTime = new Timer().repeat(nRuns, () -> cur, t -> {
                            InsertionSort.sort(cur);
                            return null;
                        }, null, null);
                        allTime += curTime;
                    }

                    System.out.println("average time: " + allTime / repeat + " ms, " + "type: " + j + ", " + "length: " + curLen +".");
                }else if(j == 2) { // partial-ordered

                    double allTime = 0;
                    for(int m = 0; m < repeat; m++) {
                        Integer[] cur = new Integer[curLen];
                        for(int k = 0; k < cur.length / 2; k++) {
                            cur[k] = (Integer)k;
                        }
                        for(int k = cur.length / 2; k < cur.length; k++) {
                            cur[k] = (Integer)(int)(Math.random() * curLen * 1000);
                        }
                        double curTime = new Timer().repeat(nRuns, () -> cur, t -> {
                            InsertionSort.sort(cur);
                            return null;
                        }, null, null);
                        allTime += curTime;
                    }

                    System.out.println("average time: " + allTime / repeat + " ms, " + "type: " + j + ", " + "length: " + curLen +".");
                }

                // warmup 坏事
//                Benchmark<Integer[]> bmForInsertionSort = new Benchmark_Timer<Integer[]>("InsertionSort", null,
//                        b -> {
//                            InsertionSort.sort(cur);
//                        },
//                        null
//                );
//                double curTime = bmForInsertionSort.run(cur, nRuns);

//                double curTime = new Timer().repeat(nRuns, () -> cur, t -> {
////                    new InsertionSort().sort(cur, 0, curLen - 1);
//                    InsertionSort.sort(cur);
//                    return null;
//                }, null, null);

//                System.out.println("average time: " + curTime + " ms, " + "type: " + j + ", " + "length: " + curLen +".");
//                System.out.println("array: " + cur);

            }

        }
    }
}
