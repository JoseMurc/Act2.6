import java.util.ArrayList;
import java.util.List;

public class Funcion {
    public String titulo;
    public int sala;
    public String hora;
    public double precio;
    public List<String> ocupados = new ArrayList<>();

    public Funcion(String titulo, int sala, String hora, double precio) {
        this.titulo = titulo;
        this.sala = sala;
        this.hora = hora;
        this.precio = precio;
    }

    // TÉCNICA UTILIZADA: Replace Exception with Test
    // TÉCNICA UTILIZADA: Separate Query from modifier
    // REFACTOR: Al aplicar esta técnica se realizaron los siguientes cambios:
    // - Se agregó estaDisponible para consultar antes de modificar la función.
    // - ocupar dejó de lanzar una excepción para controlar el asiento ocupado.
    // - ocupar devuelve false si el asiento ya está ocupado.
    public boolean estaDisponible(char fila, int numero) {
        String codigo = "" + fila + numero;
        return !ocupados.contains(codigo);
    }

    public boolean ocupar(char fila, int numero) {
        if (!estaDisponible(fila, numero)) {
            return false;
        }
        String codigo = "" + fila + numero;
        ocupados.add(codigo);
        return true;
    }

     // TÉCNICA UTILIZADA: Remove setting method
    //remove setPrecio que no se usa
}
