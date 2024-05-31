package edu.pucmm.topology;

import java.util.ArrayList;
import java.util.List;

/**
 * @author me@fredpena.dev
 * @created 31/05/2024  - 07:13
 */


class RingNetwork {
    private final List<Processor> processors;

    // Constructor que crea y conecta los procesadores en un anillo
    public RingNetwork(int numProcessors) {
        processors = new ArrayList<>();
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor(i));
        }
        // Conectar cada procesador con su vecino en el anillo
        for (int i = 0; i < numProcessors; i++) {
            processors.get(i).setNext(processors.get((i + 1) % numProcessors));
        }
    }

    // Método para enviar un mensaje desde un procesador específico
    public void sendMessage(int from, String message) {
        if (from >= 0 && from < processors.size()) {
            processors.get(from).sendMessage(message, from);
        }
    }

    static class Processor {
        private final int id;
        private Processor next;

        // Constructor del procesador con un identificador único
        public Processor(int id) {
            this.id = id;
        }

        // Establecer el siguiente procesador en el anillo
        public void setNext(Processor next) {
            this.next = next;
        }

        // Método para enviar un mensaje al siguiente procesador
        public void sendMessage(String message, int originalSender) {
            System.out.println("Processor " + id + " sending: " + message);
            if (next != null && next.id != originalSender) {
                next.receiveMessage(message, originalSender);
            }
        }

        // Método para recibir un mensaje y pasarlo al siguiente procesador
        public void receiveMessage(String message, int originalSender) {
            System.out.println("Processor " + id + " received: " + message);
            if (next != null && next.id != originalSender) {
                next.receiveMessage(message, originalSender);
            }
        }
    }

    public static void main(String[] args) {
        // Crear una red de anillo con 5 procesadores
        RingNetwork ringNetwork = new RingNetwork(10);
        // Enviar un mensaje desde el procesador 0
        ringNetwork.sendMessage(0, "Hello from Processor 0");
    }
}


