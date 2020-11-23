package prog02ejer04;

import java.util.Scanner;

/**
 *
 * @author Ángel Martínez Rodríguez
 */

/*
  Diseña un programa Java que pida por teclado el nombre, el primer apellido, 
  el segundo apellido, la población y el dni de una persona, a continuación y 
  utilizando el método System.out.printf mostrar con una única instrucción la 
  siguiente información por pantalla (con valores tomados por ejemplo):

    Salva Ramis, Maria 
    DNI 78123123A 
    INCA
*/

public class Prog02Ejer04 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String nombre, apellido1, apellido2, dni, poblacion;
        System.out.println("Introduzca el nombre:");
        nombre = keyboard.next();
        nombre = refactorString(nombre);
        System.out.println("Introduzca el primer apellido:");
        apellido1 = keyboard.next();
        apellido1 = refactorString(apellido1);
        System.out.println("Introduzca el segundo apellido:");
        apellido2 = keyboard.next();
        apellido2 = refactorString(apellido2);
        System.out.println("Introduzca el DNI (12345678A):");
        dni = keyboard.next();
        System.out.println("Introduzca la población:");        
        poblacion = keyboard.next();
        //%n - salto de linea.
        //%S - formatea el string en Mayus.
        System.out.printf("%s %s, %s %nDNI %s %n%S%n", apellido1, apellido2, nombre, dni, poblacion); 
    }
    
    /*
     Función que se encarga de poner la primera letra en mayúsculas y el resto 
     en minúsculas ya que el usuario puede introducirlas como quiera.
     */
    public static String refactorString (String string){
        String s1 = string.substring(0,1);
        String s2 = string.substring(1, string.length());
        String scompleto = s1.toUpperCase() + s2.toLowerCase();
        return scompleto;
    }
    
}
