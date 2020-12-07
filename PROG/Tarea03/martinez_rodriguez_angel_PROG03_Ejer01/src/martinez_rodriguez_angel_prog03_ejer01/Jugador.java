package martinez_rodriguez_angel_prog03_ejer01;
/**
 * @author Ángel Martínez Rodríguez
 */
public class Jugador {
    private String nombre;
    private int edad;
    private byte nivel;
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the nivel
     */
    public byte getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(byte nivel) {
        this.nivel = nivel;
    }
    
    //Constructor por defecto.
    public Jugador(){
    }
    
    /**
     * Constructor en el cual se pasan los datos por variable.
     * @param nombre el nombre al que hacer set.
     * @param edad edad para hacer el set.
     * @param nivel nivel con el que hacer el set.
     */
    public Jugador(String nombre, int edad, byte nivel){
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setNivel(nivel);
    }    
}