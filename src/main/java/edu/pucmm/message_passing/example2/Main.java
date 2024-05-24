package edu.pucmm.message_passing.example2;

import java.security.SecureRandom;
import java.util.Random;

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

        Productor productor2 = new Productor(canal) {
            @Override
            public void publicar(Mensaje mensaje) {
                System.out.println("Productor 2");
                super.publicar(mensaje);
            }
        };


        canal.registrarConsumidor(new ConsumidorInfoGeneral());
        // canal.registrarConsumidor(new ConsumidorLicencia());
        var edad = new SecureRandom().nextInt(30);

        Persona persona = new Persona("Fred", "Pena", edad);

        Mensaje<Persona> msg = new Mensaje<>(persona);
        productor.publicar(msg);
        productor2.publicar(msg);


    }
}
