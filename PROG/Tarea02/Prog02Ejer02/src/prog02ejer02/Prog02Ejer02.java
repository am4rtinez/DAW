package prog02ejer02;

/**
 *
 * @author Ángel Martínez Rodríguez
 */

/*
  Corrige los errores del siguiente código de 18 líneas correspondiente al fichero Prog02Ejer02.java:
    1. /
    2. operadoresaritmeticos.java
    3. Programa que muestra el uso de los operadores aritméticos
    4. /
    5. public class operadoresaritmeticos {
    6. public static main(String[] args)
    7. short x = 7;
    8. int y = 5;
    9. float f1 = 13.5;
    10. float f2 = 8f;
    11. System.out.println("El valor de x es ", x, " y el valor de y es ", y);
    12. System.out.println("El resultado de x + y es " + (x + y));
    13. System.out.println("El resultado de x - y es " + (x - y));
    14. System.out.printf("\n%s%s\n","División entera:","x / y = ",(x/y));
    15. System.out.println("Resto de la división entera: x % y = " + (x % y));
    16. System.out.printf("El valor de f1 es %f y el de f2 es %f\n",f1,f2);
    17. System.out.println("El resultado de f1 / f2 es " + (f1 / f2))
    18. }
*/

/*
operadoresaritmeticos.java
Programa que muestra el uso de los operadores aritméticos
*/
// Añadir los caracteres * para que fuese un comentario válido.

public class Prog02Ejer02 { //Corrección del nombre de la clase. operadoresaritmeticos --> Prog02Ejer02.
    public static void main(String[] args){ //Añadir void y los {}.
        short x = 7;
        int y = 5;
        float f1 = (float) 13.5; //Hacer un cast de float.
        float f2 = 8f;
        System.out.println("El valor de x es " + x + " y el valor de y es " + y); //cambiar , por +
        System.out.println("El resultado de x + y es " + (x + y));
        System.out.println("El resultado de x - y es " + (x - y));
        System.out.printf("\n%s%s\n","División entera:","x / y = ",(x/y));
        System.out.println("Resto de la división entera: x % y = " + (x % y));
        System.out.printf("El valor de f1 es %f y el de f2 es %f\n",f1,f2);
        System.out.println("El resultado de f1 / f2 es " + (f1 / f2));
    }
}
