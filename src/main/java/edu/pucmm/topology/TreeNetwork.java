package edu.pucmm.topology;

/**
 * @author me@fredpena.dev
 * @created 31/05/2024  - 07:13
 */


class TreeNetwork {
    private final Processor root;

    // Constructor que crea una red de árbol con un número determinado de niveles
    public TreeNetwork(int levels) {
        root = buildTree(levels, 0);
    }

    // Método recursivo para construir el árbol
    private Processor buildTree(int levels, int id) {
        if (levels == 0) return null;
        Processor node = new Processor(id);
        node.left = buildTree(levels - 1, 2 * id + 1);
        node.right = buildTree(levels - 1, 2 * id + 2);
        return node;
    }

    // Método para enviar un mensaje a un procesador específico por su ID
    public void sendMessage(int id, String message) {
        sendMessage(root, id, message);
    }

    private void sendMessage(Processor node, int id, String message) {
        if (node == null) return;
        if (node.id == id) {
            node.receiveMessage(message);
        } else {
            sendMessage(node.left, id, message);
            sendMessage(node.right, id, message);
        }
    }

    static class Processor {
        private int id;
        private Processor left, right;

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
        // Crear una red de árbol con 3 niveles
        TreeNetwork treeNetwork = new TreeNetwork(3);
        // Enviar un mensaje al procesador 4
        treeNetwork.sendMessage(4, "Hello from Processor 4");
    }
}

