package edu.pucmm.topology;

import java.util.ArrayList;
import java.util.List;

/**
 * @author me@fredpena.dev
 * @created 31/05/2024  - 07:13
 */


class FullyConnectedNetwork {
    private final List<Processor> processors;

    // Constructor que crea una red completamente conectada con un número determinado de procesadores
    public FullyConnectedNetwork(int numProcessors) {
        processors = new ArrayList<>();
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor(i));
        }
    }

    // Método para enviar un mensaje de un procesador a otro
    public void sendMessage(int from, int to, String message) {
        if (from >= 0 && from < processors.size() && to >= 0 && to < processors.size()) {
            System.out.println("Direct Link: Processor " + from + " sending to Processor " + to + ": " + message);
            processors.get(to).receiveMessage(message);
        }
    }

    static class Processor {
        private final int id;

        // Constructor del procesador con un identificador único
        public Processor(int id) {
            this.id = id;
        }

        // Método para recibir un mensaje
        public void receiveMessage(String message) {
            System.out.println("Processor " + id + " received: " + message);
        }
    }

    public static void main(String[] args) {
        // Crear una red completamente conectada con 4 procesadores
        FullyConnectedNetwork fullyConnectedNetwork = new FullyConnectedNetwork(4);
        // Enviar un mensaje desde el procesador 0 al procesador 2
        fullyConnectedNetwork.sendMessage(0, 2, "Hello from Processor 0");
        // Enviar un mensaje desde el procesador 1 al procesador 3
        fullyConnectedNetwork.sendMessage(1, 3, "Hello from Processor 1");
    }
}


