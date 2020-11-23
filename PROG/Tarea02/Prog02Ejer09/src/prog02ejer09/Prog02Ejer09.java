package prog02ejer09;

/**
 *
 * @author Ángel Martínez Rodríguez
 */

/**
 *
 * Diseña un programa Java que realice las siguientes operaciones, en el orden
 * que se muestran. Se indica la variable y el tipo de dato que recibe el valor
 * o resultado de la operación indicada:
 *
 * Tipo_Variable    Variable    Valor / Operación
 *
 * float            x               5.6
 * float            y               12.0
 * int              i               3
 * int              j               i * x
 * double           dx              4.0
 * double           dz              dx * y
 * byte             bx              -128
 * byte             by              1
 * int              k               bx – by (resultado tipo byte)
 * int              k               bx – by (resultado tipo int)
 * char             cx              \u0061
 * char             cy              \u0069
 * int              z               cy – cx – 1
 *
 * El resultado del programa debe ser el siguiente:
 *
 * ------- Conversiones entre enteros y coma flotante ------- 
 * Producto de int * por float: j= i*x = 16 
 * Producto de float por double: dz=dx * y = 48.0
 * 
 * ------- Operaciones con byte ------- 
 * Resta de byte con resultado tipo byte: k= bx - by = -128 - 1 = 127 
 * Resta de byte con resultado tipo int: k=(int)(bx - by) = -129
 * 
 * ------- Operaciones con char ------- 
 * cx = a 
 * cy = i 
 * Entre la vocal 'a' y la vocal 'i' hay 7 letras del abecedario
 */

public class Prog02Ejer09 {

    public static void main(String[] args) {
        float x = 5.6f;
        float y = 12.0f;
        int i = 3;
        int j = (int) (i * x);
        double dx = 4.0;
        double dz = dx * y;
        byte bx = -128;
        byte by = 1;
        int k = (byte) (bx - by);
        int k2 = bx - by; //renombro variable a k2.
        char cx = '\u0061';
        char cy = '\u0069';
        System.out.println("------- Conversiones entre enteros y coma flotante -------");
        System.out.println("Producto de int * por float: j = i*x = " + j );
        System.out.println("Producto de float por double: dz = dx * y = " + dz);
        System.out.println("");
        System.out.println("------- Operaciones con byte -------");
        System.out.println("Resta de byte con resultado tipo byte: k = bx - by = -128 - 1 = " + k );
        System.out.println("Resta de byte con resultado tipo int: k = (int)(bx - by) = " + k2);
        System.out.println("");
        System.out.println("------- Operaciones con char -------");
        System.out.println("cx = " + cx );
        System.out.println("cy = " + cy);
        System.out.println("Entre la vocal '" + cx + "' y la vocal '" + cy 
                + "' hay " + (cy-cx) + " letras del abecedario");
    }
}
