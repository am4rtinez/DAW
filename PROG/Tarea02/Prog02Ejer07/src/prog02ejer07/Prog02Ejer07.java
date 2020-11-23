package prog02ejer07;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Ángel Martínez Rodríguez
 */

/*
   Diseña un programa Java que calcule la suma, resta, multiplicación y división 
   de dos números introducidos por teclado. Incorpora también las funciones que 
   permitan realizar la potencia del primer número introducido y su raíz cuadrada.
   La salida del programa para x=16, y=2 será:
    Introducir primer número: 16
    Introducir segundo número: 2
    x = 16.0 y = 2.0
    x + y = 18.0
    x - y = 14.0
    x * y = 32.0
    x / y = 8.0
    x ^ 2 = 256.0
    √ x = 4.0
*/
public class Prog02Ejer07 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.0"); //Formatea los decimales a un dígito decimal.
        float x, y;
        System.out.print("Introducir primer número (x): ");
        x = keyboard.nextFloat();
        System.out.print("Introducir segundo número (y): ");
        y = keyboard.nextFloat();
        //Se muestran las operaciones directamente en pantalla sin almacenarlas 
        //en variables ya que no se van a usar para otro fin.
        System.out.println("x = " + df.format(x) + " y = " + df.format(y));
        System.out.println("x + y = " + df.format((x+y)));
        System.out.println("x - y = " + df.format((x-y)));
        System.out.println("x * y = " + df.format((x*y)));
        System.out.println("x / y = " + df.format((x/y)));
        System.out.println("x ^ 2 = " + df.format(Math.pow(x,2)));
        System.out.println("√ x = " + df.format(Math.sqrt(x)));
    }
}
