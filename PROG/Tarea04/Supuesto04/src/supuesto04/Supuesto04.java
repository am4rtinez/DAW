package supuesto04;

/**
 *
 * @author Ángel Martínez Rodríguez
 * 
 * Escribe un programa que escriba los 20 primeros números de la sucesión 
 * de Fibonacci.
 * La sucesión de Fibonachi queda definida de la siguiente manera:
 * f0 = 0;
 * f1 = 1;
 * fn = fn-1 + fn-2;
 */
public class Supuesto04 {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Los 20 primeros números de la sucesión de Fibonacci:");
        for (int i = 0; i<20; i++){
            if (i==19){
                System.out.print(fibonacci(i) + "\n");
            } else {
                System.out.print(fibonacci(i) + ", ");
            }
            
        }
    }
    
    public static int fibonacci(int n) {
        switch (n) {
            case 0:
                //Retorna el valor de f0.
                return 0;
            case 1:
                //retorna el valor de f1.
                return 1;
            default:
                // En este paso n es mayor a 1 por lo que calculamos fn = fn-1 + fn-2.
                // Por ello se recurre a llamar a la propia función para obtener
                // en el primer caso los resultados de f0 = 0 y f1 = 1 y en el 
                // resto de casos las fn-1 y fn-2 correspondientes.
                return fibonacci(n-1) + fibonacci(n-2); 
        }
    }
}
