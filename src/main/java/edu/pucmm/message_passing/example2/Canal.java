package edu.pucmm.message_passing.example2;


import java.util.LinkedList;
import java.util.List;

/**
 * @author me@fredpena.dev
 * @created 06/05/2024  - 15:48
 */
class Canal {

    private List<Consumidor> consumidores = new LinkedList<>();

    void registrarConsumidor(Consumidor consumidor) {
        consumidores.add(consumidor);
    }

    void borrarConsumidor(Consumidor consumidor) {
        consumidores.remove(consumidor);
    }

    void pasarMensaje(Mensaje mensaje) {
        consumidores.forEach(consumidor -> {
            consumidor.procesarMensaje(mensaje);
        });
    }
}
