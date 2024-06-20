package edu.pucmm.barrier;

import java.util.concurrent.Phaser;

/**
 * @author me@fredpena.dev
 * @created 20/06/2024  - 07:31
 */
public class ParallelPipelineExample {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2); // Phaser inicializado con 2 parte para coordinar la primera etapa

        // Etapa 1: Carga de imágenes
        Thread stage1 = new Thread(() -> {
            // Código para cargar imágenes
            System.out.println("Etapa 1: Carga de imágenes completada");
            phaser.arriveAndAwaitAdvance(); // Espera a que todas las partes lleguen aquí antes de continuar
        });

        // Etapa 2: Preprocesamiento
        Thread stage2 = new Thread(() -> {
            phaser.arriveAndAwaitAdvance(); // Espera a que la Etapa 1 complete antes de comenzar
            // Código para preprocesar las imágenes
            System.out.println("Etapa 2: Preprocesamiento completado");
            phaser.arriveAndAwaitAdvance(); // Espera a que todas las partes lleguen aquí antes de continuar
        });

        // Etapa 3: Procesamiento Principal
        Thread stage3 = new Thread(() -> {
            phaser.arriveAndAwaitAdvance(); // Espera a que la Etapa 2 complete antes de comenzar
            // Código para aplicar filtros y efectos
            System.out.println("Etapa 3: Procesamiento principal completado");
            phaser.arriveAndAwaitAdvance(); // Espera a que todas las partes lleguen aquí antes de continuar
        });

        // Etapa 4: Almacenamiento
        Thread stage4 = new Thread(() -> {
            phaser.arriveAndAwaitAdvance(); // Espera a que la Etapa 3 complete antes de comenzar
            // Código para almacenar las imágenes procesadas
            System.out.println("Etapa 4: Almacenamiento completado");
            phaser.arriveAndDeregister(); // Deregistra esta parte del Phaser
        });

        // Iniciamos los hilos
        stage1.start();
        stage2.start();
        stage3.start();
        stage4.start();

        // Nos aseguramos de que todas las partes del Phaser estén registradas antes de comenzar
        phaser.arriveAndAwaitAdvance();

        // Esperamos a que todas las etapas del pipeline completen
        phaser.arriveAndAwaitAdvance();

        // Finalizamos el Phaser
        phaser.arriveAndDeregister();

        System.out.println("Proceso de pipeline completado");
    }
}
