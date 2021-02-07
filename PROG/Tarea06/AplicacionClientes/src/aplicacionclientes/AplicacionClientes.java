/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionclientes;

import aplicacionclientes.exceptions.DeleteFileException;
import aplicacionclientes.exceptions.InvalidOptionException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author amartinez
 * https://generadordni.es/#dni11
 */
public class AplicacionClientes {

    private static Scanner kb = new Scanner(System.in);
    private static String operacion1, operacion2, operacion3, operacion4, 
                operacion5, operacion0, operacion111;
    private static FileUtils fu = new FileUtils();
    
    private static final String DIRPATH = "data";
    private static final String CLIENTES_FILE_PATH = DIRPATH + "/clientes.dat";
    private static final String FELICITAR_FILE_PATH = DIRPATH + "/felicitaciones.txt";
    private static final int NUM_CLIENTES = 5;
    
    private static boolean fileRead;
    private static ArrayList <Cliente> clientesWrite;
    private static ArrayList <Cliente> clientesRead;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int opcion;
        String opcionkb;
        boolean salir = false;      

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
                        if (fu.comprobarFichero(CLIENTES_FILE_PATH)){        //Comprueba si existe el fichero.
                            System.out.println("El fichero " + CLIENTES_FILE_PATH + "ya existe. Seleccione otra opción.");
                        } else { // Creación del fichero.
                            clientesWrite = new ArrayList();
                            fu.crearFichero(CLIENTES_FILE_PATH);
                            for (int i=1; i<NUM_CLIENTES+1; i++){
                                inicializarCliente(i);
                            }
                            fu.escribirFichero(CLIENTES_FILE_PATH, clientesWrite);
                        }
                        break;
                    case 2:     
                        //Listar Clientes. Si no se ha leido previamente el fichero se lee.
                        //Se trabaja con los datos almacenados en el fichero que en un principio son los consistentes y 
                        //no se usan los datos de la lista de escritura.
                        System.out.println("Seleccionada opción: " + operacion2 + " (" + opcion + ").");
                        if (!fileRead){
                            clientesRead = new ArrayList();
                            clientesRead = obtenerClientes(CLIENTES_FILE_PATH);
                            mostrarClientes(clientesRead);
                        }else{
                            mostrarClientes(clientesRead);
                        }
                        break;
                    case 3:     
                        //Buscar cliente. Si no se ha leido previamente el fichero se lee.
                        //Se trabaja con los datos almacenados en el fichero que en un principio son los consistentes y 
                        //no se usan los datos de la lista de escritura.
                        System.out.println("Seleccionada opción: " + operacion3 + " (" + opcion + ").");
                        if (!fileRead){
                            clientesRead = new ArrayList();
                            clientesRead = obtenerClientes(CLIENTES_FILE_PATH);
                            buscarCliente(clientesRead);
                        }else{
                            buscarCliente(clientesRead);
                        }
                        break;
                    case 4:     
                        //Felicitar clientes. Si no se ha leido previamente el fichero se lee.
                        //Se trabaja con los datos almacenados en el fichero que en un principio son los consistentes y 
                        //no se usan los datos de la lista de escritura.
                        System.out.println("Seleccionada opción: " + operacion4 + " (" + opcion + ").");
                        if (!fileRead){
                            clientesRead = new ArrayList();
                            clientesRead = obtenerClientes(CLIENTES_FILE_PATH);
                            felicitarClientes(clientesRead);
                        }else{
                            felicitarClientes(clientesRead);
                        }
                        break;
                    case 5:
                        //Borrado de fichero. Se borran los datos de posibles ficheros cargados ya que serán inconsistentes.
                        System.out.println("Seleccionada opción: " + operacion5 + " (" + opcion + ").");
                        fu.borrarFichero(CLIENTES_FILE_PATH);
                        resetLectura();
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
        fileRead = false;       //Variable con la que evitamos leer el fichero varias veces.
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
    
