/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionclientes;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author amartinez
 */
public class AplicacionClientes {

    private static Scanner kb = new Scanner(System.in);
    private static String operacion1, operacion2, operacion3, operacion4, 
                operacion5, operacion0, operacion111;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int opcion;
        String opcionkb;
        boolean salir = false;
        Cliente cliente = new Cliente();
        FileOutputStream fichero;
        String path = "clientes.dat";
        
        inicializaApp();
//        if (!comprobarFichero(path)){
//            System.out.println("El fichero " + path + " no existe.");
//        }
        mostrarMenu();
        while (!salir) {
            System.out.println("");
            System.out.println("# Qué operación desea realizar?  ");
            // Capturamos el error de si el usuario introduce algo que no sea un número como opción.
            // Todas las operaciones se realizan sobre la cuenta A.
            try {
                
                opcionkb = kb.next();
                //Comprobamos si la cadena contiene enteros 
                //(https://www.delftstack.com/es/howto/java/how-to-check-if-a-string-is-an-integer-in-java/)
                if (opcionkb.matches("-?\\d+")){               
                    opcion = Integer.parseInt(opcionkb);
                }else{
                    //Si la cadena introducida no contiene enteros lanzamos el error.
                    throw new InvalidOptionException("¡ERROR! - Debe insertar una número.");
                }
                
                switch (opcion) {
                    case 0:
                        System.out.println("Seleccionada opción: " + operacion0 + " (" + opcion + ").");
                        System.out.println("Que pase un buen día.");
                        salir = true;
                        break;
                    case 1:
                        System.out.println("Seleccionada opción: " + operacion1 + " (" + opcion + ").");
                        break;
                    case 2:
                        System.out.println("Seleccionada opción: " + operacion2 + " (" + opcion + ").");
                        break;
                    case 3:
                        System.out.println("Seleccionada opción: " + operacion3 + " (" + opcion + ").");
                        break;
                    case 4:
                        System.out.println("Seleccionada opción: " + operacion4 + " (" + opcion + ").");
                        break;
                    case 5:
                        System.out.println("Seleccionada opción: " + operacion5 + " (" + opcion + ").");
                        break;
                    case 111:
                        System.out.println("Seleccionada opción: " + operacion111 + " (" + opcion + ").");
                        mostrarMenu();
                        break;
                    default:
                        //Ninguna de las opcciones introducidas es válida.
                        System.out.println("¡WARN! - La opción seleccionada (" + opcion + ") no es válida. Intentelo de nuevo.");
                }
            } catch (InvalidOptionException ex) {
                //Mostramos el error de que no se ha introducido por teclado una opción numérica.
                System.out.println(ex.getMessage());
            }                        
        }
        
        System.exit(0);
        
    }
    
    /**
     * Método para inicializar variables y mostrar mensajes en pantalla como una carga de app.
     * Por el momento carga el logo generado y los strings de las operaciones a realizar.
     * Estos strings son consultados después para mostrar mensajes de la operación al usuario.
     */
    public static void inicializaApp(){
        mostrarLogo();          //Muestra el logo del Banco para hacerlo algo divertido.
        inicializaStrings();    //Inicializa los strings de operaciones para asi poder reutilizarlos.
    }
    
    /**
     * Método que inicializa los strings de las operaciones disponibles.
     */
    public static void inicializaStrings(){
        operacion1 = "Crear fichero clientes";
        operacion2 = "Listar clientes";
        operacion3 = "Buscar un cliente";
        operacion4 = "Felicitar clientes";
        operacion5 = "Borrar fichero de clientes";
        operacion0 = "Salir de la aplicación";
        operacion111 = "Mostrar menú";
    }
    
    /**
     * Método que muestra el logo DAWBANK de forma divertida.
     */
    public static void mostrarLogo(){
        System.out.println(
                "€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€\n"
                + "Client Data\n"
                + "€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€");
        System.out.println("");
    }
    
    /**
     * Método que muestra el menú de operaciones disponibles.
     */
    public static void mostrarMenu(){
        //He añadido la opción de mostrar menu con el 111 para forzar al usuario a apretar tres veces el 1.
        System.out.println("");
        System.out.println("=================================================================================");
        System.out.println(" MENÚ DE OPERACIONES  ");
        System.out.println("=================================================================================");
        System.out.println(" 1 - " + operacion1);
        System.out.println(" 2 - " + operacion2);
        System.out.println(" 3 - " + operacion3);
        System.out.println(" 4 - " + operacion4);
        System.out.println(" 5 - " + operacion5);
        System.out.println(" 111 - " + operacion111);
        System.out.println(" 0 - " + operacion0);
        System.out.println("=================================================================================");
    }
    
    /**
     * Función que comprueba si existe el fichero.
     * Es igual a comprobarDirectorio pero se duplica para no llevar a confusión.
     *
     * @param src String - Parametro de entrada por el cual le pasamos la ruta del fichero.
     */
    public static boolean comprobarFichero (String src)
    {
        File fichero = new File(src);
        if (!fichero.exists()){
            return false;
        }else{
            return true;
        }
    }
    
}
