package edu.pucmm.barrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author me@fredpena.dev
 * @created 20/06/2024  - 00:32
 */
public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numThreads = 3;
        Runnable barrierAction = () -> System.out.println("Todos los hilos alcanzaron la barrera");

        CyclicBarrier barrier = new CyclicBarrier(numThreads, barrierAction);

        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " esperando en la barrera");
                    barrier.await(); // Esperar a otros hilos
                    System.out.println(Thread.currentThread().getName() + " continuando después de la barrera");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
