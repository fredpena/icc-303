package edu.pucmm.barrier;

import java.util.concurrent.Phaser;

/**
 * @author me@fredpena.dev
 * @created 20/06/2024  - 07:38
 */
public class DataFlowParallelism {
    public static void main(String[] args) {
        Phaser dataReadyPhaser = new Phaser(1); // Hilo principal

        new Thread(() -> {
            processDataChunk1();
            dataReadyPhaser.arriveAndAwaitAdvance(); // Señalar que los datos están listos
        }).start();

        new Thread(() -> {
            processDataChunk2();
            dataReadyPhaser.arriveAndAwaitAdvance(); // Señalar que los datos están listos
        }).start();

        // Tarea que depende de ambos fragmentos de datos
        new Thread(() -> {
            dataReadyPhaser.awaitAdvance(dataReadyPhaser.getPhase()); // Esperar los fragmentos de datos
            processDataDependentTask();
        }).start();
    }

    private static void processDataChunk1() {
        System.out.println("Procesando fragmento de datos 1 en " + Thread.currentThread().getName());
    }

    private static void processDataChunk2() {
        System.out.println("Procesando fragmento de datos 2 en " + Thread.currentThread().getName());
    }

    private static void processDataDependentTask() {
        System.out.println("Procesando tarea dependiente de datos en " + Thread.currentThread().getName());
    }
}
