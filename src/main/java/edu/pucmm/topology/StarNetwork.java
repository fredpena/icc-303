package edu.pucmm.topology;

import java.util.ArrayList;
import java.util.List;

/**
 * @author me@fredpena.dev
 * @created 31/05/2024  - 07:12
 */


class StarNetwork {
    private final Processor concentrator;

    // Constructor que crea un procesador concentrador para la red
    public StarNetwork() {
        concentrator = new Processor("Concentrator");
    }

    // Método para conectar un procesador al concentrador
    public void connectProcessor(Processor processor) {
        concentrator.connectProcessor(processor);
    }

    // Método para enviar un mensaje desde el concentrador a todos los procesadores conectados
    public void sendMessageToAll(String message) {
        concentrator.sendMessageToAll(message);
    }

    // Clase que representa un procesador en la red
    static class Processor {
        private final String name;
        private final List<Processor> connectedProcessors;

        // Constructor del procesador con un nombre
        public Processor(String name) {
            this.name = name;
            this.connectedProcessors = new ArrayList<>();
        }

        // Método para conectar un procesador al actual
        public void connectProcessor(Processor processor) {
            connectedProcessors.add(processor);
            System.out.println(processor.name + " connected to the concentrator.");
        }

        // Método para enviar un mensaje a todos los procesadores conectados
        public void sendMessageToAll(String message) {
            System.out.println("Sending message to all connected processors...");
            for (Processor processor : connectedProcessors) {
                processor.receiveMessage(message);
            }
        }

        // Método para recibir un mensaje
        public void receiveMessage(String message) {
            System.out.println("Message received at " + name + ": " + message);
        }
    }

    public static void main(String[] args) {
        // Crear una red de estrella para procesamiento paralelo
        StarNetwork starNetwork = new StarNetwork();

        // Conectar algunos procesadores al concentrador
        Processor processor1 = new Processor("Processor 1");
        starNetwork.connectProcessor(processor1);

        Processor processor2 = new Processor("Processor 2");
        starNetwork.connectProcessor(processor2);

        // Enviar un mensaje desde el concentrador a todos los procesadores conectados
        starNetwork.sendMessageToAll("Hello everyone!");
    }
}




