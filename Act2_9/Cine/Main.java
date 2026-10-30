import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SistemaReservas s = new SistemaReservas();
        Funcion f = new Funcion("Interestelar", 3, "20:30", 12.0);

        // creamos el cliente y la lista de asientos usando objetos
        Cliente clienteAna = new Cliente("Ana", "ana@mail.com", true);
        List<Asiento> asientosAna = new ArrayList<>();
        asientosAna.add(new Asiento('F', 5));
        asientosAna.add(new Asiento('F', 6));

        int r1 = s.crearReserva(clienteAna, f, asientosAna, "App");
        System.out.println("Reserva Ana: " + r1);

        // reserva de luis usando objetos y atrapando la excepcion si falla
        Cliente clienteLuis = new Cliente("Luis", "luis@mail.com", false);
        List<Asiento> asientosLuis = new ArrayList<>();
        asientosLuis.add(new Asiento('F', 7));
        asientosLuis.add(new Asiento('F', 6));

        try {
            int r2 = s.crearReserva(clienteLuis, f, asientosLuis, "Taquilla");
            System.out.println("Reserva Luis: " + r2);
        } catch (IllegalStateException e) {
            System.out.println("Reserva Luis: error - " + e.getMessage());
        }

        // consultamos los ocupados usando el metodo seguro de solo lectura
        System.out.println("Ocupados: " + f.getOcupados());

        // buscamos la reserva aprovechando el encapsulate downcast
        Reserva ana = s.buscar(1);
        System.out.println("Precio Ana (socio): " + ana.precio);

        //TECNICA UTILIZADA: Replace Error Code with Exception
        //REFACTOR: el metodo cancelar() ahora lanza excepciones en vez de devolver codigos, por lo que se usa try-catch.
        try {
            s.cancelar(1);
            System.out.println("Cancelar 1: OK");
        } catch (RuntimeException e) {
            System.out.println("Cancelar 1: error - " + e.getMessage());
        }

        try {
            s.cancelar(99);
            System.out.println("Cancelar 99: OK");
        } catch (RuntimeException e) {
            System.out.println("Cancelar 99: error - " + e.getMessage());
        }
    }
}
