package edu.pucmm.topology;

import java.util.ArrayList;
import java.util.List;

/**
 * @author me@fredpena.dev
 * @created 31/05/2024  - 07:12
 */


class BusNetwork {
    private final List<Processor> processors;

    // Constructor que crea una lista de procesadores en la red de autobús
    public BusNetwork(int numProcessors) {
        processors = new ArrayList<>();
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor(i));
        }
    }

    // Método para enviar un mensaje de un procesador a otro a través del bus compartido
    public void sendMessage(int from, int to, String message) {
        if (from >= 0 && from < processors.size() && to >= 0 && to < processors.size()) {
            System.out.println("Bus: Processor " + from + " sending to Processor " + to + ": " + message);
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
        // Crear una red de autobús con 5 procesadores
        BusNetwork busNetwork = new BusNetwork(5);
        // Enviar un mensaje desde el procesador 0 al procesador 3
        busNetwork.sendMessage(0, 3, "Hello from Processor 0");
        // Enviar un mensaje desde el procesador 2 al procesador 4
        busNetwork.sendMessage(2, 4, "Hello from Processor 2");
    }
}

