package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        processArgs(args);

//        int threadCount = 2;
//        ForkJoinPool myPool = new ForkJoinPool(threadCount);
//        CompletableFuture cf = CompletableFuture.supplyAsync(mySup, myPool);
//        System.out.println("Degree of parallelism: " + myPool.getParallelism());

        System.out.println("Default degree of parallelism of my Computer: " + ForkJoinPool.getCommonPoolParallelism());

//        int threadCount = 4;
//        ForkJoinPool pool = new ForkJoinPool(threadCount);
//        System.out.println(" activeThreads=" + pool.getActiveThreadCount() +
//                " runningThreads=" + pool.getRunningThreadCount() +
//                " poolSize=" + pool.getPoolSize() +" queuedTasks=" + pool.getQueuedTaskCount() +
//                " queuedSubmissions=" + pool.getQueuedSubmissionCount() +
//                " parallelism=" + pool.getParallelism() +
//                " stealCount=" + pool.getStealCount());

        Random random = new Random();
        int[] array = new int[2500000];
        ArrayList<Long> timeList = new ArrayList<>();
        for (int j = 50; j < 100; j++) {
//            ParSort.cutoff = 10000 * (j + 1);
//            ParSort.cutoff = 7500 * (j + 1);
            ParSort.cutoff = 12500 * (j + 1);
            long time;
            long startTime = System.currentTimeMillis();
            for (int t = 0; t < 10; t++) {
                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                ParSort.sort(array, 0, array.length);
            }
            long endTime = System.currentTimeMillis();
            time = (endTime - startTime);
            timeList.add(time);


            System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time:" + time + "ms");

        }
        // out put result to file
//        try {
//            FileOutputStream fis = new FileOutputStream("./src/result_size2_thread2.csv");
//            OutputStreamWriter isr = new OutputStreamWriter(fis);
//            BufferedWriter bw = new BufferedWriter(isr);
//            int j = 50;
//            for (long i : timeList) {
////                String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
////                String content = (double) 7500 * (j + 1) / 1500000 + "," + (double) i / 10 + "\n";
//                String content = (double) 12500 * (j + 1) / 2500000 + "," + (double) i / 10 + "\n";
//                j++;
//                bw.write(content);
//                bw.flush();
//            }
//            bw.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();


}
