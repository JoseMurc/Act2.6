package biblioteca;

public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;       // "NOVEDAD" o "NORMAL"
    private double multaPorDia;
    private double valorReposicion;

    public Libro(String titulo, String autor, String isbn, String categoria,
                 double multaPorDia, double valorReposicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.multaPorDia = multaPorDia;
        this.valorReposicion = valorReposicion;
    }
    
    // MOVE METHOD
    //Refactor, metodo movido de prestamo a libro (MOVE METHOD)
    public double calcularMulta(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0;
        }
        double multa = diasRetraso * getMultaPorDia();
        if (getCategoria().equals("NOVEDAD")) {
            multa = multa * 2;
        }
        if (multa > getValorReposicion()) {
            multa = getValorReposicion();
        }
        return multa;
    }
    

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getCategoria() { return categoria; }
    public double getMultaPorDia() { return multaPorDia; }
    public double getValorReposicion() { return valorReposicion; }
}
