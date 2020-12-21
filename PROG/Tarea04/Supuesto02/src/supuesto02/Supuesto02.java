package supuesto02;

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
 * Escribe un programa que solicite a un usuario su nombre y edad. 
 * Debe mostrarse cuántos años tendrá dentro de una década y clasificarle según 
 * su edad en A[0-25], B[26-50], C[51-...].
 */
public class Supuesto02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String usuario;
        int edad;
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Introduzca su nombre:");
        usuario = capturaTexto();
        usuario = usuario.trim(); //Limpiamos espacios en blanco delante y detrás del texto.
        System.out.println("Introduzca su edad:");
        edad = keyboard.nextInt();
        System.out.println(usuario + " en una decada tendrá " + (edad + 10) + " años.");
        clasificaEdad(edad);
        
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
            Logger.getLogger(Supuesto02.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Método encargado de mostrar en pantalla la clasificación de edad.
     * @param edad 
     */
    public static void clasificaEdad(int edad) {
        if (edad>=0 && edad<=25){
            //Rango edad mayor o igual a 0 y menor o igual a 25.
            System.out.println("Se le ha clasificado en el grupo A de edad [0-25].");
        } else if (edad>25 && edad<51) {
            // Edad mayor a 25 y menor a 51 así obtenemos el rango 26-50.
            System.out.println("Se le ha clasificado en el grupo B de edad [26-50].");
        } else {
            // La edad siempre será mayor o iguala 51 ya que en los otros casos 
            // se ha filtrado.
            System.out.println("Se le ha clasificado en el grupo C de edad [51-..].");
        }
    }
}
