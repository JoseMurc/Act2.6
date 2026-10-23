/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

// EXTRACT CLASS
/*Refactor: Se ccrea la clase Telefono para encapsular la información del teléfono del socio, 
incluyendo el código de área y el número de teléfono. Esto mejora la cohesión y facilita la gestión de la información del teléfono.*/
public class Telefono {
    
    private String codigoArea;
    private String numero;

    public Telefono(String codigoArea, String numero) {
        this.codigoArea = codigoArea;
        this.numero = numero;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public String getNumero() {
        return numero;
    }

    public String obtenerTelefonoCompleto() {
        return "(" + codigoArea + ") " + numero;
    }

    public boolean esTelefonoLocal() {
        return codigoArea.equals("999");
    }
}