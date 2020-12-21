package supuesto03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ángel Martínez Rodríguez
 * 
 * Juego: adivina un número!
 * Realiza un programa que lea dos números enteros que representarán un 
 * intervalo [x..y] en formato numérico entero.  Haz que el programa guarde en 
 * una variable un número escogido al azar del intervalo [x..y], 
 * entonces el programa nos ofrecerá hasta 10 intentos para que adivinemos el 
 * número escogido al azar mediante las preguntas personalizadas:
 * 
 * <Pon aquí tu nombre>, introduce tu intento 1 para adivinar el número del 
 * intervalo [x..y]
 * 
 * El programa leerá del teclado el número que introducimos en cada intento y 
 * nos dirá si es mayor o menor para facilitar la búsqueda o en caso que lo 
 * adivinemos nos felicitará.  
 * 
 * En cualquier caso nos informará al final de los intentos utilizados y del 
 * éxito de nuestro resultado.
 * 
 * AYUDA: para calcular el número aleatorio, utiliza la función Math.random() 
 * returns a random number between 0.0 (inclusive), and 1.0 (exclusive): por 
 * ejemplo la variable randomNum se inicializará con un número aleatorio entre 
 * 0 y 100:
 * 
 * int randomNum = (int)(Math.random() * 101);  // 0 to 100
 */
public class Supuesto03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int x = 0, y = 10, intento = 1, randomNum, userNum;
        String jugador;
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Bienvenido a ¡Adivina un número!");
        System.out.println("Para empezar debes introducir tu nombre:");
        jugador = capturaTexto();
        System.out.println("Ahora deberas introducir el intervalo del número a adivinar [x..y].");
        System.out.println("Introduce la X:");
        x = keyboard.nextInt();
        System.out.println("Introduce la Y:");
        y = keyboard.nextInt();
        System.out.println("Comencemos..");
        randomNum = initRandom(x,y);    //Inicializamos el número a adivinar.
        //System.out.println(randomNum); //Se imprime en pantalla para realizar pruebas.
        
        // Búcle de 10 intentos. La única forma de "romper" el búcle es acertando o
        // finalizando el número de intentos.
        while (intento <= 10) {
            System.out.println(jugador + ", introduce tu intento " + intento 
                    + " para adivinar el número del intervalo [" + x + ".." + y + "].");
            userNum = keyboard.nextInt();
            
            //Comprueba si el número introducido por el usuario es el mismo randomizado.
            //Si coinciden imprime el mensaje junto al número de intentos realizados.
            // En el caso de fallar indica una pista y suma 1 intento al contador.
            if (userNum == randomNum) {
                System.out.println("ENHORABUENA! Has acertado el número (" + randomNum
                        + ") en " + intento + " intentos.");
                break;  //Finaliza la ejecución.
            } else {
                if (intento == 10){
                    System.out.println("FALLASTE! Te quedan " + (10 - intento) + " intentos.");
                    System.out.println("El número a adivinar era: " + randomNum + ".");
                }
                System.out.println("FALLASTE! Te quedan " + (10 - intento) + " intentos.");
                if (userNum>randomNum){
                    //Si es mayor el número introducido por el usuario lo indica en la pista.
                    System.out.println("PISTA: El número que introdujiste es mayor al que buscas.");
                } else {
                    // En este punto solo entra si el número introducido es menor e imprime la pista en pantalla.
                    System.out.println("PISTA: El número que introdujiste es menor al que buscas.");
                }                
                intento++;      //Suma un intento al contador.
            }
        }
    }
    
    /**
     * Inicializa el número aleatorio en el rango [x..y]
     * @param x
     * @param y
     * @return 
     * Math.floor(Math.random()*(y-x+1)+x);  Valor entre x e y, ambos incluidos.
     * Wikipedia:
     */
    public static int initRandom (int x, int y){
        return (int) (Math.floor(Math.random() * (y - x + 1)) + x);
    }
    
    /**
     * Método para capturar texto para el nombre ya que se puede introducir
     * nombre y apellidos separados por espacio.
     * @return 
     */
    public static String capturaTexto(){
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            return lector.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Supuesto03.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
