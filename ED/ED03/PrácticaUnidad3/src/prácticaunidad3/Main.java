/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prácticaunidad3;

/**
 *
 * @author Francico Javier Cabrerizo Membrilla
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CCuenta miCuenta;
        double saldoActual;
        
        miCuenta = new CCuenta("Juan López","1000-2365-85-123456789",2500,0);
        try 
        {
            miCuenta.retirar(2300);
        } catch (Exception e)
        {
            System.out.print("Fallo al retirar");
        }
        
        try
        {
            System.out.println("Ingreso en cuenta");
            miCuenta.ingresar(695);
        } catch (Exception e)
        {
            System.out.print("Fallo al ingresar");
        }
        saldoActual = miCuenta.estado();
        System.out.println("El saldo actual es"+ saldoActual );
    }

}
