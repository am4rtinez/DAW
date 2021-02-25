/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dam_ed04_actividad;

/**
 * 
 * @author amartinez
 */
public class CCuenta {

    private String nombre;
    private String cuenta;
    private double saldo;
    private double tipoInteres;

    /**
     * Constructor por defecto de la clase.
     */    
    public CCuenta()
    {
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
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

    /**
     * Constructor de la clase CCuenta inicializandolo con parametros.
     * @param nom
     * @param cue
     * @param sal
     * @param tipo 
     */
    public CCuenta(String nom, String cue, double sal, double tipo)
    {
        nombre =nom;
        cuenta=cue;
        saldo=sal;
    }

    /**
     * 
     * @param nom 
     */
    public void asignarNombre(String nom)
    {
        setNombre(nom);
    }

    /**
     * Metodo para obtener el nombre.
     * @return 
     */
    public String obtenerNombre()
    {
        return getNombre();
    }

    /**
     * Metodo para consultar el saldo.
     * @return 
     */
    public double estado()
    {
        return getSaldo();
    }

    /**
     * Metodo para ingresar
     * @param cantidad
     * @throws Exception 
     */
    public void ingresar(double cantidad) throws Exception
    {
        if (cantidad<0)
            throw new Exception("No se puede ingresar una cantidad negativa");
        setSaldo(getSaldo() + cantidad);
    }

    /**
     * Metodo para retirar
     * @param cantidad
     * @throws Exception 
     */
    public void retirar(double cantidad) throws Exception
    {
        if (cantidad <= 0)
            throw new Exception ("No se puede retirar una cantidad negativa");
        if (estado()< cantidad)
            throw new Exception ("No se hay suficiente saldo");
        setSaldo(getSaldo() - cantidad);
    }
    
    /**
     * Obtiene la cuenta.
     * @return 
     */
    public String obtenerCuenta()
    {
        return getCuenta();
    }

    /**
     * Get del nombre
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set del nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get del tipo de Interes.
     * @return 
     */
    public double getTipoInteres() {
        return tipoInteres;
    }

    /**
     * HSet del tipo de Interes.
     * @param tipoInteres 
     */
    public void setTipoInteres(double tipoInteres) {
        this.tipoInteres = tipoInteres;
    }
}
