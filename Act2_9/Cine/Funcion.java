import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Funcion {
    public String titulo;
    public int sala;
    public String hora;
    public double precio;
    //TECNICA UTILIZADA: Hide Method
    //REFACTOR: ocupados paso de publico a privado, el acceso externo se restringe mediante getOcupados() y liberar()
    private List<String> ocupados = new ArrayList<>();

    public Funcion(String titulo, int sala, String hora, double precio) {
        this.titulo = titulo;
        this.sala = sala;
        this.hora = hora;
        this.precio = precio;
    }

    //TECNICA UTILIZADA: Separate Query from Modifier
    //REFACTOR: estaDisponible() solo consulta, ocupar() solo modifica, sin hacer ambas cosas a la vez
    public boolean estaDisponible(char fila, int numero) {
        String codigo = "" + fila + numero;
        return !ocupados.contains(codigo);
    }

    //TECNICA UTILIZADA: Replace Exception with Test
    //REFACTOR: ocupar() prueba estaDisponible() y devuelve false en vez de lanzar y capturar una excepcion
    public boolean ocupar(char fila, int numero) {
        if (!estaDisponible(fila, numero)) {
            return false;
        }
        String codigo = "" + fila + numero;
        ocupados.add(codigo);
        return true;
    }

    //TECNICA UTILIZADA: Remove Setting Method
    //REFACTOR: se elimino setPrecio() porque el precio no debe modificarse tras crear la Funcion

    public List<String> getOcupados() {
        return Collections.unmodifiableList(ocupados);
    }

    // libera un asiento cuando se cancela una reserva
    public void liberar(String codigo) {
        ocupados.remove(codigo);
    }
}
