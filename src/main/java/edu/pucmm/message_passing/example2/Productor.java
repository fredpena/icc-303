package edu.pucmm.message_passing.example2;


/**
 * @author me@fredpena.dev
 * @created 06/05/2024  - 15:49
 */
abstract class Productor {

    private Canal canal;

    Productor(Canal canal) {
        this.canal = canal;
    }

    void publicar(Mensaje mensaje) {
        canal.pasarMensaje(mensaje);
    }

}
