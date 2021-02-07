/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionclientes;

import aplicacionclientes.exceptions.DeleteFileException;
import aplicacionclientes.exceptions.InvalidOptionException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author amartinez
 */
public class AplicacionClientes {

    private static Scanner kb = new Scanner(System.in);
    private static String operacion1, operacion2, operacion3, operacion4, 
                operacion5, operacion0, operacion111;
    private static FileUtils fu = new FileUtils();
    private static final String DIRPATH = "data";
    private static final String FILEPATH = DIRPATH + "/clientes.dat";
    private static ArrayList <Cliente> clientes;
    private static int NUM_CLIENTES = 2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int opcion;
        String opcionkb;
        boolean salir = false;      
        
        FileOutputStream fichero;

        inicializaApp();

        mostrarMenu();
        
        while (!salir) {
            System.out.println("");
            System.out.println("# Qué operación desea realizar?");
            // Capturamos el error de si el usuario introduce algo que no sea un número como opción.
            // Todas las operaciones se realizan sobre la cuenta A.
            try {
                opcionkb = kb.next();
                //Comprobamos si la cadena contiene enteros
                if (opcionkb.matches("-?\\d+")){
                    opcion = Integer.parseInt(opcionkb);
                }else{
                    //Si la cadena introducida no contiene enteros lanzamos el error.
                    throw new InvalidOptionException("¡ERROR! - Debe insertar una número.");
                }
                switch (opcion) {
                    case 0:     //Salir de la aplicación.
                        System.out.println("Seleccionada opción: " + operacion0 + " (" + opcion + ").");
                        System.out.println("Que pase un buen día.");
                        salir = true;
                        break;
                    case 1:     //Creación del fichero e introducción de datos.
                        System.out.println("Seleccionada opción: " + operacion1 + " (" + opcion + ").");
                        clientes = new ArrayList();
                        if (fu.comprobarFichero(FILEPATH)){        //Comprueba si existe el fichero.
                            System.out.println("El fichero " + FILEPATH + "ya existe. Seleccione otra opción.");
                        } else { // Creación del fichero.
                            fu.crearFichero(FILEPATH);
                            for (int i=1; i<NUM_CLIENTES+1; i++){
                                inicializarCliente(i);
                            }
                            fu.escribirFichero(FILEPATH, clientes);
                        }
                        break;
                    case 2:     //Listar Clientes.
                        clientes = new ArrayList();
                        System.out.println("Seleccionada opción: " + operacion2 + " (" + opcion + ").");
                        mostrarClientes();
                        break;
                    case 3:     //Buscar cliente.
                        System.out.println("Seleccionada opción: " + operacion3 + " (" + opcion + ").");
                        break;
                    case 4:     //Felicitar clientes.
                        System.out.println("Seleccionada opción: " + operacion4 + " (" + opcion + ").");
                        break;
                    case 5:     //Borrado de fichero.
                        System.out.println("Seleccionada opción: " + operacion5 + " (" + opcion + ").");
                        fu.borrarFichero(FILEPATH);
                        break;
                    case 111:
                        System.out.println("Seleccionada opción: " + operacion111 + " (" + opcion + ").");
                        mostrarMenu();
                        break;
                    default:
                        //Ninguna de las opcciones introducidas es válida.
                        System.out.println("¡WARN! - La opción seleccionada (" + opcion + ") no es válida. Intentelo de nuevo.");
                }
            } catch (InvalidOptionException | DeleteFileException ex) {
                //Mostramos el error de que no se ha introducido por teclado una opción numérica.
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
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
        if(!fu.comprobarDirectorio(DIRPATH)){
            fu.crearDirectorio(DIRPATH);
        }
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
     * Método encargado de inicializar las cuentas.
     * Como es código que se repite he generado un método para ello. Con esto podemos introducir en el main tantas cuentas como
     * queramos solicitar al usuario. Por defecto en el enunciado se piden 2 pero podrian llegar a ser muchas más.
     * @param cliente
     * @param idCliente
     */
    public static void inicializarCliente(int idCliente){
        Cliente cliente = new Cliente();
        Scanner kbic = new Scanner(System.in);
        boolean validNIF = false;
        boolean validNombre = false;
        boolean validTelefono = false;
        boolean validEmail = false;
        boolean validNacimiento = false;
        //Bucle para solicitud de NIF.
        do{
            System.out.println("# Introduzca el NIF del cliente " + idCliente + " (12345678L):");
            String NIF = kbic.nextLine();
            try {
                //Se valida el NIF introducido.
                //Si es correcto lo almacena y sale del bucle.
                cliente.validarNIF(NIF);
                validNIF = true;
            } catch (Exception ex) {
                //Si no es válido se informa al usuario lanzando un error y se le solicita que lo introduzca nuevamente.
                //No sale del bucle hasta que no se introduce correctamente,
                System.out.println(ex.getMessage());
                validNIF = false;
            }
        }while(!validNIF);
        
        do{
            System.out.println("# Introduzca el nombre del cliente " + idCliente + " (max. 100 caracteres):");
            String nombre = kbic.nextLine();
            try {
                cliente.validarNombre(nombre);
                validNombre = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                validNombre = false;
            }
        }while(!validNombre);
        
        do{
            System.out.println("# Introduzca el teléfono del cliente " + idCliente + ":");
            String telefono = kbic.nextLine();
            try {
                cliente.validarTelefono(telefono);
                validTelefono = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                validTelefono = false;
            }
        }while(!validTelefono);
        
        do{
            System.out.println("# Introduzca el email del cliente " + idCliente + ":");
            String email = kbic.nextLine();
            try {
                cliente.validarEmail(email);
                validEmail = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                validEmail = false;
            }
        }while(!validEmail);
        
        do{
            System.out.println("# Introduzca el cumpleaños del cliente " + idCliente + " (dd/mm/yyyy):");
            String nacimiento = kbic.nextLine();
            try {
                cliente.validarNacimiento(nacimiento);
                validNacimiento = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                validNacimiento = false;
            }
        }while(!validNacimiento);
        
        clientes.add(cliente);
    }
    
    public static void mostrarClientes(){
        clientes = fu.obtenerClientesFichero(FILEPATH);
        for (int i=0; i<clientes.size();i++){
            System.out.println("Cliente " + (i+1));
            System.out.println("NIF: " + clientes.get(i).getNIF());
            System.out.println("Nombre: " + clientes.get(i).getNombre());
            System.out.println("Nombre: " + clientes.get(i).getTelefono());
            System.out.println("Nombre: " + clientes.get(i).getEmail());
            System.out.println("Nombre: " + clientes.get(i).getNacimiento());
            System.out.println("--------------------------------------");
        }
    }
}
