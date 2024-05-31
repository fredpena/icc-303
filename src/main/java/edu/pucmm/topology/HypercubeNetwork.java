package edu.pucmm.topology;

import java.util.ArrayList;
import java.util.List;

/**
 * @author me@fredpena.dev
 * @created 31/05/2024  - 07:13
 */


class HypercubeNetwork {
    private final List<Processor> processors;

    // Constructor que crea una red hiperbólica de una determinada dimensión
    public HypercubeNetwork(int dimensions) {
        int numProcessors = (int) Math.pow(2, dimensions);
        processors = new ArrayList<>();
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor(i));
        }
    }

    // Método para enviar un mensaje de un procesador a otro
    public void sendMessage(int from, int to, String message) {
        if (from >= 0 && from < processors.size() && to >= 0 && to < processors.size()) {
            processors.get(to).receiveMessage(message);
        }
    }

    static class Processor {
        private int id;

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
        // Crear una red hiperbólica de 3 dimensiones
        HypercubeNetwork hypercubeNetwork = new HypercubeNetwork(3);
        // Enviar un mensaje desde el procesador 0 al procesador 7
        hypercubeNetwork.sendMessage(0, 7, "Hello from Processor 0");
    }
}

