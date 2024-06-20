package edu.pucmm.barrier;

import java.util.concurrent.CountDownLatch;

/**
 * @author me@fredpena.dev
 * @created 20/06/2024  - 00:10
 */
public class CountDownLatchExample {

    public static void main(String[] args) {
        int numTasks = 3;
        CountDownLatch latch = new CountDownLatch(numTasks);

        for (int i = 0; i < numTasks; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " ejecutando tarea");
                    latch.countDown(); // Disminuir el contador
                    latch.await(); // Esperar a que el contador llegue a cero
                    System.out.println(Thread.currentThread().getName() + " continuando después del contador");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
