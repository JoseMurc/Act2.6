import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaReservas {
    public Map<Integer, Object> db = new HashMap<>();
    public int contador = 1;

    //TECNICA UTILIZADA: Replace Error Code with Exception
    //REFACTOR: crearReserva() lanza IllegalArgumentException/IllegalStateException en vez de devolver -1, -2 o -3

    //TECNICA UTILIZADA: Rename Method
    //REFACTOR: Se renombro hacer() a crearReserva() para que el nombre comunique su proposito

    //TECNICA UTILIZADA: Remove Parameter
    //REFACTOR: Se elimino el parametro boolean imprimir de crearReserva() porque no se usaba

    //TECNICA UTILIZADA: Add Parameter
    //REFACTOR: Se agrego el parametro canal a crearReserva() y se propago a la clase y al constructor de Reserva

    //TECNICA UTILIZADA: Preserve Whole Object
    //REFACTOR: crearReserva() recibe el objeto Cliente completo en vez de nombre, email y socio sueltos

    //TECNICA UTILIZADA: Introduce Parameter Object
    //REFACTOR: Se reemplazaron char[] filas e int[] numeros por List<Asiento> asientosSolicitados.
    public int crearReserva(Cliente cliente, Funcion f, List<Asiento> asientosSolicitados, String canal) {
        if (cliente == null || cliente.nombre == null || cliente.nombre.isBlank()) {
            throw new IllegalArgumentException("el cliente y su nombre son obligatorios");
        }
        if (f == null) {
            throw new IllegalArgumentException("la funcion es obligatoria");
        }
        if (asientosSolicitados == null || asientosSolicitados.isEmpty()) {
            throw new IllegalArgumentException("los asientos son obligatorios");
        }

        // validamos primero que todos los asientos esten disponibles
        for (Asiento a : asientosSolicitados) {
            if (!f.estaDisponible(a.fila, a.numero)) {
                throw new IllegalStateException("el asiento " + a.fila + a.numero + " ya esta ocupado");
            }
        }

        // si todo esta libre, procedemos a ocuparlos
        List<String> lista = new ArrayList<>();
        for (Asiento a : asientosSolicitados) {
            f.ocupar(a.fila, a.numero);
            lista.add("" + a.fila + a.numero);
        }

        // creamos la reserva incluyendo el canal y calculamos el precio
        Reserva r = new Reserva(contador, cliente, f, lista, canal);
        r.precio = r.cliente.socio ? calcularPrecioSocio(r) : calcularPrecioGeneral(r);
        db.put(contador, r);
        contador++;
        return r.id;
    }

    //TECNICA UTILIZADA: Replace Parameter with Explicit Methods
    //REFACTOR: Se elimino el metodo generico con parametro de tipo String; el llamador invoca
    //directamente calcularPrecioGeneral() o calcularPrecioSocio() segun r.cliente.socio
    public double calcularPrecioGeneral(Reserva r) {
        return r.funcion.precio * r.asientos.size();
    }

    public double calcularPrecioSocio(Reserva r) {
        //TECNICA UTILIZADA: Replace Parameter with Method
        //REFACTOR: El precio ya no se recibe como parametro; se obtiene de r.funcion.precio.
        double base = r.funcion.precio * r.asientos.size();
        return aplicarDescuento(base, 0.15);
    }

    //TECNICA UTILIZADA: Parameterize Method
    //REFACTOR: descuentoDel15 y descuentoDel25 (duplicados) se unificaron en aplicarDescuento(precio, porcentaje)
    public double aplicarDescuento(double precio, double porcentaje) {
        return precio - precio * porcentaje;
    }

    //TECNICA UTILIZADA: Encapsulate Downcast
    //REFACTOR: El cast (Reserva) db.get(id) se centralizo aqui; buscar() y cancelar() lo reutilizan
    //en vez de castear cada uno desde su lado
    public Reserva buscarReserva(int id) {
        return (Reserva) db.get(id);
    }

    //TECNICA UTILIZADA: Replace Error Code with Exception
    //REFACTOR: cancelar() lanza excepciones en vez de devolver -1 (no existe) o -2 (ya cancelada).
    public void cancelar(int id) {
        Reserva r = buscarReserva(id);
        if (r == null) {
            throw new java.util.NoSuchElementException("no existe la reserva " + id);
        }
        if (r.cancelada) {
            throw new IllegalStateException("la reserva " + id + " ya estaba cancelada");
        }
        for (String a : r.asientos) {
            //TECNICA UTILIZADA: Hide Method
            //REFACTOR: se usa r.funcion.liberar(a) en vez de modificar r.funcion.ocupados directamente.
            r.funcion.liberar(a);
        }
        r.cancelada = true;
    }

    public Reserva buscar(int id) {
        return buscarReserva(id);
    }
}
