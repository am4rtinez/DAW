package aplicacioncuentabancaria;

/**
 * @author Ángel Martínez Rodríguez
 * Formato del CCC (formado por 20 dígitos):
 * EEEE OOOO DD NNNNNNNNNN
 * Los primeros cuatro dígitos son el Código de la Entidad, que coincide con 
 * el Número de Registro de Entidades del Banco de España (NRBE).
 * Los siguientes cuatro dígitos identifican la oficina.
 * Los siguientes dos dígitos son los llamados dígitos de control, 
 * que sirven para validar el CCC.
 * Los últimos diez dígitos identifican unívocamente la cuenta.
 */

public class CuentaBancaria {

    private String ccc;
    private String entidad;
    private String oficina;
    private String dc;
    private String numcuenta;
    private String titular;     //Almacenamiento del nombre del titular.
    private double saldo;       //Almacenamiento del saldo actual de la cuenta.
    
    //Constructor por defecto.
    public CuentaBancaria (){
        
    }
    
    //Método encargado de validar el titular. Si es válido se almacena.
    //En caso contrario se lanza un error.
    public void validarTitular(String titular) throws Exception{
        if (titular.length()>100){
            throw new Exception("¡ERROR! - El titular introducido supera los 100 caracteres permitidos.\n");
        } else {
            this.titular = titular;
        }
    }
    
    /**
     * Método que se encarga de validar el CCC introducido.
     * Comprueba que el formato del CCC introducido sea EEEE-OOOO-DD-NNNNNNNNNN y
     * valida los dígitos de control.
     * @param ccc - Código Cuenta Cliente.
     * @throws Exception 
     */
    public void validarCCC(String ccc) throws Exception{
        if (!ccc.matches("(^[0-9]{4}-[0-9]{4}-[0-9]{2}-[0-9]{10}$)")){
            throw new Exception("¡ERROR! - Formato no válido.\n");
        }
        
        if (!validarDigsControl(ccc)){
            throw new Exception("¡ERROR! - Digitos de control incorecctos.\n");
        }
        
        //Llegar a esta punto indica que ha pasado la validación. Por lo que nos
        //disponemos a almacenar los datos.
        almacenarDatos(ccc);
        
    }
    
    static boolean validarDigsControl(String ccc){
        
        //Dividimos el string en Entidad, Oficina, Digitos control y Numero cuenta para su tratamiento.
        String[] split = ccc.split("-");                
        int dc_eo;                              //Digito de control Entidad oficina.
        int dc_nc;                              //Digito de control Número cuenta.
        
        //Calcula el dígito de control para Entidad Oficina. Al no tener los 10 digitos se añade 00 al inicio.
        dc_eo = calculaDigito("00" + split[0] + split[1]);
        //Calcula el dígito de control para el número de cuenta.
        dc_nc = calculaDigito(split[3]);
        
        String dc = split[2];
        String dc_result = String.valueOf(dc_eo) + String.valueOf(dc_nc);
        
        //Compara si los digitos de control introducidos (dc) con los calculados (dc_result).
        if ((dc_result).equals(dc)){
            return true;
        } else {
            return false;
        }        
    }
    
    /**
     * Método encargado de calcular lor dígitos de control.
     * @param valor - Parámetro de 10 dígitos del cual se realizará el cálculo.
     * @return int - Devuelve el digito de control calculado.
     */
    static int calculaDigito(String valor){
        int[] factores = {1,2,4,8,5,10,9,7,3,6};    //Factores ya dados en Wikipedia.
        int chequeo = 0;                            // Variable para acumular las operaciones.
        //Se realiza el cálculo para los 10 dígitos.
        //Cada uno de los dígitos que componen el código se multiplica por un factor asociado a su posición en el código.
        //Se realiza la suma de los 10 productos obtenidos.
        for (int i=0; i < 10; i++){
            chequeo = chequeo + (Integer.parseInt(valor.substring(i,i+1)) * factores[i]);
        }
        //El resultado de esta suma se divide por 11 y se almacena el resto que produce la división.
        //Este resto se resta de 11 para obtener el dígito de control correspondiente.
        chequeo = 11 - (chequeo % 11);
        //Puesto que estamos interesados en obtener solo una cifra, 
        //si la cantidad resultante fuese 10, se tomará en su lugar el dígito 1; 
        //y si fuese 11, el 0.
        if (chequeo == 11) chequeo = 0;
        if (chequeo == 10) chequeo = 1;
        //Devolvemos el resultado (chequeo).
        return chequeo;
    }
    
    void almacenarDatos(String ccc){
        //Dividimos el string en Entidad, Oficina, Digitos control y Numero cuenta para su tratamiento.
        String[] split = ccc.split("-");  
        
        this.ccc = ccc;
        this.entidad = split[0];
        this.oficina = split[1];
        this.dc = split[2];
        this.numcuenta = split[3];
    }
    
    
    
    
    
    /**
     * @return the ccc
     */
    public String getCcc() {
        return ccc;
    }


    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    /**
     * @return the oficina
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * @param oficina the oficina to set
     */
    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    /**
     * @return the dc
     */
    public String getDc() {
        return dc;
    }

    /**
     * @param dc the dc to set
     */
    public void setDc(String dc) {
        this.dc = dc;
    }

    /**
     * @return the numcuenta
     */
    public String getNumcuenta() {
        return numcuenta;
    }

    /**
     * @param numcuenta the numcuenta to set
     */
    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }

    /**
     * @return the titular
     */
    public String getTitular() {
        return titular;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }    
    
}
