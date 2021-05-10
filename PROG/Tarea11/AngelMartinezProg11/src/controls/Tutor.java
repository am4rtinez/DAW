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
public class Tutor {
    private int codiTutor;      //Será la clave primaria.
    private String nomTutor;    //VARCHAR(40). Tiene que tener mínimo 10 caracteres.
    
    public Tutor(){
        
    }
    
    public Tutor(int codiTutor, String nomTutor){
        this.codiTutor = codiTutor;
        this.nomTutor = nomTutor;
    }

    /**
     * @return the codiTutor
     */
    public int getCodiTutor() {
        return codiTutor;
    }

    /**
     * @param codiTutor the codiTutor to set
     */
    public void setCodiTutor(int codiTutor) {
        this.codiTutor = codiTutor;
    }

    /**
     * @return the nomTutor
     */
    public String getNomTutor() {
        return nomTutor;
    }

    /**
     * @param nomTutor the nomTutor to set
     */
    public void setNomTutor(String nomTutor) throws Exception {
        if (nomTutor.length()>40){
            throw new Exception("El nombre del tutor supera los 40 caracteres permitidos.");
        }
        this.nomTutor = nomTutor;
    }
}
