import java.util.List;

public class Reserva {
    public int id;
    public Cliente cliente;
    public Funcion funcion;
    public List<String> asientos;
    public double precio;
    public boolean cancelada;
    //Se añadio atributo canal por el problema 3
    public String canal;

    public Reserva(int id, Cliente cliente, Funcion funcion, List<String> asientos, String canal) {
        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = asientos;
        //Se añadio parametro y asignacion de canal por el problema 3
        this.canal = canal;
    }

    // TÉCNICA UTILIZADA: Remove setting method
    //remove setCancelada que no se usa
    
}
