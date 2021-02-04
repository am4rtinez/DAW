package aplicacionclientes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author amartinez
 */
public class Cliente {
    private String NIF;     //Se pone en mayusculas debido a que es un acrónimo.
    private String nombre;
    private int telefono;
    private String email;
    private LocalDate nacimiento;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String LETRAS_DNI= "TRWAGMYFPDXBNJZSQVHLCKE";
    
    public Cliente(){
        
    }
    
    /**
     * @return the NIF
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nacimiento
     */
    public LocalDate getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    } 
    
    public void validarNIF(String NIF) throws Exception {
        if (!NIF.matches("(^[0-9]{8}[A-Z]{1}$)")){
            throw new Exception("¡ERROR! - Formato no válido.");
        }
        if (!validarLetraNIF(NIF)){
            throw new Exception("¡ERROR! - Letra incoreccta.");
        }
        this.NIF = NIF;
    }
    
    public boolean validarLetraNIF(String NIF) {
        char letraCad = NIF.charAt(NIF.length()-1);
        int numNIF = Integer.parseInt(NIF.substring(0,8));
        char letraCalc = calcularLetraNIF(numNIF);
        if (letraCad == letraCalc){
            return true;
        }else{
            return false;
        }
    }
    
    private static char calcularLetraNIF (int dni) {
        char letra;
        // Cálculo de la letra NIF
        letra = LETRAS_DNI.charAt(dni % 23);
        // Devolución de la letra NIF
        return letra;
    }
}
