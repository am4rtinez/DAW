package aplicacionclientes;

import aplicacionclientes.exceptions.DeleteFileException;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author amartinez
 */
public class FileUtils {

    public FileUtils (){

    }

    /**
     * Método que se encarga de borrar el fichero con la ruta indicada en el parametro src.
     * @param src String - Ruta del fichero.
     * @throws DeleteFileException 
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
        return directorio.exists();
    }

    /**
     * Función que comprueba si existe el fichero.
     *
     * @param src String - Parametro de entrada por el cual le pasamos la ruta del fichero.
     * @return 
     */
    public boolean comprobarFichero (String src)
    {
        File fichero = new File(src);
        return fichero.exists();
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
     * Método que se encarga de crear un fichero en la ruta 'dst' especificada.
     * @param dst String - Parametro de entrada que contiene la ruta de destino de creación del fichero.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void crearFichero (String dst) throws FileNotFoundException, IOException
    {
        FileOutputStream fichero = new FileOutputStream(dst);
        fichero.close();
    }
    
    /**
     * Método que escribe el fichero clientes.dat.
     * Escribe objetos en el fichero.
     * @param dst
     * @param clientes 
     */
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
    
    /**
     * Método que escribe el fichero de texto.
     * Cada vez que se le invoca sobreescribe el contenido del fichero.
     * @param dst
     * @param clientes
     * @param dtf 
     */
    public void escribirFicheroTexto (String dst, ArrayList <Cliente> clientes, DateTimeFormatter dtf){
        try {
            BufferedWriter bw;
            //"ISO-8859-1"
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst, true), "ISO-8859-1"));
            for (int i=0; i<clientes.size(); i++){
                bw.write(clientes.get(i).getNombre() + " " 
                        + clientes.get(i).getNacimiento().format(dtf) + " "
                        + clientes.get(i).getTelefono() + " "
                        + clientes.get(i).getEmail() + "\r\n");
            }
            bw.close();
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Método que lee los objetos del fichero.
     * Retorna un ArrayList de clientes.
     * @param dst
     * @return 
     */
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
