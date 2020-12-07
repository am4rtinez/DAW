package martinez_rodriguez_angel_prog03_ejer01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ángel Martínez Rodríguez
 * 
 * Construye un proyecto en Java que utilice la clase Jugador que se define a continuación:
 *  public class Jugador {
         String nombre;
         int edad;
         byte nivel;

         String getNombre() {
              return nombre;
         }
         void setNombre(String nom) {
             nombre=nom;
        }
    }
 * Añade a la clase Jugador los métodos que faltan para poder consultar y modificar 
 * el valor de todos los atributos.
 * Para ello observa cómo se han creado los métodos del atributo nombre y 
 * determina los parámetros y resultado de los demás atributos. 
 * 
 * Crea también dos métodos constructores, uno por defecto y un segundo que acepte como parámetros valores para sus tres atributos (utiliza el operador this).
 * Crea un método principal para la clase que realice las siguientes acciones:
 *      - Declare un objeto de tipo Jugador utilizando el constructor y 
 *        asignándole a los parámetros tus datos personales, para posteriormente mostrar 
 *        el contenido de sus atributos por pantalla.
 *      - Declare un segundo objeto de tipo Jugador utilizando el constructor por 
 *        defecto y que solicite un nombre, una edad y un nivel por pantalla y lo 
 *        introduzca en los atributos correspondientes haciendo uso de los métodos 
 *        setNombre, setEdad y setNivel que has creado, para posteriormente mostrar 
 *        por pantalla los nuevos valores de los atributos usando los métodos 
 *        getNombre, getEdad y getNivel que has creado. 
 */
public class Martinez_rodriguez_angel_PROG03_Ejer01 {
    public static void main(String[] args) {
        
        Jugador jugador1 = new Jugador("Ángel Martínez",31 ,(byte)2);
        Jugador jugador2 = new Jugador();
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("---- Datos Jugador1 ----");
        System.out.println("Nombre: " + jugador1.getNombre());
        System.out.println("Edad: " + jugador1.getEdad());
        System.out.println("Nivel: " + jugador1.getNivel());
        System.out.println("------------------------");
        System.out.println();
        System.out.println("Introduce los datos del jugador2");
        System.out.println("Introduce nombre: ");
        jugador2.setNombre(capturaTexto());
        System.out.println("Introduce edad: ");
        jugador2.setEdad(keyboard.nextInt());
        System.out.println("Introduce nivel: ");
        jugador2.setNivel(keyboard.nextByte());
        System.out.println();
        System.out.println("---- Datos Jugador2 ----");
        System.out.println("Nombre: " + jugador2.getNombre());
        System.out.println("Edad: " + jugador2.getEdad());
        System.out.println("Nivel: " + jugador2.getNivel());
        System.out.println("------------------------");    
    }
    
    /**
     * Método para capturar texto para el nombre ya que se puede introducir
     * nombre y apellidos separados por espacio.
     */
    public static String capturaTexto(){
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            return lector.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Martinez_rodriguez_angel_PROG03_Ejer01.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
