package edu.pucmm.topology;

/**
 * @author me@fredpena.dev
 * @created 31/05/2024  - 07:13
 */


class MeshNetwork {
    private final Processor[][] processors;
    private final int rows;
    private final int cols;

    // Constructor que crea una malla de procesadores
    public MeshNetwork(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        processors = new Processor[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                processors[i][j] = new Processor(i, j);
            }
        }
    }

    // Método para enviar un mensaje de un procesador a otro en la malla
    public void sendMessage(int fromRow, int fromCol, int toRow, int toCol, String message) {
        if (isValid(fromRow, fromCol) && isValid(toRow, toCol)) {
            processors[toRow][toCol].receiveMessage(message);
        }
    }

    // Verificar si la posición en la malla es válida
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    static class Processor {
        private final int row;
        private final int col;

        // Constructor del procesador con su posición en la malla
        public Processor(int row, int col) {
            this.row = row;
            this.col = col;
        }

        // Método para recibir un mensaje
        public void receiveMessage(String message) {
            System.out.println("Processor (" + row + ", " + col + ") received: " + message);
        }
    }

    public static void main(String[] args) {
        // Crear una red de malla de 3x3 procesadores
        MeshNetwork meshNetwork = new MeshNetwork(3, 3);
        // Enviar un mensaje desde el procesador (0, 0) al procesador (2, 2)
        meshNetwork.sendMessage(0, 0, 1, 2, "Hello from (0,0)");
    }
}

