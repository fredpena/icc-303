package edu.pucmm.fork_join;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author me@fredpena.dev
 * @created 13/06/2024  - 07:40
 */
public class ComparePerformance {

    public static void main(String[] args) {
        Random random = new Random();

        List<Long> data = random
                .longs(100_000_000, 1, 100)
                .boxed()
                .toList();

        testForkJoin(data);
        testSequentially(data);
        testSequentiallyStream(data);
        testParallelStream(data);
    }

    private static void testSequentially(List<Long> data) {
        final long start = System.currentTimeMillis();

        long sum = 0;
        for (Long l : data) {
            sum += l;
        }

        System.out.println("Executed sequentially in (ms): " + (System.currentTimeMillis() - start));
    }

    private static void testSequentiallyStream(List<Long> data) {
        final long start = System.currentTimeMillis();

        data.stream().reduce(0L, Long::sum);

        System.out.println("Executed with a sequential stream in (ms): " + (System.currentTimeMillis() - start));
    }

    private static void testForkJoin(List<Long> data) {
        final long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(data);
        pool.invoke(task);

        System.out.println("Executed with fork/join in (ms): " + (System.currentTimeMillis() - start));
    }


    private static void testParallelStream(List<Long> data) {
        final long start = System.currentTimeMillis();

        data.parallelStream().reduce(0L, Long::sum);

        System.out.println("Executed with parallel streams in (ms): " + (System.currentTimeMillis() - start));
    }
}
