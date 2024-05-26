package edu.pucmm.distributed_shared_memory.domino;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author me@fredpena.dev
 * @created 06/05/2024  - 16:31
 */
public class SharedMemory {

    static class Jugador {
        String nombre;
        Set<Integer> ficha = new HashSet<>();

        public Jugador(String nombre) {
            this.nombre = nombre;
        }

        void add(Integer elememto) {
            ficha.add(elememto);
        }
    }


    static class Tablero {

        public synchronized void asignacion(Jugador jugador) {
            jugador.add(new Random().nextInt(100));
            System.out.println("%s asignado al jugador %s".formatted(Thread.currentThread().getName(), jugador.nombre));
        }
    }

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Jugador jugador1 = new Jugador("Juan");
        Jugador jugador2 = new Jugador("Carlos");
        Jugador jugador3 = new Jugador("Albert");
        Jugador jugador4 = new Jugador("Moises");

        List.of(jugador1, jugador2, jugador3, jugador4)
                .forEach(p -> {
                    Runnable producer = () -> {
                        for (int i = 0; i < 7; i++) {
                            tablero.asignacion(p);
                        }
                    };
                    executor.execute(producer);
                });
        executor.shutdown();
    }
}
