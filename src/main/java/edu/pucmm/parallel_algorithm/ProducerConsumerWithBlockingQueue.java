package edu.pucmm.parallel_algorithm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author me@fredpena.dev
 * @created 27/05/2024  - 23:05
 */


public class ProducerConsumerWithBlockingQueue {
    static final int BUFFER_SIZE = 5;
    static BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);

    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    int item = (int) (Math.random() * 100);
                    buffer.put(item); // Agregar elemento al buffer
                    System.out.println("Producer produces: " + item);
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
                    int item = buffer.take(); // Extraer elemento del buffer
                    System.out.println("Consumer consumes: " + item);
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

