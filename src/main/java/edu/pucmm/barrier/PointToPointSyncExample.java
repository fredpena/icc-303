package edu.pucmm.barrier;

import java.util.concurrent.Phaser;

/**
 * @author me@fredpena.dev
 * @created 20/06/2024  - 07:18
 */
public class PointToPointSyncExample {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);

        Thread hilo1 = new Thread(() -> {
            System.out.println("Hilo 1: Realizando tarea 1");
            try {
                Thread.sleep(1000); // Simula el trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hilo 1: Tarea 1 completada");
            phaser.arriveAndDeregister(); // Notifica la llegada y se desregistra
        });

        Thread hilo2 = new Thread(() -> {
            System.out.println("Hilo 2: Esperando que el Hilo 1 complete la tarea 1");
            phaser.awaitAdvance(phaser.getPhase()); // Espera a que el Hilo 1 complete la tarea
            System.out.println("Hilo 2: Realizando tarea 2");
            try {
                Thread.sleep(1000); // Simula el trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hilo 2: Tarea 2 completada");
        });

        hilo1.start();
        hilo2.start();
    }
}
