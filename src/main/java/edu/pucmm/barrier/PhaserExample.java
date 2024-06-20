package edu.pucmm.barrier;

import java.util.concurrent.Phaser;

/**
 * @author me@fredpena.dev
 * @created 20/06/2024  - 00:41
 */
public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); // Inicialmente registramos el hilo principal

        for (int i = 0; i < 3; i++) {
            phaser.register(); // Registrar cada nueva tarea
            new Thread(new Task(phaser)).start();
        }

        phaser.arriveAndAwaitAdvance(); // Esperar a que todas las tareas completen la fase 1
        System.out.println("Fase 1 completada");

        phaser.arriveAndAwaitAdvance(); // Esperar a que todas las tareas completen la fase 2
        System.out.println("Fase 2 completada");

        phaser.arriveAndDeregister(); // Desregistrar el hilo principal
    }

    static class Task implements Runnable {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ejecutando fase 1");
            phaser.arriveAndAwaitAdvance(); // Completa la fase 1 y espera

            System.out.println(Thread.currentThread().getName() + " ejecutando fase 2");
            phaser.arriveAndAwaitAdvance(); // Completa la fase 2 y espera

            phaser.arriveAndDeregister(); // Completa el trabajo y se desregistra
        }
    }
}