public class Main {
    public static void main(String[] args) {
        SistemaReservas s = new SistemaReservas();
        Funcion f = new Funcion("Interestelar", 3, "20:30", 12.0);

        //Se elimino el parametro boolean imprimir de la llamada como parte del refactor del problema 2
        int r1 = s.crearReserva("Ana", "ana@mail.com", true, f,
                new char[]{'F', 'F'}, new int[]{5, 6},  "App");
        System.out.println("Reserva Ana: " + r1);

        // F7 se marca como ocupado antes de fallar en F6 (bug)
        try {
            //Se elimino el parametro boolean imprimir de la llamada como parte del refactor del problema 2
            int r2 = s.crearReserva("Luis", "luis@mail.com", false, f,
                    new char[]{'F', 'F'}, new int[]{7, 6}, "Taquilla");
            System.out.println("Reserva Luis: " + r2);
        } catch (IllegalStateException e) {
            System.out.println("Reserva Luis: error - " + e.getMessage());
        }
        System.out.println("Ocupados: " + f.ocupados);

        Reserva ana = s.buscar(1);
        System.out.println("Precio Ana (socio): " + ana.precio);

        System.out.println("Cancelar 1: " + s.cancelar(1));
        System.out.println("Cancelar 99: " + s.cancelar(99));
    }
}
