package biblioteca;

import java.util.Date;

public class Prestamo {
    private Libro libro;
    private Socio socio;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    //Refactor diasPlazoPrestamo movido de socio a prestamo (MOVE FIELD)
    private int diasPlazoPrestamo;

    public Prestamo(Libro libro, Socio socio, Date fechaPrestamo, Date fechaDevolucion, int diasPlazoPrestamo) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        //Refactor diasPlazoPrestamo movido al constructor de Prestamo y eliminado del constructor de socio (MOVE FIELD)
        this.diasPlazoPrestamo = diasPlazoPrestamo;
    }

    public Libro getLibro() { return libro; }
    public Socio getSocio() { return socio; }
    //Refactor diasPlazoPrestamo movido de socio a prestamo (MOVE FIELD)
    public int getDiasPlazoPrestamo() { return diasPlazoPrestamo; }
    
    FechaBiblioteca fecha = new FechaBiblioteca();
    
    // INTRODUCE FOREIGN METHOD
    public Date calcularFechaVencimiento() {
        //Refactor uso del nuevo metodo (INTRODUCE FOREING METHOD)
        return sumarDias(fechaPrestamo, diasPlazoPrestamo);
    }

    public int calcularDiasRetraso() {
        return fecha.diasEntre(calcularFechaVencimiento(), fechaDevolucion);
    }
    //Refactor metodo sumarDias agregado (INTRODUCE FOREING METHOD)
    public Date sumarDias(Date fecha, int dias) {
    return new Date(fecha.getTime() + dias * 24L * 60 * 60 * 1000);
    }

    // MOVE METHOD
    /*public double calcularMulta(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0;
        }
        double multa = diasRetraso * libro.getMultaPorDia();
        if (libro.getCategoria().equals("NOVEDAD")) {
            multa = multa * 2;
        }
        if (multa > libro.getValorReposicion()) {
            multa = libro.getValorReposicion();
        }
        return multa;
    }*/

    // INTRODUCE LOCAL EXTENSION
    //Refactor metodos movidos a la clase FechaBiblioteca
    
    
    // HIDE DELEGATE
    //Refactor se oculta a la clase socio
    public String getCiudadSocio(){
        return socio.getCiudad();
    }
    
    public String getTelefonoSocio(){
        return socio.getTelefono().obtenerTelefonoCompleto();
    }

    public void imprimirResumen() {
        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Socio: " + socio.getNombre());
        System.out.println("Prestado: " + fecha.formatearFecha(fechaPrestamo));
        System.out.println("Vence: " + fecha.formatearFecha(calcularFechaVencimiento()));
        System.out.println("Vence en fin de semana: " + fecha.esFinDeSemana(calcularFechaVencimiento()));
        System.out.println("Devuelto: " + fecha.formatearFecha(fechaDevolucion));
    }
}
