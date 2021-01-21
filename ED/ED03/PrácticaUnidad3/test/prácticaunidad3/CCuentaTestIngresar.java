package prácticaunidad3;

import junit.framework.TestCase;

/**
 *
 * @author Ángel Martínez Rodríguez
 */
public class CCuentaTestIngresar extends TestCase {
    CCuenta instance = new CCuenta();
    double prueba = 200;
    
    public CCuentaTestIngresar(String testName) {
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
     * Test of ingresar method, of class CCuenta.
     */
    public void testIngresar() throws Exception {
        System.out.println("Test del método ingresar.");
        double cantidad = 1000;
        System.out.println("Cantidad a ingresar: " + cantidad);
        instance.ingresar(cantidad);
        double resultado = prueba + cantidad;
        System.out.println("Estado de la cuenta debe ser: " + resultado);
        assertEquals(resultado, instance.estado());
        System.out.println("Estado real cuenta: " + instance.estado());
    }
}
