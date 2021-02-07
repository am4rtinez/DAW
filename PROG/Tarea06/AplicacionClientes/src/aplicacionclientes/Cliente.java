package aplicacionclientes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author amartinez
 */
public class Cliente implements Serializable{
    private String NIF;     //Se pone en mayusculas debido a que es un acrónimo.
    private String nombre;
    private int telefono;
    private String email;
    private LocalDate nacimiento;
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
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the nacimiento
     */
    public LocalDate getNacimiento() {
        return nacimiento;
    }
    
    //Método encargado de validar el nombre. Si es válido se almacena.
    //En caso contrario se lanza un error.
    public void validarNombre(String nombre) throws Exception{
        if (nombre.length()>100){
            throw new Exception("¡ERROR! - El nombre introducido supera los 100 caracteres permitidos.\n");
        }else if (nombre.length()==0){
            throw new Exception("¡ERROR! - El nombre no puede estar vacio.\n");
        }else{
            this.nombre = nombre;
        }
    }

    public void validarTelefono(String telefono) throws Exception {
        if (!telefono.matches("(^[6-9]{1}[0-9]{8}$)")){
            throw new Exception("¡ERROR! - Teléfono no válido.");
        }
        this.telefono = Integer.parseInt(telefono);
    }

    public void validarEmail(String email) throws Exception {
        if (!email.matches("(^[A-Za-z0-9+_.-]+@(.+)$)")){
            throw new Exception("¡ERROR! - E-mail no válido.");
        }
        this.email = email;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void validarNacimiento(String nacimiento) throws Exception {
        if (!nacimiento.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")){
            throw new Exception("¡ERROR! - Formato no válido.");
        }
        String fecha[] = nacimiento.split("/");
        this.nacimiento = LocalDate.of(Integer.parseInt(fecha[2]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[0]));
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
