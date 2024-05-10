package edu.pucmm.message_passing.example1;

import edu.pucmm.message_passing.example1.impl.ConsumidorA;
import edu.pucmm.message_passing.example1.impl.ConsumidorB;

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


        canal.registrarConsumidor(new ConsumidorA());
        canal.registrarConsumidor(new ConsumidorB());

        Mensaje<String> msg = new Mensaje<>("Hello World!");
        productor.publicar(msg);

        Mensaje<Integer> msg2 = new Mensaje<>(123456);
        productor.publicar(msg2);

    }
}