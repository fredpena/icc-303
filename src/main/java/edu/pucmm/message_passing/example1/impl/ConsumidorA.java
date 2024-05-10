package edu.pucmm.message_passing.example1.impl;

import edu.pucmm.message_passing.example1.Consumidor;
import edu.pucmm.message_passing.example1.Mensaje;

/**
 * @author me@fredpena.dev
 * @created 07/05/2024  - 09:28
 */
public class ConsumidorA extends Consumidor {
    @Override
    public void procesarMensaje(Mensaje mensaje) {
        System.out.println("Consumidor A: Acabo de recibir: " + mensaje.getContenido());
    }
}
