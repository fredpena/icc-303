package edu.pucmm.barrier;

import java.util.concurrent.Phaser;

/**
 * @author me@fredpena.dev
 * @created 20/06/2024  - 07:11
 */
public class SplitPhaseBarrierExample {

    public static void main(String[] args) {
        final int numPhases = 3;
        final int numThreads = 4;

        Phaser phaser = new Phaser(numThreads);

        for (int i = 0; i < numThreads; i++) {
            final int threadID = i;
            new Thread(() -> {
                for (int phase = 0; phase < numPhases; phase++) {
                    System.out.println("Hilo " + threadID + " ejecutando fase " + phase);

                    // Simulamos trabajo en la fase actual
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Esperamos a que todos los hilos lleguen a esta barrera de fase
                    phaser.arriveAndAwaitAdvance();
                }
            }).start();
        }
    }
}
