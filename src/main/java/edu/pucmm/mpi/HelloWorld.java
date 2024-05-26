

import mpi.MPI;
import mpi.MPIException;

/**
 * @author me@fredpena.dev
 * @created 24/05/2024  - 06:45
 */
public class HelloWorld {


    public static void main(String[] args) throws MPIException {
        // Inicializar MPI
        MPI.Init(args);

        // Obtener el rango del proceso
        int rank = MPI.COMM_WORLD.Rank();

        // Obtener el tamaño del grupo
        int size = MPI.COMM_WORLD.Size();

        // Imprimir mensaje
        System.out.println("Hola desde el proceso " + rank + " de " + size);

        // Finalizar MPI
        MPI.Finalize();

    }
}
