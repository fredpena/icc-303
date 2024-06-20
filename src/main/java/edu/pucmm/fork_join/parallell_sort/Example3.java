package edu.pucmm.fork_join.parallell_sort;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * @author me@fredpena.dev
 * @created 11/06/2024  - 09:39
 */
public class Example3 {

    public static void main(String[] args) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        };

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callable";
            }
        };

        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Supplier";
            }
        };


        runnable.run();

        System.out.println(callable.call());

        System.out.println(supplier.get());

    }
}
