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
public class Alumno {
    private String codiAlumne;   //formato de 3 letras mayúsculas y 2 números (AVF23, DSB21, …)
    private String nomAlumne;    //VARCHAR(40) NOT NULL. Tiene que tener mínimo 10 caracteres.
    private int codiTutorAlumne;         //INT NOT NULL. Será la clave foránea del codiTutor de la tabla Tutor.
    
    public Alumno (){
        
    }
    
    public Alumno (String codiAlumne, String nomAlumne, int codiTutorAlumne){
        this.codiAlumne = codiAlumne;
        this.nomAlumne = nomAlumne;
        this.codiTutorAlumne = codiTutorAlumne;
    }
    
    //MÉTODO ESTÁTICO DE CLASE USADO PARA VALIDAR UN CÓDIGO, SE LLAMA 
    //DIRECTAMENTE DESDE CLASE
    public static boolean validaCodi(String codiAlumne) 
    {
        if (!codiAlumne.matches("(^[A-Z]{3}[0-9]{2}$)")){
            return false;
        }else{
            return true;
        }
    }    
    
    //MÉTODO SETTER DEL CÓDIGO
    //ANTES DE ESTABLECER UN CÓDIGO SIEMPRE VALIDAREMOS EL PATRÓN 
    //LLAMANDO AL MÉTODO DE CLASE validaCodi()
    public void setCodiAlumne(String codiAlumne) throws Exception 
    {
        if (!validaCodi(codiAlumne)){
            throw new Exception("El código de alumno no tiene formato válido (AAA12).");
        }
        this.codiAlumne = codiAlumne;
    }

    //GETTER DEL CÓDIGO
    public String getCodiAlumne () 
    {
        return codiAlumne;
    }

    /**
     * @return the nomAlumne
     */
    public String getNomAlumne() {
        return nomAlumne;
    }

    /**
     * @param nomAlumne the nomAlumne to set
     */
    public void setNomAlumne(String nomAlumne) throws Exception {
        if (nomAlumne.length()>40){
            throw new Exception("El nombre del alumno supera los 40 caracteres permitidos.");
        }
        this.nomAlumne = nomAlumne;
    }

    /**
     * @return the codiTutorAlumne
     */
    public int getCodiTutorAlumne() {
        return codiTutorAlumne;
    }

    /**
     * @param codiTutorAlumne the codiTutorAlumne to set
     */
    public void setCodiTutorAlumne(int codiTutorAlumne) {
        this.codiTutorAlumne = codiTutorAlumne;
    }
}

