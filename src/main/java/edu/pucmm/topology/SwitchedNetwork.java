package edu.pucmm.topology;

import java.util.HashMap;
import java.util.Map;

/**
 * @author me@fredpena.dev
 * @created 31/05/2024  - 07:13
 */


class SwitchedNetwork {
    private final Map<Integer, Processor> processors;
    private final Switch sw;

    // Constructor que crea una red conmutada con un número determinado de procesadores
    public SwitchedNetwork(int numProcessors) {
        processors = new HashMap<>();
        sw = new Switch();
        for (int i = 0; i < numProcessors; i++) {
            Processor p = new Processor(i);
            processors.put(i, p);
            sw.addProcessor(p);
        }
    }

    // Método para enviar un mensaje de un procesador a otro usando el switch
    public void sendMessage(int from, int to, String message) {
        if (processors.containsKey(from) && processors.containsKey(to)) {
            sw.routeMessage(from, to, message);
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

    static class Switch {
        private final Map<Integer, Processor> routingTable;

        // Constructor del switch
        public Switch() {
            routingTable = new HashMap<>();
        }

        // Método para agregar un procesador al switch
        public void addProcessor(Processor p) {
            routingTable.put(p.id, p);
        }

        // Método para enrutar un mensaje desde un procesador de origen a un procesador de destino
        public void routeMessage(int from, int to, String message) {
            if (routingTable.containsKey(to)) {
                routingTable.get(to).receiveMessage("Message from " + from + ": " + message);
            }
        }
    }

    public static void main(String[] args) {
        // Crear una red conmutada con 4 procesadores
        SwitchedNetwork switchedNetwork = new SwitchedNetwork(4);
        // Enviar un mensaje desde el procesador 1 al procesador 3
        switchedNetwork.sendMessage(1, 3, "Hello from Processor 1");
    }
}

