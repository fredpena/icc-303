package edu.pucmm.barrier;

import java.util.concurrent.Phaser;

/**
 * @author me@fredpena.dev
 * @created 20/06/2024  - 07:24
 */
public class IterativeAverageExample {
    public static void main(String[] args) {
        double[] data = {3.0, 1.5, 4.5, 2.0, 5.0}; // Datos iniciales

        // Configuramos el Phaser para sincronizar hilos
        Phaser phaser = new Phaser(data.length);

        // Inicializamos el promedio inicial
        double average = calculateInitialAverage(data);

        // Iteramos hasta que se alcance la convergencia
        while (!hasConverged(data, average, 0.001)) {
            // Cada hilo actualiza su valor basado en el promedio actual
            for (int i = 0; i < data.length; i++) {
                final int index = i;
                double finalAverage = average;
                new Thread(() -> {
                    data[index] = updateValue(data[index], finalAverage);
                    phaser.arriveAndAwaitAdvance(); // Espera a que todos los hilos lleguen aquí
                }).start();
            }

            // Esperamos a que todos los hilos completen la iteración antes de calcular el nuevo promedio
            phaser.arriveAndAwaitAdvance();

            // Calculamos el nuevo promedio
            average = calculateAverage(data);
        }

        System.out.println("Promedio convergido: " + average);
    }

    // Funciones auxiliares para calcular promedio inicial, actualizar valores y verificar convergencia
    private static double calculateInitialAverage(double[] data) {
        double sum = 0.0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    private static double updateValue(double value, double average) {
        return (value + average) / 2.0; // Actualización simple para ilustrar el proceso
    }

    private static boolean hasConverged(double[] data, double currentAverage, double epsilon) {
        double previousAverage = calculateAverage(data);
        return Math.abs(currentAverage - previousAverage) < epsilon;
    }

    private static double calculateAverage(double[] data) {
        double sum = 0.0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }
}
