package aplicacionclientes;

import aplicacionclientes.exceptions.DeleteFileException;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author amartinez
 */
public class FileUtils {

    public FileUtils (){

    }

    /**
     * Método que se encarga de borrar el fos con la ruta indicada en el
 parametro src.
     *
     * @param src String - Ruta del fos.
     */
    public void borrarFichero (String src) throws DeleteFileException
    {
        File fichero = new File(src);
        if (comprobarFichero(src)) {
            if (fichero.delete()) {
                System.out.println("Deleted the file: " + fichero.getName());
            } else {
                throw new DeleteFileException("ERROR! El fichero no ha podido ser eliminado.");
            }
        }else{
            throw new DeleteFileException("ERROR! El fichero no existe.");
        }
        
    }

    /**
     * Función que comprueba si existe el directorio.
     *
     * @param dir String - Parametro de entrada por el cual le pasamos la ruta del directorio.
     * @return 
     */
    public boolean comprobarDirectorio (String dir)
    {
        File directorio = new File(dir);
        if (!directorio.exists()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Función que comprueba si existe el fos.
     *
     * @param src String - Parametro de entrada por el cual le pasamos la ruta del fos.
     */
    public boolean comprobarFichero (String src)
    {
        File directorio = new File(src);
        if (!directorio.exists()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Método que crea un directorio en la ruta que le indicamos por el parametro dst.
     *
     * @param dst String - Parametro de entrada por el cual le pasamos la ruta del directorio.
     */
    public void crearDirectorio (String dst){
        File directorio = new File(dst);
        directorio.mkdir();
    }

    /**
     * Método que se encarga de crear un fos en la ruta 'dst' especificada.
     * 
     * @param dst String - Parametro de entrada que contiene la ruta de destino de creación del fos.
     */
    public void crearFichero (String dst) throws FileNotFoundException, IOException
    {
        FileOutputStream fichero = new FileOutputStream(dst);
        fichero.close();
    }
    
    public void escribirFichero (String dst, ArrayList <Cliente> clientes) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(dst);
            oos = new ObjectOutputStream(fos);
            for(int i=0; i<clientes.size(); i++){
                oos.writeObject(clientes.get(i));
            }
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public ArrayList <Cliente> obtenerClientesFichero(String dst){
        ArrayList <Cliente> clientes = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {            
            fis = new FileInputStream(dst);
            ois = new ObjectInputStream(fis);
            while (fis.available()>0) { //Sale del bucle si alcanzamos final de fichero. Solucionado EOFException.
                Cliente c = (Cliente) ois.readObject();
                if (c != null) {
                    clientes.add(c);
                }
            }
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return clientes;
    }
}
