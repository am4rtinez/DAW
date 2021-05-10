/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

/**
 *
 * @author amartinez
 */
public class Equip {
    
    private String nombre;
    private int pg;
    private int pe;
    private int pp;
    private int puntos;
    
    public Equip()
    {
        this.pg = 0;
        this.pe = 0;
        this.pp = 0;
        this.puntos = 0;
    }
    
    public void calcularPuntos()
    {
        this.puntos = getPg()*3 + getPe();
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
     * @return the pg
     */
    public int getPg() {
        return pg;
    }

    /**
     * @param pg the pg to set
     */
    public void setPg(int pg) {
        this.pg = pg;
        calcularPuntos();
    }

    /**
     * @return the pe
     */
    public int getPe() {
        return pe;
    }

    /**
     * @param pe the pe to set
     */
    public void setPe(int pe) {
        this.pe = pe;
        calcularPuntos();
    }

    /**
     * @return the pp
     */
    public int getPp() {
        return pp;
    }

    /**
     * @param pp the pp to set
     */
    public void setPp(int pp) {
        this.pp = pp;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }
    
    
}
