package edu.pucmm.message_passing.example2;

/**
 * @author me@fredpena.dev
 * @created 09/05/2024  - 08:36
 */
class Persona {
    private String nombre;
    private String apellido;
    private int edad;

    Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    String getNombre() {
        return nombre;
    }

    String getApellido() {
        return apellido;
    }

    int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
               "nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", edad=" + edad +
               '}';
    }
}
