import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaReservas {
    public Map<Integer, Object> db = new HashMap<>();
    public int contador = 1;

    // Nombre poco claro, muchos parámetros sueltos y flag sin uso
    // TÉCNICA UTILIZADA: Replace Error Code with Exception
    // REFACTOR: Al aplicar esta técnica se realizaron los siguientes cambios:
    // - Los datos inválidos ya lanzan IllegalArgumentException.
    // - El método ya no devuelve -1 ni -2 para representar errores.
    // - La ocupación fallida propaga IllegalStateException en lugar de devolver -3.

    // TECNICA UTILIZADA: Rename Method
    // REFACTOR: Se renombró el método crearReserva() para reflejar mejor su propósito y mejorar la legibilidad del código.

    //TECNICA UTILIZADA: Remove Parameter
    // REFACTOR: Se elimino el parametro boolean imprimir del metodo crearReserva ya que no se utilizaba
    public int crearReserva(String nombre, String email, boolean socio, Funcion f, char[] filas, int[] numeros, String canal) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }
        if (f == null) {
            throw new IllegalArgumentException("La función es obligatoria");
        }
        if (filas == null || numeros == null || filas.length == 0 || filas.length != numeros.length) {
            throw new IllegalArgumentException("Los asientos deben tener filas y números válidos");
        }

        List<String> lista = new ArrayList<>();
        for (int i = 0; i < filas.length; i++) {
            if (!f.estaDisponible(filas[i], numeros[i])) {
                throw new IllegalStateException("El asiento ya está ocupado");
            }
        }
        for (int i = 0; i < filas.length; i++) {
            f.ocupar(filas[i], numeros[i]);
            lista.add("" + filas[i] + numeros[i]);
        }
        Cliente c = new Cliente(nombre, email, socio);
        // TECNICA UTILIZADA: Add parameter
        // REFACTOR: Se añadio el parametro canal a la clase Reserva como un atributo y parametro de su constructor 
        // para añadirlo en esta llamada al constructor
        Reserva r = new Reserva(contador, c, f, lista, canal);
        if (socio) {
        // tecnica utlizada: replace parameter with explicit method
        r.precio = calcularPrecioSocio(r);
        } else {
        r.precio = calcularPrecioGeneral(r);
        }
        db.put(contador, r);
        contador++;
        return r.id;
    }

     // tecnica utilizada: replace parameter with method
    // refactor: se elimino el parametro tipo porque el metodo ya puede consultar directamente si el cliente es socio desde la reserva
    public double calcularPrecio(Reserva r) {
        double base = r.funcion.precio * r.asientos.size();
        if (r.cliente.socio) {
            return aplicarDescuento(base, 0.15);
        } 
        return base;
    }
    
    // tecnica utlizada: replace parameter with explicit method
    // Calcula el precio de una reserva general
    public double calcularPrecioGeneral(Reserva r) {
    double base = r.funcion.precio * r.asientos.size();
    return base;
    }

    // Calcula el precio de una reserva para socios
    public double calcularPrecioSocio(Reserva r) {
    double base = r.funcion.precio * r.asientos.size();
    return aplicarDescuento(base, 0.15);
    }
    
    // tecnica utlizada: parameterize method
    public double aplicarDescuento(double precio, double porcentaje) {

    return precio - precio * porcentaje;
    }

    // TÉCNICA UTILIZADA: Encapsulate Downcast
    /* 
    * - El método buscarReserva ahora recibe int en lugar de String para coincidir con las claves del mapa.
    * - El cast de Object a Reserva se centralizó en buscarReserva.
    * - buscar pasó a devolver Reserva directamente y reutiliza buscarReserva.
    * - Las clases que consultan reservas ya no necesitan realizar el cast manualmente.
    */
    public Reserva buscarReserva(int id) {
        return (Reserva) db.get(id);
    }

    // Downcast repetido en cada llamada
    public int cancelar(int id) {
        Reserva r = buscarReserva(id);
        if (r == null) return -1;
        if (r.cancelada) return -2;
        for (String a : r.asientos) {
            r.funcion.ocupados.remove(a);
        }
        r.setCancelada(true);
        return 0;
    }

    public Reserva buscar(int id) {
        return (Reserva) db.get(id);
    }
}
