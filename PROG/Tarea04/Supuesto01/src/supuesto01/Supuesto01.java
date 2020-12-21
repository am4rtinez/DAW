package supuesto01;

import java.util.Scanner;

/**
 *
 * @author Ángel Martínez Rodríguez
 * 
 * Realiza un programa que lea un número entero e indique si es par, 
 * si es múltiplo de 3, o ambas cosas.
 */
public class Supuesto01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        int entero;
        
        System.out.println("Introduce un número entero.");
        entero = keyboard.nextInt();
        if (par(entero) && multiplo3(entero)){
            //Comprobamos que se cumplan ambas condiciones.
            System.out.println("El número introducido es par y multiplo de 3.");
        } else if (par(entero)){
            //Comprobamos si es par. 
            System.out.println("El numero introducido es par.");
        } else if (multiplo3(entero)){
            //Comprobamos si es múltiplo de 3.
            System.out.println("El numero introducido es multiplo de 3.");
        } else {                                   
            //En este caso como no se cumplen ninguna de las opciones anteriores
            //podemos afirmar que el entero es impar y no multiplo de 3.
            System.out.println("El número introducido es impar y no multiplo de 3.");
        }
    }
    
    /**
     * Función encargada de comprobar si el entero es par.
     * @param n
     * @return 
     */
    public static boolean par(int n){
        if (n%2==0){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Función encargada de comprobar si el entero es múltiplo de 3.
     * @param n
     * @return 
     */
    public static boolean multiplo3(int n){
        if (n%3==0){
            return true;
        }else{
            return false;
        } 
    }
}
