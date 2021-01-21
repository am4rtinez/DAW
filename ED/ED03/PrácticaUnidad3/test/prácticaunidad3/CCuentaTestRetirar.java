package prácticaunidad3;

import junit.framework.TestCase;

/**
 *
 * @author Ángel Martínez Rodríguez
 */
public class CCuentaTestRetirar extends TestCase {
    CCuenta instance = new CCuenta();
    double prueba = 500;
    
    public CCuentaTestRetirar(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        instance.ingresar(prueba);
        System.out.println("Saldo inicial: " + prueba);
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of retirar method, of class CCuenta.
     */
    public void testRetirar() throws Exception {
        System.out.println("Test del método retirar.");
        double resultado;
        double cantidad = 0;
        System.out.println("Cantidad a retirar: " + cantidad);
        instance.retirar(cantidad);
        resultado = prueba - cantidad;
        System.out.println("Estado de la cuenta debe ser: " + resultado);
        assertEquals(resultado, instance.estado());
        System.out.println("Estado real cuenta: " + instance.estado());
        
        cantidad = 200;
        System.out.println("Cantidad a retirar: " + cantidad);
        resultado = resultado - 200;
        System.out.println("Estado de la cuenta debe ser: " + resultado);
        instance.retirar(cantidad);
        assertEquals(resultado, instance.estado());
        System.out.println("Estado real cuenta: " + instance.estado());
        
        cantidad = 1000;
        System.out.println("Cantidad a retirar: " + cantidad);
        System.out.println("Esta operación no debe ser posible.");
        try {
            instance.retirar(cantidad);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }   
}
