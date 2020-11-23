package prog02ejer05;

/**
 *
 * @author Ángel Martínez Rodríguez
 */

/*
  Diseña un programa Java que cree un tipo enumerado para los meses del año.  
  El programa debe realizar las siguientes operaciones:
  - Crear una variable m del tipo enumerado y asignarle el mes de noviembre. 
    Mostrar por pantalla su valor.
  - Asignar a la variable m, la cadena de texto "NOVIEMBRE". Mostrar por pantalla 
    el valor de la variable de tipo enumerado tras realizar la asignación.
*/

public class Prog02Ejer05 {
    
    // Declaración del tipo enumerado.
    static enum Meses {
        ENERO,
        FEBRERO, 
        MARZO,
        ABRIL,
        MAYO,
        JUNIO,
        JULIO,
        AGOSTO,
        SEPTIEMBRE,
        OCTUBRE,
        NOVIEMBRE,
        DICIEMBRE
    }
    
    public static void main(String[] args) {
        Meses m = Meses.NOVIEMBRE; //Asignar el mes de Noviembre.
        System.out.println(m);
        m = Meses.valueOf("NOVIEMBRE"); //Asignamos la cadena de texto NOVIEMBRE.
        System.out.println(m);
    }   
}
