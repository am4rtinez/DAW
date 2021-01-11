package aplicacioncuentabancaria;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author Ángel Martínez Rodríguez
 */
public class AplicacionCuentaBancaria {

    private static Scanner kb = new Scanner(System.in);
    private static String operacion1, operacion2, operacion3, operacion4, 
                operacion5, operacion6, operacion7, operacion8, 
                operacion9, operacion0, operacion111;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int opcion;
        boolean salir = false;
               
        CuentaBancaria cuentaA = new CuentaBancaria();
        CuentaBancaria cuentaB = new CuentaBancaria();
        
        inicializaApp();
        
        //Introducción y validación del titular B y la cuenta B.
        inicializarCuenta(cuentaA,"A");
        
        //Introducción y validación del titular B y la cuenta B.
        inicializarCuenta(cuentaB, "B");
        
        //Una vez validados titulares y CCCs se procede a mostrar el menú de operaciones a realizar.
        mostrarMenu();
        while (!salir) {
            System.out.println("");
            System.out.println("# Qué operación desea realizar?  ");
            // Capturamos el error de si el usuario introduce algo que no sea un número como opción.
            // Todas las operaciones se realizan sobre la cuenta A.
            try {
                opcion = kb.nextInt();
                switch (opcion) {
                    case 0:
                        System.out.println("Seleccionada opción: " + operacion0 + " (" + opcion +").");
                        System.out.println("Que pase un buen día.");
                        salir = true;
                        break;
                    case 1:
                        System.out.println("Seleccionada opción: " + operacion1 + " (" + opcion +").");
                        System.out.println(cuentaA.getCcc());
                        break;
                    case 2:
                        System.out.println("Seleccionada opción: " + operacion2 + " (" + opcion +").");
                        System.out.println(cuentaA.getTitular());
                        break;
                    case 3:
                        System.out.println("Seleccionada opción: " + operacion3 + " (" + opcion +").");
                        break;
                    case 4:
                        System.out.println("Seleccionada opción: " + operacion4 + " (" + opcion +").");
                        break;
                    case 5:
                        System.out.println("Seleccionada opción: " + operacion5 + " (" + opcion +").");
                        break;
                    case 6:
                        System.out.println("Seleccionada opción: " + operacion6 + " (" + opcion +").");
                        break;
                    case 7:
                        System.out.println("Seleccionada opción: " + operacion7 + " (" + opcion +").");
                        break;
                    case 8:
                        System.out.println("Seleccionada opción: " + operacion8 + " (" + opcion +").");
                        break;
                    case 9:
                        System.out.println("Seleccionada opción: " + operacion9 + " (" + opcion +").");
                        break;
                    case 111:
                        System.out.println("Seleccionada opción: " + operacion111 + " (" + opcion +").");
                        mostrarMenu();
                        break;
                    default:
                        //Ninguna de las opcciones introducidas es válida.
                        System.out.println("¡WARN! - La opción seleccionada (" + opcion + ") no es válida. Intentelo de nuevo.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("¡ERROR! - Debe insertar un número.");
                kb.nextInt();
            }                        
        }
        
        System.exit(0);
        
    }
    
    public static void inicializaApp(){
        mostrarLogo();          //Muestra el logo del Banco para hacerlo algo divertido.
        inicializaStrings();    //Inicializa los strings de operaciones para asi poder reutilizarlos.
    }
    
    public static void inicializaStrings(){
        operacion1 = "Ver el número de cuenta completo (CCC - Código Cuenta Cliente)";
        operacion2 = "Ver el titular de la cuenta";
        operacion3 = "Ver el código de la entidad";
        operacion4 = "Ver el código de la oficina";
        operacion5 = "Ver el número de la cuenta";
        operacion6 = "Ver los dígitos de control de la cuenta";
        operacion7 = "Realizar un ingreso";
        operacion8 = "Retirar efectivo";
        operacion9 = "Transferencia entre cuentas (A -> B)";
        operacion0 = "Salir de la aplicación";
        operacion111 = "Mostrar menú";
    }
    
    public static void mostrarLogo(){
        System.out.println(
                "€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€v\n"
                + "€€       €€€€€€  €€€€€  €€€€  €€€€  €€      €€€€€€€  €€€€€  €€€€€€€  €€  €€€€  €€\n"
                + "€€  €€€€  €€€€    €€€€  €€€€  €€€€  €€  €€€  €€€€€    €€€€   €€€€€€  €€  €€€  €€€\n"
                + "€€  €€€€  €€€  €€  €€€  €€€€  €€€€  €€  €€€€  €€€  €€  €€€    €€€€€  €€  €€  €€€€\n"
                + "€€  €€€€  €€  €€€€  €€  €€€€  €€€€  €€  €€€  €€€  €€€€  €€  €€  €€€  €€  €  €€€€€\n"
                + "€€  €€€€  €€  €€€€  €€   €€    €€  €€€      €€€€  €€€€  €€  €€€  €€  €€     €€€€€\n"
                + "€€  €€€€  €€        €€€  €€    €€  €€€  €€€  €€€        €€  €€€€  €  €€  €€  €€€€\n"
                + "€€  €€€€  €€  €€€€  €€€  €€    €€  €€€  €€€€  €€  €€€€  €€  €€€€€    €€  €€€  €€€\n"
                + "€€  €€€€  €€  €€€€  €€€€    €€    €€€€  €€€  €€€  €€€€  €€  €€€€€€   €€  €€€€  €€\n"
                + "€€       €€€  €€€€  €€€€€  €€€€  €€€€€      €€€€  €€€€  €€  €€€€€€€  €€  €€€€  €€\n"
                + "€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€");
        System.out.println("");
    }
    
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
        System.out.println(" 6 - " + operacion6);
        System.out.println(" 7 - " + operacion7);
        System.out.println(" 8 - " + operacion8);
        System.out.println(" 9 - " + operacion9);
        System.out.println(" 111 - " + operacion111);
        System.out.println(" 0 - " + operacion0);
        System.out.println("=================================================================================");
    }
    
