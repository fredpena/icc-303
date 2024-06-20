package edu.pucmm.fork_join.parallell_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author me@fredpena.dev
 * @created 11/06/2024  - 07:06
 */
public class Example2 {
    public static void main(String[] args) {

        Random rand = new Random();

        int MAX = 900_000_000;

        // Creating an array
        int[] numbers = new int[MAX];

        // Iterating Loop till i = 1000
        // with interval of 10
        for (int i = 0; i < MAX; i += 1) {
            numbers[i] = rand.nextInt();
        }

        // Start and End Time of Arrays.sort()
        long startTime = System.nanoTime();

        // Performing Serial Sort
       // Arrays.sort(numbers);

        long endTime = System.nanoTime();

        // Printing result of Serial Sort
        System.out.println("Start and End Time in Serial (in ns): "
                           + startTime + ":" + endTime);
        System.out.println("Time taken by Serial Sort(in ns): "
                           + (endTime - startTime));

        // Start and End Time of Arrays.parallelSort()
        startTime = System.nanoTime();

        // Performing Parallel Sort
        Arrays.parallelSort(numbers);

        endTime = System.nanoTime();

        // Printing result of Parallel Sort
        System.out.println("Start and End Time in parallel (in ns): "
                           + startTime + ":" + endTime);
        System.out.println("Time taken by Parallel Sort(in ns): "
                           + (endTime - startTime));
        System.out.println();
    }
}
