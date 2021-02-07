package aplicacionclientes;

import java.io.Serializable;
import java.time.LocalDate;

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

    /**
     * Método que se encarga de validar el teléfono y lo almacena.
     * @param telefono
     * @throws Exception 
     */
    public void validarTelefono(String telefono) throws Exception {
        //En España los teleéfonos comprenden su primera cifra entre el 6 y el 9.
        //6 y 7 para telefonía móvil. 8 y 9 para tlefonía fija.
        if (!telefono.matches("(^[6-9]{1}[0-9]{8}$)")){
            throw new Exception("¡ERROR! - Teléfono no válido.");
        }
        this.telefono = Integer.parseInt(telefono);
    }

    /**
     * Método que valida un email.
     * @param email
     * @throws Exception 
     */
    public void validarEmail(String email) throws Exception {
        if (!email.matches("(^[A-Za-z0-9+_.-]+@(.+)$)")){
            throw new Exception("¡ERROR! - E-mail no válido.");
        }
        this.email = email;
    }

    /**
     * Método que valida una fecha de nacimiento y la almacena.
     * En este caso se debe introducir con formato dd/mm/yyyy y después se almacena con el formato correspondiente a LocalDate.
     * @param nacimiento the nacimiento to set
     */
    public void validarNacimiento(String nacimiento) throws Exception {
        if (!nacimiento.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")){
            throw new Exception("¡ERROR! - Formato no válido.");
        }
        String fecha[] = nacimiento.split("/");
        this.nacimiento = LocalDate.of(Integer.parseInt(fecha[2]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[0]));
    } 
    
    /**
     * Método encargado de validar el NIF y lo almacena.
     * @param NIF
     * @throws Exception 
     */
    public void validarNIF(String NIF) throws Exception {
        if (!NIF.matches("(^[0-9]{8}[A-Z]{1}$)")){
            throw new Exception("¡ERROR! - Formato no válido.");
        }
        if (!validarLetraNIF(NIF)){
            throw new Exception("¡ERROR! - Letra incoreccta.");
        }
        this.NIF = NIF;
    }
    
    /**
     * Metodo que valida la letra del NIF.
     * @param NIF
     * @return 
     */
    private boolean validarLetraNIF(String NIF) {
        char letraCad = NIF.charAt(NIF.length()-1);
        int numNIF = Integer.parseInt(NIF.substring(0,8));
        char letraCalc = calcularLetraNIF(numNIF);
        if (letraCad == letraCalc){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Método que calcula la letra del NIF.
     * @param dni
     * @return 
     */
    private static char calcularLetraNIF (int dni) {
        char letra;
        // Cálculo de la letra NIF
        letra = LETRAS_DNI.charAt(dni % 23);
        // Devolución de la letra NIF
        return letra;
    }
}
