/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dam_ed04_actividad;

/**
 * 
 * @author amartinez
 */
public class Main {

    /**
     * Metodo main.
     * @param args 
     */
    public static void main(String[] args) {
        CCuenta cuenta1;
        double saldoActual;

        cuenta1 = new CCuenta("Antonio López","1000-2365-85-1230456789",2500,0);
        operativa_cuenta(cuenta1);
    }

    /**
     * Metodo que realiza la operativa de las cuentas.
     * @param cuenta1 
     */
    private static void operativa_cuenta(CCuenta cuenta1) {
        float cantidad = 0;
        consultaSaldo(cuenta1);
        try {
            cantidad = 2300;
            System.out.println("Retirada de :" + cantidad);
            cuenta1.retirar(cantidad);
            consultaSaldo(cuenta1);
            
        } catch (Exception e) {
            System.out.println("Fallo al retirar");
        }
        try {
            cantidad = 695;
            System.out.println("Ingreso en cuenta de " + cantidad);
            cuenta1.ingresar(cantidad);
            consultaSaldo(cuenta1);
        } catch (Exception e) {
            System.out.println("Fallo al ingresar");
        }
    }
    
    /**
     * Metodo para consultar el saldo y lo imprime en pantalla.
     * @param cuenta1 
     */
    private static void consultaSaldo(CCuenta cuenta1) {
        double saldoActual;
        saldoActual = cuenta1.estado();
        System.out.println("El saldo actual es "+ saldoActual );
    }
    
}
