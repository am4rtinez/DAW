package prog02ejer10;

import java.util.Scanner;

/**
 *
 * @author Ángel Martínez Rodríguez
 */
/**
 *
 * Calcula la longitud y el área de una circunferencia de radio r. Define la
 * constante PI como 3.1416 y pide por pantalla el valor del radio. Añade
 * comentarios para indicar qué cálculo se corresponde a qué. El resultado para
 * una circunferencia de radio 4 sería:
 *
 * longitud = 25.13279914855957 área = 50.26559829711914
 */
public class Prog02Ejer10 {

    static final double PI = 3.1416; //declarado como constante.

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        double longitud, area, radio;
        System.out.print("Introduzca el radio de la circunferencia: ");
        radio = keyboard.nextDouble();
        longitud = (2 * PI * radio);
        area = PI * (Math.pow(radio, 2));
        System.out.println("longitud = " + longitud);
        System.out.println("área = " + area);
    }
}