    private static void resetLectura(){
        fileRead = false;
        clientesRead = new ArrayList();
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
        
        clientesWrite.add(cliente);
    }
    
    public static ArrayList <Cliente> obtenerClientes(String path){
        ArrayList <Cliente> clientes = fu.obtenerClientesFichero(CLIENTES_FILE_PATH);
        fileRead = true;
        return clientes;
    }
    
    public static void mostrarClientes(ArrayList <Cliente> clientes){
        System.out.println("Listado Clientes:");
        System.out.println("----------------------");
        for (int i=0; i<clientes.size();i++){
            System.out.println("Cliente " + (i+1));
            System.out.println("NIF: " + clientes.get(i).getNIF());
            System.out.println("Nombre: " + clientes.get(i).getNombre());
            System.out.println("Teléfono: " + clientes.get(i).getTelefono());
            System.out.println("E-mail: " + clientes.get(i).getEmail());
            System.out.println("Cumpleaños: " + clientes.get(i).getNacimiento().format(DTF));
            System.out.println("--------------------------------------");
        }
    }
    
    public static void buscarCliente(ArrayList <Cliente> clientes){
        boolean encontrado = false;
        boolean validNIF = false;
        Scanner kbbc = new Scanner(System.in);
        String document;
        do{
            System.out.println("# Introduzca el NIF del cliente a buscar (12345678L):");
            document = kbbc.nextLine();
            try {
                //Se valida el formato del NIF introducido (no validamos la letra.
                //Si es correcto sale del bucle.
                if (!document.matches("(^[0-9]{8}[A-Z]{1}$)")) {
                    throw new Exception("¡ERROR! - Formato no válido.");
                }
                validNIF = true;
            } catch (Exception ex) {
                //Si no es válido se informa al usuario lanzando un error y se le solicita que lo introduzca nuevamente.
                //No sale del bucle hasta que no se introduce correctamente,
                System.out.println(ex.getMessage());
                validNIF = false;
            }
        }while(!validNIF);
        
        for (int i=0; i<clientes.size() && !encontrado; i++){
            if (clientes.get(i).getNIF().equals(document)){
                encontrado = true;
                System.out.println("--------------------------------------");
                System.out.println("Datos Cliente " + (i + 1));
                System.out.println("NIF: " + clientes.get(i).getNIF());
                System.out.println("Nombre: " + clientes.get(i).getNombre());
                System.out.println("Teléfono: " + clientes.get(i).getTelefono());
                System.out.println("E-mail: " + clientes.get(i).getEmail());
                System.out.println("Cumpleaños: " + clientes.get(i).getNacimiento().format(DTF));
                System.out.println("--------------------------------------");
            }
        }
        if (!encontrado){
            System.out.println("El cliente buscado no esta en el listado.");
        }
    }
    
    public static void felicitarClientes(ArrayList <Cliente> clientes){
        LocalDate today = LocalDate.now();
        LocalDate oneWeek = today.plus(1, ChronoUnit.WEEKS);
        
//        System.out.println(today);
//        System.out.println(oneWeek);
        
        ArrayList <Cliente> felicitaciones = new ArrayList();

        for (int i=0; i<clientes.size(); i++){
            Period p = Period.between(oneWeek.with(clientes.get(i).getNacimiento().getMonth()).withDayOfMonth(clientes.get(i).getNacimiento().getDayOfMonth()),oneWeek);
            int days = p.getDays();
            if (days<=7 && days >= 0){
                felicitaciones.add(clientes.get(i));
                System.out.println(clientes.get(i).getNombre() + " " 
                        + clientes.get(i).getNacimiento().format(DTF) + " "
                        + clientes.get(i).getTelefono() + " "
                        + clientes.get(i).getEmail());
            }
        }

        if (felicitaciones.size() > 0){
            try {
                fu.crearFichero(FELICITAR_FILE_PATH);
                fu.escribirFicheroTexto(FELICITAR_FILE_PATH, felicitaciones, DTF);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
