package martinez_rodriguez_angel_prog03_ejer02;

import numeros.Complejo;

/**
 * @author Ángel Martínez Rodríguez
 * 
 * Dada una clase Complejo con dos atributos:
 *  - real: parte real del número complejo
 *  - imag: parte imaginaria del número complejo
 * 
 * Y los siguientes métodos dentro de la clase:
 *  - public Complejo(): Constructor que inicializa los atributos a cero.
 *  - public Complejo(double real, double imag): Constructor que inicializa los 
 *    atributos a los valores indicados por los parámetros.
 *  - public double getReal(): Devuelve la parte real del objeto.
 *  - public double getImag(): Devuelve la parte imaginaria del objeto.
 *  - public void setReal(double real): Asigna a la parte real del objeto el 
 *    valor indicado en el parámetro real.
 *  - public void setImag(double imag): Asigna a la parte imaginaria del objeto 
 *    el valor indicado en el parámetro imag.
 *  - public String toString(): Convierte a String el número complejo, mediante 
 *    la concatenación de sus atributos y devuelve como resultado la cadena de 
 *    texto 3 + 4i, si 3 es la parte real y 4 la parte imaginaria.
 *  - public void sumar(Complejo b): Suma la parte real con la parte real del 
 *    número complejo b y la parte imaginaria con la parte imaginaria del número complejo b.
 * 
 * Crea un proyecto que contenga la clase Complejo en un paquete llamado numeros y pruebe todos sus métodos.
 */

public class Martinez_rodriguez_angel_PROG03_Ejer02 {

    static Complejo ca;
    static Complejo cb;
    static Complejo cc;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ca = new Complejo();                                //Crea un complejo A inicializado a 0.
        System.out.println("ca = " + ca.toString());        //Imprime en formato String el Complejo A inicializado a 0.
        cb = new Complejo();                                //Crea un complejo B inicializado a 0.
        cb.setReal(10);
        cb.setImag(8);
        System.out.println("cb = " + cb.toString());        //Imprime el complejo B en string.
        cc = new Complejo(2,3);                             //Crea un complejo C inicializado con valores.
        System.out.println("cc = " + cc.toString());        //Imprime el complejo C en string.
        cb.sumar(cc);                                       //Suma el complejo C al complejo B.
        System.out.println("cb + cc = " + cb.toString());   //Muestra el complejo B después de la suma.
    }
    
}
