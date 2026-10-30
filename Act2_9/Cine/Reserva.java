import java.util.List;

public class Reserva {
    public int id;
    public Cliente cliente;
    public Funcion funcion;
    public List<String> asientos;
    public double precio;
    public boolean cancelada;
    //TECNICA UTILIZADA: Add Parameter
    //REFACTOR: se agrego el atributo canal para registrar el canal de venta de la reserva.
    //esto tambien incluye agregar canal al parametro del constructor de Reserva
    public String canal;

    public Reserva(int id, Cliente cliente, Funcion funcion, List<String> asientos, String canal) {
        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = asientos;
        this.canal = canal;
    }

    //TECNICA UTILIZADA: Remove Setting Method
    //REFACTOR: se elimino setCancelada(), cancelar() en SistemaReservas asigna el campo directamente.

}
