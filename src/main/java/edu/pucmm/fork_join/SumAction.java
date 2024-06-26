package edu.pucmm.fork_join;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


/**
 * @author me@fredpena.dev
 * @created 13/06/2024  - 07:33
 */
public class SumAction extends RecursiveAction {
    // A thread can easily handle, let's say, five elements
    private static final int SEQUENTIAL_THRESHOLD = 5;

    // The list with the numbers
    private final List<Long> data;

    // Since compute() doesn't take parameters, you have to
    // pass in the task's constructor the data to work
    public SumAction(List<Long> data) {
        this.data = data;
    }


    @Override
    protected void compute() {
//        System.out.println(data.size());
        if (data.size() <= SEQUENTIAL_THRESHOLD) { // base case
            long sum = computeSumDirectly();
            System.out.format("Sum of %s: %d\n", data.toString(), sum);
        } else { // recursive case
            // Calculate new range
            int mid = data.size() / 2;
            SumAction firstSubtask =
                    new SumAction(data.subList(0, mid));
            SumAction secondSubtask =
                    new SumAction(data.subList(mid, data.size()));

            firstSubtask.fork(); // queue the first task
            secondSubtask.compute(); // compute the second task
            firstSubtask.join(); // wait for the first task result

            // Or simply call
            //invokeAll(firstSubtask, secondSubtask);
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

        List<Long> data = random
                .longs(100, 1, 20)
                .boxed()
                .toList();

//        data.forEach(System.out::println);

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Pool parallelism: " + pool.getParallelism());
        SumAction task = new SumAction(data);
        pool.invoke(task);
    }
}
