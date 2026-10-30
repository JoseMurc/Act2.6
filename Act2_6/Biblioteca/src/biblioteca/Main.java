package biblioteca;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        //Recepcion recepcion = new Recepcion(catalogo);

        //REMOVE MIDDLE MAN
        //Refactor se removio recepcion de la ecuacion y se usa directamente a catalogo
        catalogo.agregar(new Libro("Java Basico", "R. Perez", "978-1-11111-111-1", "NOVEDAD", 5, 300));
        catalogo.agregar(new Libro("Estructuras de Datos", "M. Diaz", "978-2-22222-222-2", "NORMAL", 3, 400));
        System.out.println("Libros registrados: " + catalogo.total());

        Libro libro = catalogo.buscarPorTitulo("java basico");
        Telefono telefono = new Telefono("999", "1234567");
        Socio socio = new Socio("Ana Lopez", "Merida", telefono);

        Date fechaPrestamo = new GregorianCalendar(2026, Calendar.OCTOBER, 1).getTime();
        Date fechaDevolucion = new GregorianCalendar(2026, Calendar.OCTOBER, 22).getTime();
        //Refactor diasPlazoPrestamo movido del constructor de socio al de prestamo (MOVE FIELD)
        Prestamo prestamo = new Prestamo(libro, socio, fechaPrestamo, fechaDevolucion, 14);

        System.out.println("-----------------------------");
        prestamo.imprimirResumen();
        System.out.println("-----------------------------");

        // HIDE DELEGATE
        // Se oculto la clase socio por medio de la clase Prestamo
        System.out.println("Ciudad del socio: " + prestamo.getCiudadSocio());
        System.out.println("Telefono del socio: " + prestamo.getTelefonoSocio());
        System.out.println("Autor del libro: " + prestamo.getLibro().getAutor());

        int retraso = prestamo.calcularDiasRetraso();
        System.out.println("Dias de retraso: " + retraso);
        // los metodos calcular multa e Isbn ahora se llaman desde la clase libro
        System.out.println("Multa: $" + libro.calcularMulta(retraso));
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Telefono local: " + socio.getTelefono().esTelefonoLocal());
    }
}
