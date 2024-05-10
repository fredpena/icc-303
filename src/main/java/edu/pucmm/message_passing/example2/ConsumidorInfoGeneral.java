package edu.pucmm.message_passing.example2;


/**
 * @author me@fredpena.dev
 * @created 09/05/2024  - 08:40
 */
class ConsumidorInfoGeneral extends Consumidor {
    @Override
    public void procesarMensaje(Mensaje mensaje) {
        System.out.println("procesar info general -->");

        if (mensaje.getContenido() instanceof Persona obj) {
            System.out.println(obj);

        }

    }
}
