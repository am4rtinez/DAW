/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numeros;

import java.text.DecimalFormat;

/**
 *
 * @author Ángel Martínez Rodríguez
 */
public class Complejo {

    private double real;
    private double imag;
    
    /**
     * @return the real
     */
    public double getReal() {
        return real;
    }

    /**
     * @param real the real to set
     */
    public void setReal(double real) {
        this.real = real;
    }

    /**
     * @return the imag
     */
    public double getImag() {
        return imag;
    }

    /**
     * @param imag the imag to set
     */
    public void setImag(double imag) {
        this.imag = imag;
    }
    
    //Constructor por defecto
    public Complejo(){
        
    }
    
    /**
     * Constructor que inicializa los atributos a los valores pasados por parámetro.
     * @param real
     * @param imag 
     */ 
    public Complejo(double real, double imag){
        this.real = real;
        this.imag = imag;
    }

    //Creamos otros métodos para la clase.

    /**
     * Método que devuelve el complejo en formato String
     * @return real + imagi (1 + 3i).
     */
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0");
        return (df.format(getReal()) + " + " + df.format(getImag()) + "i");
    }
    
    //Método que suma la parte real y la parte imaginaria de un Complejo b pasado 
    //por parámetro y lo asigna al Complejo.
    public void sumar(Complejo b){
        this.real = real + b.getReal();
        this.imag = imag + b.getImag();
    }
}
