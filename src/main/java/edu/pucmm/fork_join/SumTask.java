package edu.pucmm.fork_join;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


/**
 * @author me@fredpena.dev
 * @created 13/06/2024  - 07:38
 */
public class SumTask extends RecursiveTask<Long> {
    // A thread can easily handle, let's say for testing, five elements
    //private static final int SEQUENTIAL_THRESHOLD = 5;
    // To compare the performance, I'm testing with 100,000
    private static final int SEQUENTIAL_THRESHOLD = 100_000;

    // The list with the numbers
    private final List<Long> data;

    // Since compute() doesn't take parameters, you have to
    // pass in the task's constructor the data to work
    public SumTask(List<Long> data) {
        this.data = data;
    }

    //Return type matches the generic
    @Override
    protected Long compute() {
        if (data.size() <= SEQUENTIAL_THRESHOLD) { // base case
            return computeSumDirectly();
        } else { // recursive case
            // Calculate new range
            int mid = data.size() / 2;
            SumTask firstSubtask = new SumTask(data.subList(0, mid));
            SumTask secondSubtask = new SumTask(data.subList(mid, data.size()));

            // queue the first task
            firstSubtask.fork();

            // Return the sum of all subtasks
            return secondSubtask.compute() + firstSubtask.join();
        }
    }

    /**
     * Method that calculates the sum
     */
    private long computeSumDirectly() {
        long sum = 0;
        for (Long l : data) {
            sum += l;
        }
        return sum;
    }

    public static void main(String[] args) {
        Random random = new Random();

        List<Long> data = random.longs(100, 1, 5).boxed().toList();

//        data.forEach(System.out::println);

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Pool parallelism: " + pool.getParallelism());
        SumTask task = new SumTask(data);
        System.out.println("Sum: " + pool.invoke(task));
    }
}