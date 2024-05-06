package edu.pucmm.message_passing.example1;

/**
 * @author me@fredpena.dev
 * @created 06/05/2024  - 15:49
 */
public class Main {

    public static void main(String[] args) {
        Canal canal = new Canal();

        Productor productor = new Productor(canal) {
            @Override
            public void publicar(Mensaje mensaje) {
                super.publicar(mensaje);
            }
        };

        Consumidor consumidor = new Consumidor() {
            @Override
            public void procesarMensaje(Mensaje mensaje) {
                System.out.println("Consumidor: Acabo de recibir: " + mensaje.getContenido());
            }
        };

        canal.registrarConsumidor(consumidor);

        Mensaje<String> msg = new Mensaje<>("Hello World!");
        productor.publicar(msg);

        Mensaje<Integer> msg2 = new Mensaje<>(123456);
        productor.publicar(msg2);

    }
}