package edu.pucmm.parallel_algorithm;

import java.util.Random;

/**
 * @author me@fredpena.dev
 * @created 27/05/2024  - 22:33
 */

public class MatrixMultiplication {
    static final int SIZE = 1000;
    static final int NUM_THREADS = 4;
    static final int BLOCK_SIZE = SIZE / NUM_THREADS;

    static double[][] matrixA = new double[SIZE][SIZE];
    static double[][] matrixB = new double[SIZE][SIZE];
    static double[][] result = new double[SIZE][SIZE];

    static class WorkerThread extends Thread {
        int startRow, endRow;

        public WorkerThread(int startRow, int endRow) {
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < SIZE; j++) {
                    for (int k = 0; k < SIZE; k++) {
                        result[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();

        // Llenar las matrices con números aleatorios
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrixA[i][j] = rand.nextDouble();
                matrixB[i][j] = rand.nextDouble();
            }
        }

        // Crear e iniciar los threads
        WorkerThread[] threads = new WorkerThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new WorkerThread(i * BLOCK_SIZE, (i + 1) * BLOCK_SIZE);
            threads[i].start();
        }


        // Esperar a que todos los threads terminen
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

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

