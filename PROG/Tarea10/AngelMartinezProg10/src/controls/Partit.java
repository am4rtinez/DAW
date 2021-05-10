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
public class Partit {
    private String local;
    private String visitant;
    private int golsLocal;
    private int golsVisitant;
    private String guanyador;
    
    //Constructor básico.
    public Partit ()
    {
        
    }
    
    //Constructor para declarar un partido. No se añade el guanyador.
    public Partit (String local, String visitant, int golsLocal, int golsVisitant)
    {
        this.local = local;
        this.visitant = visitant;
        this.golsLocal = golsLocal;
        this.golsVisitant = golsVisitant;
    }
    
    //Constructor para declarar un partido desde la BD obteniendo el resultado del ganador o empate.
    public Partit (String local, String visitant, int golsLocal, int golsVisitant, String guanyador)
    {
        this.local = local;
        this.visitant = visitant;
        this.golsLocal = golsLocal;
        this.golsVisitant = golsVisitant;
        this.guanyador = guanyador;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) throws Exception {
        if (local.length()>20){
            throw new Exception("El nombre del equipo local supera los 20 caracteres permitidos.");
        }
        this.local = local;
    }

    /**
     * @return the visitant
     */
    public String getVisitant() {
        return visitant;
    }

    /**
     * @param visitant the visitant to set
     */
    public void setVisitant(String visitant) throws Exception {
        if (visitant.length()>20){
            throw new Exception("El nombre del equipo visitante supera los 20 caracteres permitidos.");
        }
        this.visitant = visitant;
    }

    /**
     * @return the golsLocal
     */
    public int getGolsLocal() {
        return golsLocal;
    }

    /**
     * @param golsLocal the golsLocal to set
     */
    public void setGolsLocal(int golsLocal) {
        this.golsLocal = golsLocal;
    }

    /**
     * @return the golsVisitant
     */
    public int getGolsVisitant() {
        return golsVisitant;
    }

    /**
     * @param golsVisitant the golsVisitant to set
     */
    public void setGolsVisitant(int golsVisitant) {
        this.golsVisitant = golsVisitant;
    }

    /**
     * @return the guanyador
     */
    public String getGuanyador() {
        return guanyador;
    }

    /**
     * @param guanyador the guanyador to set
     */
    public void setGuanyador(String guanyador) {
        this.guanyador = guanyador;
    }
    
}
