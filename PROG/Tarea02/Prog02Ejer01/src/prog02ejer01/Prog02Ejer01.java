package prog02ejer01;

/**
 *
 * @author Ángel Martínez Rodríguez
 */

/**
 *
 * Complétalo siguiendo la sintaxis recomendada para nombrar y declarar las
 * variables o constantes propuestas según enunciado y a las que asignarás un
 * valor por defecto según apartado b); además, el tipo de dato elegido debe ser
 * el de menos bits posibles que puedan representar el valor. Justifica tu
 * elección mediante comentarios en el programa.
 *
 * a) Modifícalo para utilizar las variables que se indican, decláralas en el
 * main: Nombre de un empleado. - STRING Si un empleado está casado o no. -
 * BOOLEAN Sexo, con dos valores posibles 'V' o 'M'. - ENUM Valor máximo
 * constante no modificable: 3600. Día de la semana. Día del año. Milisegundos
 * transcurridos desde el 01/01/1970 hasta nuestros días. El total de una
 * factura. - Población mundial del planeta tierra. - LONG Mensaje de despedida.
 *
 * b) Además realiza las modificaciones siguientes en el programa, para mostrar
 * el siguiente resultado respetando tabulaciones y líneas en blanco; debes usar
 * obligatoriamente la variable o constante declarada anteriormente:
        ----- EJERCICIO DE VARIABLES Y TIPOS DE DATOS -----
        Nombre empleado: Joan Pons Serra 
        casado? : true 
        sexo :  V 
        El valor máximo es 3600
        Día de la semana: 5 --- Día del año: 300
        Tiempo en milisegundos es 1576800000000
        El total de la factura en notación científica es 1.035068E+04 - 
        Población mundial: 6775235741 

                                      THE END! 
 */
public class Prog02Ejer01 {
    static enum Sexo {
        V,
        M
    }
    public static void main(String[] args) {
        
        String employe = "Joan Pons Serra"; //Nombre de empleado (suponiendo Nombre y apellidos).
        boolean married = true; //Boolean ya que solo puede tener valor sí o no.
        Sexo gen = Sexo.V; //tipo ENUM para que solo tenga valor V o M.
        //final para hacerla constante y tipo short ya que almacena numeros 
        //del rango [-32,768 .. 32,767]
        final short MAX = 3600; 
        byte day_week = 5; //Almacena numeros del rango [-128 .. 127].
        short day_year = 300; //almacena numeros del rango [-32,768 .. 32,767]
        long milis = 1576800000000L; //almacena numeros del rango [-9,223,372,036,854,775,808 .. 9,223,372,036,854,775,807]
        double factura = 1.035068E+04; //almacena numeros de 15 digitos decimales.
        long population = 6775235741L; //Supera el rango de los integers [-2147483648 .. 2147483647]
        String despedida = "THE END!"; //tipo string para almacenar la cadena de texto
        
        // TODO code application logic here
        System.out.println("----- EJERCICIO DE VARIABLES Y TIPOS DE DATOS -----");
        System.out.println("Nombre empleado: " + employe);
        System.out.println("casado? : " + married);
        System.out.println("sexo : " + gen);
        System.out.println("El valor máximo es " + MAX);
        System.out.println("Día de la semana: " + day_week + " --- " + "Día del año : " + day_year);
        System.out.println("Tiempo en milisegundos es " + milis);
        System.out.println("El total de la factura en notación científica es " + factura);
        System.out.println("Población mundial: " + population);
        System.out.println("");
        System.out.println("\t" + "\t" + "\t" + "\t" + despedida); 
    }
}
