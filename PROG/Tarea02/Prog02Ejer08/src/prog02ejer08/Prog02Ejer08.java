package prog02ejer08;

import java.util.Scanner;

/**
 *
 * @author Ángel Martínez Rodríguez
 */
/**
 * 
 * Diseña un programa Java que solicite un número de 6 dígitos del teclado,
 * separe el número en sus dígitos individuales y los muestre por pantalla. Por
 * ejemplo, si el número es 753123 que muestre: 7-5-3-1-2-3
 */
public class Prog02Ejer08 {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int numero = 0; //Asignandolo como tipo int nos cercioramos de que sea un número.
        /*
         Comprobamos que el numero introducido contenga 6 digitos. Cuando se 
         cumple la condicion sale del bucle ya con el numero asignado. Al inicializarse
         a 0 siempre va a entrar en el bucle.
         */
        while (String.valueOf(numero).length() != 6) {
            System.out.print("Introduzca un número de 6 dígitos: ");
            numero = keyboard.nextInt();
        }
        //Convertimos el número en string para tratarlo.
        String transformado = Integer.toString(numero);
        //Bucle encargado de imprimir los dígitos.
        //Utilización del metodo System.out.print para imprimir en una misma línea de texto.
        for (int i = 1; i <= transformado.length(); i++) {
            if (i <= transformado.length() - 1) {
                // Comprobación para añadir el caracter -
                System.out.print(transformado.substring(i - 1, i) + "-");
            } else {
                //Al tratarse del último dígito no es necesario añadir el caracter -.
                //Añadido \n para que no se mezcle el BUILD SUCCESSFUL del output de netbeans en la misma linea.
                System.out.print(transformado.substring(i - 1) + "\n");
            }
        }
    }
}