    /**
     * Método encargado de inicializar las cuentas.
     * Como es código que se repite he generado un método para ello. Con esto podemos introducir en el main tantas cuentas como
     * queramos solicitar al usuario. Por defecto en el enunciado se piden 2 pero podrian llegar a ser muchas más.
     * @param cuenta
     * @param idCuenta 
     */
    public static void inicializarCuenta(CuentaBancaria cuenta, String idCuenta){
        String titular;
        boolean validTitular = false;
        boolean validCCC = false;
        
        //Bucle para solicitud de titular.
        do{
            System.out.println("# Introduzca el nombre del titular de la Cuenta " + idCuenta + ":");
            titular = kb.nextLine();
            try {
                //Validamos si el titular introducido no supera los caracteres definidos en el método de la clase.
                //Si se valida sale del bucle y almacena los datos y pasa a solicitar el CCC.
                cuenta.validarTitular(titular);
                validTitular = true;
            } catch (Exception ex) {
                //Si no es validado lanza un mensaje informando de que
                //el titular no se ha validado.
                System.out.println(ex.getMessage());
                validTitular = false;
            }
        }while(!validTitular);
        
        //Bucle para solicitud de CCC.
        do{
            System.out.println("# Introduzca el CCC Cuenta " + idCuenta + " (EEEE-OOOO-DD-NNNNNNNNNN):");
            String ccc = kb.nextLine();
            try {
                //Como en el caso anterior se valida el CCC introducido.
                //Si es correcto lo almacena y sale del bucle.
                cuenta.validarCCC(ccc);
                validCCC = true;
            } catch (Exception ex) {
                //Si no es válido se informa al usuario lanzando un error y se le solicita que lo introduzca nuevamente.
                //No sale del bucle hasta que no se introduce correctamente,
                System.out.println(ex.getMessage());
                validCCC = false;
            }
        }while(!validCCC);
    }
}
