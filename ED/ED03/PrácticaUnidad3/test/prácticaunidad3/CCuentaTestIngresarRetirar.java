package prácticaunidad3;

import junit.framework.TestCase;

/**
 *
 * @author Ángel Martínez Rodríguez
 */
public class CCuentaTestIngresarRetirar extends TestCase {
    CCuenta instance = new CCuenta("Juan López", "1000-2365-85-123456789", 2500, 0); //Aprovechamos la inicializacion de la cuenta del main.
    
    public CCuentaTestIngresarRetirar(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {

    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of ingresar method, of class CCuenta.
     */
    public void testIngresarRetirar() throws Exception {
        System.out.println("Test del método ingresar/retirar.");
        double cantidad = 1000;
        double resultado = instance.estado() + cantidad;
        System.out.println("Cantidad a ingresar: " + cantidad);
        instance.ingresar(cantidad);
        System.out.println("Estado de la cuenta debe ser: " + resultado);
        assertEquals(resultado, instance.estado());
        System.out.println("Estado real cuenta: " + instance.estado());
        cantidad = 1000;
        System.out.println("Cantidad a retirar: " + cantidad);
        resultado = instance.estado() - cantidad;
        try {
            instance.retirar(cantidad);
            assertEquals(resultado, instance.estado());
            System.out.println("Estado real cuenta: " + instance.estado());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
