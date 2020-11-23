package prog02ejer06;

import java.util.Scanner;

/**
 *
 * @author Ángel Martínez Rodríguez
 */

/*
 Diseña un programa Java para calcular el espacio recorrido por un vehículo que 
 circula a una velocidad constante durante un tiempo determinado.  
 Tanto la velocidad como el tiempo se introducen desde teclado.
 La salida del programa para una velocidad de 6 m/s y un tiempo de 10 segundos será:
    - Introducir velocidad: 6
    - Introducir tiempo: 10
    - El espacio recorrido es de 60 metros
*/

public class Prog02Ejer06 {
    /*
     Se preparan las variables en float ya que el usuario podria añadir decimales.
     No es el caso del ejemplo pero la aplicación se deja preparada para ello
     para que no lance un error si el usuario introduce decimales.     
    */    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        float vel, seg = 0;
        System.out.print("Introduzca la velocidad (m/s): ");
        vel = keyboard.nextFloat();
        System.out.print("Introduzca el tiempo (s): ");
        seg = keyboard.nextFloat();
        System.out.println("El espacio recorrido es de : " + (vel * seg) + " metros.");        
    }    
}
