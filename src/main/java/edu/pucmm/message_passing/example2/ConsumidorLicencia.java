package edu.pucmm.message_passing.example2;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author me@fredpena.dev
 * @created 09/05/2024  - 08:45
 */
public class ConsumidorLicencia extends Consumidor {
    @Override
    void procesarMensaje(Mensaje mensaje) {
        System.out.println("procesar licencia -->");

        if (mensaje.getContenido() instanceof Persona obj) {
            if (obj.getEdad() < 18) {
                System.out.println("No aplica para licencia");
            } else {
                var flag = new SecureRandom().nextBoolean();
                var licencia = flag ? "Si" : "No";

                System.out.println(licencia);
            }
        }


    }
}
