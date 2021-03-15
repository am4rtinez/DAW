/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog08notes;

/**
 *
 * @author ESCRIBE AQUÍ TU NOMBRE Y APELLIDOS
 */
public class Alumno {
    private String codi;
    private float[] notes;
    
    //CONSTRUCTOR POR DEFECTO QUE CREA EL ARRAY DE DIMENSIÓN 4
    public Alumno () 
    {
        this.notes = new float[4];
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
    public void setCodi(String codiAlumne) throws Exception 
    {
        if (!validaCodi(codiAlumne)){
            throw new Exception("El código de alumno no tiene formato válido (AAA12).");
        }
        this.codi = codiAlumne;
    }
    
    //SETTER DE UNA NOTA DEL ARRAY DE NOTES, RECIBE EL ÍNDICE DEL ARRAY Y LA NOTA
    //LA NOTA DEBE SER VALIDADA, UN VALOR ENTRE 0 Y 10
    public void setNota(int i, float nota) throws Exception 
    {
        //Como en el main se verifica que se introduzca un entero no hace falta validar
        //aquí. Únicamente revisamos que se encuentre dentro del rango indicado [0..10]
        if (nota<0 || nota>10){
            throw new Exception("Nota incorrecta.");
        }
        notes[i]=nota;
    }
    
    //SETTER DEL ARRAY DE NOTES, RECIBE EL ARRAY COMPLETO
    //CADA UNA DE LAS NOTAS DEBE SER VALIDADA, UN VALOR ENTRE 0 Y 10
    //Atención: el array es un puntero!!!
    // No se implementa ya que no se usa este método.
//    public void setNotes(float[] notesAlumne) throws Exception 
//    {
//
//    }

    //GETTER DEL CÓDIGO
    public String getCodi () 
    {
        return codi;
    }
    
    //GETTER DE UNA NOTA DEL ARRAY, RECIBE EL ÍNDICE POR PARÁMETRO
    public float getNota(int i) throws Exception 
    {
        if (i>notes.length || i<0){
            throw new Exception("El valor introducido en parámetro no esta dentro del array.");
        } else {
            return notes[i];
        }        
    }   
    
    //GETTER DEL ARRAY DE NOTAS
    //Atención: el array es un puntero!!!
    public float[] getNotes () 
    {
        return notes;
    }
    
    //MÉTODO QUE DEVUELVE EL CÁLCULO DE LA MEDIA DE LAS NOTAS DEL ARRAY DE NOTAS
    public float mitjanaNotes () 
    {
        float sumatorio = 0;
        for (int i=0; i<notes.length; i++){
            sumatorio = sumatorio + notes[i];
        }
        return sumatorio/notes.length; //devuelve el cálculo de la media.
    }
}

