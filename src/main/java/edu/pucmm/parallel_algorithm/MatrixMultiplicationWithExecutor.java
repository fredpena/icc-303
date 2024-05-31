package edu.pucmm.parallel_algorithm;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author me@fredpena.dev
 * @created 27/05/2024  - 23:00
 */


public class MatrixMultiplicationWithExecutor {
    static final int SIZE = 10;
    static final int NUM_THREADS = 10;
    static final int BLOCK_SIZE = SIZE / NUM_THREADS;

    static double[][] matrixA = new double[SIZE][SIZE];
    static double[][] matrixB = new double[SIZE][SIZE];
    static double[][] result = new double[SIZE][SIZE];

    public static void main(String[] args) throws Exception {
        Random rand = new Random();

        // Llenar las matrices con números aleatorios
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrixA[i][j] = rand.nextDouble();
                matrixB[i][j] = rand.nextDouble();
            }
        }

        // Crear ExecutorService con un pool fijo de threads
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Enviar tareas de multiplicación de bloques de matrices al ExecutorService
        Future<?>[] futures = new Future[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            final int startRow = i * BLOCK_SIZE;
            final int endRow = (i + 1) * BLOCK_SIZE;
            futures[i] = executor.submit(() -> {
                for (int row = startRow; row < endRow; row++) {
                    for (int col = 0; col < SIZE; col++) {
                        for (int k = 0; k < SIZE; k++) {
                            result[row][col] += matrixA[row][k] * matrixB[k][col];
                        }
                    }
                }
            });
        }

        // Esperar a que todas las tareas se completen
        for (Future<?> future : futures) {
            future.get();
        }

        // Apagar el ExecutorService
        executor.shutdown();

        // Mostrar parte de la matriz resultante
        System.out.println("Matriz Resultante (primeras 5 filas y 5 columnas):");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%.2f\t", result[i][j]);
            }
            System.out.println();
        }
    }
}

