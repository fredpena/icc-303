package edu.pucmm.parallel_algorithm;

import java.util.concurrent.Semaphore;

/**
 * @author me@fredpena.dev
 * @created 27/05/2024  - 22:44
 */

public class ProducerConsumer {
    static final int BUFFER_SIZE = 5;
    static int[] buffer = new int[BUFFER_SIZE];
    static Semaphore empty = new Semaphore(BUFFER_SIZE); //Controla el número de espacios vacíos en el buffer
    static Semaphore full = new Semaphore(0); // Controla el número de elementos en el buffer
    static Semaphore mutex = new Semaphore(1); // Controla el acceso a la región crítica del buffer.


    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    empty.acquire();
                    mutex.acquire();
                    int item = (int) (Math.random() * 100);
                    buffer[i % BUFFER_SIZE] = item;
                    System.out.println("Producer produces: " + item);
                    mutex.release();
                    full.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    full.acquire();
                    mutex.acquire();
                    int item = buffer[i % BUFFER_SIZE];
                    System.out.println("Consumer consumes: " + item);
                    mutex.release();
                    empty.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
    }
}
