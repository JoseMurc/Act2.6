package biblioteca;

// MOVE FIELD
//Refactor: Reemplace los tex
public class Socio {
    private String nombre;
    private String ciudad;
    // REFACTOR: Reemplace los textos sueltos del codigo de area y el numero por un objeto de la clase Telefono que cree arriba.
    private Telefono telefono;
    // TECNICA SUGERIDA: MOVE FIELD
    // REFACTOR: Borre por completo la variable diasPlazoPrestamo y su 'get' de aqui. Como el socio ni la usaba, se muda a la clase Prestamo.
    // REFACTOR: Actualice este constructor para que reciba el objeto Telefono directo y quite el dato de los dias de prestamo para que tenga sentido.
    
    public Socio(String nombre, String ciudad, Telefono telefono) {
        this.nombre = nombre;
        this.ciudad =  ciudad;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
   public Telefono getTelefono() { return telefono; }

}